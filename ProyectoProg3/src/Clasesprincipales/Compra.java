package Clasesprincipales;
public class Compra {
	private TipoProducto prenda = TipoProducto.CAMISETAS;
	private Talla talla=Talla.L;
	
	
	
	public Compra(TipoProducto prenda, Talla talla) {
		super();
		this.prenda = prenda;
		this.talla = talla;
	}

	public TipoProducto getPrenda() {
		return prenda;
	}

	public void setPrenda(TipoProducto prenda) {
		this.prenda = prenda;
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	@Override
	public String toString() {
		return "Compras [prenda=" + prenda + ", talla=" + talla+"]";
	}
	
}
