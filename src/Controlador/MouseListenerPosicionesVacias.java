package Controlador;

import java.awt.event.MouseEvent;
import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionesVacias extends MouseListenerPosicion{

	public MouseListenerPosicionesVacias(PanelCentral panelCentral, int x, int y){
		super(panelCentral, x, y);
		this.x = x;
		this.y = y;
		this.panelCentral = panelCentral;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON1)
			panelCentral.seleccionadaPosicionVaciaEn(x, y);
		if(mouseEvent.getButton() == MouseEvent.BUTTON3)
			panelCentral.clickIzquierdoEnPosicionVacia(x, y);
	}
}
