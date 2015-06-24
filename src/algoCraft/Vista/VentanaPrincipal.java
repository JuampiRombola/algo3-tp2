package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaPrincipal{
	//Clase auxiliar para escuchar el evento de cerrado de la ventana
	
	private JFrame marco;
	private JMenuBar barraMenu;
    private JMenu menuJuego, menuAcercaDe;
    private JMenuItem itmNuevaPartida, itmSalir, itmCreadores;
	
	public VentanaPrincipal(){
		inicializarBarraMenu();
		marco = new JFrame("AlgoCraft");
		marco.setExtendedState(JFrame.MAXIMIZED_BOTH);
		marco.addWindowListener(new CloseListener());
		marco.setJMenuBar(barraMenu);
		marco.add(new VistaMapa(), BorderLayout.EAST);
		marco.setVisible(true);
	}
	
	private void inicializarBarraMenu() {
		this.barraMenu = new JMenuBar();
		this.menuJuego = new JMenu("Juego");
		this.menuAcercaDe = new JMenu("Acerca De");
		this.itmNuevaPartida = new JMenuItem("Nueva Partida");
		this.itmSalir = new JMenuItem("Salir");
		this.itmCreadores = new JMenuItem("Creadores");
		
		this.barraMenu.add(this.menuJuego);
		this.menuJuego.add(this.itmNuevaPartida);
		this.menuJuego.add(this.itmSalir);
		
		this.barraMenu.add(this.menuAcercaDe);
		this.menuAcercaDe.add(this.itmCreadores);
	}
	
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
}
