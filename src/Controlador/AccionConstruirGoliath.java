package Controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import algoCraft.construcciones.Fabrica;
import algoCraft.juego.Juego;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.NoSePuedeConstruirLaUnidadPorSobrepoblacion;
import algoCraft.juego.excepciones.NoSeTienenLosRecursosSuficientes;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class AccionConstruirGoliath extends AbstractAction{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Juego juego;
	
	public AccionConstruirGoliath(Juego juego, int x, int y){
		super("Construir");
		this.x = x;
		this.y = y;
		this.juego = juego;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual;
		jugadorActual = juego.getJugadorActual();
		Posicion posicion = new Posicion(x, y, true);
		Fabrica fabrica = (Fabrica) Mapa.getMapa().getUnidad(posicion);
		try{
			jugadorActual.crearGoliath(fabrica);
		}catch(NoSePuedeConstruirLaUnidadPorSobrepoblacion e) {
			  JOptionPane.showMessageDialog(null, "No se tienen suficientes suministros", "Error",
                      JOptionPane.ERROR_MESSAGE);
		}catch(NoSeTienenLosRecursosSuficientes e) {
			  JOptionPane.showMessageDialog(null, "No se tienen suficientes recursos", "Error",
                      JOptionPane.ERROR_MESSAGE);
		}
	}
}
