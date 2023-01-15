package BD;
/**
 * esta es nuestra base de datos la cual esta fromada de un moton de metodos mediante los cuales podemos recuperar informacion de esta 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import Clasesprincipales.Colorc;
import Clasesprincipales.Franquicia;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
import Clasesprincipales.Tienda;
import Clasesprincipales.TipoProducto;
import Clasesprincipales.Usuario;

public class BD {
	private static Exception lastError = null;
	private static Logger logger = Logger.getLogger( "BD" );
	private static Connection conn;

	static HashMap<Integer,Producto> mapa= new HashMap<>();
	private static TreeMap<String, Usuario> mapaUsuarios = new TreeMap<>();
	public static Map<Integer, Producto> mapaProductos = new HashMap<>();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * PRIMERP TODA LA INICIALIZACION DE LA BASE DE DATOS Y DEMAS 
	 */
	
	/** Inicializa una BD SQLITE y devuelve una conexiï¿½n con ella
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexiï¿½n con la base de datos indicada. Si hay algï¿½n error, se devuelve null
	 */
	public static Statement abrirlaconexion( String nombreBD) throws SQLException{ 
		try 
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection( "jdbc:sqlite:" + nombreBD);
				 
			Statement st = conn.createStatement();
			logger.log( Level.INFO, "Conectada base de datos " + nombreBD );
			return st;
			
		} catch (ClassNotFoundException e) {
			lastError = e;
			logger.log( Level.SEVERE, "Error en conexión de base de datos " + nombreBD, e );
			e.printStackTrace();
			return null;
		}
	}
	
	/** Devuelve statement para usar la base de datos
	 * @param con	Conexiï¿½n ya creada y abierta a la base de datos
	 * @return	sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	public static Statement usarBD( Connection con ) {
		try {
			Statement stm = con.createStatement();
			stm.setQueryTimeout(30);  // poner timeout 30 msg
			return stm;
		} catch (SQLException e) {
			lastError = e;
			logger.log( Level.SEVERE, "Error en uso de base de datos", e );
			e.printStackTrace();
			return null;
		}
	}
	
	
	/** Cierra la base de datos abierta
	 * @param con	Conexiï¿½n abierta de la BD
	 * @param st	Sentencia abierta de la BD
	 */
	public static void cerrarBD() {
		try {
			conn.close();
			logger.log( Level.INFO, "Cierre de base de datos" );
		} catch (SQLException e) {
			lastError = e;
			logger.log( Level.SEVERE, "Error en cierre de base de datos", e );
			e.printStackTrace();
		}
	}
	
	
	
    public static void initDatos() { //mirar porque igual tenemos que cambiar la relacion de producto y de pedido . 
		
		try {
			
			String com = "create table usuario(nombre String, dni String, fechNa String, telefono String, direccion String, apellido String, contraseña String, usuario String)";
			Statement stmt = abrirlaconexion("DeustoOutlet.db");
			stmt.executeUpdate("drop table if exists usuario");
			int veces1=stmt.executeUpdate(com);
			if (veces1==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com4 = "create table pedido(codigo_pedido INTEGER primary key autoincrement, dni String, estado String, fecha_compra String, codigo_producto String)"; //clave externa del dni del usuario. 
			stmt.executeUpdate("drop table if exists pedido");
			int veces2=stmt.executeUpdate(com4);
			if (veces2==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com2 = "create table tienda(codigo_tienda INTEGER ,  franquicia String, foto String)";
			stmt.executeUpdate("drop table if exists tienda");
			int veces3=stmt.executeUpdate(com2);
			if (veces3==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com3 = "create table producto(codigo_producto INTEGER primary key autoincrement, nombre String, precio integer, color String, talla String, tipo String , ruta_foto String, codigo_tienda integer, cantidad integer)";
			stmt.executeUpdate("drop table if exists producto");
			int veces4=stmt.executeUpdate(com3);
			if (veces4==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			/**
			 * AHORA VAMOS INSERTAR EN LA BASE DE DATOS TODOS LOS DATOS NECESARIOS PARA NUESTRA APLICACION
			 */
			
			/**
			 * INSERCION DE USUARIOS
			 */
			
			/**
			 * INSERCION DE USUARIOS QUE NO SON ADMINISTRADORES
			 * la insercion de los usuarios que no son administradores la haremos en la ventana de logica y los almacenaremos en un .dat
			 */
			InsertarUsuario(new Usuario("Maria", "45344345L", "13/04/1997", "767665543", "Calle Rodrigez Arias", "Rodriguez", "mery456", "mariarodriguez5"));
			InsertarUsuario(new Usuario("Aritz", "74544345L", "10/10/2003", "644665543", "Calle De Mar", "Yero", "trabajoprogram", "yero55"));
			InsertarUsuario(new Usuario("Lucia", "68458021D", "03/08/1970", "688475093", "Calle Sabino Arana", "Lopez", "luciita", "lopez70"));
			InsertarUsuario(new Usuario("Mario", "45345678H", "18/01/2000", "76893023", "Calle Licenciado Pozas", "Martin", "marioooM", "mariarodriguez5"));
			InsertarUsuario(new Usuario("Iker", "78302745L", "20/12/2006", "644684014", "Calle Doctor Areilza", "Sangrador", "isang", "ikertxu"));
			InsertarUsuario(new Usuario("Mar", "70348021M", "02/03/1979", "940675093", "Calle Sabino Arana", "Garcia", "delmar10", "margar"));
			InsertarUsuario(new Usuario("Javier", "45345892A", "05/11/1993", "936195803", "Calle Rodriguez Arias", "Amezua", "javiertxin", "javi93"));
			InsertarUsuario(new Usuario("Marina", "78370236N", "06/04/1999", "540164926", "Calle la Laguna", "Nogales", "nogalmar", "marinanogales"));
			InsertarUsuario(new Usuario("Arrate", "5093105M", "07/07/2004", "567302178", "Calle Gran Via", "Calvente", "arracal", "arrate04"));
			
			InsertarUsuario(new Usuario("Iñigo", "74902345J", "14/03/1998", "849264910", "Calle Alcala", "Eguiluz", "ieguiluz", "igms09"));
			InsertarUsuario(new Usuario("Aimar", "74920473K", "11/09/2002", "763620942", "Calle Larios", "Iglesias", "aimigles", "aimar80"));
			InsertarUsuario(new Usuario("Sara", "62057921L", "04/07/1971", "734902347", "Calle de los Ciegos", "de la Ydalga", "sariydalg", "sara71"));
			InsertarUsuario(new Usuario("Jorge", "74820512M", "19/05/2008", "012456892", "Calle Betis", "Martin", "jorge93", "90jorde08"));
			InsertarUsuario(new Usuario("Lucas", "72950639N", "21/11/2003", "094123784", "Calle Arolantxa", "San Juan", "sanLucas", "20lucas"));
			InsertarUsuario(new Usuario("Xabier", "75920576H", "03/02/1972", "648902347", "Calle Autonomia", "Garcia", "xabi32", "xabi47"));
			InsertarUsuario(new Usuario("Daniel", "29576923S", "06/10/1990", "169502934", "Calle Astarloa", "Martinez", "daniMart", "09jans"));
			InsertarUsuario(new Usuario("Martina", "83068219S", "07/05/1995", "758934023", "Calle Gran Via", "Puente", "martinPu", "martiti"));
			InsertarUsuario(new Usuario("Daniela", "85234681I", "08/06/2001", "756902347", "Calle Doctor Areilza", "Diez", "dadiez", "daninueve"));

			/**
			 * INSERCION DE LOS PRODUCTOS
			 */
			/**
			 * PRODUCTO1
			 */
			Producto p1 = new Producto("Chaqueta de pelo", 50, Colorc.NEGRO, Talla.XS, TipoProducto.CHAQUETA);
			Producto p11 = new Producto("Chaqueta de pelo", 50, Colorc.NEGRO, Talla.S, TipoProducto.CHAQUETA);
			Producto p111= new Producto("Chaqueta de pelo", 50, Colorc.NEGRO, Talla.M, TipoProducto.CHAQUETA);
			Producto p1111 = new Producto("Chaqueta de pelo", 50, Colorc.NEGRO, Talla.L, TipoProducto.CHAQUETA);
			Producto p11111 = new Producto("Chaqueta de pelo", 50, Colorc.NEGRO, Talla.XL, TipoProducto.CHAQUETA);
			InsertarProducto(p1,"Fotosproductos/prod1.jpg",1);
			InsertarProducto(p11,"Fotosproductos/prod1.jpg",1);
			InsertarProducto(p111,"Fotosproductos/prod1.jpg",1);
			InsertarProducto(p1111,"Fotosproductos/prod1.jpg",1);
			InsertarProducto(p11111,"Fotosproductos/prod1.jpg",1);
			
			/**
			 * PRODUCTO2
			 */
			Producto p2 = new Producto("Pantalones vaqueros", 15, Colorc.AZUL, Talla.XS, TipoProducto.PANTALON);
			Producto p22 = new Producto("Pantalones vaqueros", 15, Colorc.AZUL, Talla.S, TipoProducto.PANTALON);
			Producto p222 = new Producto("Pantalones vaqueros", 15, Colorc.AZUL, Talla.M, TipoProducto.PANTALON);
			Producto p2222 = new Producto("Pantalones vaqueros", 15, Colorc.AZUL, Talla.L, TipoProducto.PANTALON);
			Producto p22222 = new Producto("Pantalones vaqueros", 15, Colorc.AZUL, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p2,"Fotosproductos/prod2.jpg",2);
			InsertarProducto(p22,"Fotosproductos/prod2.jpg",2);
			InsertarProducto(p222,"Fotosproductos/prod2.jpg",2);
			InsertarProducto(p2222,"Fotosproductos/prod2.jpg",2);
			InsertarProducto(p22222,"Fotosproductos/prod2.jpg",2);
			
			/**
			 * PRODUCTO3
			 */
			Producto p3 = new Producto("Camisa con volantes", 20, Colorc.BLANCO, Talla.XS, TipoProducto.CAMISETA);
			Producto p33 = new Producto("Camisa con volantes", 20, Colorc.BLANCO, Talla.S, TipoProducto.CAMISETA);
			Producto p333 = new Producto("Camisa con volantes", 20, Colorc.BLANCO, Talla.M, TipoProducto.CAMISETA);
			Producto p3333 = new Producto("Camisa con volantes", 20, Colorc.BLANCO, Talla.L, TipoProducto.CAMISETA);
			Producto p33333 = new Producto("Camisa con volantes", 20, Colorc.BLANCO, Talla.XL, TipoProducto.CAMISETA);
			InsertarProducto(p3,"Fotosproductos/prod3.jpg",3);
			InsertarProducto(p33,"Fotosproductos/prod3.jpg",3);
			InsertarProducto(p333,"Fotosproductos/prod3.jpg",3);
			InsertarProducto(p3333,"Fotosproductos/prod3.jpg",3);
			InsertarProducto(p33333,"Fotosproductos/prod3.jpg",3);
			
			
			/**
			 * PRODUCTO4
			 */	
			Producto p4 = new Producto("Pantalones parachute", 30, Colorc.NEGRO, Talla.XS,TipoProducto.PANTALON);
			Producto p44 =new Producto("Pantalones parachute", 30, Colorc.NEGRO, Talla.S,TipoProducto.PANTALON);
			Producto p444 =new Producto("Pantalones parachute", 30, Colorc.NEGRO, Talla.M,TipoProducto.PANTALON);
			Producto p4444 =new Producto("Pantalones parachute", 30, Colorc.NEGRO, Talla.L,TipoProducto.PANTALON);
			Producto p44444 =new Producto("Pantalones parachute", 30, Colorc.NEGRO, Talla.XL,TipoProducto.PANTALON);			
			InsertarProducto(p4,"Fotosproductos/prod4.jpg",4);
			InsertarProducto(p44,"Fotosproductos/prod4.jpg",4);
			InsertarProducto(p444,"Fotosproductos/prod4.jpg",4);
			InsertarProducto(p4444,"Fotosproductos/prod4.jpg",4);
			InsertarProducto(p44444,"Fotosproductos/prod4.jpg",4);
			
			/**
			 * PRODUCTO5
			 * MISMO QUE EL PRODUCTO 4 PERO EN DIFERENTE COLOR
			 */
			Producto p5 = new Producto("Pantalones parachute", 30, Colorc.VERDE, Talla.XS,TipoProducto.PANTALON);
			Producto p55 =new Producto("Pantalones parachute", 30, Colorc.VERDE, Talla.S,TipoProducto.PANTALON);
			Producto p555 =new Producto("Pantalones parachute", 30, Colorc.VERDE, Talla.M,TipoProducto.PANTALON);
			Producto p5555=new Producto("Pantalones parachute", 30, Colorc.VERDE, Talla.L,TipoProducto.PANTALON);
			Producto p55555 =new Producto("Pantalones parachute", 30, Colorc.VERDE, Talla.XL,TipoProducto.PANTALON);			
			InsertarProducto(p5,"Fotosproductos/prod5.jpg",4);
			InsertarProducto(p55,"Fotosproductos/prod5.jpg",4);
			InsertarProducto(p555,"Fotosproductos/prod5.jpg",4);
			InsertarProducto(p5555,"Fotosproductos/prod5.jpg",4);
			InsertarProducto(p55555,"Fotosproductos/prod5.jpg",4);
			
			/**
			 * PRODUCTO6
			 * MISMO QUE EL PRODUCTO 4 PERO EN DIFERENTE COLOR
			 */
			Producto p6 = new Producto("Pantalones parachute", 30, Colorc.GRIS, Talla.XS,TipoProducto.PANTALON);
			Producto p66 =new Producto("Pantalones parachute", 30, Colorc.GRIS, Talla.S,TipoProducto.PANTALON);
			Producto p666 =new Producto("Pantalones parachute", 30,Colorc.GRIS, Talla.M,TipoProducto.PANTALON);
			Producto p6666=new Producto("Pantalones parachute", 30, Colorc.GRIS, Talla.L,TipoProducto.PANTALON);
			Producto p66666 =new Producto("Pantalones parachute", 30, Colorc.GRIS, Talla.XL,TipoProducto.PANTALON);			
			InsertarProducto(p6,"Fotosproductos/prod6.jpg",4);
			InsertarProducto(p66,"Fotosproductos/prod6.jpg",4);
			InsertarProducto(p666,"Fotosproductos/prod6.jpg",4);
			InsertarProducto(p6666,"Fotosproductos/prod6.jpg",4);
			InsertarProducto(p66666,"Fotosproductos/prod6.jpg",4);
			
			/**
			 * PRODUCTO7
			 */
			Producto p7 = new Producto("Blusa con boton delantero", 35, Colorc.BLANCO, Talla.XS, TipoProducto.CAMISETA);
			Producto p77 = new Producto("Blusa con boton delantero", 35, Colorc.BLANCO, Talla.S, TipoProducto.CAMISETA);
			Producto p777 = new Producto("Blusa con boton delantero", 35, Colorc.BLANCO, Talla.M, TipoProducto.CAMISETA);
			Producto p7777 = new Producto("Blusa con boton delantero", 35, Colorc.BLANCO, Talla.L, TipoProducto.CAMISETA);
			Producto p77777 = new Producto("Blusa con boton delantero", 35, Colorc.BLANCO, Talla.XL, TipoProducto.CAMISETA);
			InsertarProducto(p7,"Fotosproductos/prod7.jpg",5);
			InsertarProducto(p77,"Fotosproductos/prod7.jpg",5);
			InsertarProducto(p777,"Fotosproductos/prod7.jpg",5);
			InsertarProducto(p7777,"Fotosproductos/prod7.jpg",5);
			InsertarProducto(p77777,"Fotosproductos/prod7.jpg",5);
			
			/**
			 * PRODUCTO8
			 */
			Producto p8 = new Producto("Vestido de verano", 29, Colorc.BLANCO, Talla.XS, TipoProducto.VESTIDO);
			Producto p88 = new Producto("Vestido de verano", 29, Colorc.BLANCO, Talla.S, TipoProducto.VESTIDO);
			Producto p888 = new Producto("Vestido de verano", 29, Colorc.BLANCO, Talla.M, TipoProducto.VESTIDO);
			Producto p8888 = new Producto("Vestido de verano", 29, Colorc.BLANCO, Talla.L, TipoProducto.VESTIDO);
			Producto p88888 = new Producto("vestido de verano", 29, Colorc.BLANCO, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p8,"Fotosproductos/prod8.jpg",6);
			InsertarProducto(p88,"Fotosproductos/prod8.jpg",6);
			InsertarProducto(p888,"Fotosproductos/prod8.jpg",6);
			InsertarProducto(p8888,"Fotosproductos/prod8.jpg",6);
			InsertarProducto(p88888,"Fotosproductos/prod8.jpg",6);
			
			/**
			 * PRODUCTO9
			 */
			Producto p9 = new Producto("Vestido de verano", 29, Colorc.AMARILLO, Talla.XS, TipoProducto.VESTIDO);
			Producto p99 = new Producto("Vestido de verano", 29, Colorc.AMARILLO, Talla.S, TipoProducto.VESTIDO);
			Producto p999 = new Producto("Vestido de verano", 29, Colorc.AMARILLO, Talla.M, TipoProducto.VESTIDO);
			Producto p9999 = new Producto("Vestido de verano", 29, Colorc.AMARILLO, Talla.L, TipoProducto.VESTIDO);
			Producto p99999 = new Producto("Vestido de verano", 29, Colorc.AMARILLO, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p9,"Fotosproductos/prod9.jpg",6);
			InsertarProducto(p99,"Fotosproductos/prod9.jpg",6);
			InsertarProducto(p999,"Fotosproductos/prod9.jpg",6);
			InsertarProducto(p9999,"Fotosproductos/prod9.jpg",6);
			InsertarProducto(p99999,"Fotosproductos/prod9.jpg",6);
			
			/**
			 * PRODUCTO10
			 */
			Producto p10 = new Producto("Pantalones de campana", 35, Colorc.ROJO, Talla.XS, TipoProducto.PANTALON);
			Producto p101 = new Producto("Pantalones de campana", 35, Colorc.ROJO, Talla.S, TipoProducto.PANTALON);
			Producto p102 = new Producto("Pantalones de campana", 35, Colorc.ROJO, Talla.M, TipoProducto.PANTALON);
			Producto p103 = new Producto("Pantalones de campana", 35, Colorc.ROJO, Talla.L, TipoProducto.PANTALON);
			Producto p104 = new Producto("Pantalones de campana", 35, Colorc.ROJO, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p10,"Fotosproductos/prod10.jpg",1);
			InsertarProducto(p101,"Fotosproductos/prod10.jpg",1);
			InsertarProducto(p102,"Fotosproductos/prod10.jpg",1);
			InsertarProducto(p103,"Fotosproductos/prod10.jpg",1);
			InsertarProducto(p104,"Fotosproductos/prod10.jpg",1);
			
			/**
			 * PRODUCTO11
			 * MISMO QUE EL PRODUCTO 10 PERO EN DIFERENTE COLOR
			 */
			Producto p110 = new Producto("Pantalones de campana", 35, Colorc.BLANCO, Talla.XS, TipoProducto.PANTALON);
			Producto p1101 = new Producto("Pantalones de campana", 35, Colorc.BLANCO, Talla.S, TipoProducto.PANTALON);
			Producto p112 = new Producto("Pantalones de campana", 35, Colorc.BLANCO, Talla.M, TipoProducto.PANTALON);
			Producto p113 = new Producto("Pantalones de campana", 35, Colorc.BLANCO, Talla.L, TipoProducto.PANTALON);
			Producto p114 = new Producto("Pantalones de campana", 35, Colorc.BLANCO, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p110,"Fotosproductos/prod11.jpeg",1);
			InsertarProducto(p1101,"Fotosproductos/prod11.jpeg",1);
			InsertarProducto(p112,"Fotosproductos/prod11.jpeg",1);
			InsertarProducto(p113,"Fotosproductos/prod11.jpeg",1);
			InsertarProducto(p114,"Fotosproductos/prod11.jpeg",1);
			
			/**
			 * PRODUCTO12
			 * MISMO QUE EL PRODUCTO 10 PERO EN DIFERENTE COLOR
			 */
			Producto p120 = new Producto("Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.XS, TipoProducto.PANTALON);
			Producto p1201 = new Producto("Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.S, TipoProducto.PANTALON);
			Producto p122 = new Producto("Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.M, TipoProducto.PANTALON);
			Producto p123 = new Producto("Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.L, TipoProducto.PANTALON);
			Producto p124 = new Producto("Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p120,"Fotosproductos/prod12.jpg",2);
			InsertarProducto(p1201,"Fotosproductos/prod12.jpg",2);
			InsertarProducto(p122,"Fotosproductos/prod12.jpg",2);
			InsertarProducto(p123,"Fotosproductos/prod12.jpg",2);
			InsertarProducto(p124,"Fotosproductos/prod12.jpg",2);
			
			/**
			 * PRODUCTO13
			 * MISMO QUE EL PRODUCTO 10 PERO EN DIFERNETE COLOR
			 */
			Producto p130 = new Producto("Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.XS, TipoProducto.PANTALON);
			Producto p1301 = new Producto("Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.S, TipoProducto.PANTALON);
			Producto p132 = new Producto("Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.M, TipoProducto.PANTALON);
			Producto p133 = new Producto("Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.L, TipoProducto.PANTALON);
			Producto p134 = new Producto("Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p130,"Fotosproductos/prod13.jpeg",2);
			InsertarProducto(p1301,"Fotosproductos/prod13.jpeg",2);
			InsertarProducto(p132,"Fotosproductos/prod13.jpeg",2);
			InsertarProducto(p133,"Fotosproductos/prod13.jpeg",2);
			InsertarProducto(p134,"Fotosproductos/prod13.jpeg",2);
			
			/**
			 * PRODUCTO14
			 */
			Producto p140 = new Producto("Vestido de invierno", 29, Colorc.BLANCO, Talla.XS, TipoProducto.VESTIDO);
			Producto p1401 = new Producto("Vestido de invierno", 29, Colorc.BLANCO, Talla.S, TipoProducto.VESTIDO);
			Producto p1402 = new Producto("Vestido de invierno", 29, Colorc.BLANCO, Talla.M, TipoProducto.VESTIDO);
			Producto p1403 = new Producto("Vestido de invierno", 29, Colorc.BLANCO, Talla.L, TipoProducto.VESTIDO);
			Producto p1404 = new Producto("Vestido de invierno", 29, Colorc.BLANCO, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p140,"Fotosproductos/prod14.jpeg",3);
			InsertarProducto(p1401,"Fotosproductos/prod14.jpeg",3);
			InsertarProducto(p1402,"Fotosproductos/prod14.jpeg",3);
			InsertarProducto(p1403,"Fotosproductos/prod14.jpeg",3);
			InsertarProducto(p1404,"Fotosproductos/prod14.jpeg",3);
			
			/**
			 * PRODUCTO15
			 */
			Producto p150 = new Producto("Vestido de invierno", 29, Colorc.GRIS, Talla.XS, TipoProducto.VESTIDO);
			Producto p1501 = new Producto("Vestido de invierno", 29, Colorc.GRIS, Talla.S, TipoProducto.VESTIDO);
			Producto p1502 = new Producto("Vestido de invierno", 29, Colorc.GRIS, Talla.M, TipoProducto.VESTIDO);
			Producto p1503 = new Producto("Vestido de invierno", 29, Colorc.GRIS, Talla.L, TipoProducto.VESTIDO);
			Producto p1504 = new Producto("Vestido de invierno", 29, Colorc.GRIS, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p150,"Fotosproductos/prod15.jpg",3);
			InsertarProducto(p1501,"Fotosproductos/prod15.jpg",3);
			InsertarProducto(p1502,"Fotosproductos/prod15.jpg",3);
			InsertarProducto(p1503,"Fotosproductos/prod15.jpg",3);
			InsertarProducto(p1504,"Fotosproductos/prod15.jpg",3);
			
			/**
			 * PRODUCTO16
			 * MISMO QUE EL PRODUCTO 15 PERO EN DIFERENTE COLOR
			 */
			Producto p160 = new Producto("Vestido de invierno", 29, Colorc.VERDE, Talla.XS, TipoProducto.VESTIDO);
			Producto p1601 = new Producto("Vestido de invierno", 29, Colorc.VERDE, Talla.S, TipoProducto.VESTIDO);
			Producto p1602 = new Producto("Vestido de invierno", 29, Colorc.VERDE, Talla.M, TipoProducto.VESTIDO);
			Producto p1603 = new Producto("Vestido de invierno", 29, Colorc.VERDE, Talla.L, TipoProducto.VESTIDO);
			Producto p1604 = new Producto("Vestido de invierno", 29, Colorc.VERDE, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p160,"Fotosproductos/prod16.jpg",3);
			InsertarProducto(p1601,"Fotosproductos/prod16.jpg",3);
			InsertarProducto(p1602,"Fotosproductos/prod16.jpg",3);
			InsertarProducto(p1603,"Fotosproductos/prod16.jpg",3);
			InsertarProducto(p1604,"Fotosproductos/prod16.jpg",3);
			
			/**
			 * PRODUCTO17
			 */ 
			Producto p170 = new Producto("Pantalones cortos", 25, Colorc.BLANCO, Talla.XS, TipoProducto.PANTALON);
			Producto p1701 = new Producto("Pantalones cortos", 25, Colorc.BLANCO, Talla.S, TipoProducto.PANTALON);
			Producto p1702 = new Producto("Pantalones cortos", 25, Colorc.BLANCO, Talla.M, TipoProducto.PANTALON);
			Producto p1703 = new Producto("Pantalones cortos", 25, Colorc.BLANCO, Talla.L, TipoProducto.PANTALON);
			Producto p1704 = new Producto("Pantalones cortos", 25, Colorc.BLANCO, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p170,"Fotosproductos/prod17.jpg",4);
			InsertarProducto(p1701,"Fotosproductos/prod17.jpg",4);
			InsertarProducto(p1702,"Fotosproductos/prod17.jpg",4);
			InsertarProducto(p1703,"Fotosproductos/prod17.jpg",4);
			InsertarProducto(p1704,"Fotosproductos/prod17.jpg",4);
			
			/**
			 * PRODUCTO18
			 * MISMO QUE EL PRODUCTO 4 PERO EN DIFERENTE COLOR
			 */
			Producto p180 = new Producto("Pantalones cortos", 25, Colorc.AZUL, Talla.XS, TipoProducto.PANTALON);
			Producto p1801 = new Producto("Pantalones cortos", 25, Colorc.AZUL, Talla.S, TipoProducto.PANTALON);
			Producto p1802 = new Producto("Pantalones cortos", 25, Colorc.AZUL, Talla.M, TipoProducto.PANTALON);
			Producto p1803 = new Producto("Pantalones cortos", 25, Colorc.AZUL, Talla.L, TipoProducto.PANTALON);
			Producto p1804 = new Producto("Pantalones cortos", 25, Colorc.AZUL, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p180,"Fotosproductos/prod18.jpg",4);
			InsertarProducto(p1801,"Fotosproductos/prod18.jpg",4);
			InsertarProducto(p1802,"Fotosproductos/prod18.jpg",4);
			InsertarProducto(p1803,"Fotosproductos/prod18.jpg",4);
			InsertarProducto(p1804,"Fotosproductos/prod18.jpg",4);
			
			/**
			 * PRODUCTO19
			 * MISMO QUE EL PRODUCTO 17 PERO EN OTRO COLOR
			 */ 
			Producto p190 = new Producto("Pantalones cortos", 25, Colorc.AMARILLO, Talla.XS, TipoProducto.PANTALON);
			Producto p1901 = new Producto("Pantalones cortos", 25, Colorc.AMARILLO, Talla.S, TipoProducto.PANTALON);
			Producto p1902 = new Producto("Pantalones cortos", 25, Colorc.AMARILLO, Talla.M, TipoProducto.PANTALON);
			Producto p1903 = new Producto("Pantalones cortos", 25, Colorc.AMARILLO, Talla.L, TipoProducto.PANTALON);
			Producto p1904 = new Producto("Pantalones cortos", 25, Colorc.AMARILLO, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p190,"Fotosproductos/prod19.jpg",4);
			InsertarProducto(p1901,"Fotosproductos/prod19.jpg",4);
			InsertarProducto(p1902,"Fotosproductos/prod19.jpg",4);
			InsertarProducto(p1903,"Fotosproductos/prod19.jpg",4);
			InsertarProducto(p1904,"Fotosproductos/prod19.jpg",4);
			
			/**
			 * PRODUCTO20
			 * MISMO QUE EL PRODUCTO 17 PERO EN DIFERENTE COLOR
			 */
			Producto p200 = new Producto("Pantalones cortos", 25, Colorc.ROSA, Talla.XS, TipoProducto.PANTALON);
			Producto p2001 = new Producto("Pantalones cortos", 25, Colorc.ROSA, Talla.S, TipoProducto.PANTALON);
			Producto p2002 = new Producto("Pantalones cortos", 25, Colorc.ROSA, Talla.M, TipoProducto.PANTALON);
			Producto p2003 = new Producto("Pantalones cortos", 25, Colorc.ROSA, Talla.L, TipoProducto.PANTALON);
			Producto p2004 = new Producto("Pantalones cortos", 25, Colorc.ROSA, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p200,"Fotosproductos/prod20.jpg",4);
			InsertarProducto(p2001,"Fotosproductos/prod20.jpg",4);
			InsertarProducto(p2002,"Fotosproductos/prod20.jpg",4);
			InsertarProducto(p2003,"Fotosproductos/prod20.jpg",4);
			InsertarProducto(p2004,"Fotosproductos/prod20.jpg",4);
			
			/**
			 * PRODUCTO21
			 *
			 */
			Producto p210 = new Producto("Falda larga", 30, Colorc.ROSA, Talla.XS, TipoProducto.FALDA);
			Producto p2101 = new Producto("Falda larga", 30, Colorc.ROSA, Talla.S, TipoProducto.FALDA);
			Producto p2102 = new Producto("Falda larga", 30, Colorc.ROSA, Talla.M, TipoProducto.FALDA);
			Producto p2103 = new Producto("Falda larga", 30, Colorc.ROSA, Talla.L, TipoProducto.FALDA);
			Producto p2104 = new Producto("Falda larga", 30, Colorc.ROSA, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p210,"Fotosproductos/prod21.jpg",5);
			InsertarProducto(p2101,"Fotosproductos/prod21.jpg",5);
			InsertarProducto(p2102,"Fotosproductos/prod21.jpg",5);
			InsertarProducto(p2103,"Fotosproductos/prod21.jpg",5);
			InsertarProducto(p2104,"Fotosproductos/prod21.jpg",5);
			
			/**
			 * PRODUCTO22
			 * MISMO QUE EL PRODUCTO21 PERO EN OTRO COLOR
			 */
			Producto p220 = new Producto("Falda larga", 30, Colorc.BLANCO, Talla.XS, TipoProducto.FALDA);
			Producto p2201 = new Producto("Falda larga", 30, Colorc.BLANCO, Talla.S, TipoProducto.FALDA);
			Producto p2202 = new Producto("Falda larga", 30, Colorc.BLANCO, Talla.M, TipoProducto.FALDA);
			Producto p2203 = new Producto("Falda larga", 30, Colorc.BLANCO, Talla.L, TipoProducto.FALDA);
			Producto p2204 = new Producto("Falda larga", 30, Colorc.BLANCO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p220,"Fotosproductos/prod22.jpg",5);
			InsertarProducto(p2201,"Fotosproductos/prod22.jpg",5);
			InsertarProducto(p2202,"Fotosproductos/prod22.jpg",5);
			InsertarProducto(p2203,"Fotosproductos/prod22.jpg",5);
			InsertarProducto(p2204,"Fotosproductos/prod22.jpg",5);
			
			/**
			 * PRODUCTO23
			 * MISMO QUE EL PRODUCTO21 PERO EN OTRO COLOR
			 */
			Producto p230 = new Producto("Falda larga", 30, Colorc.NEGRO, Talla.XS, TipoProducto.FALDA);
			Producto p2301 = new Producto("Falda larga", 30, Colorc.NEGRO, Talla.S, TipoProducto.FALDA);
			Producto p2302 = new Producto("Falda larga", 30, Colorc.NEGRO, Talla.M, TipoProducto.FALDA);
			Producto p2303 = new Producto("Falda larga", 30, Colorc.NEGRO, Talla.L, TipoProducto.FALDA);
			Producto p2304 = new Producto("Falda larga", 30, Colorc.NEGRO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p230,"Fotosproductos/prod23.jpg",5);
			InsertarProducto(p2301,"Fotosproductos/prod23.jpg",5);
			InsertarProducto(p2302,"Fotosproductos/prod23.jpg",5);
			InsertarProducto(p2303,"Fotosproductos/prod23.jpg",5);
			InsertarProducto(p2304,"Fotosproductos/prod23.jpg",5);
			
			/**
			 * PRODUCTO24
			 */
			Producto p240 = new Producto("Falda corta", 30, Colorc.BLANCO, Talla.XS, TipoProducto.FALDA);
			Producto p2401 = new Producto("Falda corta", 30, Colorc.BLANCO, Talla.S, TipoProducto.FALDA);
			Producto p2402 = new Producto("Falda corta", 30, Colorc.BLANCO, Talla.M, TipoProducto.FALDA);
			Producto p2403 = new Producto("Falda corta", 30, Colorc.BLANCO, Talla.L, TipoProducto.FALDA);
			Producto p2404 = new Producto("Falda corta", 30, Colorc.BLANCO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p240,"Fotosproductos/prod24.jpg",2);
			InsertarProducto(p2401,"Fotosproductos/prod24.jpg",2);
			InsertarProducto(p2402,"Fotosproductos/prod24.jpg",2);
			InsertarProducto(p2403,"Fotosproductos/prod24.jpg",2);
			InsertarProducto(p2404,"Fotosproductos/prod24.jpg",2);
			
			/**
			 * PRODUCTO25
			 * MISMO QUE EL PRODUCTO24 PERO EN OTRO COLOR
			 */
			Producto p250 = new Producto("Falda corta", 30, Colorc.NEGRO, Talla.XS, TipoProducto.FALDA);
			Producto p2501 = new Producto("Falda corta", 30, Colorc.NEGRO, Talla.S, TipoProducto.FALDA);
			Producto p2502 = new Producto("Falda corta", 30, Colorc.NEGRO, Talla.M, TipoProducto.FALDA);
			Producto p2503 = new Producto("Falda corta", 30, Colorc.NEGRO, Talla.L, TipoProducto.FALDA);
			Producto p2504 = new Producto("Falda corta", 30, Colorc.NEGRO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p250,"Fotosproductos/prod25.jpeg",2);
			InsertarProducto(p2501,"Fotosproductos/prod25.jpeg",2);
			InsertarProducto(p2502,"Fotosproductos/prod25.jpeg",2);
			InsertarProducto(p2503,"Fotosproductos/prod25.jpeg",2);
			InsertarProducto(p2504,"Fotosproductos/prod25.jpeg",2);
			
			/**
			 * PRODUCTO25
			 * MISMO QUE EL PRODUCTO24 PERO EN OTRO COLOR
			 */
			Producto p260 = new Producto("Falda corta", 30, Colorc.ROJO, Talla.XS, TipoProducto.FALDA);
			Producto p2601 = new Producto("Falda corta", 30, Colorc.ROJO, Talla.S, TipoProducto.FALDA);
			Producto p2602 = new Producto("Falda corta", 30, Colorc.ROJO, Talla.M, TipoProducto.FALDA);
			Producto p2603 = new Producto("Falda corta", 30, Colorc.ROJO, Talla.L, TipoProducto.FALDA);
			Producto p2604 = new Producto("Falda corta", 30, Colorc.ROJO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p260,"Fotosproductos/prod26.jpg",2);
			InsertarProducto(p2601,"Fotosproductos/prod26.jpg",2);
			InsertarProducto(p2602,"Fotosproductos/prod26.jpg",2);
			InsertarProducto(p2603,"Fotosproductos/prod26.jpg",2);
			InsertarProducto(p2604,"Fotosproductos/prod26.jpg",2);
			/**
			 * ESTA ES LA INSERCION DE TODOS LOS PRODUCTOS
			 */
			
			/**
			 * INSERCION DE PEDIDOS
			 */
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = df.format(new Date());
			ArrayList<Producto> lprodu = new ArrayList<>();
			ArrayList<Producto> lprodu2 = new ArrayList<>();
			ArrayList<Producto> lproductos = BD.getProductos();
			
			lprodu.add(lproductos.get(24));
			lprodu.add(lproductos.get(68));
			lprodu.add(lproductos.get(100));
			InsertarPedido("45344345L", "Comprado", fecha, lprodu);

			lprodu2.add(lproductos.get(0));
			InsertarPedido("678999999", "No Finalizado", fecha, lprodu2);
			
			/**
			 * INSERCION DE TIENDAS
			 */
			Tienda t1=new Tienda(1,Franquicia.BERSHKA);
			Tienda t2=new Tienda(2,Franquicia.LEFTIES);
			Tienda t3=new Tienda(3,Franquicia.OYSHO);
			Tienda t4=new Tienda(4,Franquicia.PULLANDBEAR);
			Tienda t5=new Tienda(5,Franquicia.STRADIVARIUS);
			Tienda t6=new Tienda(6,Franquicia.ZARA);
			InsertarTienda(t1,"/Fotos/-bershka.jpg");
			InsertarTienda(t2,"/Fotos/lefti.jpg");
			InsertarTienda(t3,"/Fotos/oysho.jpg");
			InsertarTienda(t4,"/Fotos/pull.jpg");
			InsertarTienda(t5,"/Fotos/strad.jpg");
			InsertarTienda(t6,"/Fotos/zara.jpg");
			

			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log( Level.SEVERE, "No se pudo crear la base de datos", e );
		}
		}
    /**
     * AQUI TERMINA LA INICIALIZACION DE LOS DATOS QUE DEBUELVE UNA BASE DE DATOS TOTALMENTE INIZIALIZADA

     */
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
    
    /**
     * METODOS DE LA BASE DE DATOS

     */
    
    
    /**
     * INSERCION DE UN USUARIO EN LA BASE DE DATOS
     * @param usuario 
     * @return un true o un false dependiendo de si la insercion del usuario se ha realizado correctamente o no
     */
    public static boolean InsertarUsuario(Usuario us) {
    	if(mapaUsuarios.get(us.getUsuario()) != null) {
    		logger.log( Level.SEVERE, "Inserción de usuario incorrecta (ya existe): " + us );
			return true;
    	}
    	String sent = "";
		try {
			Statement stmt = abrirlaconexion("DeustoOutlet.db");
	    	sent = "insert into usuario values(" +
	    			"'"+ us.getNombre() + "', " + 
	    			"'" + us.getDni() + "', " + 
	    			"'" + us.getFechNa() + "', " + 
	    			"'" + us.getTelefono() + "', " + 
	    			"'" + us.getDireccion() + "', " + 
	    			"'" + us.getApellido() + "', " +
	    			"'" + us.getContraseña() + "', " +
	    			"'" + us.getUsuario() + "');";
			int val = stmt.executeUpdate(sent);
			logger.log( Level.INFO, "BD añadida " + val + " fila\t" + sent);
			if(val != 1) {
				logger.log( Level.SEVERE, "Error en insert de BD\t" + sent);
				return false;  
			}
			mapaUsuarios.put(us.getUsuario(), us);
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
		
    			
    }
    /**
     * INSERCION DE UN producto EN LA BASE DE DATOS
     * @param producto, ruta de la foto para poder insertarla en la base de  datos y el codigo del producto que estamos insertando.  
     * @return un true o un false dependiendo de si la insercion del producto se ha realizado correctamente o no
     */
    public static boolean InsertarProducto(Producto pro, String rutafoto, int codigo) {
    	String sent = "";
    	try {
    		Statement stmt = abrirlaconexion("DeustoOutlet.db");
        	sent = "insert into producto (nombre, precio, color, talla, tipo, ruta_foto, codigo_tienda, cantidad) values(" + 
        			"'" + pro.getNombre() + "', " +
        			"" + pro.getPrecio() + ", " + 
        			"'" + pro.getColor() + "', " +
        			"'" + pro.getTalla() + "', " + 
        			"'" + pro.getTipo() + "','"+ rutafoto+"',"+codigo+","+10+")";
        	int val = stmt.executeUpdate(sent);
			logger.log( Level.INFO, "Producto añadido " + val + " fila\t" + sent);
			if(val != 1) {
				logger.log( Level.SEVERE, "Error en insert de BD\t" + sent);
				return false;  
			}
        	
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
    }
    /**
     * LA TABLA PEDIDO VA A TENER UNA LISTA DE LOS PRODUCTOS QUE HAY EN ELLA 
     * DE ESTA FORMA VAMOS A PODER ACCEDER A TODOS LOS PRODUCTOS QUE HAY EN  UN PEDIDO DE UNA FORMA MUCHO MAS FACIL Y A LA HORA DE ALMACENARLO TAMBIEN .
     * 
     */
    /**
     * INSERCION DE UN pedido EN LA BASE DE DATOS
     * @param el dni de el usuario que ha hecho ese pedido, el estado de ese pedido, la fecha en la que hemos hecho la comprwa del pedido, una lista de todos los productos que hay en ese pedido.
     * EL ESTADO DEL PEDIDIO PUEDE SER DE DOS TIPOS : FINALIZADO O NO FINALIZADO DEPENDIENDO DE SI AUN ESTAMOS REALIZANDO EL PEDIDO. 
     * @return un true o un false dependiendo de si la insercion del PEDIDO se ha realizado correctamente o no
     */
    public static boolean InsertarPedido(String dni, String estado, String fecha_compra, ArrayList<Producto> lproductos) {
    	String p = "";
		try {
			Statement stmt = conn.createStatement();
			String pCodigo = "";
			for(Producto producto : lproductos) {
				pCodigo += producto.getCodigo() + ",";
				
			}
			p ="insert into pedido (dni, estado, fecha_compra, codigo_producto) values ('"+dni+"', '"+estado+"', '"+fecha_compra+"', '"+pCodigo+"');";
	    	int val = stmt.executeUpdate(p);
	    	if(val != 1) {
 				logger.log( Level.SEVERE, "Error en insert de BD\t" + p);
 				return false;  
 			}
	    	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			lastError = e;
			e.printStackTrace();
			return false;
		}
    	
		return true;
    	
    }
    /**
     * INSERCION DE UNA TIENDA EN LA BASE DE DATOS
     * @param la tienda y la ruta de la foto en la que esta la tienda.  
     * @return un true o un false dependiendo de si la insercion del producto se ha realizado correctamente o no
     */
    public static boolean InsertarTienda(Tienda t, String rutafoto) {
    	String sent = "";
    	try {
    		Statement stmt = abrirlaconexion("DeustoOutlet.db");
        	sent = "insert into tienda (franquicia, foto) values( '"+ t.getFranquicia() +  "','"+ rutafoto+"')";
        	int val = stmt.executeUpdate(sent);
        	if(val != 1) {
				logger.log( Level.SEVERE, "Error en insert de BD\t" + sent);
				return false;  
			}
        	
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
    }

    
    
    /**
     * **********************************************************************************************************************************************
     */
    
    /**
     * ELIMINAR UN PRODUCTO DE LA BASE DE DATOS
     * @param codigo Y PRODUCTO
     * @return TRUE O FALSE DEPENDIENDO DE SI LA ELIMINACION SE HA REALIZADO BIEN
     */
    public static boolean EliminarProducto(Producto pro, int codigo) {
    	String sent = "";
    	try {
    		Statement stmt = abrirlaconexion("DeustoOutlet.db");
    		
    		sent = "select cantidad from producto" + " where codigo = " + pro.getCodigo() + ";";
    		if(Integer.parseInt(sent) > 0) {
    			sent = "update producto set cantidad = cantidad - 1" + " where codigo = " + pro.getCodigo() + ";" ;	
    		}else {
				logger.log( Level.SEVERE, "Error en update de BD\t" + sent);
    			return false;
    		}
    		
    		int val = stmt.executeUpdate(sent);
			if(val != 1) {
				logger.log( Level.SEVERE, "Error en update de BD\t" + sent);
				return false;
    		}
			return true;
			
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
    }
    /**
     * 
     * @return TODOS LOS PRODUCTOS DE LA BASE DE DATOS EN UN ARRAYLIST
     */
    
    public static ArrayList<Producto> getProductos(){
 	   String sent = "";
 	   ArrayList<Producto> lproducto = new ArrayList<Producto>();
 	   try {
 		   Statement stm = abrirlaconexion("DeustoOutlet.db");
 		   sent = "select * from producto";
 		   ResultSet rs = stm.executeQuery(sent);
 		   while (rs.next()) {
 			   Producto p = new Producto(rs.getInt("codigo_producto"), rs.getString("nombre"), rs.getInt("precio"), Colorc.valueOf(rs.getString("color")), Talla.valueOf(rs.getString("talla")), TipoProducto.valueOf(rs.getString("tipo")));
 			   lproducto.add(p);
 			   mapaProductos.put(p.getCodigo(), p);
 		   }
 		   
 		   rs.close();
 		   logger.log(Level.INFO, "BD\t" + sent);
 		   return lproducto;
 	} catch (SQLException e) {
 		logger.log(Level.SEVERE, "Error en BD\t" + sent, e);
 		lastError = e;
 		e.printStackTrace();
 		return null;
 	}
 	   
    }  
    /**
     * 
     * @return TODOS LOS USUARIOS DE LA BASE DE DATOS EN UNA ARRAYLIST
     */
    public static ArrayList <Usuario> getUsuario() {
    	String sent = "";
    	ArrayList<Usuario> lusuario = new ArrayList<Usuario> ();
    	try {
    		Statement stm = abrirlaconexion("DeustoOutlet.db");
    		sent = "select * from usuario";
    		ResultSet rs = stm.executeQuery(sent);
    		while (rs.next()) {
    			Usuario u = new Usuario(rs.getString("nombre"), rs.getString("dni"), rs.getString("fechNa"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("apellido"), rs.getString("contraseña"), rs.getString("usuario") );
    			lusuario.add(u);
    			mapaUsuarios.put(u.getDni(), u);
    		}
    		rs.close();
    		logger.log(Level.INFO, "BD\t" + sent);
    		return lusuario;
    	}catch (SQLException e) {
     		logger.log(Level.SEVERE, "Error en BD\t" + sent, e);
     		lastError = e;
     		e.printStackTrace();
     		return null;
     	}
    	
    }
   /**
    * 
    * @return TODOS LOS PEDIDOS DE LA BASE DE DATOS EN UNA ARRAY LIST
    */
    public static ArrayList<Pedidos> getPedidos() {
 	   String sent = "";
 	   ArrayList<Pedidos> lpedidos = new ArrayList<Pedidos>();
 	   try {
 		   Statement stm = abrirlaconexion("DeustoOutlet.db");
 		   sent = "select * from pedido";
 		   ResultSet rs = stm.executeQuery(sent);
 		   while (rs.next()) {
 			   List<Producto> listaProductos = new ArrayList<>();
 			  
 			   String [] datos = rs.getString("codigo_producto").split(",");
 			   Pedidos pe = null;
 			   for(int i=0; i<datos.length; i++) {
 				   for(Producto p : getProductos()) {
 					   if(Integer.parseInt(datos[i]) == p.getCodigo()) {
 						   listaProductos.add(p);
 						   pe = new Pedidos(listaProductos, rs.getInt("codigo_pedido"));
 						   
 					   }
 				   }
 			   }
 				   
 			   lpedidos.add(pe);
 		   }
 		   rs.close();
 		   logger.log(Level.INFO, "BD\t" + sent);
 		   return lpedidos;
 	} catch (SQLException e) {
 		logger.log(Level.SEVERE, "Error en BD\t" + sent, e);
 		lastError = e;
 		e.printStackTrace();
 		return null;
 	}
    }
   
    /**
     * 
     * @param tipo
     * @param color
     * @param precio
     * @param talla
     * @return CANTIDAD DE ESE PRODUCRO CON ESAS CARACTERISTICAS 
     */
   public static int cantidadProductos(TipoProducto tipo, Colorc color, int precio, Talla talla) {
	   String sent = "";
	   int cantidad = 0;
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   sent = "select * from producto where tipo = '" + tipo + "' and color = '" + color + "' and precio <= " + precio + " and talla = '" + talla + "';";
		   ResultSet rs = stm.executeQuery(sent);
		   while(rs.next()) {
			   cantidad = rs.getInt("cantidad");
		   }
		   rs.close();
		   logger.log(Level.INFO, "BD\t" + sent);
		   return cantidad;
	} catch (SQLException e) {
		logger.log(Level.SEVERE, "Error en BD\t" + sent, e);
		lastError = e;
		e.printStackTrace();
		return 0;
	}


   }   
   
   /**
    * 
    * @param tipo
    * @param color
    * @param precio
    * @param talla
    * @return UNA LISTA DE LOS PRODUCTS QUE TIENEN ESAS CARACTERISTICAS
    */
   public static List<Producto> buscarProductoCaracteristicas(TipoProducto tipo, Colorc color, int precio, Talla talla) {
	   
	   if(cantidadProductos(tipo, color, precio, talla) > 0) {
		   String sent = "";
		   List<Producto>lproducto = new ArrayList<Producto>();
		   try {
			   sent = "select * from producto where tipo = '" + tipo + "' and color = '" + color + "' and precio <= " + precio + " and talla = '" + talla + "'";
			   Statement stm = abrirlaconexion("DeustoOutlet.db");
			   ResultSet rs = stm.executeQuery( sent );
			   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
			   while(rs.next()) {
				   Producto p = new Producto(rs.getInt("codigo_producto"), rs.getString("nombre"), rs.getInt("precio"), Colorc.valueOf(rs.getString("color")), Talla.valueOf(rs.getString("talla")), TipoProducto.valueOf(rs.getString("tipo")));
				   lproducto.add(p);
			   }
			   rs.close();
			   logger.log(Level.INFO, "BD\t" + sent);
			   return lproducto;
			   
		} catch (SQLException e) {
			lastError = e;
			logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
			e.printStackTrace();
			return null;
		}
	  }
	return null; 
   }
   /**
    * 
    * @param tipo
    * @return UNA LISTA DE LOS PRODUCTOS DE ESE TIPO
    */
   
   public static List<Producto> buscarProductoTipo(TipoProducto tipo) {
	   
		   String sent = "";
		   List<Producto>lproducto = new ArrayList<Producto>();
		   try {
			   sent = "select * from producto where tipo = '" + tipo+ "';";
			   Statement stm = abrirlaconexion("DeustoOutlet.db");
			   ResultSet rs = stm.executeQuery( sent );
			   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
			   while(rs.next()) {
				   Producto p = new Producto(rs.getInt("codigo_producto"), rs.getString("nombre"), rs.getInt("precio"), Colorc.valueOf(rs.getString("color")), Talla.valueOf(rs.getString("talla")), TipoProducto.valueOf(rs.getString("tipo")));
				   lproducto.add(p);
			   }
			   rs.close();
			   logger.log(Level.INFO, "BD\t" + sent);
			   return lproducto;
			   
		} catch (SQLException e) {
			lastError = e;
			logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
			e.printStackTrace();
			return null;
		}
   }
   /**
    * 
    * @param color
    * @return TODOS LOS PRODUCTOS DE ESE COLOR
    */
   public static List<Producto> buscarProductoColor(Colorc color){
	   String sent = "";
	   List<Producto>lproducto = new ArrayList<Producto>();
	   
		try {
			sent = "select * from producto where color = '" + color+ "';";
			Statement stm;
			stm = abrirlaconexion("DeustoOutlet.db");
			ResultSet rs = stm.executeQuery( sent );
			logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
			while(rs.next()) {
				Producto p = new Producto(rs.getInt("codigo_producto"), rs.getString("nombre"), rs.getInt("precio"), Colorc.valueOf(rs.getString("color")), Talla.valueOf(rs.getString("talla")), TipoProducto.valueOf(rs.getString("tipo")));
				lproducto.add(p);
			}
			rs.close();
			logger.log(Level.INFO, "BD\t" + sent);
			return lproducto;
		} catch (SQLException e) {
			lastError = e;
			logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
			e.printStackTrace();
			return null;
		}
	   
   }
   /**
    * 
    * @param talla
    * @return TODOS LOS PRODUCTOS DE ESA TALLA
    */
   public static List<Producto> buscarProductoTalla (Talla talla){
	   String sent = "";
	   List<Producto>lproducto = new ArrayList<Producto>();
	   
		try {
			sent = "select * from producto where talla = '" + talla+ "';";
			Statement stm;
			stm = abrirlaconexion("DeustoOutlet.db");
			ResultSet rs = stm.executeQuery( sent );
			logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
			while(rs.next()) {
				Producto p = new Producto(rs.getInt("codigo_producto"), rs.getString("nombre"), rs.getInt("precio"), Colorc.valueOf(rs.getString("color")), Talla.valueOf(rs.getString("talla")), TipoProducto.valueOf(rs.getString("tipo")));
				lproducto.add(p);
			}
			rs.close();
			logger.log(Level.INFO, "BD\t" + sent);
			return lproducto;
		} catch (SQLException e) {
			lastError = e;
			logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
			e.printStackTrace();
			return null;
		}
   }
   /**
    * 
    * @return EL SIGUENTE CODIGO DE PEDIDO
    * esta funcion analiza todos los pedidos y sus codigos para ver cual seria el siguente codigo de pedido
    */

   public static int getcodigopedido() {
	   String sent = "";
	   
	   try {
		   sent = "select codigo_pedido from pedido";
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   ResultSet rs = stm.executeQuery( sent );
		   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
		   int n=0;
		   while(rs.next()) {
			   if (rs.getInt("codigo_pedido")>n) {
				   n=rs.getInt("codigo_pedido");
			   }
		   }
		   rs.close();
		   logger.log(Level.INFO, "BD\t" + sent);
		   return n+1;
		   
	} catch (SQLException e) {
		lastError = e;
		logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
		e.printStackTrace();
		return 0;
	}
	
   }   
   /**
    * 
    * @return EL SIGUENTE CODIGO DE producto
    * esta funcion analiza todos los productos y sus codigos para ver cual seria el siguente codigo de producto
    */
   public static int getcodigoproducto() {
	   String sent = "";
	   
	   try {
		   sent = "select codigo_producto from producto";
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   ResultSet rs = stm.executeQuery( sent );
		   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
		   int n=0;
		   while(rs.next()) {
			   if (rs.getInt("codigo_producto")>n) {
				   n=rs.getInt("codigo_producto");
			   }
		   }
		   rs.close();
		   logger.log(Level.INFO, "BD\t" + sent);
		   return n+1;
		   
	} catch (SQLException e) {
		lastError = e;
		logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
		e.printStackTrace();
		return 0;
	} 
   }
   /**
    * 
    * @param producto del que queremos conseguir el URL
    * @return el url de la foto de ese producto
    */
   public static String getURLFOTO(Producto p) {
	   
	   String sent = "select * from producto where codigo_producto = " + p.getCodigo() ;
	   
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   ResultSet rs = stm.executeQuery( sent );
		   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
		   while(rs.next()) {
			  return rs.getString("ruta_foto");
		   }
		   rs.close();
		   logger.log(Level.INFO, "BD\t" + sent);
		   
		   
	} catch (SQLException e) {
		lastError = e;
		logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
		e.printStackTrace();
		return null;
	}
	return null;
	   
   }
   /**
    * 
    * @param producto
    * @return a que tienda pertenece ese producto
    */
   public static  int getcodigoTienda(Producto p) {
	   
	   String sent = "select * from producto where codigo_producto = " + p.getCodigo() ;
	   
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   ResultSet rs = stm.executeQuery( sent );
		   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
		   while(rs.next()) {
			  return rs.getInt("codigo_tienda");
		   }
		   rs.close();
		   logger.log(Level.INFO, "BD\t" + sent);
		   
		   
	} catch (SQLException e) {
		lastError = e;
		logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
		e.printStackTrace();
		return -1;
	}
	return -1;
	   
   }
   /**
    * 
    * @param usuario (nombre de usuario)
    * @return el dni de ese usuario 
    */
 public static  String getDNIusuario(String usuario) {
	   //podemos hacerlo asi ya que el usuario tambien es una clave alternativa
	   String sent = "select * from usuario where usuario = '" + usuario +"'";
	   
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   ResultSet rs = stm.executeQuery( sent );
		   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
		   while(rs.next()) {
			 return rs.getString("dni");
		   }
		   rs.close();
		   logger.log(Level.INFO, "BD\t" + sent);
		   
		   
	} catch (SQLException e) {
		lastError = e;
		logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
		e.printStackTrace();
		return null;
	}
	return null;
	   
   }
 /**
  * 
  * @param p
  * @return que codigo tiene un producto
  */
    public static  int getcodigoProducto(Producto p) {
	   
	   String sent = "select * from producto where codigo_producto = " + p.getCodigo() ;
	   
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   ResultSet rs = stm.executeQuery( sent );
		   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
		   while(rs.next()) {
			  return rs.getInt("codigo_producto");
		   }
		   rs.close();
		   logger.log(Level.INFO, "BD\t" + sent);
		   
		   
	} catch (SQLException e) {
		lastError = e;
		logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
		e.printStackTrace();
		return -1;
	}
	return -1;
	   
   }
    /**
     * 
     * @return un mapa que tiene como clave el codigo del producto y su valor es ese producto
     */
    public static HashMap<Integer,Producto> Completar_mapa() {
    	mapa=new HashMap<>();
    	 ArrayList<Producto> pr=BD.getProductos();
    	for (Producto p:pr) {
    		 if (!mapa.containsKey(p.getCodigo())){
    			 mapa.put(p.getCodigo(), p);
    		 }
    	}
    	return mapa;
    }
    /**
     * 
     * @param Usuario
     * @return te devuelve la lista de productos que tenia en el carrito ese usuario 
     */
    public static ArrayList<Producto> getlistaProductosCarritoAnterior(String Usuario) {
 	   ArrayList<Producto> pr=new ArrayList<Producto>();
 	   String sent = "select * from pedido where dni = '" + Usuario +"' and estado = 'NO finalizado'" ;
 	   mapa=Completar_mapa();
 	   try {
 		   Statement stm = abrirlaconexion("DeustoOutlet.db");
 		   ResultSet rs = stm.executeQuery( sent );
 		   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
 		   while(rs.next()) {
 			   
 			  pr.add(mapa.get(rs.getInt("codigo_producto")));
 			  
 		   }
 		   rs.close();
 		   logger.log(Level.INFO, "BD\t" + sent);
 		   return pr;
 		   
 	} catch (SQLException e) {
 		lastError = e;
 		logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
 		e.printStackTrace();
 		return null;
 	}
 	
 	   
    }
    /**
     * 
     * @param usuario (nombre de usuario)
     * @return el usuario entero 
     */
   public static Usuario buscarUsuarioNombre(String usuario) {
	   String sent = "select * from usuario where usuario = '" + usuario + "'";
	   try {
		   if(mapaUsuarios.containsKey(usuario)) {
			   return mapaUsuarios.get(usuario);
		   }
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   ResultSet rs = stm.executeQuery( sent );
		   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
		   if(rs.next()) {
			   Usuario u = new Usuario(rs.getString("nombre"), rs.getString("dni"), rs.getString("fechNa"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("apellido"), rs.getString("contraseña"), rs.getString("usuario"));
			   rs.close();
			   mapaUsuarios.put(u.getUsuario(), u);
			   return u;
		   }
		   else {
			   rs.close();
			   return null;
		   }	   
		   
	} catch (SQLException e) {
		lastError = e;
		logger.log( Level.SEVERE, "Error en búsqueda de base de datos: " + sent, e );
		e.printStackTrace();
		return null;
	}
	   
   }
   /**
    * 
    * @param codigo
    * @return el estado de ese pedido (si esta finalizado o aun no lo esta )
    */
   public static String estadoPedidos(Pedidos codigo) {
	   String sent = "";
	   String estado = "";
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   sent = "select * from pedido where codigo_pedido = " + codigo + ";";
		   ResultSet rs = stm.executeQuery(sent);
		   while(rs.next()) {
			   estado = rs.getString("estado");
		   }
		   rs.close();
		   logger.log(Level.INFO, "BD\t" + sent);
		   return estado;
	} catch (SQLException e) {
		logger.log(Level.SEVERE, "Error en BD\t" + sent, e);
		lastError = e;
		e.printStackTrace();
		
	}
	return estado;


   }
   /**
    * 
    * @param pedidp
    * @param codigo
    * @return true o false dependiendo de si el pedido se ha aztualizado bien o no
    */
   public static boolean ActualizarPedidoEstado(Pedidos p, int codigo) {
   	String sent = "";
   	try {
   		Statement stmt = abrirlaconexion("DeustoOutlet.db");
		sent = "update pedido set estado = Comprado" + " where codigo = " + p.getCodigo() + ";" ;	
	
   		int val = stmt.executeUpdate(sent);
		if(val != 1) {
			logger.log( Level.SEVERE, "Error en update de BD\t" + sent);
			return false;
   		}
			return true;
			
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
   }
   
   public static Exception getLastError() {
		return lastError;
	}
   
   
 	
	
}
