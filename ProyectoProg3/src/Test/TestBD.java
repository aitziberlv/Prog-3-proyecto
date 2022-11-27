package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import BD.BD;
import Clasesprincipales.Producto;

public class TestBD {

	
	//Test para comprobar los codigos de los productos de la base de datos estan correctamente en or
	@Test
	public void getProductos() {
		ArrayList<Producto> lp = BD.getProductos();
		int antCod = 0;
		for (Producto p : lp) {
			assertTrue( p.getCodigo() > antCod );
			antCod = p.getCodigo();
		}
	}

}
