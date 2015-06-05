package unidades;

import mapa.Posicion;
import mapa.Posicionable;
import interfaces.unidadesYEstructuras.Atacable;
import interfaces.unidadesYEstructuras.Seleccionable;



public class Unidad implements Seleccionable, Posicionable{

	protected Vida vida;
	protected Arma arma;
	private Posicion posicion;

	public Unidad(int vidaMaxima, Arma arma){
		this.arma = arma;
		this.vida = new Vida(vidaMaxima);
	}

	public void atacar(Atacable atacable, double distanciaEntreAtacanteYAtacable) {
		if (!estaDestruido())
			arma.atacar(atacable, distanciaEntreAtacanteYAtacable);
	}


	public boolean estaDestruido(){
		return vida.getVida() == 0;
	}
	
	public int getVidaActual(){
		return vida.getVida();
	}
	
	public int getVidaMaxima(){
		return  vida.getVidaMaxima();
	}
	
	//Cuando tengamos danio aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public int getDanio(){
		return this.arma.getDanio();
	}
	//Cuando tengamos rango aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public double getRango(){
		return this.arma.getRango();
	}
	
	public void recibePuntosDeDanio(int danio) {
		vida.recibirDanio(danio);
	}

	@Override
	public boolean esTerrestre() {
		return true;
	}

	@Override
	public void setPosicion(int x, int y) {
		this.posicion = new Posicion(x, y, this.esTerrestre());
	}

	@Override
	public Posicion getPosicion() {
		return this.posicion;
	}
}