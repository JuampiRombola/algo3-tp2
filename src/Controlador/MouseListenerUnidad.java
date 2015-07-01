package Controlador;

import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelCentral;

public class MouseListenerUnidad extends MouseListenerAtacable{

	public MouseListenerUnidad(PanelCentral panelCentral, int x, int y) {
		super(panelCentral, x, y);
	}
	public void mousePressed(MouseEvent mouseEvent) {
		super.mousePressed(mouseEvent);
		if(mouseEvent.getButton() == MouseEvent.BUTTON1)
			panelCentral.seleccionadaUnidadEn(x, y);
	}
}
