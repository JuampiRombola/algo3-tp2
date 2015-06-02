package recursos;


public abstract class Recurso {
	static int recoleccion = 10;
	private int unidadesRestantes;
	
	public Recurso(int unidadesMaximas) {
		this.unidadesRestantes = unidadesMaximas;
	}
	
	public int getUnidadesRestantes() {
		return this.unidadesRestantes;
	}
	
	public boolean estaDestruido() {
		return unidadesRestantes <= 0;
	}
	
	public void recolectar() {
		if  (!this.estaDestruido()) {
			this.unidadesRestantes -= recoleccion;
		}

	}
}
