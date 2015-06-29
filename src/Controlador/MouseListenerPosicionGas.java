package Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import algoCraft.Vista.PanelBotonera;

public class MouseListenerPosicionGas extends MouseAdapter{
		private PanelBotonera botonera;
		private int y;
		private int x;
			
			public MouseListenerPosicionGas(PanelBotonera botonera, int x, int y){
				super();
				this.botonera = botonera;
				this.x = x;
				this.y = y;
			}
			
			public void mousePressed(MouseEvent mouseEvent) {
				botonera.adecuarseParaFabricarRecolectorDeGas(x ,y);;
			}
}
