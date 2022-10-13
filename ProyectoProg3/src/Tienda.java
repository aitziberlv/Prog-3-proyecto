
public class Tienda {
	private Tipo prenda=Tipo.CAMISETAS;
	private Talla talla=Talla.L; 
	private Franquicia tienda=Franquicia.ZARA;
	
	public Tienda(Tipo prenda, Talla talla, Franquicia tienda) {
		super();
		this.prenda = prenda;
		this.talla = talla;
		this.tienda = tienda;
	}

	public Tipo getPrenda() {
		return prenda;
	}

	public void setPrenda(Tipo prenda) {
		this.prenda = prenda;
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public Franquicia getTienda() {
		return tienda;
	}

	public void setTienda(Franquicia tienda) {
		this.tienda = tienda;
	}

	@Override
	public String toString() {
		return "Compras [prenda=" + prenda + ", talla=" + talla + ", tienda=" + tienda + "]";
	}
	
}
