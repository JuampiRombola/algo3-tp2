package unidades;

public class Vida {
	private int vidaMaxima;
	private int vidaActual;
	
	Vida(int vidaMaxima){
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaMaxima;
	}

	public int getVida() {
		return vidaActual;
	}
	
	public int getVidaMaxima() {
		return vidaMaxima;
	}
	
	public void recibirDanio(int danio){
		if(vidaActual > danio){
			vidaActual = vidaActual - danio;
		}else{
			vidaActual = 0;
		}
	}
}
