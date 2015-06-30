package Controlador;

import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelCentral;

public class MouseListenerUnidad extends MouseListenerPosicion{

	public MouseListenerUnidad(PanelCentral panelCentral, int x, int y) {
		super(panelCentral, x, y);
	}
	public void mousePressed(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON1)
			panelCentral.seleccionadaUnidadEn(x, y);
	}
}
