package algoCraft.construcciones;

import algoCraft.Atacable;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Vida;

public abstract class Edificio implements Atacable {
	protected Vida vida;
	protected Posicion posicion;
	protected int turnosEnConstruirse;
	protected int contadorDeTurnos;
	protected boolean estaEnConstruccion;
	
	public Edificio(int vida, Posicion posicion, int turnosEnConstruirse) {
		this.vida = new Vida(vida);
		this.posicion = posicion;
		this.estaEnConstruccion = true;
		this.contadorDeTurnos = 0;
		this.turnosEnConstruirse = turnosEnConstruirse;
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
	
	public void avanzarTurno(Jugador jugador) {
		this.contadorDeTurnos++;
		if(this.estaEnConstruccion)
			continuarConstruccion(jugador);
	}

	protected void continuarConstruccion(Jugador jugador) {
		if(this.contadorDeTurnos == this.turnosEnConstruirse){
			this.estaEnConstruccion = false;
			this.contadorDeTurnos = 0;
		}
	}
	
	public boolean estaEnConstruccion() {
		return this.estaEnConstruccion;
	}
}
