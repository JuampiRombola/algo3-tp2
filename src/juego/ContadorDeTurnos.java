package juego;

public class ContadorDeTurnos{
	
	private static boolean instanciaCreada = false;
	private static ContadorDeTurnos contador;
	private int turnoActual;
	
	private ContadorDeTurnos(){
		turnoActual = 1;
	}
	
	//Nada debe llamarlo por fuera de las pruebas excepto la clase que vaya a manejarlo.
	static public void iniciarContador(){
		contador = new ContadorDeTurnos();
		instanciaCreada = true;
	}
	
	
	static public ContadorDeTurnos getInstancia(){
		return contador;
	}
	
	public int obtenerTurnoActual() {
		if (instanciaCreada){
			return turnoActual;
		}else{
			throw new NoFueCreadoUnContadorDeTurnos();
		}
	}
	
	public void avanzarTurno(){
		if (instanciaCreada){
			turnoActual++;
		}else{
			throw new NoFueCreadoUnContadorDeTurnos();
		}
			
	}
}
