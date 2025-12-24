package ejercicio3.cine.v2;

public class SalaProyeccion {

	// Es el recurso compartido porque las taquillas venden para la misma sala

	private int asientosDisponibles;
	private int clientesSinEntrada = 0;
	private int clientesAtendidosConExito = 0;

	public int getClientesSinEntrada() {
		return clientesSinEntrada;
	}

	public int getClientesAtendidosConExito() {
		return clientesAtendidosConExito;
	}

	public void setClientesSinEntrada(int clientesSinEntrada) {
		this.clientesSinEntrada = clientesSinEntrada;
	}

	public void setClientesAtendidosConExito(int clientesAtendidosConExito) {
		this.clientesAtendidosConExito = clientesAtendidosConExito;
	}

	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}

	public SalaProyeccion() {
		super();
		this.asientosDisponibles = Configuracion.NUM_ASIENTOS;
	}

	public synchronized boolean reservarAsiento() {

		if (asientosDisponibles > 0) {
			asientosDisponibles--;
			clientesAtendidosConExito++;
			return true;

		} else {

			clientesSinEntrada++;
			return false;
		}
	}

}
