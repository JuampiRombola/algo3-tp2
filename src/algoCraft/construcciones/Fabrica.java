package algoCraft.construcciones;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class Fabrica extends ConstructorDeUnidades {
	static int vidaMaxima = 1250;
	static boolean inicialmenteTerrestre = true;
	static int nivel = 2;
	private boolean habilitado = true;
	
	public Fabrica(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
	}

	public Unidad crearUnidad() throws EdificioNoHabilitadoException {
		if (this.habilitado) {
			return new Goliath(this.posicion.getX(), this.posicion.getY() + 1);
		} else {
			throw new EdificioNoHabilitadoException();
		}
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void habilitarProduccion() {
		this.habilitado = true;
	}

	public void deshabilitarProduccion() {
		this.habilitado = false;
	}
	
	public boolean estaHabilitado() {
		return habilitado;
	}
}

