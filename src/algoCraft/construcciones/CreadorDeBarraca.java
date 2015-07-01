package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;

public class CreadorDeBarraca extends CreadorDeEdificios {

	@Override
	public Edificio crearEdificio(Jugador jugador, int x, int y) {
		Barraca barraca = new Barraca(jugador, x, y);
		Mapa.getMapa().agregarPosicionable(barraca);
		return barraca;
	}

}
