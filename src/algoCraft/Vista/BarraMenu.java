package algoCraft.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import algoCraft.mapa.Mapa;

public class BarraMenu {
	private JFrame marco;
	private JMenuBar barraMenu;
    private JMenu menuJuego, menuAcercaDe;
    private JMenuItem itmNuevaPartida, itmSalir, itmCreadores;
    
    public BarraMenu(JFrame marco){
    	this.marco = marco;
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
		
		this.agregarEventosBarraMenu();
    }
    
    public JMenuBar getBarraMenu() {
    	return this.barraMenu;
    }

    private void agregarEventosBarraMenu() {
		this.itmSalir.addActionListener(new CerrarVentana());
		this.itmCreadores.addActionListener(new MostrarDialogoCreadores(this.marco));
		this.itmNuevaPartida.addActionListener(new NuevoJuegoListener());
	}
    
    public class MostrarDialogoCreadores implements ActionListener{
    	private AcercaDeDialog dialog;
    	public MostrarDialogoCreadores(JFrame marco) {
    		this.dialog = new AcercaDeDialog(marco);
    	}
    	
		public void actionPerformed(ActionEvent e){
            this.dialog.setVisible(true);
			}
	}
    
    public class NuevoJuegoListener implements ActionListener{
 
		public void actionPerformed(ActionEvent e){
			Mapa.getMapa().limpiarMapa();
            Mapa.getMapa().cargarBases(2);
            System.out.println("Creando nuevo juego ...");
		}
	}
    
    public class CerrarVentana implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
			}
	}
	
	public class AcercaDeDialog extends JDialog{
		private static final long serialVersionUID = 1L;

	public AcercaDeDialog(JFrame owner) {
	      super(owner, "Creadores", true);
	      add(new JLabel("<html>Ariel Vergara<br>Mauro Toscano<br>Juan Pablo Rombola<html>", SwingConstants.CENTER));
	      this.setLocationRelativeTo(null);
	      this.setSize(150, 130);
	   }
	}
}
