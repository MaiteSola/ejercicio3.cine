package ejercicio3.cine;

public class GeneradorDeClientes implements Runnable {

	private ColaEntradas cola;

	public GeneradorDeClientes(ColaEntradas cola) {
		super();
		this.cola = cola;
	}

	@Override
	public void run() {
		try {

			for (int i = 0; i < Configuracion.TOTAL_CLIENTES; i++) {
				Cliente nuevoCliente = new Cliente(i + 1);
				cola.anyadirClienteACola(nuevoCliente);
				Thread.sleep(Configuracion.MS_ENTRE_LLEGADAS);
			}

			System.out.println("Se cierra la llegada de clientes");
			cola.cerrarCola();

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

}
