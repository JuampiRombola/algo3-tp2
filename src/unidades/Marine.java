package unidades;

public class Marine extends UnidadTerran{

	static int vidaMaxima = 40;
	static int danioBasico = 6;
	static int rangoBasico = 4;
	static Arma arma = new Arma(danioBasico,rangoBasico);
	
	public Marine() {
		super(vidaMaxima, arma);
	}

}
