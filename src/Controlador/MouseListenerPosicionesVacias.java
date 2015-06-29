package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import algoCraft.Vista.PanelBotonera;

public class MouseListenerPosicionesVacias extends MouseAdapter{
	private PanelBotonera botonera;
	private int x;
	private int y;
	
	
	public MouseListenerPosicionesVacias(PanelBotonera botonera, int x, int y){
		super();
		this.x = x;
		this.y = y;
		this.botonera = botonera;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		botonera.adecuarseParaFabricarEdificiosDeProduccion(x,y);;
	}
}
