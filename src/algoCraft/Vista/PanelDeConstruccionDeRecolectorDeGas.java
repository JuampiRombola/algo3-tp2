package algoCraft.Vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.AccionConstruirRecolectorDeGas;
import algoCraft.construcciones.Refineria;
import algoCraft.juego.Juego;

public class PanelDeConstruccionDeRecolectorDeGas extends PanelDeConstruccion {

	private static String[] nombresEdificios = {"Refineria"};
	private JPanel costoDeLaRefineria;
	private JLabel labelCostoGas;
	private JLabel labelCostoMineral;
	private JLabel labelCostoRefineria = new JLabel("Costo de una refineria");
	
	PanelDeConstruccionDeRecolectorDeGas(Juego juego) {
		super(nombresEdificios, juego);
		costoDeLaRefineria = new JPanel();
		costoDeLaRefineria.setLayout(new BoxLayout(costoDeLaRefineria,BoxLayout.Y_AXIS));
		labelCostoMineral = (new JLabel(String.valueOf(Refineria.getCostoMineral()) +
								" unidades de minerales"));
		labelCostoGas = (new JLabel(String.valueOf(Refineria.getCostoGas() +
				" unidades de gas")));
		labelCostoRefineria.setForeground(Color.white);
		labelCostoMineral.setForeground(Color.white);
		labelCostoGas.setForeground(Color.white);
		costoDeLaRefineria.add(labelCostoRefineria);
		costoDeLaRefineria.add(labelCostoMineral);
		costoDeLaRefineria.add(labelCostoGas);
		costoDeLaRefineria.setBackground(Color.BLACK);
		costoDeLaRefineria.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(costoDeLaRefineria);
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		button.setAction(new AccionConstruirRecolectorDeGas(juego, x, y));
	}
}
