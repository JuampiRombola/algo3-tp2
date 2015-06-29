package algoCraft.Vista;

import java.awt.Color;


import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class PanelRecursos extends JPanel implements Observer{
	private JLabel labelMinerales = new JLabel("Minerales");
	private JLabel cantidadDeMinerales = new JLabel();
	private JLabel labelGas = new JLabel("Gas");
	private JLabel cantidadDeGas = new JLabel();
	private JLabel labelSuministros = new JLabel("Suministros");
	private JLabel cantidadDeSuministros = new JLabel();
	private Juego juego;
	
	private static final long serialVersionUID = 1L;
	
	PanelRecursos(Juego juego){
		this.juego = juego;
		this.setBackground(Color.black);
		agregarLabelMinerales();
		AgregarCantidadDeMinerales();
		agregarLabelGas();
		agregarCantidadDeGas();
		agregarLabelSuministros();
		agregarCantidadDeSuministros();
		juego.getJugadorActual().addObserver(this);
	}

	private void agregarCantidadDeSuministros() {
		cantidadDeSuministros.setHorizontalAlignment(JLabel.CENTER);
		cantidadDeSuministros.setForeground(Color.white);
		String strCantidadDeSuministros = obtenerStringSuministros();
		cantidadDeSuministros.setText(strCantidadDeSuministros);
		this.add(cantidadDeSuministros);
	}

	private String obtenerStringSuministros() {
		String strCantidadDeSuministros = new String(String.valueOf(juego.getJugadorActual().getPoblacion()));
		strCantidadDeSuministros += "/";
		strCantidadDeSuministros += String.valueOf(juego.getJugadorActual().getPoblacionMaxima());
		return strCantidadDeSuministros;
	}
	
	private void agregarLabelSuministros() {
		labelSuministros.setHorizontalAlignment(JLabel.CENTER);
		labelSuministros.setForeground(Color.white);
		this.add(labelSuministros);
	}
	
	private void agregarCantidadDeGas() {
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

	private void AgregarCantidadDeMinerales() {
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

	@Override
	public void update(Observable arg0, Object arg1) {
		cantidadDeMinerales.setText(String.valueOf(juego.getJugadorActual().getMineral()));
		cantidadDeGas.setText(String.valueOf(juego.getJugadorActual().getGasVespeno()));
		cantidadDeSuministros.setText(obtenerStringSuministros());
	}
}