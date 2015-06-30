package algoCraft.Vista;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.AccionConstruirMarine;
import algoCraft.juego.Juego;
import algoCraft.unidades.Marine;

public class PanelDeConstruccionDeLaBarraca extends PanelDeConstruccion {
	
	private static final long serialVersionUID = 1L;
	private static String[] nombresUnidades = {"Marine"};
	JPanel costoDelMarine;
	JLabel labelCostoDeUnMarine = new JLabel("Costo de un marine");
	JLabel labelCostoMinerales;
	JLabel labelCostoGas;
	
	PanelDeConstruccionDeLaBarraca(Juego juego) {
		super(nombresUnidades, juego);
		costoDelMarine = new JPanel();
		costoDelMarine.setLayout(new BoxLayout(costoDelMarine,BoxLayout.Y_AXIS));
		labelCostoMinerales = (new JLabel(String.valueOf(Marine.getCantidadDeMineralQueCUesta() +
								" unidades de minerales")));
		labelCostoGas = (new JLabel(String.valueOf(Marine.getCantidadDeGasQueCUesta() +
				" unidades de gas")));
		labelCostoDeUnMarine.setForeground(Color.white);
		labelCostoMinerales.setForeground(Color.white);
		labelCostoGas.setForeground(Color.white);
		costoDelMarine.add(labelCostoDeUnMarine);
		costoDelMarine.add(labelCostoMinerales);
		costoDelMarine.add(labelCostoGas);
		costoDelMarine.setBackground(Color.BLACK);
		costoDelMarine.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(costoDelMarine);
	}

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		this.button.setAction(new AccionConstruirMarine(juego, x, y));
	}

}
