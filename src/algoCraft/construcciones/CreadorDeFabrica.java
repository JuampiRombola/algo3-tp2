package algoCraft.construcciones;

import algoCraft.juego.Jugador;

public class CreadorDeFabrica extends CreadorDeEdificios {
	private Barraca barraca;
	
	public CreadorDeFabrica(Barraca barraca) {
		this.barraca = barraca;
	}
	
	@Override
	public Edificio crearEdificio(Jugador jugador, int x, int y) {
		return new Fabrica(jugador, x, y, this.barraca);
	}

}
