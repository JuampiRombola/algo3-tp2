package algoCraft.construcciones;


public class CreadorDeGoliath extends CreadorDeUnidades {
	private Fabrica fabrica;
	
	public CreadorDeGoliath(Fabrica fabrica) {
		this.fabrica = fabrica;
	}
	
	@Override
	public void crearUnidad() {
		this.fabrica.crearGoliath();
	}

}
