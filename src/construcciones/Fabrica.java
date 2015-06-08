package construcciones;

import mapa.Posicion;
import unidades.Goliath;
import unidades.Unidad;

public class Fabrica extends Edificio implements Creable{
	static int vidaMaxima = 1250;
	static boolean inicialmenteTerrestre = true;
	
	public Fabrica(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
	}

	@Override
	public Unidad crearUnidad() {
		return new Goliath(this.posicion.getX(), this.posicion.getY() + 1);
	}
}

