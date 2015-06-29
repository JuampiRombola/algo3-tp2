package algoCraft.Vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import algoCraft.construcciones.Base;
import algoCraft.construcciones.CentroDeMineral;
import algoCraft.construcciones.Refineria;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.Posicionable;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;

public class VistaMapa extends JPanel implements Observer{
	
	@SuppressWarnings({ "rawtypes" })
	private HashMap<Class, Graficador> graficadores = new HashMap<Class, Graficador>();

	private PanelCentral panelCentral;
	
	private static final long serialVersionUID = 1L;
	//Practica con el tamanio de las cosas, todavia no muestra el mapa real.
	public VistaMapa(PanelCentral panelCentral){
		this.panelCentral = panelCentral;
		this.setBackground(Color.black);
		Mapa mapa = Mapa.getMapa();
		mapa.addObserver(this);
	    this.setLayout(new GridBagLayout());
	    this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		boolean booleano = true;
		GridBagConstraints gbc = new GridBagConstraints();
		for (int i = 1; i <= mapa.getAncho(); i++){
			for (int j = 1; j <= mapa.getAlto(); j++){
				Color color = (booleano) ? Color.blue : Color.green;
				PosicionVista cuadrado = new PosicionVista(color);
				booleano = !booleano;
				gbc.gridx = i;
                gbc.gridy = j;
                this.add(cuadrado, gbc);
			}
		}
		agregarGraficadoresExistentes();
	}
	
	private void agregarGraficadoresExistentes(){
		graficadores.put(GasVespeno.class, new GraficadorGasVespeno());
		graficadores.put(Mineral.class, new GraficadorMineral());
		graficadores.put(Base.class, new GraficadorBase());
		graficadores.put(CentroDeMineral.class, new GraficadorRecolectorMinerales());
		graficadores.put(Refineria.class, new GraficadorRefineria());
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.removeAll();
		this.setLayout(new GridBagLayout());
		Mapa mapa = Mapa.getMapa();
		for (int i = 1; i <= mapa.getAncho(); i++){
			for (int j = 1; j <= mapa.getAlto(); j++){
				Posicion posicionRevisada = new Posicion(i,j, true);
                agregarVistaDe(posicionRevisada);
			}
		}
		this.revalidate();
		this.repaint(); 
	}

	private void agregarVistaDe(Posicion posicion) {
		Mapa mapa = Mapa.getMapa();
		Graficador graficador;
		GridBagConstraints gbc = new GridBagConstraints();
		//La parte visual tiene la primera posicion en 0, el mapa cuenta desde 1.
		gbc.gridx = posicion.getX() - 1;
		gbc.gridy = posicion.getY() - 1;
		if(mapa.posicionEstaOcupada(posicion)){
			Posicionable posicionable = mapa.getUnidad(posicion);
			if(graficadores.containsKey(posicionable.getClass())){
				graficador = graficadores.get(posicionable.getClass());
				this.add(graficador.getPosicionVista(panelCentral, posicion.getX(), posicion.getY()), gbc);
			}
		}else{
			this.add(new VistaPosicionVacia(panelCentral, posicion.getX(),posicion.getY()), gbc);
		}
	}
}
