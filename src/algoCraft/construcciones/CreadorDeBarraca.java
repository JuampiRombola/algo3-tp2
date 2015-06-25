package algoCraft.construcciones;

import algoCraft.juego.Jugador;

public class CreadorDeBarraca extends CreadorDeEdificios {

	@Override
	public Edificio crearEdificio(Jugador jugador, int x, int y) {
		return new Barraca(jugador, x, y);
	}

}
