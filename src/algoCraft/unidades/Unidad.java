package algoCraft.unidades;

import algoCraft.Atacable;
import algoCraft.mapa.Posicion;



public class Unidad implements Atacable {

	private Vida vida;
	private Arma arma;
	private Posicion posicion;
	private int turnosEnConstruirse;
	private boolean activa;

	public Unidad(int vidaMaxima, Arma arma, Posicion posicion, int turnosEnConstruirse) {
		this.arma = arma;
		this.vida = new Vida(vidaMaxima);
		this.posicion = posicion;
		this.activa = true;
		this.turnosEnConstruirse = turnosEnConstruirse;
	}
	
	public void atacar(Atacable atacable) {
		if (!this.estaDestruido() && this.activa) {
			arma.atacar(atacable, posicion.calcularDistancia(atacable.getPosicion()));
			this.activa = false;
		}
	}

	public boolean estaDestruido() {
		return this.vida.getPuntosDeVida() == 0;
	}
	
	public int getVidaActual() {
		return this.vida.getPuntosDeVida();
	}
	
	public int getVidaMaxima() {
		return  this.vida.getPuntosDeVidaMaximos();
	}
	
	//Cuando tengamos danio aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public int getDanio() {
		return this.arma.getDanio();
	}
	//Cuando tengamos rango aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public double getRango() {
		return this.arma.getRango();
	}
	
	public void recibePuntosDeDanio(int danio) {
		this.vida.recibirDanio(danio);
	}

	public boolean esTerrestre() {
		return true;
	}

	public void setPosicion(int x, int y) {
		this.posicion = new Posicion(x, y, this.esTerrestre());
	}

	public Posicion getPosicion() {
		return this.posicion;
	}
	
	public int getTurnosEnConstruirse() {
		return this.turnosEnConstruirse;
	}
	
	public void avanzarTurno(){
		this.activa = true;
	}
}