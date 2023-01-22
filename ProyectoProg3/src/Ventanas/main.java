package Ventanas;

import BD.BD;
import Logica.Logica;

public class main {

	public static void main(String[] args) {
		
		/**
		 * ESTA ES GENERALMENTE LA VENTANA MEDIANTE LA CUAL VAMOS A PODER ENTRAR EN LA APLICACION :
		 * 
		 */
		/**
		 * EN CASO DE QUE ESTE CREADA LA BASE DE DATOS UNICAMENTE HAY QUE INICIAR LOS DATOS CON EL NOMBRE DE ESTA
		 */
		BD.initDatos("DeustoOutlet.db");
		/**
		 * SI LA BASE DE DATOS HAY QUE CREARLA DESE CERO DEBEREMOS DE INSERTAR TODA LA INFORMACION DE NUESTROS CLIENTES, PRODUCTOS, TIENDAS...
		 */
		//BD.insertarDatos();
		/**
		 * CON ESTO PODREMOS ENTRAR A LA APLICACION 
		 */
		
		/**
		 * LA APLICACION PUEDE SER USADA POR LOS ADMINISTRADORES DE LA APLICACION O POR LOS USUARIOS QUE QUIEREN COMPRAR PRODUCTOS. 
		 * SI ERES UN ADMINISTRADOR PUEDES ACCEDER CON EL USUARIO Y CONTRASEÑA DE : usuario=admin,contraseña=admin
		 * 
		 * SI ERES UN COMPRADOR, USUARIO, CLIENTE DE ESTA APLICACION PUEDES ACCEDER CON ALGUNO DE LOS SIGUENTES USUARIOS O TAMBIEN CREARTE UNO:
		 * usuario=mariarodriguez5 contraseña=mery456
		 * usuario=yero55 contraseña=trabajoprogram
		 */
		Ventana_Portada vp = new Ventana_Portada();
		vp.setVisible(true);
		vp.setExtendedState(Ventana_Portada.MAXIMIZED_BOTH);
		

	}

}
