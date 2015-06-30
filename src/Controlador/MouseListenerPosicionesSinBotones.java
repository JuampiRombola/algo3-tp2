package Controlador;

import java.awt.event.MouseEvent;
import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionesSinBotones extends MouseListenerPosicion {
	
	public MouseListenerPosicionesSinBotones(PanelCentral panelCentral, int x, int y){
		super(panelCentral, x, y);
		this.panelCentral= panelCentral;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON1)
			panelCentral.setVisible(false);
	}
}
