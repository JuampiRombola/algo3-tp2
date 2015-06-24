package algoCraft.recursos;

import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.Posicionable;


public abstract class Recurso implements Posicionable {
	protected int unidadesRestantes;
	protected Posicion posicion;
	protected Boolean estaDestruido;
	
	public Recurso(int unidadesMaximas, Posicion posicion) {
		this.unidadesRestantes = unidadesMaximas;
		this.posicion = posicion;
		this.estaDestruido = false;
	}
	
	public int getUnidadesRestantes() {
		return this.unidadesRestantes;
	}
	
	private void verificarCantidadRestante(){
		if (this.unidadesRestantes <= 0) {
			this.estaDestruido = true;
			if ((Mapa.getMapa().posicionEstaOcupada(this.posicion)))
				Mapa.getMapa().removerRecurso(this);
		}
	}
	
	public boolean estaDestruido() {
		return this.estaDestruido;
	}
	
	public int extraer(int cantidadExtraida) {
		if  (this.estaDestruido())
			return 0;
		this.unidadesRestantes -= cantidadExtraida;
		verificarCantidadRestante();
		return cantidadExtraida;
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
