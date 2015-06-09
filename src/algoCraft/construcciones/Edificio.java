package algoCraft.construcciones;

import algoCraft.Atacable;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Vida;

public abstract class Edificio implements Atacable{
	protected Vida vida;
	protected Posicion posicion;
	
	public Edificio(int vida, Posicion posicion) {
		this.vida = new Vida(vida);
		this.posicion = posicion;
	}
	
	public boolean estaDestruido() {
		return this.getVidaActual() <= 0;
	}
	
	public void recibePuntosDeDanio(int danio) {
		this.vida.recibirDanio(danio);
	}
	
	public int getVidaActual() {
		return this.vida.getPuntosDeVida();
	}
	
	public boolean esTerrestre(){
		return true;
	}
	
	public void setPosicion(int x, int y) {
		this.posicion = new Posicion(x, y, this.esTerrestre());
	}
	
	public Posicion getPosicion() {
		return this.posicion;
	}
}
