package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.recursos.Mineral;

public class CreadorDeCentroDeMineral extends CreadorDeEdificios {
	private Mineral mineral;
	
	public CreadorDeCentroDeMineral(Mineral mineral) {
		this.mineral = mineral;
	}
	
	@Override
	public Edificio crearEdificio(Jugador jugador, int x, int y) {
		return new CentroDeMineral(jugador, this.mineral);
	}

}
