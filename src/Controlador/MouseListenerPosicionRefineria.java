package Controlador;

import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionRefineria extends MouseListenerPosicion{

	public MouseListenerPosicionRefineria(PanelCentral panelCentral, int x, int y){
		super(panelCentral, x, y);
		this.panelCentral = panelCentral;
		this.x = x;
		this.y = y;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON1)
			panelCentral.seleccionadaRefineriaEn(x, y);
	}
}
