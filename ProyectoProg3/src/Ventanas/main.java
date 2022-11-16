package Ventanas;

import java.util.ArrayList;

import Clasesprincipales.Colorc;
import Clasesprincipales.Franquicia;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
import Clasesprincipales.TipoProducto;

public class main {
	public static void main(String[] args) {
		Ventana_Portada vp =new Ventana_Portada();
		vp.setVisible(true);
		
		//Creacion productos
		ArrayList<Producto> lp = new ArrayList<>();
		Producto p1 = new Producto(1, "Chaqueta de pelo", 20, Colorc.NEGRO, Talla.XS, TipoProducto.CHAQUETAS, Franquicia.BERSHKA);
		lp.add(p1);
	}
}
