package algoCraft.construcciones;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class Barraca extends ConstructorDeUnidades{
	static int vidaMaxima = 1000;
	static boolean inicialmenteTerrestre = true;
	static int nivel = 1;
	private boolean habilitado = true;
	
	public Barraca(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
	}

	public Unidad crearUnidad() throws EdificioNoHabilitadoException {
		if (this.habilitado) {
			return new Marine(this.posicion.getX(), this.posicion.getY() + 1);
		} else {
			throw new EdificioNoHabilitadoException();
		}
	}
	
	public int getNivel() {
		return nivel;
	}

	void habilitarProduccion() {
		this.habilitado = true;
	}

	void deshabilitarProduccion() {
		this.habilitado = false;
	}
	
	public boolean estaHabilitado() {
		return this.habilitado;
	}
}
