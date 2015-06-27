package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;

public class CreadorDeDepositoDeSuministros extends CreadorDeEdificios {

	@Override
	public Edificio crearEdificio(Jugador jugador, int x, int y) {
		DepositoDeSuministros deposito = new DepositoDeSuministros(jugador, x, y);
		Mapa.getMapa().agregarUnidad(deposito);
		return deposito;
	}

}
