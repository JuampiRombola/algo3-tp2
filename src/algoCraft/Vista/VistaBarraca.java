package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionBarraca;

public class VistaBarraca extends PosicionVista{

	private static final long serialVersionUID = 1L;

	VistaBarraca(PanelCentral panelCentral,int x, int y){
		super(Color.yellow);
		addMouseListener(new MouseListenerPosicionBarraca(panelCentral, x, y));
	}
}