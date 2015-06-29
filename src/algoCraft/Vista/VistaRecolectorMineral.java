package algoCraft.Vista;

import java.awt.Color;

import Controlador.MouseListenerPosicionesSinBotones;

public class VistaRecolectorMineral extends PosicionVista{
private static final long serialVersionUID = 1L;
	
	public VistaRecolectorMineral(PanelBotonera botonera,int x, int y){
		super(Color.blue);
		  addMouseListener(new MouseListenerPosicionesSinBotones(botonera));
	}
}
