package ejercicio3.cine;

public class Cine {

	public static void main(String[] args) throws InterruptedException {

		// 1 Creo la SalaProyeccion(La peli)
		SalaProyeccion sala = new SalaProyeccion();

		// 2 Creo la cola
		ColaEntradas cola = new ColaEntradas();

		// 3 lanzo generador de clientes
		Thread generador = new Thread(new GeneradorDeClientes(cola));
		generador.start();

		// 4 Abro las taquillas
		long inicioVenta = System.currentTimeMillis();
		Taquilla taquilla1 = new Taquilla(sala, cola, "Taquilla1");
		Taquilla taquilla2 = new Taquilla(sala, cola, "Taquilla2");

		Thread h1 = new Thread(taquilla1);
		Thread h2 = new Thread(taquilla2);

		h1.start();
		h2.start();

		// Esperar a que las taquillas terminen su venta
		h1.join();
		h2.join();
		long finVenta = System.currentTimeMillis();
		
		int clientesSinEntradaTotales = sala.getClientesSinEntrada() + cola.clientesRestantes();

		// 5 Mostrar resultados
		System.out.println("===RESUMEN===");
		System.out.println(
				"Ventas exitosas: " + sala.getClientesAtendidosConExito() + " personas han conseguido entrada");
		System.out.println(
				"Clientes sin entrada: " + clientesSinEntradaTotales + " personas no han conseguido entrada.");
		System.out.println("Tiempo venta: " + (finVenta - inicioVenta) / 1000 + " segundos ha durado la venta.");

	}

}
