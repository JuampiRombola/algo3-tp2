package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionBase;

public class VistaBase extends PosicionVista{

	private static final long serialVersionUID = 1L;

	VistaBase(PanelCentral panelCentral,int x, int y){
		super(new Color(150,25,25));
		addMouseListener(new MouseListenerPosicionBase(panelCentral, x, y));
	}
}
