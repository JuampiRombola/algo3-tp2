package interfaces.unidadesYEstructuras;

import mapa.Posicionable;

public interface Atacable extends Posicionable{
	boolean estaDestruido();
	void recibePuntosDeDanio(int danio);
	int getVidaActual();
}
