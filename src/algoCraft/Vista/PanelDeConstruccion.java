package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public abstract class PanelDeConstruccion extends JPanel {
	private static final long serialVersionUID = 1L;
	//String[] nombresEdificios = {"Extractor de Minerales", "Extractor de Gas Vespeno"};
	protected JComboBox<String> edificios;
	protected JButton button = new JButton("Construir");
	protected Juego juego;
	
	PanelDeConstruccion(String[] nombresEdificios, Juego juego){
		this.juego = juego;
		edificios = new JComboBox<String>(nombresEdificios);
		this.add(button, BorderLayout.CENTER);
		this.add(edificios, BorderLayout.SOUTH);
		this.setBackground(Color.black);;
	}
	abstract void prepararEventoDeProduccion(int x, int y);
}
