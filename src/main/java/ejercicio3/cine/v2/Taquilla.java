package ejercicio3.cine.v2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Taquilla implements Runnable {

	private Duration tiempoDeVentaDeEntradas;
	private SalaProyeccion sala;
	private ArrayList<ColaEntradas> listaColas;
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Duration getTiempoDeVentaDeEntradas() {
		return tiempoDeVentaDeEntradas;
	}

	public void setTiempoDeVentaDeEntradas(Duration tiempoDeVentaDeEntradas) {
		this.tiempoDeVentaDeEntradas = tiempoDeVentaDeEntradas;
	}

	public SalaProyeccion getSala() {
		return sala;
	}

	public ArrayList<ColaEntradas> getListaColas() {
		return listaColas;
	}

	public void setListaColas(ArrayList<ColaEntradas> listaColas) {
		this.listaColas = listaColas;
	}

	public void setSala(SalaProyeccion sala) {
		this.sala = sala;
	}

	public Taquilla(SalaProyeccion sala, ArrayList<ColaEntradas> listaColas, String nombre) {
		super();
		this.sala = sala;
		this.listaColas = listaColas;
		this.nombre = nombre;
		this.tiempoDeVentaDeEntradas = Configuracion.TIEMPO_DE_VENTA_ENTRADAS_EN_MINUTOS;
	}

	@Override
	public void run() {
		long tiempoInicio = System.currentTimeMillis();
		long tiempoFin = tiempoInicio + tiempoDeVentaDeEntradas.toMillis();

		try {

			while (System.currentTimeMillis() < tiempoFin && !Thread.currentThread().isInterrupted()) {

				Cliente cliente = null;
				boolean todasCerradasYVacias = true;
				String nombreColaUsada = "";

				for (ColaEntradas cola : listaColas) {

					if (cola.clientesRestantes() > 0) {
						cliente = cola.extraerCliente();
						if (cliente != null) {
							nombreColaUsada = cola.getNombre();
							break;
						}
					}

					if (cola.isColaAbierta()) {
						todasCerradasYVacias = false;
					}
				}

				
				if (cliente != null) {

					if (sala.reservarAsiento()) {
						venderEntrada();
						System.out.println(nombre + " vendió a: " + cliente + " de la fila "+nombreColaUsada);
					} else {
						System.out.println("SALA LLENA. " + nombre + " cierra.");
						break;
					}
				} else {

					if (todasCerradasYVacias) {
						System.out.println(nombre + " ve todas las colas cerradas y vacías. Se retira.");
						break;
					}

					Thread.sleep(100);
				}
			}
		} catch (InterruptedException e) {

			System.out.println(Thread.currentThread().getName() + ": Venta interrumpida");
			Thread.currentThread().interrupt();

		}
		System.out.println(Thread.currentThread().getName() + "ha cerrado su ventana de venta.");

	}

	public void venderEntrada() throws InterruptedException {

		int tiempoVenta = ThreadLocalRandom.current().nextInt(Configuracion.TIEMPO_MIN_VENTA_SEG,
				Configuracion.TIEMPO_MAX_VENTA_SEG + 1);

		// Convertimos en ms y dividos por el factor velocidad para la simulación
		long tiempoFinal = (tiempoVenta * 1000L) / Configuracion.FACTOR_VELOCIDAD;
		Thread.sleep(tiempoFinal);

	}

}
