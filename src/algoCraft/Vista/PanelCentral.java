package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import algoCraft.construcciones.Barraca;
import algoCraft.juego.Juego;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class PanelCentral extends JPanel{
	private static final long serialVersionUID = 1L;
	private PanelBotonera panelBotonera;
	private JPanel panelDeSeleccion;
	boolean existeUnPanelDeSeleccion;
	
	public PanelCentral(Juego juego){
		this.setBackground(Color.black);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.panelBotonera = new PanelBotonera(juego);
		this.add(panelBotonera);
		existeUnPanelDeSeleccion = false;
	}
	
	public PanelBotonera getBotonera(){
		return panelBotonera;
	}
	
	public void seleccionadoGasVespenoEn(int posicionX, int posicionY){
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
		if(existeUnPanelDeSeleccion){
			this.remove(panelDeSeleccion);
		}
		//Esto es para evitar que te muestra la opcion de crear marines si el edificio esta en construccion
		// Podria sacarse si el casteo en el metodo booleano no es agradable
		if(laBarracaEstaEnConstruccion(posicionX, posicionY)){
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

	private boolean laBarracaEstaEnConstruccion(int posicionX, int posicionY) {
		Posicion posicion = new Posicion(posicionX, posicionY, true);//Es terrestre, entonces pongo true
		Barraca barraca = (Barraca) Mapa.getMapa().getUnidad(posicion);
		return barraca.estaEnConstruccion();
	}

	public void seleccionadoCentroDeMineralEn(int x, int y) {
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
}
