package algoCraft.unidades;

public class Vida {
	protected int vidaMaxima;
	protected int vidaActual;
	
	public Vida(int vidaMaxima) {
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
		if(vidaActual > danio) {
			vidaActual = vidaActual - danio;
		} else {
			vidaActual = 0;
		}
	}
	
	public void setVidaActualEnCero() {
		this.vidaActual = 0;
	}
	
	public void maximizarVida() {
		this.vidaActual = this.vidaMaxima;
	}
	
	public void sumarVida(int vida) {
		this.vidaActual += vida;
	}
}
