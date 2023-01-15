package Ventanas;

import BD.BD;
import Logica.Logica;

public class main {

	public static void main(String[] args) {
		//BD.initDatos();
		Ventana_Portada vp = new Ventana_Portada();
		vp.setVisible(true);
		vp.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);
		Logica.inizializardat();
		

	}

}
