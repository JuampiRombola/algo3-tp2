package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionFabrica;

public class VistaFabrica extends PosicionVista {
	private static final long serialVersionUID = 1L;

	VistaFabrica(PanelCentral panelCentral,int x, int y){
		super(Color.orange);
		addMouseListener(new MouseListenerPosicionFabrica(panelCentral, x, y));
	}
}
