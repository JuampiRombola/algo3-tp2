package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionRefineria;

public class VistaRefineria extends PosicionVista {
	
	private static final long serialVersionUID = 1L;
	
	VistaRefineria(PanelCentral panelCentral, int x, int y) {
		super(new Color (0, 80, 0));
		addMouseListener(new MouseListenerPosicionRefineria(panelCentral, x, y));
	}

}
