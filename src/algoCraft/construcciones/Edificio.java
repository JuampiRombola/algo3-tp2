package algoCraft.construcciones;

import algoCraft.Atacable;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Vida;

public abstract class Edificio implements Atacable {
	protected Jugador jugador;
	protected Vida vida;
	protected Posicion posicion;
	protected int turnosEnConstruirse;
	protected int contadorDeTurnos;
	protected boolean estaEnConstruccion;
	
	public Edificio(Jugador jugador, int vida, Posicion posicion, int turnosEnConstruirse) {
		this.jugador = jugador;
		this.vida = new Vida(vida);
		this.posicion = posicion;
		this.estaEnConstruccion = true;
		this.contadorDeTurnos = 0;
		this.turnosEnConstruirse = turnosEnConstruirse;
	}
	
	public int getTurnosEnConstruirse() {
		return this.turnosEnConstruirse;
	}
	
	public int getTurnosQuePasaroDeConstruccion() {
		return this.contadorDeTurnos;
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}
	
	public boolean estaDestruido() {
		return this.getVidaActual() <= 0;
	}
	
	protected void destruir() {
		Mapa.getMapa().removerUnidad(this);
	}
	
	public void recibePuntosDeDanio(int danio) {
		this.vida.recibirDanio(danio);
		if (this.estaDestruido()) {
			this.jugador.removerEdificio(this);
			this.destruir();
		}
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
	
	public void avanzarTurno() {
		this.contadorDeTurnos++;
		if(this.estaEnConstruccion)
			this.continuarConstruccion();
	}

	protected void continuarConstruccion() {
		if(this.contadorDeTurnos == this.turnosEnConstruirse){
			this.estaEnConstruccion = false;
			this.contadorDeTurnos = 0;
		}
	}
	
	public boolean estaEnConstruccion() {
		return this.estaEnConstruccion;
	}
	
	public int getVidaMaxima() {
		return vida.getPuntosDeVidaMaximos();
	}
}
