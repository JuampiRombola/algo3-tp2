package Controlador;

import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionFabrica extends MouseListenerPosicion{

		public MouseListenerPosicionFabrica(PanelCentral panelCentral, int x, int y){
			super(panelCentral, x, y);
			this.panelCentral = panelCentral;
			this.x = x;
			this.y = y;
		}
			
		public void mousePressed(MouseEvent mouseEvent) {
			if(mouseEvent.getButton() == MouseEvent.BUTTON1)
				panelCentral.seleccionadaFabricaEn(x, y);
		}
}
