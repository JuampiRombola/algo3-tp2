package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdificiosDeProduccionTest {
	
	private ConstructorDeUnidades crearEdificioDeNivel1Completado(){
		Barraca barraca = new Barraca(1, 1);
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		return barraca;
	}
	
	private ConstructorDeUnidades crearEdificioDeNivel2Completado(){
		Fabrica fabrica = new Fabrica(1, 1);
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		return fabrica;
	}
	
	@Test
	public void siTengoUnEdificioDeNivel1YActualizoLasDependenciasSigueEstandoHabilitado() throws NoExisteUnEdificioDeNivelAnterior{
		ConstructorDeUnidades deNivel1 = crearEdificioDeNivel1Completado();
		EdificiosDeProduccion edificios = new EdificiosDeProduccion();
		edificios.agregarEdificio(deNivel1);
		edificios.validarDependencias();
		assertTrue(deNivel1.estaHabilitado());
	}
	
	@Test
	public void siTengoUnEdificioDeNivel1YOtroDeNivel2YActualizoLasDependenciasElDeNivel2SigueEstandoHabilitado(){
		ConstructorDeUnidades deNivel1 = crearEdificioDeNivel1Completado();
		ConstructorDeUnidades deNivel2 = crearEdificioDeNivel2Completado();
		EdificiosDeProduccion edificios = new EdificiosDeProduccion();
		try {
			edificios.agregarEdificio(deNivel1);
		} catch (NoExisteUnEdificioDeNivelAnterior e) {}
		try {
			edificios.agregarEdificio(deNivel2);
		} catch (NoExisteUnEdificioDeNivelAnterior e) {}
		edificios.validarDependencias();
		assertTrue(deNivel2.estaHabilitado());
	}
	
	@Test
	public void siTengoUnEdificioDeNivel1YOtroDeNivel2YDestruyenElDeNivel1ElDeNivel2NoEstaHabilitado(){
		ConstructorDeUnidades deNivel1 = crearEdificioDeNivel1Completado();
		ConstructorDeUnidades deNivel2 = crearEdificioDeNivel2Completado();
		EdificiosDeProduccion edificios = new EdificiosDeProduccion();
		try {
			edificios.agregarEdificio(deNivel1);
		} catch (NoExisteUnEdificioDeNivelAnterior e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			edificios.agregarEdificio(deNivel2);
		} catch (NoExisteUnEdificioDeNivelAnterior e) {}
		
		deNivel1.recibePuntosDeDanio(deNivel1.getVidaActual());
		edificios.validarDependencias();
		assertTrue(!deNivel2.estaHabilitado());
	}
	
	@Test(expected = NoExisteUnEdificioDeNivelAnterior.class)
	public void siTratoDePonerUnEdificioDeNivel2SinHaberPuestoUnoDeNivel1AnteriormenteObtengoUnaExcepcion() throws NoExisteUnEdificioDeNivelAnterior{
		ConstructorDeUnidades deNivel2 = crearEdificioDeNivel2Completado();
		EdificiosDeProduccion edificios = new EdificiosDeProduccion();
		edificios.agregarEdificio(deNivel2);
		assertTrue(!deNivel2.estaHabilitado());
	}
}
