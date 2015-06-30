package algoCraft.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Controlador.MouseListenerSonidoClick;
import algoCraft.juego.Juego;

public class MenuNuevaPartida extends JMenu{

	private static final long serialVersionUID = 1L;
	private JMenuItem itmDosJugadores, itmTresJugadores, itmCuatroJugadores;
	
	private Juego juego;
	
	MenuNuevaPartida(Juego juego){
		super("Nueva partida");
		this.juego = juego;
		itmDosJugadores = new JMenuItem("Dos jugadores");
		itmDosJugadores.addMouseListener(new MouseListenerSonidoClick());
		itmTresJugadores = new JMenuItem("Tres jugadores");
		itmTresJugadores.addMouseListener(new MouseListenerSonidoClick());
		itmCuatroJugadores = new JMenuItem("Cuatro jugadores");
		itmCuatroJugadores.addMouseListener(new MouseListenerSonidoClick());
		
		itmDosJugadores.addActionListener(new dosJugadoresListener());
		itmTresJugadores.addActionListener(new tresJugadoresListener());
		itmCuatroJugadores.addActionListener(new cuatroJugadoresListener());
		
		this.add(itmDosJugadores);
		this.add(itmTresJugadores);
		this.add(itmCuatroJugadores);
	}
	
	   public class dosJugadoresListener implements ActionListener{
		   
			public void actionPerformed(ActionEvent e){
				juego.limpiarJuego();
				juego.agregarJugadorLlamado(JOptionPane.showInputDialog("Nombre del primer jugador"));
				juego.agregarJugadorLlamado(JOptionPane.showInputDialog("Nombre del segundo jugador"));
				juego.iniciarPartida();
			}
		}
	   
	   public class tresJugadoresListener implements ActionListener{
		   
			public void actionPerformed(ActionEvent e){
				juego.limpiarJuego();
				juego.agregarJugadorLlamado(JOptionPane.showInputDialog("Nombre del primer jugador"));
				juego.agregarJugadorLlamado(JOptionPane.showInputDialog("Nombre del segundo jugador"));
				juego.agregarJugadorLlamado(JOptionPane.showInputDialog("Nombre del tercer jugador"));
				juego.iniciarPartida();
			}
		}
	   
	   public class cuatroJugadoresListener implements ActionListener{
		   
			public void actionPerformed(ActionEvent e){
				juego.limpiarJuego();
				juego.agregarJugadorLlamado(JOptionPane.showInputDialog("Nombre del primer jugador"));
				juego.agregarJugadorLlamado(JOptionPane.showInputDialog("Nombre del segundo jugador"));
				juego.agregarJugadorLlamado(JOptionPane.showInputDialog("Nombre del tercer jugador"));
				juego.agregarJugadorLlamado(JOptionPane.showInputDialog("Nombre del cuarto jugador"));
				juego.iniciarPartida();
			}
		}
}
