package ejercicio3.cine;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class Taquilla implements Runnable {

	private Duration tiempoDeVentaDeEntradas;
	private SalaProyeccion sala;
	private ColaEntradas cola;
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

	public ColaEntradas getCola() {
		return cola;
	}

	public void setSala(SalaProyeccion sala) {
		this.sala = sala;
	}

	public void setCola(ColaEntradas cola) {
		this.cola = cola;
	}

	public Taquilla(SalaProyeccion sala, ColaEntradas cola, String nombre) {
		super();
		this.sala = sala;
		this.cola = cola;
		this.nombre = nombre;
		this.tiempoDeVentaDeEntradas = Configuracion.TIEMPO_DE_VENTA_ENTRADAS_EN_MINUTOS;
	}

	@Override
	public void run() {
		long tiempoInicio = System.currentTimeMillis();
		long tiempoFin = tiempoInicio + tiempoDeVentaDeEntradas.toMillis();

		try {

			while (System.currentTimeMillis() < tiempoFin && !Thread.currentThread().isInterrupted()) {

				// 1. Coger un cliente de la cola

				Cliente cliente = cola.extraerCliente();

				if (cliente != null) {

					if (sala.reservarAsiento()) {
						venderEntrada();
						System.out.println(Thread.currentThread().getName()+ "vendida a: "+ cliente);

					} else {

						System.out.println("Sala llena. El cliente " + cliente + " se va sin entrada.");
						break; // Cierro taquilla porque no hay más clientes
					}

				}

			}

			System.out.println(Thread.currentThread().getName() + " ha cerrado venta de entradas.");

		} catch (InterruptedException e) {

			System.out.println(Thread.currentThread().getName() + ": Venta interrumpida");
			Thread.currentThread().interrupt();

		}
		System.out.println(Thread.currentThread().getName()+ "ha cerrado su ventana de venta.");

	}

	public void venderEntrada() throws InterruptedException {

		int tiempoVentaSegundos = ThreadLocalRandom.current().nextInt(Configuracion.TIEMPO_MIN_VENTA_SEG,
				Configuracion.TIEMPO_MAX_VENTA_SEG + 1);

		Thread.sleep(tiempoVentaSegundos * 1000); // Convertimos en milisegundos

	}

}
