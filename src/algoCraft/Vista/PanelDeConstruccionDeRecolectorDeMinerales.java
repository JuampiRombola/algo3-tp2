package algoCraft.Vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.construcciones.CentroDeMineral;
import algoCraft.juego.Juego;
import Controlador.AccionConstruirRecolectorDeMinerales;

public class PanelDeConstruccionDeRecolectorDeMinerales extends PanelDeConstruccion {
	
	private static String[] nombresEdificios = {"Recolector de minerales"};
	private static final long serialVersionUID = 1L;
	private JPanel costoDelRecolector;
	private JLabel labelCostoGas;
	private JLabel labelCostoMineral;
	private JLabel labelCostoRecolector = new JLabel("Costo de un recolector de minerales");

	PanelDeConstruccionDeRecolectorDeMinerales (Juego juego) {
		super(nombresEdificios, juego);
		costoDelRecolector = new JPanel();
		costoDelRecolector.setLayout(new BoxLayout(costoDelRecolector,BoxLayout.Y_AXIS));
		labelCostoMineral = (new JLabel(String.valueOf(CentroDeMineral.getCostoMineral()) +
								" unidades de minerales"));
		labelCostoGas = (new JLabel(String.valueOf(CentroDeMineral.getCostoGas() +
				" unidades de gas")));
		labelCostoRecolector.setForeground(Color.white);
		labelCostoMineral.setForeground(Color.white);
		labelCostoGas.setForeground(Color.white);
		costoDelRecolector.add(labelCostoRecolector);
		costoDelRecolector.add(labelCostoMineral);
		costoDelRecolector.add(labelCostoGas);
		costoDelRecolector.setBackground(Color.BLACK);
		costoDelRecolector.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(costoDelRecolector);
	}

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		button.setAction(new AccionConstruirRecolectorDeMinerales(juego, x, y));
	}
}
