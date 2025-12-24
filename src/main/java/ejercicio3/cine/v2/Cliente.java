package ejercicio3.cine.v2;

public class Cliente {

	private int id;
	// private long horaLlegada;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cliente" + id;
	}

}
