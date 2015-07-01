package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;

public class CreadorDeFabrica extends CreadorDeEdificios {
	private Barraca barraca;
	
	public CreadorDeFabrica(Barraca barraca) {
		this.barraca = barraca;
	}
	
	@Override
	public Edificio crearEdificio(Jugador jugador, int x, int y) {
		Fabrica fabrica = new Fabrica(jugador, x, y, this.barraca);
		Mapa.getMapa().agregarPosicionable(fabrica);
		return fabrica;
	}

}
