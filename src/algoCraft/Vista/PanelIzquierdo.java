package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Controlador.AccionTerminarTurno;
import Controlador.MouseListenerSonidoClick;
import algoCraft.juego.Juego;

public class PanelIzquierdo extends JPanel implements Observer {
	

	private static final long serialVersionUID = 1L;
	private boolean primerTurno = true;
	private Juego juego;
	private PanelJugador panelJugador;
	private PanelCentral panelCentral;
	
	public PanelIzquierdo(Juego juego, PanelCentral panelCentral){
		this.panelCentral = panelCentral;
		this.juego = juego;
		juego.addObserver(this);
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		setBorder(new LineBorder(Color.GRAY));
		if(!primerTurno){
			this.remove(panelJugador);
		}
		if(primerTurno){
			JButton botonTerminarTurno = new JButton(new AccionTerminarTurno(juego));
			botonTerminarTurno.addMouseListener(new MouseListenerSonidoClick());
			this.add(botonTerminarTurno, BorderLayout.SOUTH);
		}
		panelJugador = new PanelJugador(juego);
		this.add(panelJugador, BorderLayout.NORTH);
		primerTurno = false;
		this.add(this.panelCentral, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
}
