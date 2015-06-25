package algoCraft.construcciones;

import algoCraft.juego.Jugador;

public class CreadorDeDepositoDeSuministros extends CreadorDeEdificios {

	@Override
	public Edificio crearEdificio(Jugador jugador, int x, int y) {
		return new DepositoDeSuministros(jugador, x, y);
	}

}
