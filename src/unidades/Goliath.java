package unidades;

public class Goliath extends UnidadTerran {
	static int vidaMaxima = 125;
	static int danioBasico = 12;
	static int rangoBasico = 5;
	static Arma arma = new Arma(danioBasico,rangoBasico);
	public Goliath() {
		super(vidaMaxima, arma);
	}
}
