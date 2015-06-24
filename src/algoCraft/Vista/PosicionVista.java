package algoCraft.Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PosicionVista extends JPanel{

	private static final long serialVersionUID = 1L;
	private Color color;
	
	PosicionVista(Color color){
		this.color = color;
	}
	
	public void paintComponent(Graphics grafico) {
        super.paintComponent(grafico);
        grafico.setColor(color);
        Dimension dimension = getSize();
        grafico.fill3DRect(0, 0, dimension.width, dimension.height, true);
    }
	

    @Override
    public Dimension getPreferredSize() {
    	return new Dimension(25, 25);
    }
    
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
