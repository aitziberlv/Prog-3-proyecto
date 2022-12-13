/**
 * esta es una clase que esta creada unicamente para utilizarla en el test como prueba. 
 * 
 */

package DATOSDEPRUEBA;

import java.util.ArrayList;

import Clasesprincipales.Producto;

public class Datosdeprueba {
	/**
	 * Vamos a añadir algunos productos a la lista (10 productos concretamente, los 10 primeros )
	 * Algunos de ellos tendran el mismo URL porque se trata de el mismo producto pero en diferentes tallas por eso en la base de datos tienen tallas diferentes.
	 * 
	 * @return lista de productos de prueba que contiene una lista de productos que tienen su codigo y el URL de el producto
	 */
	public static ArrayList<Producto> conseguirDatosDePueba(){
		
		ArrayList<Producto> p=new ArrayList<>();
		Producto p1=new Producto(1,"");
		Producto p2=new Producto(2,"");
		Producto p3=new Producto(3,"");
		Producto p4=new Producto(4,"");
		Producto p5=new Producto(5,"");
		Producto p6=new Producto(6,"");
		Producto p7=new Producto(7,"");
		Producto p8=new Producto(8,"");
		Producto p9=new Producto(9,"");
		Producto p10=new Producto(10,"");
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
		p.add(p7);
		p.add(p8);
		p.add(p9);
		p.add(p10);
		
		return p;
	}
	
}
