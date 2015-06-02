package construcciones;

public abstract class EdificioRecolector {
	private int recursosRecolectados;
	
	public EdificioRecolector() {
		this.recursosRecolectados = 0;
	}
	
	public int getRecursosRecolectados() {
		return recursosRecolectados;
	}
}
