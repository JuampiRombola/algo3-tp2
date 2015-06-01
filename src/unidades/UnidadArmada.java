package unidades;

import interfacesParaUnidadesYEstructuras.Atacable;
import interfacesParaUnidadesYEstructuras.Atacante;

public class UnidadArmada extends Unidad implements Atacante{
	private Arma arma;
	
	public UnidadArmada(int vidaMaxima, Arma arma){
		super(vidaMaxima);
		this.arma = arma;
	}

	public void atacar(Atacable atacable, int distanciaEntreAtacanteYAtacable) {
		arma.atacar(atacable, distanciaEntreAtacanteYAtacable);
	}
}
