package unidades;

public class UnidadTerran extends Unidad{
	
	public UnidadTerran(int vidaMaxima, Arma arma) {
		super(vidaMaxima, arma);
	}
	
	public void recibePuntosDeDanio(int danio) {
		vidaActual = vidaActual - danio;
		//Los valores negativos no tienen sentido en el modelo
		if(vidaActual < 0){ 
			vidaActual = 0;
		}
	}

}
