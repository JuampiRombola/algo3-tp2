package algoCraft.unidades;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Base;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.unidades.Arma;
import algoCraft.unidades.Unidad;
import algoCraft.unidades.excepciones.AtaqueFueraDeRangoException;
import algoCraft.unidades.excepciones.LaUnidadPerteneceAUnJugadorInactivo;
import algoCraft.unidades.excepciones.LaUnidadYaSeMovioEnEsteTurno;
import algoCraft.unidades.excepciones.NoPuedeAtacar;
import algoCraft.unidades.excepciones.RangoDeMovimientoInvalido;

public class UnidadTest{
	private int rangoArmaDePrueba = 10;
	private int danioArmaDePrueba = 100;
	private Arma armaDePrueba = new Arma(danioArmaDePrueba, rangoArmaDePrueba);
	private int vidaMaxima = 10;
	private int cantidadDePoblacionQueOcupa = 1;
	private Posicion posicion = new Posicion(1, 1, true);
	private Posicion posicionEnRango = new Posicion (2, 1, true);
	//La posicion de la unidad que vaya a atacar es 1 1. 100, 100 esta fuera de rango.
	private Posicion posicionFueraDeRango = new Posicion(15, 15, true);

	
	private Unidad nuevaUnidadAtacante(){
		Jugador jugador = new Jugador("jugador1", new Base(3, 3));
		jugador.activar();
		return new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 1);
	}
	public Unidad nuevaUnidad() {
		Jugador jugador = new Jugador("jugador1", new Base(3, 3));
		jugador.activar();
		return new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, cantidadDePoblacionQueOcupa);
	}
	
	public Unidad nuevaUnidadEnRangoDeAtaque(int vida) {
		Jugador jugador = new Jugador("jugador1", new Base(3, 3));
		jugador.activar();
		return  new Unidad(jugador, vida, armaDePrueba, posicionEnRango, cantidadDePoblacionQueOcupa);
	}
	
	public Unidad nuevaUnidadFueraDeRangoDeAtaque(int vida) {
		Jugador jugador = new Jugador("jugador1", new Base(3, 3));
		jugador.activar();
		return new Unidad(jugador, vidaMaxima, armaDePrueba, posicionFueraDeRango, cantidadDePoblacionQueOcupa);
	}
	
	@Test
	public void alCrearseLaUnidadNoEstaDestruida() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = nuevaUnidad();
		assertTrue(!unidad.estaDestruido());
	}
	
	@Test
	public void cuandoSeCreaUnaUnidadEstaTieneUnRangoDeMovimiento() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = new Unidad(null, vidaMaxima, armaDePrueba, posicion, 2);
		
		assertEquals(4, unidad.getRangoMovimiento());
	}
	
	@Test
	public void cuandoSeCreaUnaUnidadEstaPuedeAtacar() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = new Unidad(null, vidaMaxima, armaDePrueba, posicion, 2);
		
		assertEquals(true, unidad.puedeAtacar());
	}
	
	@Test
	public void cuandoSeCreaUnaUnidadEstaPuedeMoverse() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = new Unidad(null, vidaMaxima, armaDePrueba, posicion, 2);
		
		assertEquals(true, unidad.puedeMoverse());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaEstaDestruido() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaEstaDestruido() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima + 1);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirEnDosAtaquesUnDanioIgualASuVidaMAximaEstaDestruido() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaLaVidaActualEs0() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaLaVidaActualEs0() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima + 20);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructor() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = nuevaUnidad();
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructorLuegoDeRecibirDanio() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(100);
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDelAtacableEsteEsDestruido() {
		Mapa.reiniciarInstanciaParaTest();
		int vidaAtacable = danioArmaDePrueba;
		Unidad atacable = nuevaUnidadEnRangoDeAtaque(vidaAtacable);
		nuevaUnidadAtacante().atacar(atacable);
		assertTrue(atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaEnRangoConMenorDanioQueLaVidaDelAtacableEsteNoEsDestruido() {
		Mapa.reiniciarInstanciaParaTest();
		int vidaAtacable = danioArmaDePrueba + 1;
		Unidad atacable = nuevaUnidadEnRangoDeAtaque(vidaAtacable);
		nuevaUnidadAtacante().atacar(atacable);
		assertTrue(!atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaConMayorDanioQueLaVidaDelAtacablePeroFueraDeRangoElAtacableNoEsDestruido() {
		Mapa.reiniciarInstanciaParaTest();
		int vidaAtacable = danioArmaDePrueba - 1;
		Unidad atacable = nuevaUnidadFueraDeRangoDeAtaque(vidaAtacable);
		try {
			nuevaUnidadAtacante().atacar(atacable);
			fail();
		} catch (AtaqueFueraDeRangoException e) {}
		
		assertTrue(!atacable.estaDestruido());
	}
	
	
	@Test
	public void siTengoUnArmaConDanio1DevuelveConGetDanio1() {
		Mapa.reiniciarInstanciaParaTest();
		int danio = 1;
		int rango = 10;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		Unidad unidad = new Unidad(null, vida, arma, posicion, 1);
		assertTrue(unidad.getDanio() == 1);
	}
	
	public Unidad crearUnidadDestruida() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = nuevaUnidad();
		armaDePrueba.atacar(unidad, rangoArmaDePrueba);
		return unidad;
	}
	
	@Test(expected = NoPuedeAtacar.class)
	public void siDisparoUnArmaYEstoyDestruidoNoPuedoDestruirAUnAtacable() {
		Mapa.reiniciarInstanciaParaTest();
		int vidaAtacable = danioArmaDePrueba;
		Unidad unidadDestruida = crearUnidadDestruida();
		Unidad atacable = nuevaUnidadEnRangoDeAtaque(vidaAtacable);
		
		unidadDestruida.atacar(atacable);
	}
	
	@Test
	public void siTengoUnArmaConDanio2DevuelveConGetDanio2() {
		Mapa.reiniciarInstanciaParaTest();
		int danio = 2;
		int rango = 10;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		Unidad unidad = new Unidad(null, vida, arma, posicion, 1);
		assertTrue(unidad.getDanio() == 2);
	}
	
	@Test
	public void  siTengoUnArmaConRango2DevuelveConGetRango2() {
		Mapa.reiniciarInstanciaParaTest();
		int danio = 10;
		int rango = 2;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		Unidad unidad = new Unidad(null, vida, arma, posicion, 1);
		assertTrue(unidad.getRango() == 2);
	}
	
	@Test
	public void  siTengoUnArmaConRango1DevuelveConGetRango1() {
		Mapa.reiniciarInstanciaParaTest();
		int danio = 10;
		int rango = 1;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		Unidad unidad = new Unidad(null, vida, arma, posicion, 1);
		assertTrue(unidad.getRango() == 1);
	}
	@Test(expected = NoPuedeAtacar.class)
	public void siUnaUnidadIntentaAtacarDosVecesSeguidasSeLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		int vidaAtacable = danioArmaDePrueba*2;
		Unidad unidadAtacante = nuevaUnidadAtacante();
		Unidad unidadAtacable = nuevaUnidadEnRangoDeAtaque(vidaAtacable);
		unidadAtacante.atacar(unidadAtacable);
		
		unidadAtacante.atacar(unidadAtacable);
	}
	
	@Test
	public void unaUnidadHaceDanioEnElSegundoDisparoSiAvanzoUnTurno() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidadAtacante = nuevaUnidadAtacante();
		int vidaAtacable = danioArmaDePrueba*2;
		Unidad unidadAtacable = nuevaUnidadEnRangoDeAtaque(vidaAtacable);
		unidadAtacante.atacar(unidadAtacable);
		unidadAtacante.avanzarTurno();
		unidadAtacante.atacar(unidadAtacable);
		assertTrue(unidadAtacable.estaDestruido());
	}
	
	@Test
	public void getPoblacionDevuelve1SiEsaFueLaPoblacionQueSeLeDioEnElConstructorALaUnidad() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = new Unidad(null, vidaMaxima, armaDePrueba, posicion, 1);
		assertEquals(unidad.getPoblacionQueOcupa(), 1);
	}
	
	@Test
	public void getPoblacionDevuelve2SiEsaFueLaPoblacionQueSeLeDioEnElConstructorALaUnidad() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = new Unidad(null, vidaMaxima, armaDePrueba, posicion, 2);
		assertEquals(unidad.getPoblacionQueOcupa(), 2);
	}
	
	@Test
	public void getPosicionDevuelveUnaQueEsIgualALaDada() {
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = new Unidad(null, vidaMaxima, armaDePrueba, posicion, 2);
		assertEquals(unidad.getPosicion(), posicion);
	}
	
	@Test
	public void siLePidoAlMapaLoQueHayEnLaPosicionDeLaUnidadLaEncuentro(){
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = new Unidad(null, vidaMaxima, armaDePrueba, posicion, 2);
		assertEquals(Mapa.getMapa().getPosicionable(posicion), unidad);
	}
	
	@Test(expected = PosicionVaciaException.class)
	public void siLaUnidadEsDestruidaNoLaEncuentroEnElMapa(){
		Mapa.reiniciarInstanciaParaTest();
		Unidad unidad = new Unidad(new Jugador("jugador1", new Base(3, 3)), vidaMaxima, armaDePrueba, posicion, 2);
		unidad.recibePuntosDeDanio(vidaMaxima + 1);
		assertTrue(unidad.estaDestruido());
		Mapa.getMapa().getPosicionable(posicion);
	}
	
	@Test
	public void siUnaUnidadSeMueveAUnaPosicionNuevaLaEncuentroAlli(){
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		Unidad unidad = new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 2);
		Posicion nuevaPosicion =  new Posicion(1, 2, unidad.esTerrestre());
		unidad.moverseA(1, 2);
		assertEquals(unidad, Mapa.getMapa().getPosicionable(nuevaPosicion));
	}
	
	@Test(expected = PosicionVaciaException.class)
	public void siUnaUnidadSeMueveAUnaPosicionNuevaNoLaEncuentroDondeEstaba(){
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		Unidad unidad = new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 2);
		unidad.moverseA(1, 2);
		assertEquals(unidad, Mapa.getMapa().getPosicionable(posicion));
	}
	
	@Test(expected = NoPuedeAtacar.class)
	public void siTratoDeAtacarConUnaUnidadDeUnJugadorInactivoLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Unidad unidad = new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 2);
		
		unidad.atacar(null);
	}
	
	@Test(expected = NoPuedeAtacar.class)
	public void siUnaUnidadTrataDeAtacarAUnAliadoLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Unidad unidad = new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 2);
		Unidad unidadAliada = new Unidad(jugador, vidaMaxima, armaDePrueba, new Posicion(5, 5, true), 2);
		
		unidad.atacar(unidadAliada);
	}
	
	@Test(expected = RangoDeMovimientoInvalido.class)
	public void siUnaUnidadTrataDeMoverseMasDelRangoPermitidoSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		Unidad unidad = new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 2);
		
		unidad.moverseA(1, 10);
	}
	
	@Test(expected = LaUnidadYaSeMovioEnEsteTurno.class)
	public void siUnaUnidadTrataDeMoverseDosVecesEnElMismoTurnoSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		Unidad unidad = new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 2);
		
		unidad.moverseA(1, 2);
		unidad.moverseA(1, 3);
	}
	
	@Test(expected = NoPuedeAtacar.class)
	public void siUnaUnidadTrataDeAtacarAOtraDelMismoJugadorSeLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		Unidad unidad1 = new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 2);
		Unidad unidad2 = new Unidad(jugador, vidaMaxima, armaDePrueba, new Posicion(2, 2, true), 2);
		
		unidad1.atacar(unidad2);
	}
	
	@Test(expected = NoPuedeAtacar.class)
	public void siUnaUnidadDeUnJugadorNoActivoTrataAtacarSeLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Unidad unidad1 = new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 2);
		Unidad unidad2 = new Unidad(null, vidaMaxima, armaDePrueba, new Posicion(2, 2, true), 2);
		
		unidad1.atacar(unidad2);
	}
	
	@Test(expected = LaUnidadPerteneceAUnJugadorInactivo.class)
	public void siUnaUnidadDeUnJugadorNoActivoTrataMoverseSeLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Unidad unidad1 = new Unidad(jugador, vidaMaxima, armaDePrueba, posicion, 2);
		
		unidad1.moverseA(1, 1);
	}
}
