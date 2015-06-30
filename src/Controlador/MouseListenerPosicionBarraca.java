package Controlador;
import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionBarraca extends MouseListenerAtacable{
	private PanelCentral panelCentral;
	private int y;
	private int x;
			
		public MouseListenerPosicionBarraca(PanelCentral panelCentral, int x, int y){
			super(panelCentral, x, y);
			this.panelCentral = panelCentral;
			this.x = x;
			this.y = y;
		}
			
		public void mousePressed(MouseEvent mouseEvent) {
			super.mousePressed(mouseEvent);
			if(mouseEvent.getButton() == MouseEvent.BUTTON1)
				panelCentral.seleccionadaBarracaEn(x, y);
		}
}
