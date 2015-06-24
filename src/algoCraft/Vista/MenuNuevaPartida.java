package algoCraft.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import algoCraft.mapa.Mapa;

public class MenuNuevaPartida extends JMenu{

	private static final long serialVersionUID = 1L;
	private JMenuItem itmDosJugadores, itmTresJugadores, itmCuatroJugadores;
	
	MenuNuevaPartida(){
		super("Nueva partida");
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
				Mapa.getMapa().limpiarMapa();
	            Mapa.getMapa().cargarBases(2);
	            System.out.println("Creando nuevo juego ...");
			}
		}
	   
	   public class tresJugadoresListener implements ActionListener{
		   
			public void actionPerformed(ActionEvent e){
				Mapa.getMapa().limpiarMapa();
	            Mapa.getMapa().cargarBases(3);
	            System.out.println("Creando nuevo juego ...");
			}
		}
	   
	   public class cuatroJugadoresListener implements ActionListener{
		   
			public void actionPerformed(ActionEvent e){
				Mapa.getMapa().limpiarMapa();
	            Mapa.getMapa().cargarBases(4);
	            System.out.println("Creando nuevo juego ...");
			}
		}
}
