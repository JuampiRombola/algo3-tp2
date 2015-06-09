package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdificiosDeProduccionTest {
	
	private ConstructorDeUnidades crearEdificioDeNivel1(){
		return new Barraca(1, 1);
	}
	
	private ConstructorDeUnidades crearEdificioDeNivel2(){
		return new Fabrica(1, 1);
	}
	
	@Test
	public void siTengoUnEdificioDeNivel1YActualizoLasDependenciasSigueEstandoHabilitado(){
		ConstructorDeUnidades deNivel1 = crearEdificioDeNivel1();
		EdificiosDeProduccion edificios = new EdificiosDeProduccion();
		edificios.agregarEdificio(deNivel1);
		edificios.validarDependencias();
		assertTrue(deNivel1.estaHabilitado());
	}
	
	@Test
	public void siTengoUnEdificioDeNivel1YOtroDeNivel2YActualizoLasDependenciasElDeNivel2SigueEstandoHabilitado(){
		ConstructorDeUnidades deNivel1 = crearEdificioDeNivel1();
		ConstructorDeUnidades deNivel2 = crearEdificioDeNivel2();
		EdificiosDeProduccion edificios = new EdificiosDeProduccion();
		edificios.agregarEdificio(deNivel1);
		edificios.agregarEdificio(deNivel2);
		edificios.validarDependencias();
		assertTrue(deNivel2.estaHabilitado());
	}
	
	@Test
	public void siTengoUnEdificioDeNivel1YOtroDeNivel2YDestruyenElDeNivel1ElDeNivel2NoEstaHabilitado(){
		ConstructorDeUnidades deNivel1 = crearEdificioDeNivel1();
		ConstructorDeUnidades deNivel2 = crearEdificioDeNivel2();
		EdificiosDeProduccion edificios = new EdificiosDeProduccion();
		edificios.agregarEdificio(deNivel1);
		edificios.agregarEdificio(deNivel2);
		deNivel1.recibePuntosDeDanio(deNivel1.getVidaActual());
		edificios.validarDependencias();
		assertTrue(!deNivel2.estaHabilitado());
	}
}
