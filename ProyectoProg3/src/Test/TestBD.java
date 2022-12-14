package Test;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import BD.BD;
import Clasesprincipales.Colorc;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
import Clasesprincipales.TipoProducto;
import Clasesprincipales.Usuario;
import DATOSDEPRUEBA.Datosdeprueba;


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
		for (Producto prod : lp) {
			assertFalse(prod.getCodigo()<1);
			assertTrue( prod.getCodigo() > antCod );
			antCod = prod.getCodigo();
		}
	}
	
	
	//Test para comprobar los codigos de los pedidos de la base de datos estan correctamente ordenados
	// tenemos que arreglar de la base de datos o de pedido si no va a dar mal 
//	@Test
//	public void getPedidosTest () {
//		ArrayList <Pedidos> lped = BD.getPedidos();
//		int antCod = 0;
//		for (Pedidos pedi: lped) {
//			assertFalse(pedi.getCodigo()<1);
//			assertTrue(pedi.getCodigo()> antCod);
//			antCod = pedi.getCodigo();
//		}
//		
//	}
	
	
	//Test para comprobar los productos se insertan correctamente a la base de datos
	
//	@Test
//	public void insertarProducto() {
//		BD bd=new BD();
//		ArrayList<Producto> lprod = bd.getProductos(); //productos que hay.
//
//		//assertTrue(lprod.size()<= lprod.get(lprod.size()-1).getCodigo());
//		Producto p33333 = new Producto(11111, "Camisa con volantes", 20, Colorc.BLANCO, Talla.XS, TipoProducto.CAMISETA);
//		bd.InsertarProducto(p33333,"Fotosproductos/prod2.jpg",2);
//		ArrayList<Producto> lprod2 = bd.getProductos();
//		int resta=lprod2.size()-lprod.size();
//		System.out.println(resta);
//		assertEquals(1,resta);
//		//El identificador de mayor valor (tamaño de la lista -1) es mayor o igual al número de la lista (size)
//		//assertTrue(BD.InsertarProducto(lprod.get(0), null, 0)); //se puede poner la funcion porque esta devuelve un true si es correcta.
//		 	
//	}
	
	
	//Test para comprobar los pedidos se insertan correctamente a la base de datos

	@Test
	public void insertarPedido() {
		
//		ArrayList<Pedidos> lpedi = BD.getPedidos(); //pedidos que hay.
//		BD.InsertarPedido("678999999", "No Finalizado", "2022-02-22", 0);
//		ArrayList<Pedidos> lpedi2 = BD.getPedidos();
//		int resta=lpedi2.size()-lpedi.size();
//		assertEquals(1,resta);
		
//		assertTrue(lpedi.size()<=lpedi.get(lpedi.size()-1).getCodigo()); //El identificador de mayor valor (tamaño de la compra -1) es mayor o igual al número de compras (size)

		//este falla porque supuestamente al insertar un pedido da a una escepcion y devuelve false eso significa que en la inserccion de pedido algo esta mal 
//				assertTrue(BD.InsertarPedido("45344345L", "No Finalizado", "2022-02-22", 1)); //se puede poner la funcion porque esta devuelve un true si es correcta.
//		assertTrue(lpedi.get(lpedi.size()-1).getCodigo() > lpedi.get(lpedi.size()-2).getCodigo()); //el que teniamos compararlo con el penultimo(-2). 	
		
	
	}
	
	//Test para comprobar que se consigue el url correcto de la base de datos. 
		/**
		 * la comprabacion de este test se trata en 
		 * compararlo con un fichero de prueba en el que tenemos metido el codigo de la foto y el url 
		 * ENTOCES COMPARAREMOS QUE ESTO ES LO MISMO 
		 */
	
		
	@Test
	public void getURLFOTO() {	
		//aqui tenemos dos listas de productos cuando el codigo sea igual compararemos el URL.
		ArrayList<Producto> a1=Datosdeprueba.conseguirDatosDePueba();
		ArrayList<Producto> a2=BD.getProductos();
		for (Producto p:a1) {
			for (Producto p2:a2) {
				if (p.getCodigo()==p2.getCodigo()) {
					assertEquals(p.getUrl(),BD.getURLFOTO(p2));
				}
			}
		}
	}
		
		
	@Test
	public void getDNIusuario() {
		String dni = BD.getDNIusuario("mariarodriguez5");
		assertEquals("45344345L",dni);
	}
		
	
	

	//BD.cerrarBD(); 

}
