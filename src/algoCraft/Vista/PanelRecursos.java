package algoCraft.Vista;

import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class PanelRecursos extends JPanel{
	private JLabel labelMinerales = new JLabel("Minerales");
	private JLabel cantidadDeMinerales = new JLabel();
	private JLabel labelGas = new JLabel("Gas");
	private JLabel cantidadDeGas = new JLabel();
	private JLabel labelSuministros = new JLabel("Suministros");
	private JLabel cantidadDeSuministros = new JLabel();
	
	private static final long serialVersionUID = 1L;
	
	PanelRecursos(Juego juego){
		this.setBackground(Color.black);
		agregarLabelMinerales();
		AgregarCantidadDeMinerales(juego);
		agregarLabelGas();
		agregarCantidadDeGas(juego);
		agregarLabelSuministros();
		agregarCantidadDeSuministros(juego);
	}

	private void agregarCantidadDeSuministros(Juego juego) {
		cantidadDeSuministros.setHorizontalAlignment(JLabel.CENTER);
		cantidadDeSuministros.setForeground(Color.white);
		String strCantidadDeSuministros = new String(String.valueOf(juego.getJugadorActual().getPoblacion()));
		strCantidadDeSuministros += "/";
		strCantidadDeSuministros += String.valueOf(juego.getJugadorActual().getPoblacionMaxima());
		cantidadDeSuministros.setText(strCantidadDeSuministros);
		this.add(cantidadDeSuministros);
	}
	
	private void agregarLabelSuministros() {
		labelSuministros.setHorizontalAlignment(JLabel.CENTER);
		labelSuministros.setForeground(Color.white);
		this.add(labelSuministros);
	}
	
	private void agregarCantidadDeGas(Juego juego) {
		cantidadDeGas.setHorizontalAlignment(JLabel.CENTER);
		cantidadDeGas.setForeground(Color.white);
		cantidadDeGas.setText(String.valueOf(juego.getJugadorActual().getGasVespeno()));
		this.add(cantidadDeGas);
	}

	private void agregarLabelGas() {
		labelGas.setHorizontalAlignment(JLabel.CENTER);
		labelGas.setForeground(Color.white);
		this.add(labelGas);
	}

	private void AgregarCantidadDeMinerales(Juego juego) {
		cantidadDeMinerales.setHorizontalAlignment(JLabel.LEFT);
		cantidadDeMinerales.setForeground(Color.white);
		cantidadDeMinerales.setText(String.valueOf(juego.getJugadorActual().getMineral()));
		this.add(cantidadDeMinerales);
	}

	private void agregarLabelMinerales() {
		labelMinerales.setHorizontalAlignment(JLabel.LEFT);
		labelMinerales.setForeground(Color.white);
		this.add(labelMinerales);
	}
}