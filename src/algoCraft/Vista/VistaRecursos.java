package algoCraft.Vista;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class VistaRecursos extends JPanel{
	private JLabel labelMinerales = new JLabel("Minerales");
	private JLabel cantidadDeMinerales = new JLabel();
	private JLabel labelGas = new JLabel("Gas");
	private JLabel cantidadDeGas = new JLabel();
	
	private static final long serialVersionUID = 1L;
	
	VistaRecursos(Juego juego){
		this.setBackground(Color.black);
		labelMinerales.setHorizontalAlignment(JLabel.LEFT);
		labelMinerales.setForeground(Color.white);
		this.add(labelMinerales);
		cantidadDeMinerales.setHorizontalAlignment(JLabel.LEFT);
		cantidadDeMinerales.setForeground(Color.white);
		cantidadDeMinerales.setText(String.valueOf(juego.getJugadorActual().getMineral()));
		this.add(cantidadDeMinerales);
		labelGas.setHorizontalAlignment(JLabel.CENTER);
		labelGas.setForeground(Color.white);
		this.add(labelGas);
		cantidadDeGas.setHorizontalAlignment(JLabel.CENTER);
		cantidadDeGas.setForeground(Color.white);
		cantidadDeGas.setText(String.valueOf(juego.getJugadorActual().getGasVespeno()));
		this.add(cantidadDeGas);
	}
}