package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionesSinBotones extends MouseAdapter {
	private PanelCentral panelCentral;
	
	public MouseListenerPosicionesSinBotones(PanelCentral panelCentral){
		super();
		this.panelCentral= panelCentral;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		panelCentral.setVisible(false);
	}
}
