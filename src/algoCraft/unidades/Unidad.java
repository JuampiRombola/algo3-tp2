package algoCraft.unidades;

import algoCraft.Atacable;
import algoCraft.juego.ContadorDeTurnos;
import algoCraft.mapa.Posicion;



public class Unidad implements Atacable {

	private Vida vida;
	private Arma arma;
	private Posicion posicion;

	private int turnoUltimaAccion;
	private boolean realiceUnaAccion;

	public Unidad(int vidaMaxima, Arma arma, Posicion posicion) {
		this.arma = arma;
		this.vida = new Vida(vidaMaxima);
		this.posicion = posicion;
		this.realiceUnaAccion = false;
	
	}

	private boolean pasoUnTurnoDesdeLaUltimaAccion() {
		ContadorDeTurnos contador = ContadorDeTurnos.getInstancia();
		int turnosPasadosDesdeUltimaAccion  = turnoUltimaAccion - contador.obtenerTurnoActual();
		return (turnosPasadosDesdeUltimaAccion != 0) ;
	}
	
	private boolean estoyActiva(){
		return (!realiceUnaAccion || pasoUnTurnoDesdeLaUltimaAccion());
	}


	private void actualizoElTurnoDeLaUltimaAccion() {
		ContadorDeTurnos contador = ContadorDeTurnos.getInstancia();
		turnoUltimaAccion = contador.obtenerTurnoActual();
	}
	
	public void atacar(Atacable atacable) {
		if (!estaDestruido() && estoyActiva()){
			arma.atacar(atacable, posicion.calcularDistancia(atacable.getPosicion()));
			realiceUnaAccion = true;
			actualizoElTurnoDeLaUltimaAccion();
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
}