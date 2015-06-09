package algoCraft.construcciones;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class Fabrica extends ConstructorDeUnidades{
	static int vidaMaxima = 1250;
	static boolean inicialmenteTerrestre = true;
	
	public Fabrica(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
	}

	public Unidad crearUnidad() {
		return new Goliath(this.posicion.getX(), this.posicion.getY() + 1);
	}
}

