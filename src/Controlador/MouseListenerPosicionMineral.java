package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicionMineral extends MouseAdapter{
	private PanelCentral panelCentral;
	private int x;
	private int y;
	
	public MouseListenerPosicionMineral(PanelCentral panelCentral, int x, int y){
		super();
		this.panelCentral = panelCentral;
		this.x = x;
		this.y = y;
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		panelCentral.setVisible(true);
		panelCentral.seleccionadoMineralEn(x, y);
	}
}
