package ejercicio3.cine;

import java.time.Duration;

public class Configuracion {

	//Parámetros del cine
	public static final int NUM_ASIENTOS = 200;
	public static final int NUM_TAQUILLAS = 2;
	public static final int NUM_COLAS = 1;

	
	//Configuración de tiempos
	public static final int MINUTOS_APERTURA = 30;
	public static final int CLIENTES_POR_MINUTO = (int) (Math.random() * 6) + 10;
	
	public static final int TIEMPO_MIN_VENTA_SEG = 20;
    public static final int TIEMPO_MAX_VENTA_SEG = 30;

    //Para acelerar la simulación (1 = normal, 100=rapido, 1000= muy rapido
    public static final int FACTOR_VELOCIDAD = 1;
    
    //Cálculos automáticos
    public static final int TOTAL_CLIENTES = MINUTOS_APERTURA *CLIENTES_POR_MINUTO;

    //Tiempo de venta de entradas
	public static final Duration TIEMPO_DE_VENTA_ENTRADAS_EN_MINUTOS = Duration.ofMinutes(30);
	
	//Tiempo entre llegadas de clientes
	public static final int MS_ENTRE_LLEGADAS = (6000 / CLIENTES_POR_MINUTO)/FACTOR_VELOCIDAD;
	
	

}
