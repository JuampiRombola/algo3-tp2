package interfaces.unidadesYEstructuras;

public interface Atacable {
	public boolean estaDestruido();
	public void recibePuntosDeDanio(int danio);
	public int getVidaActual();
}
