
public class Compras {
	private Tipo prenda=Tipo.CAMISETAS;
	private Talla talla=Talla.L; 
	private Tienda tienda=Tienda.ZARA;
	
	public Compras(Tipo prenda, Talla talla, Tienda tienda) {
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

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	@Override
	public String toString() {
		return "Compras [prenda=" + prenda + ", talla=" + talla + ", tienda=" + tienda + "]";
	}
	
}
