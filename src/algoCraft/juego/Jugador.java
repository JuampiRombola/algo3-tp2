package algoCraft.juego;

import java.util.ArrayList;

import algoCraft.construcciones.Edificio;
import algoCraft.unidades.Unidad;

public class Jugador {
	static int cantidadInicialDeMineral = 200;
	static int cantidadInicialDeGasVespeno = 0;
	private String nombre;
	private int mineral;
	private int gasVespeno;
	private ArrayList<Edificio> edificios;
	private ArrayList<Unidad> unidades;
	

	Jugador(String nombre) {
		this.nombre = nombre;
		this.mineral = cantidadInicialDeMineral;
		this.gasVespeno = cantidadInicialDeGasVespeno;
		this.edificios = new ArrayList<Edificio>();
		this.unidades = new ArrayList<Unidad>();
	}
	
	public int getGasVespeno() {
		return this.gasVespeno;
	}
	
	public int getMineral() {
		return this.mineral;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public ArrayList<Unidad> getUnidades() {
		return this.unidades;
	}
	
	public ArrayList<Edificio> getEdificios() {
		return this.edificios;
	}
}
