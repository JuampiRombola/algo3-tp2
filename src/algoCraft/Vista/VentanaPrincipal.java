package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import algoCraft.juego.Juego;

public class VentanaPrincipal{
	
	private JFrame marco;
	private Juego juego;
	private PanelBotonera botonera;
	public VentanaPrincipal(){
		marco = new JFrame("AlgoCraft");
		marco.setLayout(new BorderLayout());
		marco.setExtendedState(JFrame.MAXIMIZED_BOTH);
		marco.addWindowListener(new CloseListener());
		juego = new Juego();
		JMenuBar barraMenu = (new BarraMenu(marco, juego)).getBarraMenu();
		marco.setJMenuBar(barraMenu);
		marco.getContentPane().add(new PanelIzquierdo(juego, marco), BorderLayout.WEST);
		botonera = new PanelBotonera(juego);
		marco.getContentPane().add(botonera, BorderLayout.CENTER);
		marco.getContentPane().add(new VistaMapa(botonera), BorderLayout.EAST);
		marco.setVisible(true);
		marco.getContentPane().setBackground(Color.black);
		marco.setVisible(true);
	}

	public static class CloseListener extends WindowAdapter{
		public void windowClosing(WindowEvent e) {	
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
}
