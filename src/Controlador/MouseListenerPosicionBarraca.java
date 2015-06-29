package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionBarraca extends MouseAdapter{
	private PanelCentral panelCentral;
	private int y;
	private int x;
			
		public MouseListenerPosicionBarraca(PanelCentral panelCentral, int x, int y){
			super();
			this.panelCentral = panelCentral;
			this.x = x;
			this.y = y;
		}
			
		public void mousePressed(MouseEvent mouseEvent) {
			panelCentral.seleccionadaBarracaEn(x, y);
		}
}
