package construcciones;

import interfaces.unidadesYEstructuras.Atacable;
import mapa.Posicion;
import recursos.Recurso;
import unidades.Vida;


public abstract class EdificioRecolector implements Atacable{
	protected int recursosRecolectados;
	protected Recurso recurso;
	protected Posicion posicion;
	protected Vida vida;
	
	public EdificioRecolector(Recurso recurso, int vida) {
		this.recursosRecolectados = 0;
		this.recurso = recurso;
		this.posicion = recurso.getPosicion();
		this.vida = new Vida(vida);
	}
	
	public int getRecursosRecolectados() {
		return recursosRecolectados;
	}
	
	public void recolectar(int cantidadRecolectada) {
		this.recursosRecolectados += cantidadRecolectada;
		this.recurso.extraer(cantidadRecolectada);
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
	
	public boolean estaDestruido() {
		return this.getVidaActual() <= 0;
	}
	
	public void recibePuntosDeDanio(int danio){
		this.vida.recibirDanio(danio);
	}
	
	public int getVidaActual(){
		return this.vida.getPuntosDeVida();
	}
	
}
