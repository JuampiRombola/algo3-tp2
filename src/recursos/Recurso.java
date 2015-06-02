package recursos;

import construcciones.EdificioRecolector;


public abstract class Recurso {
	static int recoleccion = 10;
	protected int unidadesRestantes;
	protected EdificioRecolector recolector;
	
	public Recurso(int unidadesMaximas) {
		this.unidadesRestantes = unidadesMaximas;
	}
	
	public int getUnidadesRestantes() {
		return this.unidadesRestantes;
	}
	
	public boolean estaDestruido() {
		return unidadesRestantes <= 0;
	}
	
	public void setRecolector(EdificioRecolector recolector) {
		this.recolector = recolector;
	}
	
	public void recolectar() {
		if  ((!this.estaDestruido()) && (this.recolector != null)) {
			this.unidadesRestantes -= recoleccion;
			this.recolector.recolectar(recoleccion);
		}

	}
}
