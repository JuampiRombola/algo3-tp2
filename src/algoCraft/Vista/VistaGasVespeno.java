package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionGas;

public class VistaGasVespeno extends PosicionVista {

	private static final long serialVersionUID = 1L;

	VistaGasVespeno(PanelBotonera botonera){
		super(Color.green);
		  addMouseListener(new MouseListenerPosicionGas(botonera));
	}
}
