package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controlador.AccionTerminarTurno;
import algoCraft.juego.Juego;

public class PanelIzquierdo extends JPanel implements Observer {
	

	private static final long serialVersionUID = 1L;
	private boolean primerTurno = true;
	private Juego juego;
	private PanelJugador panelJugador;
	
	public PanelIzquierdo(Juego juego){
		this.juego = juego;
		juego.addObserver(this);
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(!primerTurno){
			this.remove(panelJugador);
		}
		if(primerTurno){
			this.add(new JButton(new AccionTerminarTurno(juego)), BorderLayout.SOUTH);
		}
		panelJugador = new PanelJugador(juego);
		this.add(panelJugador, BorderLayout.NORTH);
		primerTurno = false;
		this.revalidate();
		this.repaint();
	}
}
