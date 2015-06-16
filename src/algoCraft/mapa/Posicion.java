package algoCraft.mapa;

import algoCraft.mapa.Posicion;

public class Posicion {
	
	private int x;
	private int y;
	private boolean esTerrestre;

	public Posicion(int x, int y, boolean esTerrestre) {
		this.x = x;
		this.y = y;
		this.esTerrestre = esTerrestre;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	@Override
	public boolean equals(Object otroObjeto) {
		if (otroObjeto.getClass() != this.getClass())
			return false;
		
		Posicion otraPosicion = (Posicion)otroObjeto;
		return ((this.x == otraPosicion.x) && (this.y == otraPosicion.y) 
				&& (this.esTerrestre == otraPosicion.esTerrestre));
	}
	
	@Override
	public int hashCode() {
		int corrimiento = (this.esTerrestre) ? 1 : 0;
		return ((this.x * 31) ^ this.y) + corrimiento;
	}
	
	public double calcularDistancia(Posicion posicion) {
		double distanciaEnX = posicion.getX() - this.x;
		double distanciaEnY = posicion.getY() - this.y;
		return Math.sqrt(Math.pow(distanciaEnX, 2) + Math.pow(distanciaEnY, 2));
	}
	
	public void sumar(Posicion diferencia) {
		this.x += diferencia.x;
		this.y += diferencia.y;
	}

	public Posicion diferencia(Posicion otraPosicion) {
		int diferenciaEnX = this.x - otraPosicion.x;
		int diferenciaEnY = this.y - otraPosicion.y;
		return (new Posicion(diferenciaEnX, diferenciaEnY, this.esTerrestre));
	}
}
