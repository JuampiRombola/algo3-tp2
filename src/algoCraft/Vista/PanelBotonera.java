package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class PanelBotonera extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private PanelDeConstruccion panelDeConstruccion;
	PanelBotonera(Juego juego){
		juego.addObserver(this);
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.setForeground(Color.white);
		panelDeConstruccion = new PanelDeConstruccion();
		panelDeConstruccion.setVisible(false);
		this.add(panelDeConstruccion, BorderLayout.NORTH);
		this.setVisible(false);
	}
	
	public void adecuarseParaProduccion(){
		this.panelDeConstruccion.setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.setVisible(true);
	}
}
