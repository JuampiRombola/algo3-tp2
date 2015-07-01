package algoCraft.Vista;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import algoCraft.Atacable;
import algoCraft.Musica;
import algoCraft.construcciones.Edificio;
import algoCraft.juego.Juego;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;
import algoCraft.unidades.excepciones.LaUnidadYaSeMovioEnEsteTurno;
import algoCraft.unidades.excepciones.RangoDeMovimientoInvalido;

public class PanelCentral extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	private PanelBotonera panelBotonera;
	private JPanel panelDeSeleccion;
	private boolean existeUnPanelDeSeleccion;
	private boolean hayUnaUnidadSeleccionada;
	private Posicion posicionUnidadSeleccionada;
	
	public PanelCentral(Juego juego){
		this.setBackground(Color.black);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.panelBotonera = new PanelBotonera(juego);
		this.add(panelBotonera);
		existeUnPanelDeSeleccion = false;
		hayUnaUnidadSeleccionada = false;
		juego.addObserver(this);
		setBorder(new LineBorder(Color.GRAY));
	}
	
	public void seleccionadoGasVespenoEn(int posicionX, int posicionY){
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		this.setVisible(true);
		panelBotonera.setVisible(true);
		this.panelDeSeleccion = new PanelDeSeleccionDeGasVespeno(posicionX, posicionY);
		panelBotonera.adecuarseParaFabricarRecolectorDeGas(posicionX, posicionY);
		this.add(panelDeSeleccion);
		existeUnPanelDeSeleccion = true;
		this.revalidate();
		this.repaint();
	}
	
	public void seleccionadoMineralEn(int posicionX, int posicionY){
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		this.setVisible(true);
		panelBotonera.setVisible(true);
		this.panelDeSeleccion = new PanelDeSeleccionDeMineral(posicionX, posicionY);
		this.add(panelDeSeleccion);
		panelBotonera.adecuarseParaFabricarRecolectorDeMineral(posicionX, posicionY);
		existeUnPanelDeSeleccion = true;
		this.revalidate();
		this.repaint();
	}
	
	public void seleccionadaPosicionVaciaEn(int posicionX, int posicionY){
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		this.setVisible(true);
		panelBotonera.setVisible(true);
		panelBotonera.adecuarseParaFabricarEdificiosDeProduccion(posicionX, posicionY);
		existeUnPanelDeSeleccion = false;
		this.revalidate();
		this.repaint();
	}
	
	public void seleccionadaRefineriaEn(int posicionX, int posicionY){
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		this.setVisible(true);
		panelBotonera.setVisible(false);
		existeUnPanelDeSeleccion = true;
		this.panelDeSeleccion = new PanelDeSeleccionDeRefineria(posicionX, posicionY);
		this.add(panelDeSeleccion);
		this.revalidate();
		this.repaint();
	}

	public void seleccionadaBarracaEn(int posicionX, int posicionY) {
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		//Esto es para evitar que te muestra la opcion de crear marines si el edificio esta en construccion
		// Podria sacarse si el casteo en el metodo booleano no es agradable
		existeUnPanelDeSeleccion = true;
		this.panelDeSeleccion = new PanelDeSeleccionDeBarraca(posicionX, posicionY);
		this.add(panelDeSeleccion);
		if(estaEnConstruccion(posicionX, posicionY)){
			panelBotonera.setVisible(false);
		}else{
			this.setVisible(true);
			panelBotonera.setVisible(true);
			panelBotonera.adecuarseParaFabricarDesdeBarraca(posicionX, posicionY);
		}
		this.revalidate();
		this.repaint();
	}

	private boolean estaEnConstruccion(int posicionX, int posicionY) {
		Posicion posicion = new Posicion(posicionX, posicionY, true);//Es terrestre, entonces pongo true
		Edificio edificio = (Edificio) Mapa.getMapa().getPosicionable(posicion);
		return edificio.estaEnConstruccion();
	}

	public void seleccionadoCentroDeMineralEn(int x, int y) {
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		this.setVisible(true);
		panelBotonera.setVisible(false);
		existeUnPanelDeSeleccion = true;
		this.panelDeSeleccion = new PanelDeSeleccionDeCentroDeMineral(x, y);
		this.add(panelDeSeleccion);
		this.revalidate();
		this.repaint();
		
	}
	
	public void seleccionadaBaseEn(int x, int y) {
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		this.setVisible(true);
		panelBotonera.setVisible(false);
		existeUnPanelDeSeleccion = true;
		this.panelDeSeleccion = new PanelDeSeleccionDeBase(x, y);
		this.add(panelDeSeleccion);
		this.revalidate();
		this.repaint();
	}
	
	public void seleccionadoDepositoDeSuministrosEn(int x, int y) {
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		this.setVisible(true);
		panelBotonera.setVisible(false);
		existeUnPanelDeSeleccion = true;
		this.panelDeSeleccion = new PanelDeSeleccionDeDepositoDeSuministros(x, y);
		this.add(panelDeSeleccion);
		this.revalidate();
		this.repaint();
	}

	public void seleccionadaFabricaEn(int x, int y) {
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		//Esto es para evitar que te muestra la opcion de crear marines si el edificio esta en construccion
		// Podria sacarse si el casteo en el metodo booleano no es agradable
		existeUnPanelDeSeleccion = true;
		this.panelDeSeleccion = new PanelDeSeleccionDeFabrica(x, y);
		this.add(panelDeSeleccion);
		if(estaEnConstruccion(x, y)){
			panelBotonera.setVisible(false);
		}else{
			this.setVisible(true);
			panelBotonera.setVisible(true);
			panelBotonera.adecuarseParaFabricarDesdeFabrica(x, y);
		}
		this.revalidate();
		this.repaint();
	}
	
	public void clickIzquierdoEnPosicionVacia(int x, int y){
		if(hayUnaUnidadSeleccionada)
			moverUnidadSeleccionadaA(x,y);
	}

	public void clickIzquierdoSobreAtacable(int x, int y){
		if(hayUnaUnidadSeleccionada){
			Unidad atacante = obtenerUnidadSeleccionada();
			Atacable atacable = obtenerAtacableEn(x, y);
			atacante.atacar(atacable);
			Musica.reproducir("recursos/Musica/ataque.wav");
		}
	}

	private void moverUnidadSeleccionadaA(int x, int y) {
		Unidad unidad = obtenerUnidadSeleccionada() ;
		try{
			unidad.moverseA(x, y);
			if (unidad.getClass() == Marine.class)
				Musica.reproducir("Recursos/Musica/movMarine.wav");
			else
				Musica.reproducir("Recursos/Musica/movGoliath.wav");
		}catch(RangoDeMovimientoInvalido e){
			 JOptionPane.showMessageDialog(null, "La unidad no se puede mover tan lejos", "Error",
                     JOptionPane.ERROR_MESSAGE);
		}catch(LaUnidadYaSeMovioEnEsteTurno e){
			 JOptionPane.showMessageDialog(null, "La unidad ya se movio en este turno", "Error",
                     JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private Unidad obtenerUnidadSeleccionada(){
		return (Unidad) Mapa.getMapa().getPosicionable(posicionUnidadSeleccionada);
	}
	
	private Atacable obtenerAtacableEn(int x, int y){
		return (Atacable) Mapa.getMapa().getPosicionable(new Posicion(x, y, true));
	}
	
	public void seleccionadaUnidadEn(int x, int y) {
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		hayUnaUnidadSeleccionada = true;
		panelBotonera.setVisible(false);
		existeUnPanelDeSeleccion = true;
		this.panelDeSeleccion = new PanelDeSeleccionDeUnidad(x, y);
		this.add(panelDeSeleccion);
		this.revalidate();
		this.repaint();
		//Las unidades son terrestres por ahora, true marca eso
		posicionUnidadSeleccionada = new Posicion(x, y, true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.limpiarElementos();
	}
	
	private void limpiarElementos(){
		if(existeUnPanelDeSeleccion)
			this.remove(panelDeSeleccion);
		hayUnaUnidadSeleccionada = false;
		panelBotonera.setVisible(false);
		this.revalidate();
		this.repaint();
	}
}
