package Ventanas;

import BD.BD;

public class main {

	public static void main(String[] args) {
		BD.initDatos();
		Ventana_Portada vp = new Ventana_Portada();
		vp.setVisible(true);
		
		

	}

}
