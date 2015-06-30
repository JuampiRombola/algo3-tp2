package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.construcciones.EdificioRecolector;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class PanelDeSeleccionDeEdificioRecolector extends JPanel {

	private static final long serialVersionUID = 1L;
	private int y;
	private int x;
	private JLabel nombreDeLoSeleccionado;
	private JLabel labelVida;
	private JLabel labelRecolectado;
	private JLabel labelRecursosRestantes;

	public PanelDeSeleccionDeEdificioRecolector(String recolector, int x, int y){	
		this.x = x;
		this.y = y;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		nombreDeLoSeleccionado = new JLabel(recolector + crearStringEnConstruccion());
		nombreDeLoSeleccionado.setForeground(Color.white);
		nombreDeLoSeleccionado.setAlignmentX(CENTER_ALIGNMENT);
		this.add(nombreDeLoSeleccionado);
		labelVida = new JLabel(crearStringVida());
		labelVida.setAlignmentX(CENTER_ALIGNMENT);
		labelVida.setForeground(Color.white);
		this.add(labelVida);
		labelRecolectado = new JLabel(crearStringRecursosRecolectados());
		labelRecolectado.setAlignmentX(CENTER_ALIGNMENT);
		labelRecolectado.setForeground(Color.white);
		this.add(labelRecolectado);
		labelRecursosRestantes = new JLabel(crearStringRecursosRestantes());
		labelRecursosRestantes.setAlignmentX(CENTER_ALIGNMENT);
		labelRecursosRestantes.setForeground(Color.white);
		this.add(labelRecursosRestantes);
		this.setBackground(Color.black);
	}

	private String crearStringEnConstruccion(){
		EdificioRecolector recolector;
		boolean esTerrestre = true;
		recolector = (EdificioRecolector) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return (recolector.estaEnConstruccion()) ? "(en construccion)" : "(construido)";
	}
	
	private String crearStringVida(){
		EdificioRecolector recolector;
		boolean esTerrestre = true;
		recolector = (EdificioRecolector) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return new String("Vida: " + String.valueOf(recolector.getVidaActual()) + "/" +
							String.valueOf(recolector.getVidaMaxima()));
	}
	
	private String crearStringRecursosRecolectados(){
		EdificioRecolector recolector;
		boolean esTerrestre = true;
		recolector = (EdificioRecolector) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return new String("Recursos recolectados: " + String.valueOf(recolector.getRecursosRecolectados()));
	}
	
	private String crearStringRecursosRestantes() {
		EdificioRecolector recolector;
		boolean esTerrestre = true;
		recolector = (EdificioRecolector) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return new String("Recursos restantes : " + String.valueOf(recolector.getRecursosRestantes()));
	}
}
