package juego;

public class ContadorDeTurnos {
	
	private int turnoActual;
	
	public ContadorDeTurnos(){
		turnoActual = 1;
	}
	
	public int obtenerTurnoActual() {
		return turnoActual;
	}
	
	public void avanzarTurno(){ //Package private
		turnoActual++;
	}
}
