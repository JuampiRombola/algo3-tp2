package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerUnidad;

public class VistaMarine extends PosicionVista{
	private static final long serialVersionUID = 1L;
	
	public VistaMarine(PanelCentral panelCentral,int x, int y){
		super(new Color(0, 155, 211));
		addMouseListener(new MouseListenerUnidad(panelCentral, x, y));
	}
}
