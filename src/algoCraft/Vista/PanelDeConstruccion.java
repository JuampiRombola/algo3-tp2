package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PanelDeConstruccion extends JPanel {
	private static final long serialVersionUID = 1L;
	String[] nombresEdificios = {"Extractor de Minerales", "Extractor de Gas Vespeno"};
	private JComboBox<String> edificios;
	
	PanelDeConstruccion(){
		edificios = new JComboBox<String>(nombresEdificios);
		this.add(new JButton("Construir"), BorderLayout.CENTER);
		this.add(edificios, BorderLayout.SOUTH);
		this.setBackground(Color.black);;
	}
}
