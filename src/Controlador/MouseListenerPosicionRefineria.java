package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionRefineria extends MouseAdapter{
	private PanelCentral panelCentral;
	private int x;
	private int y;
	
	public MouseListenerPosicionRefineria(PanelCentral panelCentral, int x, int y){
		super();
		this.panelCentral = panelCentral;
		this.x = x;
		this.y = y;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		panelCentral.seleccionadaRefineriaEn(x, y);
	}
}
