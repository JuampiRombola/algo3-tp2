package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionGas extends MouseAdapter{
	private PanelCentral panelCentral;
	private int y;
	private int x;
			
		public MouseListenerPosicionGas(PanelCentral panelCentral, int x, int y){
			super();
			this.panelCentral = panelCentral;
			this.x = x;
			this.y = y;
		}
			
		public void mousePressed(MouseEvent mouseEvent) {
			panelCentral.seleccionadoGasVespenoEn(x, y);
		}
}
