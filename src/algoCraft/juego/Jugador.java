package algoCraft.juego;

public class Jugador {
	static int cantidadInicialDeMineral = 200;
	static int cantidadInicialDeGasVespeno = 0;
	private String nombre;
	private int mineral;
	private int gasVespeno;

	Jugador(String nombre) {
		this.nombre = nombre;
		this.mineral = cantidadInicialDeMineral;
		this.gasVespeno = cantidadInicialDeGasVespeno;
	}
	
	public int getGasVespeno() {
		return this.gasVespeno;
	}
	
	public int getMineral() {
		return this.mineral;
	}

	public String getNombre() {
		return this.nombre;
	}
}
