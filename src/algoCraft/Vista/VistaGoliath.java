package algoCraft.Vista;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Controlador.MouseListenerUnidad;

public class VistaGoliath extends PosicionVista {
	private static final long serialVersionUID = 1L;
	
	public VistaGoliath(PanelCentral panelCentral,int x, int y){
		super(Color.lightGray);
		try {
			BufferedImage myPicture = ImageIO.read(new File("recursos/imagenes/icoGoliath.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setAlignmentX(CENTER_ALIGNMENT);
			this.add(picLabel);
		} catch (IOException e) {}
		addMouseListener(new MouseListenerUnidad(panelCentral, x, y));
	}
}
