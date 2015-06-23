package algoCraft.Vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import algoCraft.mapa.Mapa;

public class VistaMapa extends JPanel{
	
	private static final long serialVersionUID = 1L;
	//Practica con el tamanio de las cosas, todavia no muestra el mapa real.
	public VistaMapa(){
		Mapa mapa = Mapa.getMapa();
	    this.setLayout(new GridBagLayout());
	    this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		boolean booleano = true;
		GridBagConstraints gbc = new GridBagConstraints();
		for (int i = 0; i < mapa.getAncho(); i++){
			for (int j = 0; j < mapa.getAlto(); j++){
				VistaCuadrado cuadrado;
				if(booleano){
					cuadrado= new VistaCuadrado(Color.blue);
					booleano = false;
				}else{
					cuadrado = new VistaCuadrado(Color.green);
					booleano = true;
				}
				gbc.gridx = i;
                gbc.gridy = j;
                this.add(cuadrado, gbc);
			}
		}
	}
}

