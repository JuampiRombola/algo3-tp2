package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelBotonera;

public class MouseListenerPosicionGas extends MouseAdapter{
		private PanelBotonera botonera;
			
			public MouseListenerPosicionGas(PanelBotonera botonera){
				super();
				this.botonera = botonera;
			}
			
			public void mousePressed(MouseEvent mouseEvent) {
				botonera.adecuarseParaFabricarRecolectorDeGas();;
			}
}
