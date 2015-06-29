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
	
	PanelBotonera(Juego juego){
		layout = new CardLayout();
		this.setLayout(layout);
		this.setBackground(Color.black);
		this.setForeground(Color.white);
		panelDeConstruccionDeEdificiosDeProduccion = new PanelDeConstruccionDeEdificiosDeProduccion();
		this.add(panelDeConstruccionDeEdificiosDeProduccion, identificadorPanelEdificioProduccion);
		panelDeConstruccionDeRecolectorDeMinerales = new PanelDeConstruccionDeRecolectorDeMinerales();
		this.add(panelDeConstruccionDeRecolectorDeMinerales, identificadorPanelRecolectorMineral);
		panelDeConstruccionDeRecolectorDeGas = new PanelDeConstruccionDeRecolectorDeGas();
		this.add(panelDeConstruccionDeRecolectorDeGas, identificadorPanelRecolectorGas);
		this.setVisible(false);
	}
	
	public void adecuarseParaFabricarEdificiosDeProduccion(){
		this.setVisible(true);
	    layout.show(this, identificadorPanelEdificioProduccion);
	}


	public void adecuarseParaFabricarRecolectorDeMineral() {
		this.setVisible(true);
	    layout.show(this, identificadorPanelRecolectorMineral);
	}

	public void adecuarseParaFabricarRecolectorDeGas() {
		this.setVisible(true);
	    layout.show(this, identificadorPanelRecolectorGas);
	}
}
