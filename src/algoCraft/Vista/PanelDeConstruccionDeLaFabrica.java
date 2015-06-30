package algoCraft.Vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.AccionConstruirGoliath;
import algoCraft.juego.Juego;
import algoCraft.unidades.Goliath;
public class PanelDeConstruccionDeLaFabrica extends PanelDeConstruccion {

	private static final long serialVersionUID = 1L;
	private static String[] nombresUnidades = {"Goliath"};
	private JPanel cost;
	private JLabel labelCosto= new JLabel("Costo de un Goliath");
	private JLabel labelCostoMinerales;
	private JLabel labelCostoGas;
	
	PanelDeConstruccionDeLaFabrica(Juego juego) {
		super(nombresUnidades, juego);
		cost = new JPanel();
		cost.setLayout(new BoxLayout(cost,BoxLayout.Y_AXIS));
		labelCostoMinerales = (new JLabel(String.valueOf(Goliath.getCantidadDeMineralQueCUesta() +
								" unidades de minerales")));
		labelCostoGas = (new JLabel(String.valueOf(Goliath.getCantidadDeGasQueCUesta() +
				" unidades de gas")));
		labelCosto.setForeground(Color.white);
		labelCostoMinerales.setForeground(Color.white);
		labelCostoGas.setForeground(Color.white);
		cost.add(labelCosto);
		cost.add(labelCostoMinerales);
		cost.add(labelCostoGas);
		cost.setBackground(Color.BLACK);
		cost.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(cost);
	}

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		this.button.setAction(new AccionConstruirGoliath(juego, x, y));
	}
}
