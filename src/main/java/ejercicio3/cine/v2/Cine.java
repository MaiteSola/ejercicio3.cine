package ejercicio3.cine.v2;

import java.util.ArrayList;

public class Cine {

	public static void main(String[] args) throws InterruptedException {

		// 1 Creo la SalaProyeccion(La peli)
		SalaProyeccion sala = new SalaProyeccion();

		// 2 Creo las colas
		ArrayList<ColaEntradas> listaColas = generarColas();

		// 3 lanzo generador de clientes
		Thread generador = new Thread(new GeneradorDeClientes(listaColas));
		generador.start();

		// 4 Abro las taquillas y reciben toda la lista, luego deciden qué cola utilizar
		long inicioVenta = System.currentTimeMillis();
		Taquilla taquilla1 = new Taquilla(sala, listaColas, "Taquilla1");
		Taquilla taquilla2 = new Taquilla(sala, listaColas, "Taquilla2");

		Thread h1 = new Thread(taquilla1);
		Thread h2 = new Thread(taquilla2);

		h1.start();
		h2.start();

		// Esperar a que las taquillas terminen su venta
		h1.join();
		h2.join();
		long finVenta = System.currentTimeMillis();

		// Clientes sin entrada en filas
		int clientesAtrapadosEnFilas = 0;
		for (ColaEntradas c : listaColas) {
			clientesAtrapadosEnFilas += c.clientesRestantes();
		}

		// 2. Clientes sin llegar a ser atendidos totales
		int clientesSinEntradaTotales = sala.getClientesSinEntrada() + clientesAtrapadosEnFilas;

		System.out.println("\n====================================");
		System.out.println("            DATOS DE VENTA           ");
		System.out.println("======================================");
		System.out.println("Ventas exitosas: " + sala.getClientesAtendidosConExito() + " entradas.");
		System.out.println("Clientes sin entrada (TOTAL): " + clientesSinEntradaTotales);
		System.out.println("   -> Por sala llena: " + sala.getClientesSinEntrada());
		System.out.println("   -> Por cierre de taquilla (en fila): " + clientesAtrapadosEnFilas);
		System.out.println("Tiempo total: " + (finVenta - inicioVenta) / 1000.0 + " segundos.");
		System.out.println("========================================");

	}

	public static ArrayList<ColaEntradas> generarColas() {
		ColaEntradas cola;
		ArrayList<ColaEntradas> listaDeColas = new ArrayList<ColaEntradas>();

		for (int i = 0; i < Configuracion.NUM_COLAS; i++) {
			cola = new ColaEntradas("Cola" + i, true);
			listaDeColas.add(cola);

		}

		return listaDeColas;
	}

}
