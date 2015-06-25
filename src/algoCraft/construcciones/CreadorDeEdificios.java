package algoCraft.construcciones;

import algoCraft.juego.Jugador;

public abstract class CreadorDeEdificios {
	
	public abstract Edificio crearEdificio(Jugador jugador, int x, int y);
}
