package algoCraft.unidades;

import algoCraft.Atacable;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.excepciones.LaUnidadPerteneceAUnJugadorInactivo;
import algoCraft.unidades.excepciones.LaUnidadYaSeMovioEnEsteTurno;
import algoCraft.unidades.excepciones.NoPuedeAtacar;
import algoCraft.unidades.excepciones.RangoDeMovimientoInvalido;

public class Unidad implements Atacable {
	private Jugador jugador;
	private Vida vida;
	private Arma arma;
	private Posicion posicion;
	private boolean activa;
	private boolean movimientoActivo;
	private int poblacionQueOcupa;
	static int rangoMovimiento = 4;

	public Unidad(Jugador jugador, int vidaMaxima, Arma arma, Posicion posicion, int poblacionQueOcupa) {
		this.jugador = jugador;
		this.arma = arma;
		this.vida = new Vida(vidaMaxima);
		this.posicion = posicion;
		this.activa = true;
		this.movimientoActivo = true;
		this.poblacionQueOcupa = poblacionQueOcupa;
		Mapa.getMapa().agregarPosicionable(this);
	}
	
	public void atacar(Atacable atacable) {
		if (this.estaDestruido() || !this.activa || !this.jugador.estaActivo() || this.jugador == atacable.getJugador())
			throw new NoPuedeAtacar();
		arma.atacar(atacable, posicion.calcularDistancia(atacable.getPosicion()));
		this.activa = false;
	}

	public boolean estaDestruido() {
		return this.getVidaActual() <= 0;
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
		if (this.estaDestruido()){
			this.jugador.removerUnidad(this);
			Mapa.getMapa().removerPosicionable(this);
		}
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
	
	public void avanzarTurno(){
		this.activa = true;
		this.movimientoActivo = true;
	}

	public int getPoblacionQueOcupa() {
		return this.poblacionQueOcupa;
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}
	
	public void moverseA(int xDestino, int yDestino) {
		if (!this.jugador.estaActivo())
			throw new LaUnidadPerteneceAUnJugadorInactivo();
		if (this.posicion.calcularDistancia(new Posicion(xDestino, yDestino, true)) > rangoMovimiento)
			throw new RangoDeMovimientoInvalido();
		if (!this.movimientoActivo)
			throw new LaUnidadYaSeMovioEnEsteTurno();
		Mapa.getMapa().moverUnidad(this, xDestino, yDestino);
		this.movimientoActivo = false;
	}

	public int getRangoMovimiento() {
		return rangoMovimiento;
	}

	public boolean puedeAtacar() {
		return this.activa;
	}
	
	public boolean puedeMoverse() {
		return this.movimientoActivo;
	}
}