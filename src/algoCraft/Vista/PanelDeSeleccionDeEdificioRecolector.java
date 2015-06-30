package algoCraft.Vista;

import java.awt.Color;

import javax.swing.JLabel;

import algoCraft.construcciones.EdificioRecolector;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class PanelDeSeleccionDeEdificioRecolector extends PanelDeConstruccionDeEdificios {

	private static final long serialVersionUID = 1L;
	private JLabel labelRecolectado;
	private JLabel labelRecursosRestantes;

	public PanelDeSeleccionDeEdificioRecolector(String recolector, int x, int y){
		super(recolector, x, y);
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
