package Ventanas;

import java.util.ArrayList;

import BD.BD;
import Clasesprincipales.Colorc;
import Clasesprincipales.Franquicia;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
import Clasesprincipales.TipoProducto;


public class main {
	public static void main(String[] args) {
		BD bd = new BD();
		//hay que ponerle el nomrbe a la conexion. 
		bd.abrirlaconexion("BaseDatosprog",""); //al abrir la conexion se crean todos los datos y crear una ruta de fotos para llevarle ahi
		bd.cerrarBD();
		
		//Creacion productos
		ArrayList<Producto> lp = new ArrayList<>();
		//Producto p1 = new Producto(1, "Chaqueta de pelo", 20, Colorc.NEGRO, Talla.XS, TipoProducto.CHAQUETAS, Franquicia.BERSHKA);
		//lp.add(p1);
	}
}
