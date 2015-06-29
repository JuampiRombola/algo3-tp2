package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.GasVespeno;

public class PanelDeSeleccionDeGasVespeno extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel nombreDeLoSeleccionado;
	private JLabel recursosRestantes;
	private int x;
	private int y;
	
	public PanelDeSeleccionDeGasVespeno(int x, int y){
		this.x = x;
		this.y = y;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		nombreDeLoSeleccionado = new JLabel("Gas Vespeno");
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
		GasVespeno gas;
		boolean esTerrestre = true;
		int gasRestante;
		gas = (GasVespeno) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		gasRestante = gas.getUnidadesRestantes();
		return new String("Quedan " + String.valueOf(gasRestante) + " unidades de Gas");
	}
}
