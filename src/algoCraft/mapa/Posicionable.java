package algoCraft.mapa;

public interface Posicionable {
	boolean esTerrestre();
	void setPosicion(int x, int y);
	Posicion getPosicion();
	void avanzarTurno();
}
