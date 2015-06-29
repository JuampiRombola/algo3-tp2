package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionesVacias;

public class VistaPosicionVacia extends PosicionVista{
	
	private static final long serialVersionUID = 1L;
	public VistaPosicionVacia(PanelCentral panelCentral,int x, int y){
		super(Color.LIGHT_GRAY);
		addMouseListener(new MouseListenerPosicionesVacias(panelCentral, x ,y));
	}
} 
