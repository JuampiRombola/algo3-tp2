package Controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import algoCraft.juego.Juego;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.juego.excepciones.NoSeTienenLosRecursosSuficientes;

public class AccionConstruirEdificioDeProduccion extends AbstractAction{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Juego juego;
	private JComboBox<String> comboBox;
	
	public AccionConstruirEdificioDeProduccion(Juego juego, JComboBox<String> comboBox, int x, int y){
		super("Construir");
		this.x = x;
		this.y = y;
		this.juego = juego;
		this.comboBox = comboBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual;
		jugadorActual = juego.getJugadorActual();
		try{
			if (comboBox.getSelectedItem() == "Barraca")
				jugadorActual.crearBarraca(x, y);
			if(comboBox.getSelectedItem() == "Fabrica")
				jugadorActual.crearFabrica(x, y);
			if(comboBox.getSelectedItem() == "Deposito de suministros")
				jugadorActual.crearDepositoDeSuministros(x, y);
		}catch(NoSeTienenLosRecursosSuficientes e){
			 JOptionPane.showMessageDialog(null, "No se tienen suficientes recursos", "Error",
                     JOptionPane.ERROR_MESSAGE);
		}catch(NoSePuedeConstruirElEdificio e){
			JOptionPane.showMessageDialog(null, "Se necesita una barraca construida", "Error",
                    JOptionPane.ERROR_MESSAGE);
		}
	}
}
