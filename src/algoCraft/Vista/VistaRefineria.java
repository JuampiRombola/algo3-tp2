package algoCraft.Vista;

import java.awt.Color;
import Controlador.MouseListenerPosicionesSinBotones;

public class VistaRefineria extends PosicionVista {
	
	VistaRefineria(PanelBotonera botonera) {
		super(new Color (0, 80, 0));
		addMouseListener(new MouseListenerPosicionesSinBotones(botonera));
	}

	
	private static final long serialVersionUID = 1L;

}
