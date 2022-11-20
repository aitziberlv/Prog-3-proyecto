package Logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Clasesprincipales.Usuario;
import Ventanas.main;

public class Logica {
	
	/**
	 * Array list en el que tendremos todos los usuarios de la aplicacion. 
	 */
	private ArrayList<Usuario> u =new ArrayList<>();
	/**
	 * @author aiitz
	 * Operaciones de lectura y escritura con el fichero para el array list de usuarios 
	 * @param fichero
	 */
	public void escribir(String fichero) {
    	try {
    		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fichero));
    		oos.writeObject(u);
			oos.close();
		}
    		
    	catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	public  void lectura(String fichero ) {	
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fichero));
			ArrayList<Usuario> cargado= (ArrayList<Usuario>) ois.readObject();
			u = cargado;
			ois.close();
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		}
	}
	/**
	 * Funcion para iniciar sesion en la aplicacion 
	 * @param nombre
	 * @param contrasena
	 * @return devuelve un booleano que te dice si ese usuario esta o no esta en el array list . 
	 */
	public boolean iniciar_sesion(String nombre,String contrasena) {
		
    	for (Usuario p:u) {
			if ( nombre.equals(p.getNombre()) && contrasena.equals(p.getContraseña())) {
				return true;
				
		    }
		
        }
		return false;
    }
	/**
	 * 
	 */
	public boolean registrarte(String usuario) {
		for (Usuario uu:u) {
			if (uu.getUsuario()==usuario) {
				return false;
			}else {
				/**
				 * comprobar validez de los datos. 
				 */
		}
			
		}
		return true;
	}
	
	public void registrar(String usuario, String correo,String nombre,String apellido,String telefono,String contraseña) {
		Usuario us=new Usuario(nombre,null,null,telefono,apellido,null,contraseña,usuario);
		u.add(us);
		escribir("Usuarios.dat");
	}
	public static void main(String[] args) {
		/**
		 * creacion de usuario para que se inicialize con un usuario ya hecho. 
		 */
	}
}
