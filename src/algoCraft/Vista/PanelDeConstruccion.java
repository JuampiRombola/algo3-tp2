package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public abstract class PanelDeConstruccion extends JPanel {
	private static final long serialVersionUID = 1L;
	protected JComboBox<String> comboBox;
	protected JButton button = new JButton("Construir");
	protected Juego juego;
	
	PanelDeConstruccion(String[] nombresEdificios, Juego juego){
		this.juego = juego;
		comboBox = new JComboBox<String>(nombresEdificios);
		this.add(button, BorderLayout.CENTER);
		this.add(comboBox, BorderLayout.SOUTH);
		this.setBackground(Color.black);;
	}
	abstract void prepararEventoDeProduccion(int x, int y);
}
