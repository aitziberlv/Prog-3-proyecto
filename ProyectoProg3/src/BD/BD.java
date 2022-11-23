package BD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Clasesprincipales.Colorc;
import Clasesprincipales.Franquicia;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
import Clasesprincipales.Tienda;
import Clasesprincipales.TipoProducto;
import Clasesprincipales.Usuario;

public class BD {
	private static Exception lastError = null;
	private static Logger logger = Logger.getLogger( "BD" );
	
	/** Inicializa una BD SQLITE y devuelve una conexiï¿½n con ella
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexiï¿½n con la base de datos indicada. Si hay algï¿½n error, se devuelve null
	 */
	public static Statement abrirlaconexion( String nombreBD) throws SQLException{ 
		
//		try { // Crear carpeta si no existe
//			File fic = new File(rutaFotos);
//			if (!fic.exists()) {
//				Files.createDirectory( (fic).toPath() );
//			}
//		} catch (IOException ex) {
//			log( Level.SEVERE, "Ruta de fotos " + rutaFotos + " no se ha podido respaldar en el servicio de persistencia", ex );
//		}
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection( "jdbc:sqlite:" + nombreBD);
				 
			Statement st = con.createStatement();
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
	public static void cerrarBD( Connection con, Statement stm ) {
		try {
			if (stm!=null) stm.close();
			if (con!=null) con.close();
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
			stmt.executeUpdate("drop table usuario");
			int veces1=stmt.executeUpdate(com);
			if (veces1==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com4 = "create table pedido(codigo_pedido INTEGER PRIMARY KEY AUTOINCREMENT, dni String)"; //clave externa del dni del usuario. 
			stmt.executeUpdate("drop table pedido");
			int veces2=stmt.executeUpdate(com4);
			if (veces2==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com2 = "create table tienda(codigo_tienda INTEGER PRIMARY KEY AUTOINCREMENT,  franquicia String)";
			stmt.executeUpdate("drop table tienda");
			int veces3=stmt.executeUpdate(com2);
			if (veces3==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com3 = "create table IF NOT EXISTS producto(codigo_producto integer, nombre String, precio integer, color String, talla String, tipo String , ruta String)";
			stmt.executeUpdate("drop table producto");
			int veces4=stmt.executeUpdate(com3);
			if (veces4==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com5 = "create table IF NOT EXISTS compra(codigo_pertenece INTEGER PRIMARY KEY AUTOINCREMENT, id_tienda integer, id_pedido integer ,id_producto integer)";
			stmt.executeUpdate("drop table compra");
			int veces=stmt.executeUpdate(com5);
			if (veces==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			
			
			// insertar usuarios. 
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
			InsertarUsuario(new Usuario("Daniela", "850234681I", "08/06/2001", "756902347", "Calle Doctor Areilza", "Diez", "dadiez", "daninueve"));

			
			// insertar producto/ mismo producto en diferentes tallas
			//producto1.
			Producto p1 = new Producto(1, "Chaqueta de pelo", 25, Colorc.NEGRO, Talla.M, TipoProducto.CHAQUETAS);
			Producto p11 = new Producto(2, "Chaqueta de pelo", 25, Colorc.NEGRO, Talla.S, TipoProducto.CHAQUETAS);
			Producto p111= new Producto(3, "Chaqueta de pelo", 25, Colorc.NEGRO, Talla.XS, TipoProducto.CHAQUETAS);
			Producto p1111 = new Producto(4, "Chaqueta de pelo", 25, Colorc.NEGRO, Talla.L, TipoProducto.CHAQUETAS);
			Producto p11111 = new Producto(5, "Chaqueta de pelo", 25, Colorc.NEGRO, Talla.XL, TipoProducto.CHAQUETAS);
			InsertarProducto(p1,"");
			InsertarProducto(p11,"");
			InsertarProducto(p111,"");
			InsertarProducto(p1111,"");
			InsertarProducto(p11111,"");
			
			
			//producto2.
			Producto p2 = new Producto(7, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.S, TipoProducto.PANTALONES);
			Producto p22 = new Producto(8, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.M, TipoProducto.PANTALONES);
			Producto p222 = new Producto(9, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.L, TipoProducto.PANTALONES);
			Producto p2222 = new Producto(10, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.XS, TipoProducto.PANTALONES);
			Producto p22222 = new Producto(11, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.XL, TipoProducto.PANTALONES);
			InsertarProducto(p2,"");
			InsertarProducto(p22,"");
			InsertarProducto(p222,"");
			InsertarProducto(p2222,"");
			InsertarProducto(p22222,"");
			

			//producto3
			Producto p3 = new Producto(13, "Camisa con volantes", 20, Colorc.BLANCO, Talla.M, TipoProducto.CAMISETAS);
			Producto p33 = new Producto(14, "Camisa con volantes", 20, Colorc.BLANCO, Talla.S, TipoProducto.CAMISETAS);
			Producto p333 = new Producto(15, "Camisa con volantes", 20, Colorc.BLANCO, Talla.L, TipoProducto.CAMISETAS);
			Producto p3333 = new Producto(16, "Camisa con volantes", 20, Colorc.BLANCO, Talla.XL, TipoProducto.CAMISETAS);
			Producto p33333 = new Producto(17, "Camisa con volantes", 20, Colorc.BLANCO, Talla.XS, TipoProducto.CAMISETAS);
			InsertarProducto(p3,"");
			InsertarProducto(p33,"");
			InsertarProducto(p333,"");
			InsertarProducto(p3333,"");
			InsertarProducto(p33333,"");
			
			
			//PRODUCTO4
			Producto p4 = new Producto(4, "pantalones parachute", 30, Colorc.NEGRO, Talla.L,TipoProducto.PANTALONES);
			Producto p44 =new Producto(4, "pantalones parachute", 30, Colorc.NEGRO, Talla.M,TipoProducto.PANTALONES);
			Producto p444 =new Producto(4, "pantalones parachute", 30, Colorc.NEGRO, Talla.S,TipoProducto.PANTALONES);
			Producto p4444 =new Producto(4, "pantalones parachute", 30, Colorc.NEGRO, Talla.XS,TipoProducto.PANTALONES);
			Producto p44444 =new Producto(4, "pantalones parachute", 30, Colorc.NEGRO, Talla.XL,TipoProducto.PANTALONES);			
			InsertarProducto(p4,"");
			InsertarProducto(p44,"");
			InsertarProducto(p444,"");
			InsertarProducto(p4444,"");
			InsertarProducto(p44444,"");
			
			//PRODUCTO5(mismo producto que el 4 pero de otro color)
			Producto p5 = new Producto(4, "pantalones parachute", 30, Colorc.VERDE, Talla.L,TipoProducto.PANTALONES);
			Producto p55 =new Producto(4, "pantalones parachute", 30, Colorc.VERDE, Talla.M,TipoProducto.PANTALONES);
			Producto p555 =new Producto(4, "pantalones parachute", 30, Colorc.VERDE, Talla.S,TipoProducto.PANTALONES);
			Producto p5555=new Producto(4, "pantalones parachute", 30, Colorc.VERDE, Talla.XS,TipoProducto.PANTALONES);
			Producto p55555 =new Producto(4, "pantalones parachute", 30, Colorc.VERDE, Talla.XL,TipoProducto.PANTALONES);			
			InsertarProducto(p5,"");
			InsertarProducto(p55,"");
			InsertarProducto(p555,"");
			InsertarProducto(p5555,"");
			InsertarProducto(p55555,"");
			
			//PRODUCTO6(mismo producto que el 4 pero de otro color)
			Producto p6 = new Producto(4, "pantalones parachute", 30, Colorc.GRIS, Talla.L,TipoProducto.PANTALONES);
			Producto p66 =new Producto(4, "pantalones parachute", 30, Colorc.GRIS, Talla.M,TipoProducto.PANTALONES);
			Producto p666 =new Producto(4, "pantalones parachute", 30,Colorc.GRIS, Talla.S,TipoProducto.PANTALONES);
			Producto p6666=new Producto(4, "pantalones parachute", 30, Colorc.GRIS, Talla.XS,TipoProducto.PANTALONES);
			Producto p66666 =new Producto(4, "pantalones parachute", 30, Colorc.GRIS, Talla.XL,TipoProducto.PANTALONES);			
			InsertarProducto(p6,"");
			InsertarProducto(p66,"");
			InsertarProducto(p666,"");
			InsertarProducto(p6666,"");
			InsertarProducto(p66666,"");
			
			
			//producto7 
			Producto p7 = new Producto(13, "Blusa con boton delantero", 35, Colorc.BLANCO, Talla.M, TipoProducto.CAMISETAS);
			Producto p77 = new Producto(14, "Blusa con boton delantero", 35, Colorc.BLANCO, Talla.S, TipoProducto.CAMISETAS);
			Producto p777 = new Producto(15, "Blusa con boton delantero", 35, Colorc.BLANCO, Talla.L, TipoProducto.CAMISETAS);
			Producto p7777 = new Producto(16, "Blusa con boton delantero", 35, Colorc.BLANCO, Talla.XL, TipoProducto.CAMISETAS);
			Producto p77777 = new Producto(17,"Blusa con boton delantero", 35, Colorc.BLANCO, Talla.XS, TipoProducto.CAMISETAS);
			InsertarProducto(p7,"");
			InsertarProducto(p77,"");
			InsertarProducto(p777,"");
			InsertarProducto(p7777,"");
			InsertarProducto(p77777,"");
			
			//producto8 
			Producto p8 = new Producto(13, "vestido de verano", 29, Colorc.BLANCO, Talla.M, TipoProducto.VESTIDOS);
			Producto p88 = new Producto(14, "vestido de verano", 29, Colorc.BLANCO, Talla.S, TipoProducto.VESTIDOS);
			Producto p888 = new Producto(15, "vestido de verano", 29, Colorc.BLANCO, Talla.L, TipoProducto.VESTIDOS);
			Producto p8888 = new Producto(16, "vestido de verano", 29, Colorc.BLANCO, Talla.XL, TipoProducto.VESTIDOS);
			Producto p88888 = new Producto(17,"vestido de verano", 29, Colorc.BLANCO, Talla.XS, TipoProducto.VESTIDOS);
			InsertarProducto(p8,"");
			InsertarProducto(p88,"");
			InsertarProducto(p888,"");
			InsertarProducto(p8888,"");
			InsertarProducto(p88888,"");
			
			//producto9 (MISMO PRODUCTO QUE EL 8 PERO EN OTRO COLOR )
			Producto p9 = new Producto(13, "vestido de verano", 29, Colorc.AMARILLO, Talla.M, TipoProducto.VESTIDOS);
			Producto p99 = new Producto(14, "vestido de verano", 29, Colorc.AMARILLO, Talla.S, TipoProducto.VESTIDOS);
			Producto p999 = new Producto(15, "vestido de verano", 29, Colorc.AMARILLO, Talla.L, TipoProducto.VESTIDOS);
			Producto p9999 = new Producto(16, "vestido de verano", 29, Colorc.AMARILLO, Talla.XL, TipoProducto.VESTIDOS);
			Producto p99999 = new Producto(17,"vestido de verano", 29, Colorc.AMARILLO, Talla.XS, TipoProducto.VESTIDOS);
			InsertarProducto(p9,"");
			InsertarProducto(p99,"");
			InsertarProducto(p999,"");
			InsertarProducto(p9999,"");
			InsertarProducto(p99999,"");
			
			//producto10 
			Producto p10 = new Producto(13, "pantalones de campana", 32, Colorc.ROJO, Talla.M, TipoProducto.PANTALONES);
			Producto p101 = new Producto(14,"pantalones de campana", 32, Colorc.ROJO, Talla.S, TipoProducto.PANTALONES);
			Producto p102 = new Producto(15, "pantalones de campana", 32, Colorc.ROJO, Talla.L, TipoProducto.PANTALONES);
			Producto p103 = new Producto(16,"pantalones de campana", 32, Colorc.ROJO, Talla.XL, TipoProducto.PANTALONES);
			Producto p104 = new Producto(17,"pantalones de campana", 32, Colorc.ROJO, Talla.XS, TipoProducto.PANTALONES);
			InsertarProducto(p10,"");
			InsertarProducto(p101,"");
			InsertarProducto(p102,"");
			InsertarProducto(p103,"");
			InsertarProducto(p104,"");
			
			//producto11 (mismo producto que el 10 pero en otro color)
			Producto p110 = new Producto(13, "pantalones de campana", 32, Colorc.BLANCO, Talla.M, TipoProducto.PANTALONES);
			Producto p1101 = new Producto(14,"pantalones de campana", 32, Colorc.BLANCO, Talla.S, TipoProducto.PANTALONES);
			Producto p112 = new Producto(15, "pantalones de campana", 32, Colorc.BLANCO, Talla.L, TipoProducto.PANTALONES);
			Producto p113 = new Producto(16,"pantalones de campana", 32, Colorc.BLANCO, Talla.XL, TipoProducto.PANTALONES);
			Producto p114 = new Producto(17,"pantalones de campana", 32, Colorc.BLANCO, Talla.XS, TipoProducto.PANTALONES);
			InsertarProducto(p110,"");
			InsertarProducto(p1101,"");
			InsertarProducto(p112,"");
			InsertarProducto(p113,"");
			InsertarProducto(p114,"");
			
			//producto12 (mismo producto que el 10 pero en otro color)
			Producto p120 = new Producto(13, "pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.M, TipoProducto.PANTALONES);
			Producto p1201 = new Producto(14,"pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.S, TipoProducto.PANTALONES);
			Producto p122 = new Producto(15, "pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.L, TipoProducto.PANTALONES);
			Producto p123 = new Producto(16,"pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.XL, TipoProducto.PANTALONES);
			Producto p124 = new Producto(17,"pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.XS, TipoProducto.PANTALONES);
			InsertarProducto(p120,"");
			InsertarProducto(p1201,"");
			InsertarProducto(p122,"");
			InsertarProducto(p123,"");
			InsertarProducto(p124,"");
			
			//PRODUCTO13 (mismo producto que el 10 pero otro color)
			Producto p130 = new Producto(13, "pantalones de campana de estampado", 28, Colorc.AZUL, Talla.M, TipoProducto.PANTALONES);
			Producto p1301 = new Producto(14,"pantalones de campana de estampado", 28, Colorc.AZUL, Talla.S, TipoProducto.PANTALONES);
			Producto p132 = new Producto(15, "pantalones de campana de estampado", 28, Colorc.AZUL, Talla.L, TipoProducto.PANTALONES);
			Producto p133 = new Producto(16,"pantalones de campana de estampado", 28, Colorc.AZUL, Talla.XL, TipoProducto.PANTALONES);
			Producto p134 = new Producto(17,"pantalones de campana de estampado", 28, Colorc.AZUL, Talla.XS, TipoProducto.PANTALONES);
			InsertarProducto(p130,"");
			InsertarProducto(p1301,"");
			InsertarProducto(p132,"");
			InsertarProducto(p133,"");
			InsertarProducto(p134,"");
			
			//producto14
			Producto p140 = new Producto(13, "vestido de invierno", 29, Colorc.BLANCO, Talla.M, TipoProducto.VESTIDOS);
			Producto p1401 = new Producto(14, "vestido de invierno", 29, Colorc.BLANCO, Talla.S, TipoProducto.VESTIDOS);
			Producto p1402 = new Producto(15, "vestido de invierno", 29, Colorc.BLANCO, Talla.L, TipoProducto.VESTIDOS);
			Producto p1403 = new Producto(16, "vestido de invierno", 29, Colorc.BLANCO, Talla.XL, TipoProducto.VESTIDOS);
			Producto p1404 = new Producto(17,"vestido de invierno", 29, Colorc.BLANCO, Talla.XS, TipoProducto.VESTIDOS);
			InsertarProducto(p140,"");
			InsertarProducto(p1401,"");
			InsertarProducto(p1402,"");
			InsertarProducto(p1403,"");
			InsertarProducto(p1404,"");
			
			//producto15 (mismo que el 14 pero distinto color)
			Producto p150 = new Producto(13, "vestido de invierno", 29, Colorc.GRIS, Talla.M, TipoProducto.VESTIDOS);
			Producto p1501 = new Producto(14, "vestido de invierno", 29, Colorc.GRIS, Talla.S, TipoProducto.VESTIDOS);
			Producto p1502 = new Producto(15, "vestido de invierno", 29, Colorc.GRIS, Talla.L, TipoProducto.VESTIDOS);
			Producto p1503 = new Producto(16, "vestido de invierno", 29, Colorc.GRIS, Talla.XL, TipoProducto.VESTIDOS);
			Producto p1504 = new Producto(17,"vestido de invierno", 29, Colorc.GRIS, Talla.XS, TipoProducto.VESTIDOS);
			InsertarProducto(p150,"");
			InsertarProducto(p1501,"");
			InsertarProducto(p1502,"");
			InsertarProducto(p1503,"");
			InsertarProducto(p1504,"");
			
			//producto16 (mismo que el 14 pero distinto color)
			Producto p160 = new Producto(13, "vestido de invierno", 29, Colorc.VERDE, Talla.M, TipoProducto.VESTIDOS);
			Producto p1601 = new Producto(14, "vestido de invierno", 29, Colorc.VERDE, Talla.S, TipoProducto.VESTIDOS);
			Producto p1602 = new Producto(15, "vestido de invierno", 29, Colorc.VERDE, Talla.L, TipoProducto.VESTIDOS);
			Producto p1603 = new Producto(16, "vestido de invierno", 29, Colorc.VERDE, Talla.XL, TipoProducto.VESTIDOS);
			Producto p1604 = new Producto(17,"vestido de invierno", 29, Colorc.VERDE, Talla.XS, TipoProducto.VESTIDOS);
			InsertarProducto(p160,"");
			InsertarProducto(p1601,"");
			InsertarProducto(p1602,"");
			InsertarProducto(p1603,"");
			InsertarProducto(p1604,"");
			
			//producto17 
			Producto p170 = new Producto(13, "pantalones cortos", 25, Colorc.BLANCO, Talla.M, TipoProducto.PANTALONES);
			Producto p1701 = new Producto(14, "pantalones cortos", 25, Colorc.BLANCO, Talla.S, TipoProducto.PANTALONES);
			Producto p1702 = new Producto(15, "pantalones cortos", 25, Colorc.BLANCO, Talla.L, TipoProducto.PANTALONES);
			Producto p1703 = new Producto(16, "pantalones cortos", 25, Colorc.BLANCO, Talla.XL, TipoProducto.PANTALONES);
			Producto p1704 = new Producto(17,"pantalones cortos", 25, Colorc.BLANCO, Talla.XS, TipoProducto.PANTALONES);
			InsertarProducto(p170,"");
			InsertarProducto(p1701,"");
			InsertarProducto(p1702,"");
			InsertarProducto(p1703,"");
			InsertarProducto(p1704,"");
			
			//producto18 (mismo producto que el 17 pero distinto color) 
			Producto p180 = new Producto(13, "pantalones cortos", 25, Colorc.AZUL, Talla.M, TipoProducto.PANTALONES);
			Producto p1801 = new Producto(14, "pantalones cortos", 25, Colorc.AZUL, Talla.S, TipoProducto.PANTALONES);
			Producto p1802 = new Producto(15, "pantalones cortos", 25, Colorc.AZUL, Talla.L, TipoProducto.PANTALONES);
			Producto p1803 = new Producto(16, "pantalones cortos", 25, Colorc.AZUL, Talla.XL, TipoProducto.PANTALONES);
			Producto p1804 = new Producto(17,"pantalones cortos", 25, Colorc.AZUL, Talla.XS, TipoProducto.PANTALONES);
			InsertarProducto(p180,"");
			InsertarProducto(p1801,"");
			InsertarProducto(p1802,"");
			InsertarProducto(p1803,"");
			InsertarProducto(p1804,"");
			
			//producto19 (mismo producto que el 17 pero distinto color) 
			Producto p190 = new Producto(13, "pantalones cortos", 25, Colorc.AMARILLO, Talla.M, TipoProducto.PANTALONES);
			Producto p1901 = new Producto(14, "pantalones cortos", 25, Colorc.AMARILLO, Talla.S, TipoProducto.PANTALONES);
			Producto p1902 = new Producto(15, "pantalones cortos", 25, Colorc.AMARILLO, Talla.L, TipoProducto.PANTALONES);
			Producto p1903 = new Producto(16, "pantalones cortos", 25, Colorc.AMARILLO, Talla.XL, TipoProducto.PANTALONES);
			Producto p1904 = new Producto(17,"pantalones cortos", 25, Colorc.AMARILLO, Talla.XS, TipoProducto.PANTALONES);
			InsertarProducto(p190,"");
			InsertarProducto(p1901,"");
			InsertarProducto(p1902,"");
			InsertarProducto(p1903,"");
			InsertarProducto(p1904,"");
			
			//producto20 (mismo producto que el 17 pero distinto color)
			Producto p200 = new Producto(13, "pantalones cortos", 25, Colorc.ROSA, Talla.M, TipoProducto.PANTALONES);
			Producto p2001 = new Producto(14, "pantalones cortos", 25, Colorc.ROSA, Talla.S, TipoProducto.PANTALONES);
			Producto p2002 = new Producto(15, "pantalones cortos", 25, Colorc.ROSA, Talla.L, TipoProducto.PANTALONES);
			Producto p2003 = new Producto(16, "pantalones cortos", 25, Colorc.ROSA, Talla.XL, TipoProducto.PANTALONES);
			Producto p2004 = new Producto(17,"pantalones cortos", 25, Colorc.ROSA, Talla.XS, TipoProducto.PANTALONES);
			InsertarProducto(p200,"");
			InsertarProducto(p2001,"");
			InsertarProducto(p2002,"");
			InsertarProducto(p2003,"");
			InsertarProducto(p2004,"");
			
			//producto21 
			Producto p210 = new Producto(13, "falda larga", 30, Colorc.ROSA, Talla.M, TipoProducto.FALDA);
			Producto p2101 = new Producto(14, "falda larga", 30, Colorc.ROSA, Talla.S, TipoProducto.FALDA);
			Producto p2102 = new Producto(15, "falda larga", 30, Colorc.ROSA, Talla.L, TipoProducto.FALDA);
			Producto p2103 = new Producto(16, "falda larga", 30, Colorc.ROSA, Talla.XL, TipoProducto.FALDA);
			Producto p2104 = new Producto(17,"falda larga", 30, Colorc.ROSA, Talla.XS, TipoProducto.FALDA);
			InsertarProducto(p210,"");
			InsertarProducto(p2101,"");
			InsertarProducto(p2102,"");
			InsertarProducto(p2103,"");
			InsertarProducto(p2104,"");
			
			//producto22 (mismo producto que el 21 distinto color) 
			Producto p220 = new Producto(13, "falda larga", 30, Colorc.BLANCO, Talla.M, TipoProducto.FALDA);
			Producto p2201 = new Producto(14, "falda larga", 30, Colorc.BLANCO, Talla.S, TipoProducto.FALDA);
			Producto p2202 = new Producto(15, "falda larga", 30, Colorc.BLANCO, Talla.L, TipoProducto.FALDA);
			Producto p2203 = new Producto(16, "falda larga", 30, Colorc.BLANCO, Talla.XL, TipoProducto.FALDA);
			Producto p2204 = new Producto(17,"falda larga", 30, Colorc.BLANCO, Talla.XS, TipoProducto.FALDA);
			InsertarProducto(p220,"");
			InsertarProducto(p2201,"");
			InsertarProducto(p2202,"");
			InsertarProducto(p2203,"");
			InsertarProducto(p2204,"");
			
			//producto23 (mismo producto que el 21 distinto color) 
			Producto p230 = new Producto(13, "falda larga", 30, Colorc.NEGRO, Talla.M, TipoProducto.FALDA);
			Producto p2301 = new Producto(14, "falda larga", 30, Colorc.NEGRO, Talla.S, TipoProducto.FALDA);
			Producto p2302 = new Producto(15, "falda larga", 30, Colorc.NEGRO, Talla.L, TipoProducto.FALDA);
			Producto p2303 = new Producto(16, "falda larga", 30, Colorc.NEGRO, Talla.XL, TipoProducto.FALDA);
			Producto p2304 = new Producto(17,"falda larga", 30, Colorc.NEGRO, Talla.XS, TipoProducto.FALDA);
			InsertarProducto(p230,"");
			InsertarProducto(p2301,"");
			InsertarProducto(p2302,"");
			InsertarProducto(p2303,"");
			InsertarProducto(p2304,"");
			
			//producto24 
			Producto p240 = new Producto(13, "falda corta", 30, Colorc.BLANCO, Talla.M, TipoProducto.FALDA);
			Producto p2401 = new Producto(14, "falda corta", 30, Colorc.BLANCO, Talla.S, TipoProducto.FALDA);
			Producto p2402 = new Producto(15, "falda corta", 30, Colorc.BLANCO, Talla.L, TipoProducto.FALDA);
			Producto p2403 = new Producto(16, "falda corta", 30, Colorc.BLANCO, Talla.XL, TipoProducto.FALDA);
			Producto p2404 = new Producto(17,"falda corta", 30, Colorc.BLANCO, Talla.XS, TipoProducto.FALDA);
			InsertarProducto(p240,"");
			InsertarProducto(p2401,"");
			InsertarProducto(p2402,"");
			InsertarProducto(p2403,"");
			InsertarProducto(p2404,"");
			
			//producto25 (mismo producto que el 24 distinto color) 
			Producto p250 = new Producto(13, "falda corta", 30, Colorc.NEGRO, Talla.M, TipoProducto.FALDA);
			Producto p2501 = new Producto(14, "falda corta", 30, Colorc.NEGRO, Talla.S, TipoProducto.FALDA);
			Producto p2502 = new Producto(15, "falda corta", 30, Colorc.NEGRO, Talla.L, TipoProducto.FALDA);
			Producto p2503 = new Producto(16, "falda corta", 30, Colorc.NEGRO, Talla.XL, TipoProducto.FALDA);
			Producto p2504 = new Producto(17,"falda corta", 30, Colorc.NEGRO, Talla.XS, TipoProducto.FALDA);
			InsertarProducto(p250,"");
			InsertarProducto(p2501,"");
			InsertarProducto(p2502,"");
			InsertarProducto(p2503,"");
			InsertarProducto(p2504,"");
			
			//producto26 (mismo producto que el 24 distinto color) 
			Producto p260 = new Producto(13, "falda corta", 30, Colorc.ROJO, Talla.M, TipoProducto.FALDA);
			Producto p2601 = new Producto(14, "falda corta", 30, Colorc.ROJO, Talla.S, TipoProducto.FALDA);
			Producto p2602 = new Producto(15, "falda corta", 30, Colorc.ROJO, Talla.L, TipoProducto.FALDA);
			Producto p2603 = new Producto(16, "falda corta", 30, Colorc.ROJO, Talla.XL, TipoProducto.FALDA);
			Producto p2604 = new Producto(17,"falda corta", 30, Colorc.ROJO, Talla.XS, TipoProducto.FALDA);
			InsertarProducto(p260,"");
			InsertarProducto(p2601,"");
			InsertarProducto(p2602,"");
			InsertarProducto(p2603,"");
			InsertarProducto(p2604,"");
			
			
			//Insertar pedidos
			InsertarPedido(1, "45344345L");
			InsertarPedido(2, "74544345L");
			InsertarPedido(3, "68458021D");
			InsertarPedido(4, "45345678H");
			InsertarPedido(5, "78302745L");
			InsertarPedido(6, "78370236N");
			InsertarPedido(7, "70348021M");
			InsertarPedido(8, "74902345J");
			InsertarPedido(9, "74920473K");
			InsertarPedido(1, "72950639N");
			InsertarPedido(2, "75920576H");
			InsertarPedido(3, "29576923S");
			InsertarPedido(4, "83068219S");
			InsertarPedido(5, "83068219S");
			InsertarPedido(6, "850234681I");
			InsertarPedido(7, "83068219S");
			InsertarPedido(1, "45344345L");
			InsertarPedido(2, "74544345L");
			InsertarPedido(3, "68458021D");
			InsertarPedido(4, "45345678H");
			InsertarPedido(5, "78302745L");
			InsertarPedido(6, "78370236N");
			InsertarPedido(7, "70348021M");
			InsertarPedido(8, "74902345J");
			InsertarPedido(9, "74920473K");
			InsertarPedido(1, "72950639N");
			InsertarPedido(2, "75920576H");
			InsertarPedido(3, "29576923S");
			InsertarPedido(4, "83068219S");
			InsertarPedido(5, "83068219S");
			InsertarPedido(6, "850234681I");
			InsertarPedido(7, "83068219S");
			
			//insertar tiendas.
			Tienda t1=new Tienda(1,Franquicia.BERSHKA);
			Tienda t2=new Tienda(2,Franquicia.LEFTIES);
			Tienda t3=new Tienda(3,Franquicia.OYSHO);
			Tienda t4=new Tienda(4,Franquicia.PULLANDBEAR);
			Tienda t5=new Tienda(5,Franquicia.STRADIVARIUS);
			Tienda t6=new Tienda(6,Franquicia.ZARA);
			InsertarTienda(t1,"");
			InsertarTienda(t2,"");
			InsertarTienda(t3,"");
			InsertarTienda(t4,"");
			InsertarTienda(t5,"");
			InsertarTienda(t6,"");
			
			//insertar relacion de compra. 
			
			
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log( Level.SEVERE, "No se pudo crear la base de datos", e );
		}
		}
    
    public static boolean InsertarUsuario(Usuario us) {
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
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
		
    			
    }
    
    public static boolean InsertarProducto(Producto pro, String rutafoto) {
    	String sent = "";
    	try {
    		Statement stmt = abrirlaconexion("DeustoOutlet.db");
        	sent = "insert into producto values(" + 
        			"" + pro.getCodigo() + ", " +
        			"'" + pro.getNombre() + "', " +
        			"" + pro.getPrecio() + ", " + 
        			"'" + pro.getColor() + "', " +
        			"'" + pro.getTalla() + "', " + 
        			"'" + pro.getTipo() + "','"+ rutafoto+"')";
        	for (int i=0;i<=10;i++) {
        		int val = stmt.executeUpdate(sent);
        		logger.log( Level.INFO, "BD añadida " + val + " fila\t" + sent);
    			if(val != 1) {
    				logger.log( Level.SEVERE, "Error en insert de BD\t" + sent);
    				return false;  
    			}
        	}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
    }
    public static boolean InsertarPedido(int codigo, String dni) {
    	Statement stmt;
		try {
			stmt = abrirlaconexion("DeustoOutlet.db");
			 String p ="insert into pedido values ("+codigo+", '"+dni+"');";
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
    public static boolean InsertarTienda(Tienda t, String rutafoto) {
    	String sent = "";
    	try {
    		Statement stmt = abrirlaconexion("DeustoOutlet.db");
        	sent = "insert into tienda values(" + 
        			"" + t.getCodigo() + ", " +
        			"'" + t.getFranquicia() +  "','"+ rutafoto+"')";
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
    public static boolean InsertarCompra(int codigo, String c1,String c2, String c3) {
    	String sent = "";
    	try {
    		Statement stmt = abrirlaconexion("DeustoOutlet.db");
        	
//        	if(val != 1) {
//				logger.log( Level.SEVERE, "Error en insert de BD\t" + sent);
//				return false;  
//			}
//        	
    		
    		
    		
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
    }
   public static ArrayList<Producto> getProductos(){
	   String sent = "";
	   ArrayList<Producto> lproducto = new ArrayList<Producto>();
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   sent = "select * from producto";
		   ResultSet rs = stm.executeQuery(sent);
		   while (rs.next()) {
			   Producto p = new Producto(rs.getInt("codigo_producto"), rs.getString("nombre"), rs.getInt("precio"), rs.getString("color"), rs.getString("talla"), rs.getString("tipo"));
			   lproducto.add(p);
		   }
		   rs.close();
		   logger.log(Level.INFO, "BD\t" + sent);
		   System.out.println(lproducto);
		   return lproducto;
	} catch (SQLException e) {
		logger.log(Level.SEVERE, "Error en BD\t" + sent, e);
		lastError = e;
		e.printStackTrace();
		return null;
	}
	   
   }
   
   public static Exception getLastError() {
		return lastError;
	}
   
   
 	
	
}
