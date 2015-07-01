package algoCraft.Vista;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Controlador.MouseListenerPosicionDepositoSuministros;

public class VistaDepositoDeSuministros  extends PosicionVista{

	private static final long serialVersionUID = 1L;

	VistaDepositoDeSuministros(PanelCentral panelCentral,int x, int y){
		super(Color.lightGray);
		try {
			BufferedImage myPicture = ImageIO.read(new File("recursos/imagenes/icoDeposito.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setAlignmentX(CENTER_ALIGNMENT);
			this.add(picLabel);
		} catch (IOException e) {}
		addMouseListener(new MouseListenerPosicionDepositoSuministros(panelCentral, x, y));
	}
}
