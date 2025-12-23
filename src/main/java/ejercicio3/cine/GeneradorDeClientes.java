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
			
			//Calculamos el número de clientes que puede haber en total
			int minutosApertura = (int) Configuracion.TIEMPO_DE_VENTA_ENTRADAS_EN_MINUTOS.toMinutes();
			int totalClientes = minutosApertura* Configuracion.TASA_LLEGADA_CLIENTES;
			for (int i =0; i<totalClientes;i++) {
				Cliente nuevoCliente = new Cliente(i+1);
				cola.anyadirClienteACola(nuevoCliente);
				Thread.sleep(Configuracion.TASA_LLEGADA_CLIENTES);
			}
			System.out.println("Se cierra la llegada de clientes");
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
	}

}
