package Controlador;

import java.awt.event.MouseAdapter;
import algoCraft.Vista.PanelCentral;

public class MouseListenerPosicion extends MouseAdapter{
	
	protected PanelCentral panelCentral;
	protected int y;
	protected int x;
	
	public MouseListenerPosicion(PanelCentral panelCentral, int x, int y){
		super();
		this.panelCentral = panelCentral;
		this.x = x;
		this.y = y;
	}
}
