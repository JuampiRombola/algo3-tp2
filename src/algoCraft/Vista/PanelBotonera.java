package algoCraft.Vista;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class PanelBotonera extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private PanelDeConstruccion panelDeConstruccionDeEdificiosDeProduccion;
	private PanelDeConstruccion panelDeConstruccionDeRecolectorDeMinerales;

	private PanelDeConstruccionDeRecolectorDeGas panelDeConstruccionDeRecolectorDeGas;

	private String identificadorPanelEdificioProduccion = "PRODUCCION";
	private String identificadorPanelRecolectorMineral= "MINERAL";
	private String identificadorPanelRecolectorGas = "GAS";
	private CardLayout layout;
	int posicionSeleccionadaEnX;
	int posicionSeleccionadaEnY;
	
	PanelBotonera(Juego juego){
		layout = new CardLayout();
		this.setLayout(layout);
		this.setBackground(Color.black);
		this.setForeground(Color.white);
		panelDeConstruccionDeEdificiosDeProduccion = new PanelDeConstruccionDeEdificiosDeProduccion(juego);
		this.add(panelDeConstruccionDeEdificiosDeProduccion, identificadorPanelEdificioProduccion);
		panelDeConstruccionDeRecolectorDeMinerales = new PanelDeConstruccionDeRecolectorDeMinerales(juego);
		this.add(panelDeConstruccionDeRecolectorDeMinerales, identificadorPanelRecolectorMineral);
		panelDeConstruccionDeRecolectorDeGas = new PanelDeConstruccionDeRecolectorDeGas(juego);
		this.add(panelDeConstruccionDeRecolectorDeGas, identificadorPanelRecolectorGas);
		this.setVisible(false);
	}
	
	public void adecuarseParaFabricarEdificiosDeProduccion(int x, int y){
		this.setVisible(true);
	    layout.show(this, identificadorPanelEdificioProduccion);
	}


	public void adecuarseParaFabricarRecolectorDeMineral(int x, int y) {
		this.setVisible(true);
	    layout.show(this, identificadorPanelRecolectorMineral);
	    posicionSeleccionadaEnX = x;
	    posicionSeleccionadaEnY = y;
	    panelDeConstruccionDeRecolectorDeMinerales.prepararEventoDeProduccion(x, y);
	}

	public void adecuarseParaFabricarRecolectorDeGas(int x, int y) {
		this.setVisible(true);
	    layout.show(this, identificadorPanelRecolectorGas);
	    posicionSeleccionadaEnX = x;
	    posicionSeleccionadaEnY = y;
	    panelDeConstruccionDeRecolectorDeGas.prepararEventoDeProduccion(x, y);
	}
}
