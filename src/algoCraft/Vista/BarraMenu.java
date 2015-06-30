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

import Controlador.MouseListenerSonidoClick;
import algoCraft.Musica;
import algoCraft.juego.Juego;

public class BarraMenu {
	private JFrame marco;
	private Musica musica;
	private JMenuBar barraMenu;
    private JMenu menuJuego, menuMusica, menuAcercaDe;
    private JMenuItem itmSalir, itmCreadores, itmActivarMusica;
	private MenuNuevaPartida menuNuevaPartida;
    
    public BarraMenu(JFrame marco, Juego juego, Musica musica){
    	this.marco = marco;
    	this.musica = musica;
    	this.barraMenu = new JMenuBar();
		this.menuJuego = new JMenu("Juego");
		this.menuJuego.addMouseListener(new MouseListenerSonidoClick());
		this.menuMusica = new JMenu("Musica");
		this.menuMusica.addMouseListener(new MouseListenerSonidoClick());
		this.menuAcercaDe = new JMenu("Acerca De");
		this.menuAcercaDe.addMouseListener(new MouseListenerSonidoClick());
		this.menuNuevaPartida = new MenuNuevaPartida(juego);
		this.itmSalir = new JMenuItem("Salir");
		this.itmCreadores = new JMenuItem("Creadores");
		this.itmCreadores.addMouseListener(new MouseListenerSonidoClick());
		this.itmActivarMusica = new JMenuItem("Activar/Desactivar");
		
		this.barraMenu.add(this.menuJuego);
		this.menuJuego.add(this.menuNuevaPartida);
		this.menuJuego.add(this.itmSalir);
		
		this.barraMenu.add(this.menuMusica);
		this.menuMusica.add(this.itmActivarMusica);
		
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
		this.itmActivarMusica.addActionListener(new activarDesactivarMusica(this.musica));
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
	
	public class activarDesactivarMusica implements ActionListener{
		private Musica musica;
		
		public activarDesactivarMusica(Musica musica){
			this.musica = musica;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (this.musica.estaActiva())
				musica.stop();
			else
				musica.loop();
		}
	}
}
