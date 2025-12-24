package ejercicio3.cine;

import java.util.ArrayList;

public class ColaEntradas {

	// es un recurso compartido, porque las taquillas buscan clientes de esta cola.

	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private boolean colaAbierta = true;

	public synchronized void cerrarCola() {
		this.colaAbierta = false;
		notifyAll();
	}

	public synchronized void anyadirClienteACola(Cliente nuevoCliente) {

		listaClientes.addLast(nuevoCliente);
		System.out.println("cliente" + nuevoCliente.getId() + " ha llegado a la fila");
		notifyAll();
	}

	public synchronized Cliente extraerCliente() throws InterruptedException {

		while (listaClientes.isEmpty() && colaAbierta) {
			System.out.println(Thread.currentThread().getName() + " Espera: No hay clientes todavía");
			wait(1000);

		}

		if (listaClientes.isEmpty() && !colaAbierta) {
			return null;
		}

		return listaClientes.removeFirst();

	}

	public synchronized int clientesRestantes() {
		return listaClientes.size();
	}

}
