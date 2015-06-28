package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionesVacias;

public class VistaPosicionVacia extends PosicionVista{
	
	private static final long serialVersionUID = 1L;
	public VistaPosicionVacia(PanelBotonera botonera){
		super(Color.LIGHT_GRAY);
		  addMouseListener(new MouseListenerPosicionesVacias(botonera));
	}
} 
