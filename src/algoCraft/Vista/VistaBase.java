package algoCraft.Vista;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Controlador.MouseListenerPosicionBase;

public class VistaBase extends PosicionVista{

	private static final long serialVersionUID = 1L;

	VistaBase(PanelCentral panelCentral,int x, int y){
		super(Color.lightGray);
		try {
			BufferedImage myPicture = ImageIO.read(new File("recursos/imagenes/icoBase.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setAlignmentX(CENTER_ALIGNMENT);
			this.add(picLabel);
		} catch (IOException e) {}
		addMouseListener(new MouseListenerPosicionBase(panelCentral, x, y));
	}
}
