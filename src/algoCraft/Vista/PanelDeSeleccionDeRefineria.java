package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.construcciones.Refineria;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class PanelDeSeleccionDeRefineria extends JPanel {

	private static final long serialVersionUID = 1L;
	private int y;
	private int x;
	private JLabel nombreDeLoSeleccionado;
	private JLabel labelVida;

	public PanelDeSeleccionDeRefineria(int x, int y){	
		this.x = x;
		this.y = y;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		nombreDeLoSeleccionado = new JLabel("Refineria");
		nombreDeLoSeleccionado.setForeground(Color.white);
		nombreDeLoSeleccionado.setAlignmentX(CENTER_ALIGNMENT);
		this.add(nombreDeLoSeleccionado);
		labelVida = new JLabel(crearStringVida());
		labelVida.setAlignmentX(CENTER_ALIGNMENT);
		labelVida.setForeground(Color.white);
		this.add(labelVida);
		this.setBackground(Color.black);
	}

	private String crearStringVida(){
		Refineria refineria;
		boolean esTerrestre = true;
		refineria = (Refineria) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return new String("Vida: " + String.valueOf(refineria.getVidaActual()));
	}
}
