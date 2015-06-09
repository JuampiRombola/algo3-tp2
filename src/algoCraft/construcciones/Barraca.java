package algoCraft.construcciones;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class Barraca extends ConstructorDeUnidades{
	static int vidaMaxima = 1000;
	static boolean inicialmenteTerrestre = true;
	static int nivel = 1;
	
	public Barraca(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
	}

	public Unidad crearUnidad() {
		return new Marine(this.posicion.getX(), this.posicion.getY() + 1);
	}
	
	public int getNivel(){
		return nivel;
	}
	
}
