package Test;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import BD.BD;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Usuario;


public class TestBD {
	
	
	
	//reiniar la bd en caso de quererlo asi, cambiar la base de datos tambien.
	
//	if (new File("DeustoOutlet.db").exists()) {
//		// Poner el parámetro a true si se quiere reiniciar la base de datos
//		BD.abrirlaconexion( "DeustoOutlet.db", false ); 
//	} else {
//		BD.abrirlaconexion( "DeustoOutlet.db", true ); 
//	}
	
	
	
	
	//Test para comprobar los codigos de los productos de la base de datos estan correctamente ordenados
		
	@Test
	public void getProductosTest() {
		ArrayList<Producto> lp = BD.getProductos();
		int antCod = 0;
		for (Producto p : lp) {
			assertFalse(p.getCodigo()<1);
			assertTrue( p.getCodigo() > antCod );
			antCod = p.getCodigo();
		}
	}
	
	
	//Test para comprobar los codigos de los pedidos de la base de datos estan correctamente ordenados
	
	@Test
	public void getPedidosTest () {
		ArrayList <Pedidos> listap = BD.getPedidos();
		int antCod = 0;
		for (Pedidos ped: listap) {
			assertFalse(ped.getCodigo()<1);
			assertTrue(ped.getCodigo()> antCod);
			antCod = ped.getCodigo();
		}
		
	}
	
	
	//Test para comprobar los productos se insertan correctamente a la base de datos
	
	@Test
	public void insertarProducto() {
		
		ArrayList<Producto> lp = BD.getProductos(); //productos que hay.

		assertTrue(lp.size()<= lp.get(lp.size()-1).getCodigo()); //El identificador de mayor valor (tamaño de la lista -1) es mayor o igual al número de la lista (size)
		assertTrue(BD.InsertarProducto(lp.get(0), null, 0)); //se puede poner la funcion porque esta devuelve un true si es correcta.
		 	
	}
	
//	@Test
//	public void insertarProducto() {
//		
//		ArrayList<Producto> lusu = BD.getProductos(); //productos que hay.
//
//		assertTrue(lp.size()<= lp.get(lp.size()-1).getCodigo()); //El identificador de mayor valor (tamaño de la lista -1) es mayor o igual al número de la lista (size)
//		assertTrue(BD.InsertarProducto(lp.get(0), null, 0)); //se puede poner la funcion porque esta devuelve un true si es correcta.
//		 	
//	}
	
	
		

		
		
		
		
		
		
	
	

	//BD.cerrarBD(); 

}
