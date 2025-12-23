package ejercicio3.cine;

import java.time.Duration;

public class Configuracion {

	public static final int NUM_ASIENTOS = 200;

	public static final Duration TIEMPO_DE_VENTA_ENTRADAS_EN_MINUTOS = Duration.ofMinutes(30);
	
	//Las necesito para después calcular el tiempo de venta
	public static final int TIEMPO_MIN_VENTA_SEG = 20;
    public static final int TIEMPO_MAX_VENTA_SEG = 30;

	public static final int NUM_TAQUILLAS = 2;

	public static final int NUM_COLAS = 1;

	public static final int TASA_LLEGADA_CLIENTES = (int) (Math.random() * 6) + 10;
	

}
