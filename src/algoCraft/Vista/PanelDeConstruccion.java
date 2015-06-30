package algoCraft.Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public abstract class PanelDeConstruccion extends JPanel {
	private static final long serialVersionUID = 1L;
	protected JComboBox<String> comboBox;
	protected JButton button = new JButton("Construir");
	protected Juego juego;
	protected JPanel panelComboBoton;
	
	PanelDeConstruccion(String[] nombresEdificios, Juego juego){
		this.juego = juego;
		comboBox = new JComboBox<String>(nombresEdificios);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		panelComboBoton = new JPanel();
		panelComboBoton.setBackground(Color.black);
		this.panelComboBoton.add(comboBox);
		comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.panelComboBoton.add(button);
		this.add(panelComboBoton);
		this.panelComboBoton.setMaximumSize(new Dimension(500, 50));
		this.setBackground(Color.black);;
	}
	abstract void prepararEventoDeProduccion(int x, int y);
}
