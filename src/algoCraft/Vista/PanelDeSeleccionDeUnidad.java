package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class PanelDeSeleccionDeUnidad extends JPanel {

	private static final long serialVersionUID = 1L;
	int x, y;
	private JLabel nombreDeLoSeleccionado;
	private JLabel labelVida, labelRangoMovimiento, lavelRangoAtaque, labelDanio, labelAtaco, labelSeMovio;

	public PanelDeSeleccionDeUnidad(int x, int y){
		this.x = x;
		this.y = y;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		nombreDeLoSeleccionado = new JLabel(crearStringUnidad() +"de "+ crearStringJugador());
		nombreDeLoSeleccionado.setForeground(Color.white);
		nombreDeLoSeleccionado.setAlignmentX(CENTER_ALIGNMENT);
		this.add(nombreDeLoSeleccionado);
		labelVida = new JLabel(crearStringVida());
		labelVida.setAlignmentX(CENTER_ALIGNMENT);
		labelVida.setForeground(Color.white);
		this.add(labelVida);
		labelDanio = new JLabel(crearStringDanio());
		labelDanio.setAlignmentX(CENTER_ALIGNMENT);
		labelDanio.setForeground(Color.white);
		this.add(labelDanio);
		lavelRangoAtaque = new JLabel(crearStringRangoAtaque());
		lavelRangoAtaque.setAlignmentX(CENTER_ALIGNMENT);
		lavelRangoAtaque.setForeground(Color.white);
		this.add(lavelRangoAtaque);
		labelRangoMovimiento = new JLabel(crearStringRangoMovimiento());
		labelRangoMovimiento.setAlignmentX(CENTER_ALIGNMENT);
		labelRangoMovimiento.setForeground(Color.white);
		this.add(labelRangoMovimiento);
		labelAtaco = new JLabel(crearStringAtaco());
		labelAtaco.setAlignmentX(CENTER_ALIGNMENT);
		labelAtaco.setForeground(Color.white);
		this.add(labelAtaco);
		labelSeMovio = new JLabel(crearStringSeMovio());
		labelSeMovio.setAlignmentX(CENTER_ALIGNMENT);
		labelSeMovio.setForeground(Color.white);
		this.add(labelSeMovio);
		this.setBackground(Color.black);
	}
	
	private String crearStringDanio(){
		Unidad unidad;
		boolean esTerrestre = true;
		unidad = (Unidad) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return "Ataque: " + String.valueOf(unidad.getDanio());
	}
	
	private String crearStringRangoAtaque(){
		Unidad unidad;
		boolean esTerrestre = true;
		unidad = (Unidad) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return "Rango de ataque: " + String.valueOf(unidad.getRango());
	}
	
	private String crearStringRangoMovimiento(){
		Unidad unidad;
		boolean esTerrestre = true;
		unidad = (Unidad) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return "Rango de movimiento: " + String.valueOf(unidad.getRangoMovimiento());
	}
	
	private String crearStringAtaco(){
		Unidad unidad;
		boolean esTerrestre = true;
		unidad = (Unidad) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		String ataque = "Ya ataco: ";
		if (unidad.puedeAtacar())
			return ataque+"No";
		return ataque+"Si";
	}
	
	private String crearStringSeMovio(){
		Unidad unidad;
		boolean esTerrestre = true;
		unidad = (Unidad) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		String movimiento = "Se movio: ";
		if (unidad.puedeMoverse())
			return movimiento+"No";
		return movimiento+"Si";
	}
	
	private String crearStringUnidad(){
		Unidad unidad;
		boolean esTerrestre = true;
		unidad = (Unidad) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		if (unidad.getClass() == (new Marine(null, 1, 1)).getClass())
			return "Marine ";
		return "Goliath ";
	}
	
	private String crearStringJugador(){
		Unidad unidad;
		boolean esTerrestre = true;
		unidad = (Unidad) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return unidad.getJugador().getNombre();
	}
	
	private String crearStringVida(){
		Unidad unidad;
		boolean esTerrestre = true;
		unidad = (Unidad) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		return new String("Vida: " + String.valueOf(unidad.getVidaActual()) + "/" +
							String.valueOf(unidad.getVidaMaxima()));
	}
}