package Clasesprincipales;
public class Compra {
	private TipoProducto prenda = TipoProducto.CAMISETAS;
	private Talla talla=Talla.L;
	private Colorc color=Colorc.NARANJA;
	
	
	
	public Compra(TipoProducto prenda, Talla talla, Colorc color) {
		super();
		this.prenda = prenda;
		this.talla = talla;
		this.color = color;
	}

	public Colorc getColor() {
		return color;
	}

	public void setColor(Colorc color) {
		this.color = color;
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
		return "Compra [prenda=" + prenda + ", talla=" + talla + ", color=" + color + "]";
	}

	
	
}
