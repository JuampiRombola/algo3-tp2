package algoCraft.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import algoCraft.juego.Juego;

public class MenuNuevaPartida extends JMenu{

	private static final long serialVersionUID = 1L;
	private JMenuItem itmDosJugadores, itmTresJugadores, itmCuatroJugadores;
	
	private Juego juego;
	
	MenuNuevaPartida(Juego juego){
		super("Nueva partida");
		this.juego = juego;
		itmDosJugadores = new JMenuItem("Dos jugadores");
		itmTresJugadores = new JMenuItem("Tres jugadores");
		itmCuatroJugadores = new JMenuItem("Cuatro jugadores");
		
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
				juego.agregarJugadorLlamado("1");
				juego.agregarJugadorLlamado("2");
				juego.iniciarPartida();
	            System.out.println("Creando nuevo juego ...");
			}
		}
	   
	   public class tresJugadoresListener implements ActionListener{
		   
			public void actionPerformed(ActionEvent e){
				juego.limpiarJuego();
				juego.agregarJugadorLlamado("1");
				juego.agregarJugadorLlamado("2");
				juego.agregarJugadorLlamado("3");
				juego.iniciarPartida();
	            System.out.println("Creando nuevo juego ...");
			}
		}
	   
	   public class cuatroJugadoresListener implements ActionListener{
		   
			public void actionPerformed(ActionEvent e){
				juego.limpiarJuego();
				juego.agregarJugadorLlamado("1");
				juego.agregarJugadorLlamado("2");
				juego.agregarJugadorLlamado("3");
				juego.agregarJugadorLlamado("4");
				juego.iniciarPartida();
	            System.out.println("Creando nuevo juego ...");
			}
		}
}
