package algoCraft.construcciones;


import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;


public class Fabrica extends EdificioConstructor {
	static int vidaMaxima = 1250;
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 12;
	public static int cantidadMineral = 200;
	public static int cantidadGasVespeno = 100;

	public Fabrica(int x, int y, Barraca barraca) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse);
		if(barraca.estaEnConstruccion())
			throw new NoSePuedeConstruirElEdificio();
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

