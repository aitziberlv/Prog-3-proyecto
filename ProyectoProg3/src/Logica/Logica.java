package Logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Clasesprincipales.Usuario;

public class Logica {
	private ArrayList<Usuario> u =new ArrayList<>();
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
	public boolean iniciar_sesion(String nombre,String contrasena) {
		
    	for (Usuario p:u) {
			if ( nombre.equals(p.getNombre()) && contrasena.equals(p.getContraseña())) {
				return true;
				
		    }
		
        }
		return false;
    }
	
}
