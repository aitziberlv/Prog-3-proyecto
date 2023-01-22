package Logica;
/**
 * ESTA CLASE LLAMADA LOGICA LA VAMOS A UTILIZAR PARA GUARDAR TODOS LOS ADMINISTRADORES EN UN .DAT 
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import BD.BD;
import Clasesprincipales.Administrador;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Usuario;
import Ventanas.main;

public class Logica {
	
	/**
	 * Array list en el que tendremos todos los administradores de la aplicacion. 
	 */
	public static ArrayList<Administrador> u =new ArrayList<>();
	
	/**
	 * MEDIANTE ESTE METODO LO QUE HACEMOS ES INIZIALIZARLO CON LAS PERSONAS QUE SON ADMINISTRADORES DE NUESTRA APLICACION
	 */
	public static void inizializardat() {
		/**
		 * ESTO ES LO QUE NECESITA UN ADMINISTRADOR:
		 * (String contraseña, String usuario, String nombre, String dni, String fechNa, String telefono
		 * ,String direccion, String apellido, Habilidad hab)
		 */
		
		Administrador a1= new Administrador("admin","admin","Ander","78933459D","2001-03-06","677888434","Calle Almenda","Ruiz",15);
		Administrador a2= new Administrador("admin2","admin2","Marta","78936549D","2000-03-06","67784534","Calle de la Macarena","Perez",4);
		u.add(a1);
		u.add(a2);
		
				
	}
	
	/**
	 * Operaciones de lectura y escritura con el fichero para el array list de usuarios 
	 * @param fichero
	 */
	public static void escribir(String fichero) {
    	try {
    		inizializardat();
    		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fichero));
    		for(Administrador admin : u) {
    			oos.writeObject(admin);
    		}
			oos.close();
		}
    		
    	catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	
	public static void lectura(String fichero ) {	
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fichero));
			u.add((Administrador) ois.readObject());
			ois.close();
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		}
	}
	/**
	 * Funcion para iniciar sesion en la aplicacion 
	 * @param nombre
	 * @param contrasena
	 * @return devuelve un booleano que te dice si ese administrador esta o no esta en el array list . 
	 */
	public static boolean iniciar_sesion(String nombre,String contrasena) {
		
    	for (Administrador p:u) {
			if ( nombre.equals(p.getUsuario()) && contrasena.equals(p.getContraseña())) {
				return true;
				
		    }
		
        }
		return false;
    }
	/**
	 * dado un usuario devolver el administrador
	 */
	public static Administrador getadmin(String usuario) {
		for (Administrador a:u) {
			if ( usuario.equals(a.getUsuario())) {
				return a;
				
		    }
		}
		return null;
	}
	public static void main(String[] args) {
		escribir("Administradores.dat");
		lectura("Administradores.dat");
	}
	
	/**
	 * ademas en logica tambien nos ocuparemos  de llevar los estados de los pedidos
	 * @param fichero
	 * @param p
	 */
	
	public static void escribir_p(String fichero,Pedidos p) {
    	try {
    		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fichero));
    		
    			oos.writeObject(p);
    		
			oos.close();
		}
    		
    	catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	
	public static Pedidos lectura_p(String fichero) {	

		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fichero));
//			u.add((Administrador) ois.readObject());
			return (Pedidos) ois.readObject();
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
			return null;
		}
		
	}
}
