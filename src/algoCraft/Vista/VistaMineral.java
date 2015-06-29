package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionMineral;

public class VistaMineral extends PosicionVista{

	
	private static final long serialVersionUID = 1L;
	
	public VistaMineral(PanelCentral panelCentral,int x, int y){
		super(Color.cyan);
		  addMouseListener(new MouseListenerPosicionMineral(panelCentral, x, y));
	}
}
