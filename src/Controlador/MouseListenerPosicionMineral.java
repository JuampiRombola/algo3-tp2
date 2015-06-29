package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelBotonera;

public class MouseListenerPosicionMineral extends MouseAdapter{
private PanelBotonera botonera;
	
	public MouseListenerPosicionMineral(PanelBotonera botonera){
		super();
		this.botonera = botonera;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		botonera.adecuarseParaFabricarRecolectorDeMineral();;
	}
}
