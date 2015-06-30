package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionCentroMineral;

public class VistaRecolectorMineral extends PosicionVista{
	
	private static final long serialVersionUID = 1L;
	
	public VistaRecolectorMineral(PanelCentral panelCentral,int x, int y){
		super(Color.blue);
		addMouseListener(new MouseListenerPosicionCentroMineral(panelCentral, x, y));
	}
}
