package algoCraft.Vista;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import algoCraft.construcciones.Base;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;


public class PanelDeSeleccionDeBase extends PanelDeSeleccionDeEdificios {

	private static final long serialVersionUID = 1L;
	private JLabel rangoDeConstruccion;

	public PanelDeSeleccionDeBase(int x, int y) {	
		super("Base ", x, y, crearLabelImagen());
		rangoDeConstruccion = new JLabel(crearStringRangoDeConstruccion());
		rangoDeConstruccion.setAlignmentX(CENTER_ALIGNMENT);
		rangoDeConstruccion.setForeground(Color.white);
		this.add(rangoDeConstruccion);
	}
	
	private String crearStringRangoDeConstruccion() {
		Base base;
		boolean esTerrestre = true;
		base = (Base) Mapa.getMapa().getPosicionable(new Posicion(x, y, esTerrestre));
		return new String("Rango de construccion: "+String.valueOf(base.getRangoConstruccion()));
	}

	private static JLabel crearLabelImagen() {
		JLabel picLabel = new JLabel();
		try {
			BufferedImage myPicture = ImageIO.read(new File("recursos/imagenes/base.png"));
			picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setAlignmentX(CENTER_ALIGNMENT);	
		} catch (IOException e) {}
		return picLabel;
	}
}