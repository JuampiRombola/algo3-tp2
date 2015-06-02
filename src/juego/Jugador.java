package juego;

public class Jugador {
	static int mineralesIniciales = 200;
	private int minerales;
	
	public Jugador() {
		this.minerales = mineralesIniciales;
	}
	
	public int getMinerales() {
		return this.minerales;
	}
	
	public void sumarMinerales(int mineralesRecolectados) {
		this.minerales += mineralesRecolectados;
	}
}
