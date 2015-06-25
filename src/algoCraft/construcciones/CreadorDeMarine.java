package algoCraft.construcciones;


public class CreadorDeMarine extends CreadorDeUnidades {
	private Barraca barraca;

	public CreadorDeMarine(Barraca barraca) {
		this.barraca = barraca;
	}
	
	@Override
	public void crearUnidad() {
		this.barraca.crearMarine();
	}

}
