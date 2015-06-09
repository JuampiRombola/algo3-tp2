package algoCraft.construcciones;

import java.util.ArrayList;

public class EdificiosDeProduccion {
	
	/*Primer modelo:
		Solo hay una serie de edificios que dependen uno de los otros.
		Todo edificio puede producir unidades siempre y cuando exista uno del nivel anterior.
		En el futuro cada jugador debe tener su clase EdificiosDeProduccion
	*/
	
	
	ArrayList<ConstructorDeUnidades> edificios = new ArrayList<ConstructorDeUnidades>();
	
	public void agregarEdificio(ConstructorDeUnidades edificio){
		if(edificios.isEmpty() || !edificios.contains(edificio))
			edificios.add(edificio);
	}
	
	private int nivelDelEdificioConNivelMasAlto() {
		int nivelMasAlto = 0;
		for (ConstructorDeUnidades edificio : edificios) {
			if (edificio.getNivel() > nivelMasAlto){
				nivelMasAlto = edificio.getNivel();
			}
		}	
		return nivelMasAlto;
	}
	
	private void habilitarTodosLosEdificiosDeNivel(int i) {
		for (ConstructorDeUnidades edificio : edificios) {
			if (edificio.getNivel() == i)
				edificio.habilitarProduccion();
		}
	}
	private void deshabilitarTodosLosEdificiosDeNivel(int i) {
		for (ConstructorDeUnidades edificio : edificios) {
			if (edificio.getNivel() == i)
				edificio.deshabilitarProduccion();
		}
		
	}
	
	private boolean existeUnEdificioNoDestruidoDeNivelMenorA(int nivel) {
		int nivelBuscado = nivel -1;
		for (ConstructorDeUnidades edificio : edificios) {
			if (edificio.getNivel() == nivelBuscado && !edificio.estaDestruido()){
				return true;
			}
		}
		return false;
	}
	
	public void validarDependencias(){
		for(int i = nivelDelEdificioConNivelMasAlto(); i > 1; i--){
			if(existeUnEdificioNoDestruidoDeNivelMenorA(i)){
				habilitarTodosLosEdificiosDeNivel(i);
			}else{
				deshabilitarTodosLosEdificiosDeNivel(i);
			}
		}
	}


	
}
