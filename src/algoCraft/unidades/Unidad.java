package algoCraft.unidades;

import algoCraft.Atacable;
import algoCraft.mapa.Posicion;



public class Unidad implements Atacable {

	private Vida vida;
	private Arma arma;
	private Posicion posicion;
	// Habria que reveer todos los metodos en base a este nuevo atributo
	private int turnosEnConstruirse;
	private boolean realiceUnaAccion;

	public Unidad(int vidaMaxima, Arma arma, Posicion posicion, int turnosEnConstruirse) {
		this.arma = arma;
		this.vida = new Vida(vidaMaxima);
		this.posicion = posicion;
		this.realiceUnaAccion = false;
		this.turnosEnConstruirse = turnosEnConstruirse;
	}

	private boolean pasoUnTurnoDesdeLaUltimaAccion() {
		return true;
	}
	
	private boolean estoyActiva(){
		return (!realiceUnaAccion || pasoUnTurnoDesdeLaUltimaAccion());
	}
	
	public void atacar(Atacable atacable) {
		if (!estaDestruido() && estoyActiva()) {
			arma.atacar(atacable, posicion.calcularDistancia(atacable.getPosicion()));
		}
	}

	public boolean estaDestruido() {
		return vida.getPuntosDeVida() == 0;
	}
	
	public int getVidaActual() {
		return vida.getPuntosDeVida();
	}
	
	public int getVidaMaxima() {
		return  vida.getPuntosDeVidaMaximos();
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
		vida.recibirDanio(danio);
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
}