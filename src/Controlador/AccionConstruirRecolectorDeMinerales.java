package Controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import algoCraft.juego.Juego;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.ConstruccionFueraDeRangoException;
import algoCraft.juego.excepciones.NoSeTienenLosRecursosSuficientes;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.Mineral;

public class AccionConstruirRecolectorDeMinerales  extends AbstractAction{

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Juego juego;
	
	public AccionConstruirRecolectorDeMinerales(Juego juego, int x, int y){
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
		//Es feo castear. Pero estoy seguro que en esa posicion hay un mineral.
		Mapa mapa = Mapa.getMapa();
		Mineral mineral = (Mineral) mapa.getPosicionable(posicion);
		try{
			jugadorActual.crearCentroDeMineral(mineral);
		}catch(ConstruccionFueraDeRangoException e) {
			 JOptionPane.showMessageDialog(null, "No se puede construir fuera del rango de construccion que indica la Base", "Error",
                    JOptionPane.ERROR_MESSAGE);
		}catch(NoSeTienenLosRecursosSuficientes e){
			 JOptionPane.showMessageDialog(null, "No se tienen suficientes recursos", "Error",
                     JOptionPane.ERROR_MESSAGE);
		}
	}

}
