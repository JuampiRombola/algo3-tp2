package algoCraft.Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.AccionConstruirEdificioDeProduccion;
import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.DepositoDeSuministros;
import algoCraft.construcciones.Fabrica;
import algoCraft.juego.Juego;

public class PanelDeConstruccionDeEdificiosDeProduccion extends PanelDeConstruccion {
	private static String[] nombresEdificios = {"Barraca", "Fabrica", "Deposito de suministros"};
	
	PanelDeConstruccionDeEdificiosDeProduccion(Juego juego) {
		super(nombresEdificios, juego);
		this.add(new PanelCostoBarraca());
		this.add(new PanelCostoFabrica());
		this.add(new PanelCostoDeposito());
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		this.button.setAction(new AccionConstruirEdificioDeProduccion(juego, comboBox, x, y));
	}
	
	
	
	private class PanelCostoBarraca extends JPanel{
		private static final long serialVersionUID = 1L;
		private JPanel costo;
		private JLabel labelCosto = new JLabel("Costo de una barraca");
		private JLabel labelCostoMinerales;
		private JLabel labelCostoGas;
		
		public PanelCostoBarraca(){
			this.setMaximumSize(new Dimension(500, 100));
			this.setBackground(Color.black);
			costo = new JPanel();
			costo.setLayout(new BoxLayout(costo,BoxLayout.Y_AXIS));
			labelCostoMinerales = (new JLabel(String.valueOf(Barraca.getCostoMineral() +
									" unidades de minerales")));
			labelCostoGas = (new JLabel(String.valueOf(Barraca.getCostoGas() +
					" unidades de gas")));
			labelCosto.setForeground(Color.white);
			labelCostoMinerales.setForeground(Color.white);
			labelCostoGas.setForeground(Color.white);
			costo.add(labelCosto);
			costo.add(labelCostoMinerales);
			costo.add(labelCostoGas);
			costo.setBackground(Color.BLACK);
			costo.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(costo);
		}
		
	}
	
	private class PanelCostoFabrica extends JPanel{
		private static final long serialVersionUID = 1L;
		private JPanel costo;
		private JLabel labelCosto = new JLabel("Costo de una fabrica");
		private JLabel labelCostoMinerales;
		private JLabel labelCostoGas;
		
		public PanelCostoFabrica(){
			this.setMaximumSize(new Dimension(500, 100));
			this.setBackground(Color.black);
			costo = new JPanel();
			costo.setLayout(new BoxLayout(costo,BoxLayout.Y_AXIS));
			labelCostoMinerales = (new JLabel(String.valueOf(Fabrica.getCostoMineral() +
									" unidades de minerales")));
			labelCostoGas = (new JLabel(String.valueOf(Fabrica.getCostoGas() +
					" unidades de gas")));
			labelCosto.setForeground(Color.white);
			labelCostoMinerales.setForeground(Color.white);
			labelCostoGas.setForeground(Color.white);
			costo.add(labelCosto);
			costo.add(labelCostoMinerales);
			costo.add(labelCostoGas);
			costo.setBackground(Color.BLACK);
			costo.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(costo);
		}
		
	}
	
	private class PanelCostoDeposito extends JPanel{
		private static final long serialVersionUID = 1L;
		private JPanel costo;
		private JLabel labelCosto = new JLabel("Costo de un deposito");
		private JLabel labelCostoMinerales;
		private JLabel labelCostoGas;
		
		public PanelCostoDeposito(){
			this.setMaximumSize(new Dimension(500, 100));
			this.setBackground(Color.black);
			costo = new JPanel();
			costo.setLayout(new BoxLayout(costo,BoxLayout.Y_AXIS));
			labelCostoMinerales = (new JLabel(String.valueOf(DepositoDeSuministros.getCostoMineral() +
									" unidades de minerales")));
			labelCostoGas = (new JLabel(String.valueOf(DepositoDeSuministros.getCostoGas() +
					" unidades de gas")));
			labelCosto.setForeground(Color.white);
			labelCostoMinerales.setForeground(Color.white);
			labelCostoGas.setForeground(Color.white);
			costo.add(labelCosto);
			costo.add(labelCostoMinerales);
			costo.add(labelCostoGas);
			costo.setBackground(Color.BLACK);
			costo.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(costo);
		}
		
	}
}
