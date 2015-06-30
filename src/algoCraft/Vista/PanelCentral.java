package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import algoCraft.construcciones.Edificio;
import algoCraft.juego.Juego;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Unidad;

public class PanelCentral extends JPanel{
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
	}
	
	public PanelBotonera getBotonera(){
		return panelBotonera;
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
		if(existeUnPanelDeSeleccion){
			this.remove(panelDeSeleccion);
		}
		//Esto es para evitar que te muestra la opcion de crear marines si el edificio esta en construccion
		// Podria sacarse si el casteo en el metodo booleano no es agradable
		if(estaEnConstruccion(posicionX, posicionY)){
			panelBotonera.setVisible(false);
		}else{
			this.setVisible(true);
			panelBotonera.setVisible(true);
			existeUnPanelDeSeleccion = false;
			panelBotonera.adecuarseParaFabricarDesdeBarraca(posicionX, posicionY);
		}
		this.revalidate();
		this.repaint();
	}

	private boolean estaEnConstruccion(int posicionX, int posicionY) {
		Posicion posicion = new Posicion(posicionX, posicionY, true);//Es terrestre, entonces pongo true
		Edificio edificio = (Edificio) Mapa.getMapa().getUnidad(posicion);
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

	public void seleccionadaFabricaEn(int x, int y) {
		hayUnaUnidadSeleccionada = false;
		if(existeUnPanelDeSeleccion){
			this.remove(panelDeSeleccion);
		}
		//Esto es para evitar que te muestra la opcion de crear marines si el edificio esta en construccion
		// Podria sacarse si el casteo en el metodo booleano no es agradable
		if(estaEnConstruccion(x, y)){
			panelBotonera.setVisible(false);
		}else{
			this.setVisible(true);
			panelBotonera.setVisible(true);
			existeUnPanelDeSeleccion = false;
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
		
	}
	
	private void moverUnidadSeleccionadaA(int x, int y) {
		Unidad unidad = (Unidad) Mapa.getMapa().getUnidad(posicionUnidadSeleccionada);
		unidad.moverseA(x, y);
	}

	public void seleccionadaUnidadEn(int x, int y) {
		if(existeUnPanelDeSeleccion){
			this.remove(panelDeSeleccion);
		}
		hayUnaUnidadSeleccionada = true;
		panelBotonera.setVisible(false);
		//Las unidades son terrestres por ahora, true marca eso
		posicionUnidadSeleccionada = new Posicion(x,y,true);
	}
}
