package algoCraft.Vista;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Controlador.MouseListenerPosicionGas;

public class VistaGasVespeno extends PosicionVista {

	private static final long serialVersionUID = 1L;

	VistaGasVespeno(PanelCentral panelCentral,int x, int y){
		super(Color.green);
		try {
			BufferedImage myPicture = ImageIO.read(new File("recursos/imagenes/icoGas.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setAlignmentX(CENTER_ALIGNMENT);
			this.add(picLabel);
		} catch (IOException e) {}
		addMouseListener(new MouseListenerPosicionGas(panelCentral, x, y));
	}
}
