package ejercicio3.cine.v2;

import java.util.ArrayList;

public class GeneradorDeClientes implements Runnable {

	private ArrayList<ColaEntradas> listaColas;

	public GeneradorDeClientes(ArrayList<ColaEntradas> listaColas) {
		super();
		this.listaColas = listaColas;
	}

	@Override
	public void run() {
		try {

			for (int i = 0; i < Configuracion.TOTAL_CLIENTES; i++) {
				Cliente nuevoCliente = new Cliente(i + 1);
				boolean entregado = false;

				while (!entregado) {
					for (ColaEntradas cola : listaColas) {
						if (cola.clientesRestantes() < 10) {
							cola.anyadirClienteACola(nuevoCliente);
							entregado = true;
							break;
						}

					}

					if (!entregado) {
						Thread.sleep(1000);
					}
				}

				Thread.sleep(Configuracion.MS_ENTRE_LLEGADAS);
			}

			System.out.println("Se cierra la llegada de clientes");
			for (ColaEntradas cola : listaColas) {
				cola.cerrarCola();
			}

		} catch (

		InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

}
