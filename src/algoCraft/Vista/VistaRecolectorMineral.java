package algoCraft.Vista;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Controlador.MouseListenerPosicionCentroMineral;

public class VistaRecolectorMineral extends PosicionVista{
	
	private static final long serialVersionUID = 1L;
	
	public VistaRecolectorMineral(PanelCentral panelCentral,int x, int y){
		super(Color.lightGray);
		try {
			BufferedImage myPicture = ImageIO.read(new File("recursos/imagenes/icoCentroMineral.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setAlignmentX(CENTER_ALIGNMENT);
			this.add(picLabel);
		} catch (IOException e) {}
		addMouseListener(new MouseListenerPosicionCentroMineral(panelCentral, x, y));
	}
}
