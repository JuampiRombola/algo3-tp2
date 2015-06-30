package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionDepositoSuministros;

public class VistaDepositoDeSuministros  extends PosicionVista{

	private static final long serialVersionUID = 1L;

	VistaDepositoDeSuministros(PanelCentral panelCentral,int x, int y){
		super(new Color(175,180,90));
		addMouseListener(new MouseListenerPosicionDepositoSuministros(panelCentral, x, y));
	}
}
