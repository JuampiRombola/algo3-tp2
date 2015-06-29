package algoCraft.Vista;

import java.awt.Color;
import Controlador.MouseListenerPosicionesSinBotones;

public class VistaRefineria extends PosicionVista {
	
	VistaRefineria(PanelCentral panelCentral) {
		super(new Color (0, 80, 0));
		addMouseListener(new MouseListenerPosicionesSinBotones(panelCentral));
	}

	
	private static final long serialVersionUID = 1L;

}
