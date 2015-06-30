package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionCentroMineral extends MouseAdapter{
	private PanelCentral panelCentral;
	private int x;
	private int y;
	
	public MouseListenerPosicionCentroMineral(PanelCentral panelCentral, int x, int y){
		super();
		this.panelCentral = panelCentral;
		this.x = x;
		this.y = y;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		panelCentral.seleccionadoCentroDeMineralEn(x, y);
	}
}
