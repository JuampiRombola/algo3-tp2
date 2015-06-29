package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelBotonera;

public class MouseListenerPosicionMineral extends MouseAdapter{
private PanelBotonera botonera;
private int x;
private int y;
	
	public MouseListenerPosicionMineral(PanelBotonera botonera, int x, int y){
		super();
		this.botonera = botonera;
		this.x = x;
		this.y = y;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		botonera.adecuarseParaFabricarRecolectorDeMineral(x,y);;
	}
}
