package algoCraft.Vista;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

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
		if(existeUnPanelDeSeleccion){
			this.remove(panelDeSeleccion);
		}
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
		if(existeUnPanelDeSeleccion){
			this.remove(panelDeSeleccion);
		}
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
		if(existeUnPanelDeSeleccion){
			this.remove(panelDeSeleccion);
		}
		this.setVisible(true);
		panelBotonera.setVisible(true);
		panelBotonera.adecuarseParaFabricarEdificiosDeProduccion(posicionX, posicionY);
		existeUnPanelDeSeleccion = false;
		this.revalidate();
		this.repaint();
	}
	
	public void seleccionadaRefineriaEn(int posicionX, int posicionY){
		if(existeUnPanelDeSeleccion){
			this.remove(panelDeSeleccion);
		}
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
		this.setVisible(true);
		panelDeSeleccion.setVisible(false);
		panelBotonera.setVisible(true);
		existeUnPanelDeSeleccion = false;
		panelBotonera.adecuarseParaFabricarDesdeBarraca(posicionX, posicionY);
		this.revalidate();
		this.repaint();
	}
}
