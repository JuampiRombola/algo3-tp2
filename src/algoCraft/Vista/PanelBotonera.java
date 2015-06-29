package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class PanelBotonera extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private PanelDeConstruccion panelDeConstruccionDeEdificiosDeProduccion;
	private PanelDeConstruccion panelDeConstruccionDeRecolectorDeMinerales;

	private PanelDeConstruccionDeRecolectorDeGas panelDeConstruccionDeRecolectorDeGas;
	
	PanelBotonera(Juego juego){
		juego.addObserver(this);
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.setForeground(Color.white);
		panelDeConstruccionDeEdificiosDeProduccion = new PanelDeConstruccionDeEdificiosDeProduccion();
		panelDeConstruccionDeEdificiosDeProduccion.setVisible(false);
		this.add(panelDeConstruccionDeEdificiosDeProduccion, BorderLayout.NORTH);
		panelDeConstruccionDeRecolectorDeMinerales = new PanelDeConstruccionDeRecolectorDeMinerales();
		panelDeConstruccionDeRecolectorDeMinerales.setVisible(false);
		this.add(panelDeConstruccionDeRecolectorDeMinerales, BorderLayout.CENTER);
		panelDeConstruccionDeRecolectorDeGas = new PanelDeConstruccionDeRecolectorDeGas();
		panelDeConstruccionDeRecolectorDeGas.setVisible(false);
		this.add(panelDeConstruccionDeRecolectorDeGas, BorderLayout.EAST);
		this.setVisible(false);
		
	}
	
	public void adecuarseParaFabricarEdificiosDeProduccion(){
		panelDeConstruccionDeRecolectorDeGas.setVisible(false);
		this.panelDeConstruccionDeRecolectorDeMinerales.setVisible(false);
		this.panelDeConstruccionDeEdificiosDeProduccion.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.setVisible(true);
	}

	public void adecuarseParaFabricarRecolectorDeMineral() {
		panelDeConstruccionDeRecolectorDeGas.setVisible(false);
		this.panelDeConstruccionDeEdificiosDeProduccion.setVisible(false);
		this.panelDeConstruccionDeRecolectorDeMinerales.setVisible(true);
	}

	public void adecuarseParaFabricarRecolectorDeGas() {
		this.panelDeConstruccionDeEdificiosDeProduccion.setVisible(false);
		this.panelDeConstruccionDeRecolectorDeMinerales.setVisible(false);
		panelDeConstruccionDeRecolectorDeGas.setVisible(true);
	}
}
