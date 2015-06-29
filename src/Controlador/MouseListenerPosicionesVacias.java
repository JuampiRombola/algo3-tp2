package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionesVacias extends MouseAdapter{
	private int x;
	private int y;
	private PanelCentral panelCentral;
	
	
	public MouseListenerPosicionesVacias(PanelCentral panelCentral, int x, int y){
		super();
		this.x = x;
		this.y = y;
		this.panelCentral = panelCentral;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		panelCentral.seleccionadaPosicionVaciaEn(x, y);
	}
}
