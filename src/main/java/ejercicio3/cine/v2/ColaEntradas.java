package ejercicio3.cine.v2;

import java.util.ArrayList;

public class ColaEntradas {

	// es un recurso compartido, porque las taquillas buscan clientes de esta cola.
	private String nombre;

	private ArrayList<Cliente> listaClientesDeLaCola = new ArrayList<Cliente>();
	private boolean colaAbierta = true;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ColaEntradas(String nombre, boolean colaAbierta) {
		super();
		this.nombre = nombre;
		this.colaAbierta = colaAbierta;
	}

	public synchronized void cerrarCola() {
		this.colaAbierta = false;
		notifyAll();
	}

	public synchronized void anyadirClienteACola(Cliente nuevoCliente) throws InterruptedException {

		while ((listaClientesDeLaCola.size() >= 10) && colaAbierta) {
			wait();
		}
		if (colaAbierta) {

			listaClientesDeLaCola.addLast(nuevoCliente);
			System.out.println("cliente" + nuevoCliente.getId() + " ha llegado a la fila " + nombre);
			notifyAll();

		}
	}

	public synchronized Cliente extraerCliente() throws InterruptedException {

		while (listaClientesDeLaCola.isEmpty() && colaAbierta) {
			System.out.println(Thread.currentThread().getName() + " Espera: No hay clientes todavía");
			wait(1000);

		}

		if (listaClientesDeLaCola.isEmpty() && !colaAbierta) {
			return null;
		}

		Cliente cliente = listaClientesDeLaCola.removeFirst();
		// Avisar al generador
		notifyAll();
		return cliente;

	}

	public synchronized int clientesRestantes() {
		return listaClientesDeLaCola.size();
	}

	public synchronized boolean isColaAbierta() {
		return colaAbierta;
	}

}
