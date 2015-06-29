package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class PanelCentral extends JPanel{
	private static final long serialVersionUID = 1L;
	private PanelBotonera panelBotonera;
	
	public PanelCentral(Juego juego){
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.panelBotonera = new PanelBotonera(juego);
		this.add(panelBotonera, BorderLayout.NORTH);
	}
	
	public PanelBotonera getBotonera(){
		return panelBotonera;
	}
	
	public void seleccionadoGasVespenoEn(int posicionX, int posicionY){
		panelBotonera.adecuarseParaFabricarRecolectorDeGas(posicionX, posicionY);
	}
	
	public void seleccionadoMineralEn(int posicionX, int posicionY){
		panelBotonera.adecuarseParaFabricarRecolectorDeMineral(posicionX, posicionY);
	}
	
	public void seleccionadaPosicionVaciaEn(int posicionX, int posicionY){
		panelBotonera.adecuarseParaFabricarEdificiosDeProduccion(posicionX, posicionY);
	}
}
