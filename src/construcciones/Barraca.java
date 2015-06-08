package construcciones;

import mapa.Posicion;
import unidades.Marine;
import unidades.Unidad;

public class Barraca extends ConstructorDeUnidades{
	static int vidaMaxima = 1000;
	static boolean inicialmenteTerrestre = true;
	
	public Barraca(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
	}

	public Unidad crearUnidad() {
		return new Marine(this.posicion.getX(), this.posicion.getY() + 1);
	}
}
