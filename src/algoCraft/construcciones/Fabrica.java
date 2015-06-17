package algoCraft.construcciones;


import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;


public class Fabrica extends EdificioConstructor {
	static int vidaMaxima = 1250;
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 12;

	public Fabrica(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse);
	}

	@Override
	public void crearGoliath() throws ElEdificioEstaEnConstruccion {
		if (!this.estaEnConstruccion) {
			Goliath goliath = new Goliath(this.posicion.getX(), this.posicion.getY() + 1);
			this.unidadesEnConstruccion.offer(goliath);
		} else {
			throw new ElEdificioEstaEnConstruccion();
		}
	}
}

