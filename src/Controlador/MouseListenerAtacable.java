package Controlador;

import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelCentral;

public class MouseListenerAtacable extends MouseListenerPosicion{

	public MouseListenerAtacable(PanelCentral panelCentral, int x, int y) {
		super(panelCentral, x, y);
	}

	public void mousePressed(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON3)
			panelCentral.clickIzquierdoSobreAtacable(x, y);
	}
}
