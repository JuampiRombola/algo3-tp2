package algoCraft.Vista;

import java.awt.Color;

import javax.swing.JLabel;

import algoCraft.construcciones.EdificioConstructor;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class PanelDeSeleccionDeEdificioConstructor extends PanelDeSeleccionDeEdificios {

	private static final long serialVersionUID = 1L;
	private JLabel labelEstadoProduccion;

	public PanelDeSeleccionDeEdificioConstructor(String productor, int x, int y){
		super(productor, x, y);
		labelEstadoProduccion = new JLabel(crearStringProduccion());
		labelEstadoProduccion.setAlignmentX(CENTER_ALIGNMENT);
		labelEstadoProduccion.setForeground(Color.white);
		this.add(labelEstadoProduccion);
		this.setBackground(Color.black);
	}
	
	private String crearStringProduccion(){
		EdificioConstructor constructor;
		boolean esTerrestre = true;
		constructor = (EdificioConstructor) Mapa.getMapa().getUnidad(new Posicion(x, y, esTerrestre));
		String turnosConstruccion = " ("+constructor.getTurnosQuePasaroDeConstruccion()+"/"+constructor.getTurnosParaProducirUnidad()+")";
		if (constructor.getUnidadesEnCola() == 0)
			turnosConstruccion = "";
		if (constructor.estaEnConstruccion())
			return "";
		return new String("Unidades en cola: "+String.valueOf(constructor.getUnidadesEnCola()+turnosConstruccion));
	}
}