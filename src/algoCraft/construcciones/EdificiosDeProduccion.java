package algoCraft.construcciones;

import java.util.ArrayList;

public class EdificiosDeProduccion {
	
	/*Primer modelo:
		Solo hay una serie de edificios que dependen uno de los otros.
		Todo edificio puede producir unidades siempre y cuando exista uno del nivel anterior.
		En el futuro cada jugador debe tener su clase EdificiosDeProduccion
	*/
	
	
	ArrayList<ConstructorDeUnidades> edificios = new ArrayList<ConstructorDeUnidades>();
	
	public void agregarEdificio(ConstructorDeUnidades edificio) throws NoExisteUnEdificioDeNivelAnterior{
		if (edificios.isEmpty() || !edificios.contains(edificio))
			if (edificio.getNivel() == 1 || existeUnEdificioDeUnNivelMenorA(edificio.getNivel())) {
				edificios.add(edificio);
			} else {
				throw new NoExisteUnEdificioDeNivelAnterior();
			}
	}
	
	private int nivelDelEdificioConNivelMasAlto() {
		int nivelMasAlto = 0;
		for (ConstructorDeUnidades edificio : edificios) {
			if (edificio.getNivel() > nivelMasAlto)
				nivelMasAlto = edificio.getNivel();
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
	
	private boolean existeUnEdificioDeUnNivelMenorA(int nivel) {
		int nivelBuscado = nivel -1;
		for (ConstructorDeUnidades edificio : edificios) {
			if (edificio.getNivel() == nivelBuscado && !edificio.estaDestruido())
				return true;
		}
		return false;
	}
	
	private void borrarEdificiosDestruidos() {
		for (ConstructorDeUnidades edificio : edificios) {
			if (edificio.estaDestruido())
				edificios.remove(edificio);
		}
	}
	
	public void validarDependencias() {
		borrarEdificiosDestruidos();
		for (int i = nivelDelEdificioConNivelMasAlto(); i > 1; i--) {
			if (existeUnEdificioDeUnNivelMenorA(i)) {
				habilitarTodosLosEdificiosDeNivel(i);
			} else {
				deshabilitarTodosLosEdificiosDeNivel(i);
			}
		}
	}
}
