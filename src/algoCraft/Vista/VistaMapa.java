package algoCraft.Vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class VistaMapa extends JPanel implements Observer{
	
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
				Color color = (booleano) ? Color.blue : Color.green;
				VistaCuadrado cuadrado = new VistaCuadrado(color);
				booleano = !booleano;
				gbc.gridx = i;
                gbc.gridy = j;
                this.add(cuadrado, gbc);
			}
		}
	}
	@Override
	public void update(Observable o, Object arg) {
		Mapa mapa = Mapa.getMapa();
		for (int i = 0; i < mapa.getAncho(); i++){
			for (int j = 0; j < mapa.getAlto(); j++){
				Posicion posicionRevisada = new Posicion(i,j, true);
				mapa.getUnidad(posicionRevisada);
			}
		}
	}
}
