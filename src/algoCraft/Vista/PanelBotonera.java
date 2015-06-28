package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class PanelBotonera extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	PanelBotonera(Juego juego){
		juego.addObserver(this);
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.setForeground(Color.white);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.add(new PanelDeConstruccion(), BorderLayout.NORTH);
	}
}
