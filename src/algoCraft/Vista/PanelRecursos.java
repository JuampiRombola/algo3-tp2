package algoCraft.Vista;

import java.awt.Color;


import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class PanelRecursos extends JPanel implements Observer{
	private JLabel labelMinerales, labelGas, labelSuministros;
	private Juego juego;
	
	private static final long serialVersionUID = 1L;
	
	PanelRecursos(Juego juego){
		this.juego = juego;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.black);
		agregarLabelMinerales();
		agregarLabelGas();
		agregarLabelSuministros();
		juego.getJugadorActual().addObserver(this);
	}
	
	private String obtenerStringSuministros() {
		String strCantidadDeSuministros = new String(String.valueOf(juego.getJugadorActual().getPoblacion()));
		strCantidadDeSuministros += "/";
		strCantidadDeSuministros += String.valueOf(juego.getJugadorActual().getPoblacionMaxima());
		return strCantidadDeSuministros;
	}
	
	private void agregarLabelSuministros() {
		labelSuministros = new JLabel(tamanioLetra(12, "Poblacion: "+obtenerStringSuministros()));
		labelSuministros.setHorizontalAlignment(JLabel.LEFT);
		labelSuministros.setForeground(Color.white);
		this.add(labelSuministros);
	}
	
	private void agregarLabelGas() {
		labelGas = new JLabel(tamanioLetra(12, "Gas Vespeno: "+String.valueOf(juego.getJugadorActual().getGasVespeno())));
		labelGas.setHorizontalAlignment(JLabel.LEFT);
		labelGas.setForeground(Color.white);
		this.add(labelGas);
	}

	private void agregarLabelMinerales() {
		labelMinerales = new JLabel(tamanioLetra(12, "Minerales: "+String.valueOf(juego.getJugadorActual().getMineral())));
		labelMinerales.setHorizontalAlignment(JLabel.LEFT);
		labelMinerales.setForeground(Color.white);
		this.add(labelMinerales);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		labelMinerales.setText(tamanioLetra(12, "Minerales: " + String.valueOf(juego.getJugadorActual().getMineral())));
		labelGas.setText(tamanioLetra(12, "Gas Vespeno: "+String.valueOf(juego.getJugadorActual().getGasVespeno())));
		labelSuministros.setText(tamanioLetra(12, "Poblacion: "+obtenerStringSuministros()));
	}
	
	private String tamanioLetra(int tamanio, String string) {
		return ("<html><span style='font-size:"+tamanio+"px'>"+string+"</span></html>");
	}
}