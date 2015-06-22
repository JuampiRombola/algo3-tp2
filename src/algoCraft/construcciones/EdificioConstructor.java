package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;

public abstract class EdificioConstructor extends Edificio {
	protected boolean seTerminoDeCrearLaUnidad;
	protected int cantidadDeUnidadesAProducir;
	protected int tiempoDeConstruccion = 0;
	protected int turnosParaProducirUnidad;
	
	
	public EdificioConstructor(int vida, Posicion posicion, int turnosEnConstruirse, int turnosParaProducirUnidad) {
		super(vida, posicion, turnosEnConstruirse);
		this.seTerminoDeCrearLaUnidad = false;
		this.cantidadDeUnidadesAProducir = 0;
		this.turnosParaProducirUnidad = turnosParaProducirUnidad;
	}

	private boolean estaCreandoUnaUnidad() {
		return cantidadDeUnidadesAProducir > 0;
	}

	private boolean pasoElTiempoParaQueLaUnidadSeProduzca(){
		return (contadorDeTurnos >= turnosParaProducirUnidad);
	}
	
	@Override
	public void avanzarTurno(Jugador jugador) {
		//El metodo que usa esa variable es inutil, lo dejo porque me interesan las pruebas.
		super.avanzarTurno(jugador);
		if(estaCreandoUnaUnidad() && pasoElTiempoParaQueLaUnidadSeProduzca()){
			crearLaUnidad(jugador);
			this.contadorDeTurnos = 0;
			this.cantidadDeUnidadesAProducir--;
			this.seTerminoDeCrearLaUnidad = true;
		}
	}

	protected abstract void crearLaUnidad(Jugador jugador);
			/*Posicion posicionSalida = new Posicion(this.posicion.getX(), this.posicion.getY(), unidad.esTerrestre());
			Posicion posicionLibre = Mapa.getMapa().getPosicionVaciaCercana(posicionSalida);
			unidad.setPosicion(posicionLibre.getX(), posicionLibre.getY());
			Mapa.getMapa().agregarUnidad(unidad);
			jugador.agregarUnidad(unidad);
			jugador.sumarPoblacion(unidad.getPoblacionQueOcupa());*/
	
	
	//Metodo inutil, por ahora lo quiero por las pruebas
	public boolean seTerminoDeCrearLaUnidad() {
		return this.seTerminoDeCrearLaUnidad;
	}
}

