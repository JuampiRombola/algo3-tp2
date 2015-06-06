package interfaces.unidadesYEstructuras;

import mapa.Posicionable;

public interface Atacable extends Posicionable{
	public boolean estaDestruido();
	public void recibePuntosDeDanio(int danio);
	public int getVidaActual();
}
