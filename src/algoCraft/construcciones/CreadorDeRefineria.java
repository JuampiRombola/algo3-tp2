package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.recursos.GasVespeno;

public class CreadorDeRefineria extends CreadorDeEdificios {
	private GasVespeno gas;
	
	public CreadorDeRefineria(GasVespeno gas) {
		this.gas = gas;
	}
		
	@Override
	public Edificio crearEdificio(Jugador jugador, int x, int y) {
		return new Refineria(jugador, this.gas);
	}

}
