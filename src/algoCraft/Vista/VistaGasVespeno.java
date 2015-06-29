package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionGas;

public class VistaGasVespeno extends PosicionVista {

	private static final long serialVersionUID = 1L;

	VistaGasVespeno(PanelCentral panelCentral,int x, int y){
		super(Color.green);
		addMouseListener(new MouseListenerPosicionGas(panelCentral, x, y));
	}
}
