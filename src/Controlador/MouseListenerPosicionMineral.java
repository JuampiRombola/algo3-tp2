package Controlador;

import java.awt.event.MouseEvent;
import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionMineral extends MouseListenerPosicion{
	
	public MouseListenerPosicionMineral(PanelCentral panelCentral, int x, int y){
		super(panelCentral, x, y);
		this.panelCentral = panelCentral;
		this.x = x;
		this.y = y;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON1)
			panelCentral.seleccionadoMineralEn(x, y);
	}
}
