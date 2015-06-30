package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.Musica;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.Mineral;

public class PanelDeSeleccionDeMineral extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel nombreDeLoSeleccionado;
	private JLabel recursosRestantes;
	private int x;
	private int y;
	
	public PanelDeSeleccionDeMineral(int x, int y){
		this.x = x;
		this.y = y;
		Musica.reproducir("Recursos/Musica/recursos.wav");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		nombreDeLoSeleccionado = new JLabel("Mineral");
		nombreDeLoSeleccionado.setForeground(Color.white);
		nombreDeLoSeleccionado.setAlignmentX(CENTER_ALIGNMENT);
		this.add(nombreDeLoSeleccionado);
		recursosRestantes = new JLabel(crearStringRecursosRestantes());
		recursosRestantes.setAlignmentX(CENTER_ALIGNMENT);
		recursosRestantes.setForeground(Color.white);
		this.add(recursosRestantes);
		this.setBackground(Color.black);
	}
	
	private String crearStringRecursosRestantes(){
		//Los minerales son siempre terrestres
		Mineral mineral;
		boolean esTerrestre = true;
		int cantidadDeMineral;
		mineral = (Mineral) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		cantidadDeMineral = mineral.getUnidadesRestantes();
		return new String("Quedan " + String.valueOf(cantidadDeMineral) + " unidades de Mineral");
	}
}
