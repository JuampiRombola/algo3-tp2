package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerUnidad;

public class VistaGoliath extends PosicionVista {
	private static final long serialVersionUID = 1L;
	
	public VistaGoliath(PanelCentral panelCentral,int x, int y){
		super(new Color(0, 88, 121));
		addMouseListener(new MouseListenerUnidad(panelCentral, x, y));
	}
}