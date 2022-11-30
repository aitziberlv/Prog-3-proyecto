package Test;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import BD.BD;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Usuario;
import examen.parc202211.data.ServicioPersistenciaDeustoBeReal;
import examen.parc202211.data.ServicioPersistenciaFicheros;


public class TestBD {
	
	//Test para comprobar los codigos de los productos de la base de datos estan correctamente ordenados
		
	
	@Test
	public void getProductosTest() {
		
		//reiniar la bd en caso de quererlo asi, cambiar la base de datos tambien.
		
//		if (new File("DeustoOutlet.db").exists()) {
//			// Poner el parámetro a true si se quiere reiniciar la base de datos
//			BD.abrirlaconexion( "DeustoOutlet.db", false ); 
//		} else {
//			BD.abrirlaconexion( "DeustoOutlet.db", true ); 
//		}
		
		ArrayList<Producto> lp = BD.getProductos();
		int antCod = 0;
		for (Producto p : lp) {
			assertFalse(p.getCodigo()<1);
			assertTrue( p.getCodigo() > antCod );
			antCod = p.getCodigo();
		}
	}
	
	@Test
	public void insertarProducto() {
		
		ArrayList<Producto> lp = BD.getProductos(); //productos que hay.

		assertTrue(lp.size()<= lp.get(lp.size()-1).getCodigo()); //El identificador de mayor valor (tamaño de la lista -1) es mayor o igual al número de la lista (size)
		assertTrue(BD.InsertarProducto(lp.get)); //se puede poner la funcion porque esta devuelve un true si es correcta.
		 	
	}
	
	@Test
	public void insertarUsuarioTest () {

		Usuario usuario1 = new Usuario( "mery456", "mariarodriguez5",  );
		//ServicioPersistenciaDeustoBeReal servicio = new ServicioPersistenciaFicheros();
		//servicio.initDatosTest(  );
		//assertFalse(servicio.insertarUsuario(usuario1));
		}
		
	}
		
//	@Test
//	public void insertarPedido() {
//		
//		ArrayList<Producto> lp = BD.getProductos(); //productos que hay.
//		ArrayList<Pedidos> lped = BD.getPedidos(lped); //pedidos que hay.
//
//		assertTrue(lped.size()<=lped.get(lped.size()-1).getCodigo()); //El identificador de mayor valor (tamaño del pedido -1) es mayor o igual al número de pedidos (size)
//		assertTrue(BD.insertarPedido(lped.get(0))); //se puede poner la funcion porque esta devuelve un true si es correcta.
//		
//	}
		
		
		
		
		
		
	
	

	//BD.cerrarBD(); 

}
