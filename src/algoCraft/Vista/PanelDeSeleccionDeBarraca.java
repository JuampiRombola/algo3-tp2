package algoCraft.Vista;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class PanelDeSeleccionDeBarraca extends PanelDeSeleccionDeEdificioConstructor {

	private static final long serialVersionUID = 1L;

	public PanelDeSeleccionDeBarraca(int x, int y) {	
		super("Barraca ", x, y, crearLabelImagen());
	}
	
	private static JLabel crearLabelImagen() {
		JLabel picLabel = new JLabel();
		try {
			BufferedImage myPicture = ImageIO.read(new File("recursos/imagenes/barraca.png"));
			picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setAlignmentX(CENTER_ALIGNMENT);	
		} catch (IOException e) {}
		return picLabel;
	}
}