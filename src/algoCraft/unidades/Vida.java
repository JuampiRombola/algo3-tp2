package algoCraft.unidades;

public class Vida {
	private int vidaMaxima;
	private int vidaActual;
	
	public Vida(int vidaMaxima){
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaMaxima;
	}

	public int getPuntosDeVida() {
		return vidaActual;
	}
	
	public int getPuntosDeVidaMaximos() {
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
