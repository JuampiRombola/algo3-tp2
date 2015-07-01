package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.Musica;
import algoCraft.construcciones.Edificio;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class PanelDeSeleccionDeEdificios extends JPanel {

	private static final long serialVersionUID = 1L;
	protected int y;
	protected int x;
	private JLabel nombreDeLoSeleccionado;
	private JLabel labelVida;
	private JLabel labelEstado;

	public PanelDeSeleccionDeEdificios(String edificio, int x, int y, JLabel picLabel){	
		this.x = x;
		this.y = y;
		Musica.reproducir("Recursos/Musica/edificios.wav");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		nombreDeLoSeleccionado = new JLabel(edificio +"de "+ crearStringJugador());
		nombreDeLoSeleccionado.setForeground(Color.white);
		nombreDeLoSeleccionado.setAlignmentX(CENTER_ALIGNMENT);
		this.add(nombreDeLoSeleccionado);
		this.add(picLabel);
		labelEstado = new JLabel(crearStringEnConstruccion());
		labelEstado.setAlignmentX(CENTER_ALIGNMENT);
		labelEstado.setForeground(Color.white);
		this.add(labelEstado);
		labelVida = new JLabel(crearStringVida());
		labelVida.setAlignmentX(CENTER_ALIGNMENT);
		labelVida.setForeground(Color.white);
		this.add(labelVida);
		this.setBackground(Color.black);
	}
	
	private String crearStringJugador(){
		Edificio edificio;
		boolean esTerrestre = true;
		edificio = (Edificio) Mapa.getMapa().getPosicionable(new Posicion(x, y, esTerrestre));
		return edificio.getJugador().getNombre();
	}

	private String crearStringEnConstruccion(){
		Edificio edificio;
		boolean esTerrestre = true;
		edificio = (Edificio) Mapa.getMapa().getPosicionable(new Posicion(x, y, esTerrestre));
		String turnos = new String(edificio.getTurnosQuePasaroDeConstruccion() + "/" + edificio.getTurnosEnConstruirse());
		String estado = (edificio.estaEnConstruccion()) ? "en construccion "+"("+turnos+")" : "construido";
		return new String("Estado: " + estado);
	}
	
	private String crearStringVida(){
		Edificio edificio;
		boolean esTerrestre = true;
		edificio = (Edificio) Mapa.getMapa().getPosicionable(new Posicion(x, y, esTerrestre));
		return new String("Vida: " + String.valueOf(edificio.getVidaActual()) + "/" +
							String.valueOf(edificio.getVidaMaxima()));
	}
}