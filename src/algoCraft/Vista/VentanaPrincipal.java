package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VentanaPrincipal{
	//Clase auxiliar para escuchar el evento de cerrado de la ventana
	
	private JFrame marco;
	private JMenuBar barraMenu;
    private JMenu menuJuego, menuAcercaDe;
    private JMenuItem itmNuevaPartida, itmSalir, itmCreadores;
	
	public VentanaPrincipal(){
		marco = new JFrame("AlgoCraft");
		marco.setExtendedState(JFrame.MAXIMIZED_BOTH);
		marco.addWindowListener(new CloseListener());
		agregarBarraMenu();
		marco.add(new VistaMapa(), BorderLayout.EAST);
		marco.setVisible(true);
	}
	
	private void agregarBarraMenu() {
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
		marco.setJMenuBar(barraMenu);
	}
	
	private void agregarEventosBarraMenu() {
		this.itmSalir.addActionListener(new CerrarVentana());
		this.itmCreadores.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               AcercaDeDialog dialog = new AcercaDeDialog(marco);
               dialog.setVisible(true);
            }
         });
	}
	
	public static class CloseListener extends WindowAdapter{
		public void windowClosing(WindowEvent e) {	
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
	
	public static class CerrarVentana implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
			}
	}
	
	class AcercaDeDialog extends JDialog {
		private static final long serialVersionUID = 1L;

	public AcercaDeDialog(JFrame owner) {
	      super(owner, "Creadores", true);

	      add(new JLabel("<html>Ariel Vergara, Mauro Toscano y Juan Pablo Rombolá<html>"),
	    		  BorderLayout.CENTER);

	      // Boton Aceptar para cerrar el dialogo
	      JButton aceptar = new JButton("Aceptar");
	      aceptar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event)
	            {
	               setVisible(false);
	            }
	         });
	      JPanel panel = new JPanel();
	      panel.add(aceptar);
	      add(panel, BorderLayout.SOUTH);

	      setSize(250, 150);
	   }
	}
}
