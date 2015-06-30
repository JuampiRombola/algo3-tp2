package Controlador;

import java.awt.event.MouseEvent;
import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionGas extends MouseListenerPosicion{
			
		public MouseListenerPosicionGas(PanelCentral panelCentral, int x, int y){
			super(panelCentral, x, y);
			this.panelCentral = panelCentral;
			this.x = x;
			this.y = y;
		}
			
		public void mousePressed(MouseEvent mouseEvent) {
			if(mouseEvent.getButton() == MouseEvent.BUTTON1)
				panelCentral.seleccionadoGasVespenoEn(x, y);
		}
}
