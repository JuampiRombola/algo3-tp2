package algoCraft.construcciones;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Marine;

public class Barraca extends EdificioConstructor{
	static int vidaMaxima = 1000;
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 12;
	public static int cantidadMineral = 150;
	public static int cantidadGasVespeno = 0;
	
	public Barraca(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse);
	}

	@Override
	public void crearMarine() throws ElEdificioEstaEnConstruccion {
		if (!this.estaEnConstruccion) {
			Marine marine = new Marine(this.posicion.getX(), this.posicion.getY() + 1);
			this.unidadesEnConstruccion.offer(marine);
		} else {
			throw new ElEdificioEstaEnConstruccion();
		}
	}
}
