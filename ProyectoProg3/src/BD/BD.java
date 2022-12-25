package BD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
			stmt.executeUpdate("drop table if exists usuario");
			int veces1=stmt.executeUpdate(com);
			if (veces1==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com4 = "create table pedido(codigo_pedido INTEGER primary key autoincrement, dni String, estado String, fecha_compra String, codigo_producto INTEGER)"; //clave externa del dni del usuario. 
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
			String com3 = "create table producto(codigo_producto integer, nombre String, precio integer, color String, talla String, tipo String , ruta_foto String, codigo_tienda integer, cantidad integer)";
			stmt.executeUpdate("drop table if exists producto");
			int veces4=stmt.executeUpdate(com3);
			if (veces4==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com5 = "create table compra(codigo_pertenece INTEGER primary key autoincrement, id_pedido integer ,id_producto integer)";
			stmt.executeUpdate("drop table if exists compra");
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
			InsertarUsuario(new Usuario("Daniela", "85234681I", "08/06/2001", "756902347", "Calle Doctor Areilza", "Diez", "dadiez", "daninueve"));

			
			// insertar producto/ mismo producto en diferentes tallas
			//producto1. t1
			Producto p1 = new Producto(1, "Chaqueta de pelo", 50, Colorc.NEGRO, Talla.XS, TipoProducto.CHAQUETA);
			Producto p11 = new Producto(2, "Chaqueta de pelo", 50, Colorc.NEGRO, Talla.S, TipoProducto.CHAQUETA);
			Producto p111= new Producto(3, "Chaqueta de pelo", 50, Colorc.NEGRO, Talla.M, TipoProducto.CHAQUETA);
			Producto p1111 = new Producto(4, "Chaqueta de pelo", 50, Colorc.NEGRO, Talla.L, TipoProducto.CHAQUETA);
			Producto p11111 = new Producto(5, "Chaqueta de pelo", 50, Colorc.NEGRO, Talla.XL, TipoProducto.CHAQUETA);
			InsertarProducto(p1,"Fotosproductos/prod1.jpg",1);
			InsertarProducto(p11,"Fotosproductos/prod1.jpg",1);
			InsertarProducto(p111,"Fotosproductos/prod1.jpg",1);
			InsertarProducto(p1111,"Fotosproductos/prod1.jpg",1);
			InsertarProducto(p11111,"Fotosproductos/prod1.jpg",1);
			
			
			//producto2. t2
			Producto p2 = new Producto(6, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.XS, TipoProducto.PANTALON);
			Producto p22 = new Producto(7, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.S, TipoProducto.PANTALON);
			Producto p222 = new Producto(8, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.M, TipoProducto.PANTALON);
			Producto p2222 = new Producto(9, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.L, TipoProducto.PANTALON);
			Producto p22222 = new Producto(10, "Pantalones vaqueros", 15, Colorc.AZUL, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p2,"Fotosproductos/prod2.jpg",2);
			InsertarProducto(p22,"Fotosproductos/prod2.jpg",2);
			InsertarProducto(p222,"Fotosproductos/prod2.jpg",2);
			InsertarProducto(p2222,"Fotosproductos/prod2.jpg",2);
			InsertarProducto(p22222,"Fotosproductos/prod2.jpg",2);
			

			//producto3t3
			Producto p3 = new Producto(11, "Camisa con volantes", 20, Colorc.BLANCO, Talla.XS, TipoProducto.CAMISETA);
			Producto p33 = new Producto(12, "Camisa con volantes", 20, Colorc.BLANCO, Talla.S, TipoProducto.CAMISETA);
			Producto p333 = new Producto(13, "Camisa con volantes", 20, Colorc.BLANCO, Talla.M, TipoProducto.CAMISETA);
			Producto p3333 = new Producto(14, "Camisa con volantes", 20, Colorc.BLANCO, Talla.L, TipoProducto.CAMISETA);
			Producto p33333 = new Producto(15, "Camisa con volantes", 20, Colorc.BLANCO, Talla.XL, TipoProducto.CAMISETA);
			InsertarProducto(p3,"Fotosproductos/prod3.jpg",3);
			InsertarProducto(p33,"Fotosproductos/prod3.jpg",3);
			InsertarProducto(p333,"Fotosproductos/prod3.jpg",3);
			InsertarProducto(p3333,"Fotosproductos/prod3.jpg",3);
			InsertarProducto(p33333,"Fotosproductos/prod3.jpg",3);
			
			
			//PRODUCTO4 t4	
			Producto p4 = new Producto(16, "Pantalones parachute", 30, Colorc.NEGRO, Talla.XS,TipoProducto.PANTALON);
			Producto p44 =new Producto(17, "Pantalones parachute", 30, Colorc.NEGRO, Talla.S,TipoProducto.PANTALON);
			Producto p444 =new Producto(18, "Pantalones parachute", 30, Colorc.NEGRO, Talla.M,TipoProducto.PANTALON);
			Producto p4444 =new Producto(19, "Pantalones parachute", 30, Colorc.NEGRO, Talla.L,TipoProducto.PANTALON);
			Producto p44444 =new Producto(20, "Pantalones parachute", 30, Colorc.NEGRO, Talla.XL,TipoProducto.PANTALON);			
			InsertarProducto(p4,"Fotosproductos/prod4.jpg",4);
			InsertarProducto(p44,"Fotosproductos/prod4.jpg",4);
			InsertarProducto(p444,"Fotosproductos/prod4.jpg",4);
			InsertarProducto(p4444,"Fotosproductos/prod4.jpg",4);
			InsertarProducto(p44444,"Fotosproductos/prod4.jpg",4);
			
			//PRODUCTO5(mismo producto que el 4 pero de otro color) t4
			Producto p5 = new Producto(21, "Pantalones parachute", 30, Colorc.VERDE, Talla.XS,TipoProducto.PANTALON);
			Producto p55 =new Producto(22, "Pantalones parachute", 30, Colorc.VERDE, Talla.S,TipoProducto.PANTALON);
			Producto p555 =new Producto(23, "Pantalones parachute", 30, Colorc.VERDE, Talla.M,TipoProducto.PANTALON);
			Producto p5555=new Producto(24, "Pantalones parachute", 30, Colorc.VERDE, Talla.L,TipoProducto.PANTALON);
			Producto p55555 =new Producto(25, "Pantalones parachute", 30, Colorc.VERDE, Talla.XL,TipoProducto.PANTALON);			
			InsertarProducto(p5,"Fotosproductos/prod5.jpg",4);
			InsertarProducto(p55,"Fotosproductos/prod5.jpg",4);
			InsertarProducto(p555,"Fotosproductos/prod5.jpg",4);
			InsertarProducto(p5555,"Fotosproductos/prod5.jpg",4);
			InsertarProducto(p55555,"Fotosproductos/prod5.jpg",4);
			
			//PRODUCTO6(mismo producto que el 4 pero de otro color) t4
			Producto p6 = new Producto(26, "Pantalones parachute", 30, Colorc.GRIS, Talla.XS,TipoProducto.PANTALON);
			Producto p66 =new Producto(27, "Pantalones parachute", 30, Colorc.GRIS, Talla.S,TipoProducto.PANTALON);
			Producto p666 =new Producto(28, "Pantalones parachute", 30,Colorc.GRIS, Talla.M,TipoProducto.PANTALON);
			Producto p6666=new Producto(29, "Pantalones parachute", 30, Colorc.GRIS, Talla.L,TipoProducto.PANTALON);
			Producto p66666 =new Producto(30, "Pantalones parachute", 30, Colorc.GRIS, Talla.XL,TipoProducto.PANTALON);			
			InsertarProducto(p6,"Fotosproductos/prod6.jpg",4);
			InsertarProducto(p66,"Fotosproductos/prod6.jpg",4);
			InsertarProducto(p666,"Fotosproductos/prod6.jpg",4);
			InsertarProducto(p6666,"Fotosproductos/prod6.jpg",4);
			InsertarProducto(p66666,"Fotosproductos/prod6.jpg",4);
			
			
			//producto7 t5
			Producto p7 = new Producto(31, "Blusa con boton delantero", 35, Colorc.BLANCO, Talla.XS, TipoProducto.CAMISETA);
			Producto p77 = new Producto(32, "Blusa con boton delantero", 35, Colorc.BLANCO, Talla.S, TipoProducto.CAMISETA);
			Producto p777 = new Producto(33, "Blusa con boton delantero", 35, Colorc.BLANCO, Talla.M, TipoProducto.CAMISETA);
			Producto p7777 = new Producto(34, "Blusa con boton delantero", 35, Colorc.BLANCO, Talla.L, TipoProducto.CAMISETA);
			Producto p77777 = new Producto(35,"Blusa con boton delantero", 35, Colorc.BLANCO, Talla.XL, TipoProducto.CAMISETA);
			InsertarProducto(p7,"Fotosproductos/prod7.jpg",5);
			InsertarProducto(p77,"Fotosproductos/prod7.jpg",5);
			InsertarProducto(p777,"Fotosproductos/prod7.jpg",5);
			InsertarProducto(p7777,"Fotosproductos/prod7.jpg",5);
			InsertarProducto(p77777,"Fotosproductos/prod7.jpg",5);
			
			//producto8 t6
			Producto p8 = new Producto(36, "Vestido de verano", 29, Colorc.BLANCO, Talla.XS, TipoProducto.VESTIDO);
			Producto p88 = new Producto(37, "Vestido de verano", 29, Colorc.BLANCO, Talla.S, TipoProducto.VESTIDO);
			Producto p888 = new Producto(38, "Vestido de verano", 29, Colorc.BLANCO, Talla.M, TipoProducto.VESTIDO);
			Producto p8888 = new Producto(39, "Vestido de verano", 29, Colorc.BLANCO, Talla.L, TipoProducto.VESTIDO);
			Producto p88888 = new Producto(40,"vestido de verano", 29, Colorc.BLANCO, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p8,"Fotosproductos/prod8.jpg",6);
			InsertarProducto(p88,"Fotosproductos/prod8.jpg",6);
			InsertarProducto(p888,"Fotosproductos/prod8.jpg",6);
			InsertarProducto(p8888,"Fotosproductos/prod8.jpg",6);
			InsertarProducto(p88888,"Fotosproductos/prod8.jpg",6);
			
			//producto9 (MISMO PRODUCTO QUE EL 8 PERO EN OTRO COLOR )
			Producto p9 = new Producto(41, "Vestido de verano", 29, Colorc.AMARILLO, Talla.XS, TipoProducto.VESTIDO);
			Producto p99 = new Producto(42, "Vestido de verano", 29, Colorc.AMARILLO, Talla.S, TipoProducto.VESTIDO);
			Producto p999 = new Producto(43, "Vestido de verano", 29, Colorc.AMARILLO, Talla.M, TipoProducto.VESTIDO);
			Producto p9999 = new Producto(44, "Vestido de verano", 29, Colorc.AMARILLO, Talla.L, TipoProducto.VESTIDO);
			Producto p99999 = new Producto(45,"Vestido de verano", 29, Colorc.AMARILLO, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p9,"Fotosproductos/prod9.jpg",6);
			InsertarProducto(p99,"Fotosproductos/prod9.jpg",6);
			InsertarProducto(p999,"Fotosproductos/prod9.jpg",6);
			InsertarProducto(p9999,"Fotosproductos/prod9.jpg",6);
			InsertarProducto(p99999,"Fotosproductos/prod9.jpg",6);
			
			//producto10 
			Producto p10 = new Producto(46, "Pantalones de campana", 35, Colorc.ROJO, Talla.XS, TipoProducto.PANTALON);
			Producto p101 = new Producto(47,"Pantalones de campana", 35, Colorc.ROJO, Talla.S, TipoProducto.PANTALON);
			Producto p102 = new Producto(48, "Pantalones de campana", 35, Colorc.ROJO, Talla.M, TipoProducto.PANTALON);
			Producto p103 = new Producto(49, "Pantalones de campana", 35, Colorc.ROJO, Talla.L, TipoProducto.PANTALON);
			Producto p104 = new Producto(50, "Pantalones de campana", 35, Colorc.ROJO, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p10,"Fotosproductos/prod10.jpg",1);
			InsertarProducto(p101,"Fotosproductos/prod10.jpg",1);
			InsertarProducto(p102,"Fotosproductos/prod10.jpg",1);
			InsertarProducto(p103,"Fotosproductos/prod10.jpg",1);
			InsertarProducto(p104,"Fotosproductos/prod10.jpg",1);
			
			//producto11 (mismo producto que el 10 pero en otro color)
			Producto p110 = new Producto(51, "Pantalones de campana", 35, Colorc.BLANCO, Talla.XS, TipoProducto.PANTALON);
			Producto p1101 = new Producto(52,"Pantalones de campana", 35, Colorc.BLANCO, Talla.S, TipoProducto.PANTALON);
			Producto p112 = new Producto(53, "Pantalones de campana", 35, Colorc.BLANCO, Talla.M, TipoProducto.PANTALON);
			Producto p113 = new Producto(54,"Pantalones de campana", 35, Colorc.BLANCO, Talla.L, TipoProducto.PANTALON);
			Producto p114 = new Producto(55,"Pantalones de campana", 35, Colorc.BLANCO, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p110,"Fotosproductos/prod11.jpeg",1);
			InsertarProducto(p1101,"Fotosproductos/prod11.jpeg",1);
			InsertarProducto(p112,"Fotosproductos/prod11.jpeg",1);
			InsertarProducto(p113,"Fotosproductos/prod11.jpeg",1);
			InsertarProducto(p114,"Fotosproductos/prod11.jpeg",1);
			
			//producto12 (mismo producto que el 10 pero en otro color)
			Producto p120 = new Producto(56, "Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.XS, TipoProducto.PANTALON);
			Producto p1201 = new Producto(57,"Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.S, TipoProducto.PANTALON);
			Producto p122 = new Producto(58, "Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.M, TipoProducto.PANTALON);
			Producto p123 = new Producto(59,"Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.L, TipoProducto.PANTALON);
			Producto p124 = new Producto(60,"Pantalones de campana de estampado", 28, Colorc.NARANJA, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p120,"Fotosproductos/prod12.jpg",2);
			InsertarProducto(p1201,"Fotosproductos/prod12.jpg",2);
			InsertarProducto(p122,"Fotosproductos/prod12.jpg",2);
			InsertarProducto(p123,"Fotosproductos/prod12.jpg",2);
			InsertarProducto(p124,"Fotosproductos/prod12.jpg",2);
			
			//PRODUCTO13 (mismo producto que el 10 pero otro color)
			Producto p130 = new Producto(61, "Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.XS, TipoProducto.PANTALON);
			Producto p1301 = new Producto(62,"Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.S, TipoProducto.PANTALON);
			Producto p132 = new Producto(63, "Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.M, TipoProducto.PANTALON);
			Producto p133 = new Producto(64,"Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.L, TipoProducto.PANTALON);
			Producto p134 = new Producto(65,"Pantalones de campana de estampado", 28, Colorc.AZUL, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p130,"Fotosproductos/prod13.jpeg",2);
			InsertarProducto(p1301,"Fotosproductos/prod13.jpeg",2);
			InsertarProducto(p132,"Fotosproductos/prod13.jpeg",2);
			InsertarProducto(p133,"Fotosproductos/prod13.jpeg",2);
			InsertarProducto(p134,"Fotosproductos/prod13.jpeg",2);
			
			//producto14
			Producto p140 = new Producto(66, "Vestido de invierno", 29, Colorc.BLANCO, Talla.XS, TipoProducto.VESTIDO);
			Producto p1401 = new Producto(67, "Vestido de invierno", 29, Colorc.BLANCO, Talla.S, TipoProducto.VESTIDO);
			Producto p1402 = new Producto(68, "Vestido de invierno", 29, Colorc.BLANCO, Talla.M, TipoProducto.VESTIDO);
			Producto p1403 = new Producto(69, "Vestido de invierno", 29, Colorc.BLANCO, Talla.L, TipoProducto.VESTIDO);
			Producto p1404 = new Producto(70,"Vestido de invierno", 29, Colorc.BLANCO, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p140,"Fotosproductos/prod14.jpeg",3);
			InsertarProducto(p1401,"Fotosproductos/prod14.jpeg",3);
			InsertarProducto(p1402,"Fotosproductos/prod14.jpeg",3);
			InsertarProducto(p1403,"Fotosproductos/prod14.jpeg",3);
			InsertarProducto(p1404,"Fotosproductos/prod14.jpeg",3);
			
			//producto15 (mismo que el 14 pero distinto color)
			Producto p150 = new Producto(71, "Vestido de invierno", 29, Colorc.GRIS, Talla.XS, TipoProducto.VESTIDO);
			Producto p1501 = new Producto(72, "Vestido de invierno", 29, Colorc.GRIS, Talla.S, TipoProducto.VESTIDO);
			Producto p1502 = new Producto(73, "Vestido de invierno", 29, Colorc.GRIS, Talla.M, TipoProducto.VESTIDO);
			Producto p1503 = new Producto(74, "Vestido de invierno", 29, Colorc.GRIS, Talla.L, TipoProducto.VESTIDO);
			Producto p1504 = new Producto(75,"Vestido de invierno", 29, Colorc.GRIS, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p150,"Fotosproductos/prod15.jpg",3);
			InsertarProducto(p1501,"Fotosproductos/prod15.jpg",3);
			InsertarProducto(p1502,"Fotosproductos/prod15.jpg",3);
			InsertarProducto(p1503,"Fotosproductos/prod15.jpg",3);
			InsertarProducto(p1504,"Fotosproductos/prod15.jpg",3);
			
			//producto16 (mismo que el 14 pero distinto color)
			Producto p160 = new Producto(76, "Vestido de invierno", 29, Colorc.VERDE, Talla.XS, TipoProducto.VESTIDO);
			Producto p1601 = new Producto(77, "Vestido de invierno", 29, Colorc.VERDE, Talla.S, TipoProducto.VESTIDO);
			Producto p1602 = new Producto(78, "Vestido de invierno", 29, Colorc.VERDE, Talla.M, TipoProducto.VESTIDO);
			Producto p1603 = new Producto(79, "Vestido de invierno", 29, Colorc.VERDE, Talla.L, TipoProducto.VESTIDO);
			Producto p1604 = new Producto(80,"Vestido de invierno", 29, Colorc.VERDE, Talla.XL, TipoProducto.VESTIDO);
			InsertarProducto(p160,"Fotosproductos/prod16.jpg",3);
			InsertarProducto(p1601,"Fotosproductos/prod16.jpg",3);
			InsertarProducto(p1602,"Fotosproductos/prod16.jpg",3);
			InsertarProducto(p1603,"Fotosproductos/prod16.jpg",3);
			InsertarProducto(p1604,"Fotosproductos/prod16.jpg",3);
			
			//producto17 
			Producto p170 = new Producto(81, "Pantalones cortos", 25, Colorc.BLANCO, Talla.XS, TipoProducto.PANTALON);
			Producto p1701 = new Producto(82, "Pantalones cortos", 25, Colorc.BLANCO, Talla.S, TipoProducto.PANTALON);
			Producto p1702 = new Producto(83, "Pantalones cortos", 25, Colorc.BLANCO, Talla.M, TipoProducto.PANTALON);
			Producto p1703 = new Producto(84, "Pantalones cortos", 25, Colorc.BLANCO, Talla.L, TipoProducto.PANTALON);
			Producto p1704 = new Producto(85,"Pantalones cortos", 25, Colorc.BLANCO, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p170,"Fotosproductos/prod17.jpg",4);
			InsertarProducto(p1701,"Fotosproductos/prod17.jpg",4);
			InsertarProducto(p1702,"Fotosproductos/prod17.jpg",4);
			InsertarProducto(p1703,"Fotosproductos/prod17.jpg",4);
			InsertarProducto(p1704,"Fotosproductos/prod17.jpg",4);
			
			//producto18 (mismo producto que el 17 pero distinto color) 
			Producto p180 = new Producto(86, "Pantalones cortos", 25, Colorc.AZUL, Talla.XS, TipoProducto.PANTALON);
			Producto p1801 = new Producto(87, "Pantalones cortos", 25, Colorc.AZUL, Talla.S, TipoProducto.PANTALON);
			Producto p1802 = new Producto(88, "Pantalones cortos", 25, Colorc.AZUL, Talla.M, TipoProducto.PANTALON);
			Producto p1803 = new Producto(89, "Pantalones cortos", 25, Colorc.AZUL, Talla.L, TipoProducto.PANTALON);
			Producto p1804 = new Producto(90,"Pantalones cortos", 25, Colorc.AZUL, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p180,"Fotosproductos/prod18.jpg",4);
			InsertarProducto(p1801,"Fotosproductos/prod18.jpg",4);
			InsertarProducto(p1802,"Fotosproductos/prod18.jpg",4);
			InsertarProducto(p1803,"Fotosproductos/prod18.jpg",4);
			InsertarProducto(p1804,"Fotosproductos/prod18.jpg",4);
			
			//producto19 (mismo producto que el 17 pero distinto color) 
			Producto p190 = new Producto(91, "Pantalones cortos", 25, Colorc.AMARILLO, Talla.XS, TipoProducto.PANTALON);
			Producto p1901 = new Producto(92, "Pantalones cortos", 25, Colorc.AMARILLO, Talla.S, TipoProducto.PANTALON);
			Producto p1902 = new Producto(93, "Pantalones cortos", 25, Colorc.AMARILLO, Talla.M, TipoProducto.PANTALON);
			Producto p1903 = new Producto(94, "Pantalones cortos", 25, Colorc.AMARILLO, Talla.L, TipoProducto.PANTALON);
			Producto p1904 = new Producto(95,"Pantalones cortos", 25, Colorc.AMARILLO, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p190,"Fotosproductos/prod19.jpg",4);
			InsertarProducto(p1901,"Fotosproductos/prod19.jpg",4);
			InsertarProducto(p1902,"Fotosproductos/prod19.jpg",4);
			InsertarProducto(p1903,"Fotosproductos/prod19.jpg",4);
			InsertarProducto(p1904,"Fotosproductos/prod19.jpg",4);
			
			//producto20 (mismo producto que el 17 pero distinto color)
			Producto p200 = new Producto(96, "Pantalones cortos", 25, Colorc.ROSA, Talla.XS, TipoProducto.PANTALON);
			Producto p2001 = new Producto(97, "Pantalones cortos", 25, Colorc.ROSA, Talla.S, TipoProducto.PANTALON);
			Producto p2002 = new Producto(98, "Pantalones cortos", 25, Colorc.ROSA, Talla.M, TipoProducto.PANTALON);
			Producto p2003 = new Producto(99, "Pantalones cortos", 25, Colorc.ROSA, Talla.L, TipoProducto.PANTALON);
			Producto p2004 = new Producto(100,"Pantalones cortos", 25, Colorc.ROSA, Talla.XL, TipoProducto.PANTALON);
			InsertarProducto(p200,"Fotosproductos/prod20.jpg",4);
			InsertarProducto(p2001,"Fotosproductos/prod20.jpg",4);
			InsertarProducto(p2002,"Fotosproductos/prod20.jpg",4);
			InsertarProducto(p2003,"Fotosproductos/prod20.jpg",4);
			InsertarProducto(p2004,"Fotosproductos/prod20.jpg",4);
			
			//producto21 
			Producto p210 = new Producto(101, "Falda larga", 30, Colorc.ROSA, Talla.XS, TipoProducto.FALDA);
			Producto p2101 = new Producto(102, "Falda larga", 30, Colorc.ROSA, Talla.S, TipoProducto.FALDA);
			Producto p2102 = new Producto(103, "Falda larga", 30, Colorc.ROSA, Talla.M, TipoProducto.FALDA);
			Producto p2103 = new Producto(104, "Falda larga", 30, Colorc.ROSA, Talla.L, TipoProducto.FALDA);
			Producto p2104 = new Producto(105,"Falda larga", 30, Colorc.ROSA, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p210,"Fotosproductos/prod21.jpg",5);
			InsertarProducto(p2101,"Fotosproductos/prod21.jpg",5);
			InsertarProducto(p2102,"Fotosproductos/prod21.jpg",5);
			InsertarProducto(p2103,"Fotosproductos/prod21.jpg",5);
			InsertarProducto(p2104,"Fotosproductos/prod21.jpg",5);
			
			//producto22 (mismo producto que el 21 distinto color) 
			Producto p220 = new Producto(106, "Falda larga", 30, Colorc.BLANCO, Talla.XS, TipoProducto.FALDA);
			Producto p2201 = new Producto(107, "Falda larga", 30, Colorc.BLANCO, Talla.S, TipoProducto.FALDA);
			Producto p2202 = new Producto(108, "Falda larga", 30, Colorc.BLANCO, Talla.M, TipoProducto.FALDA);
			Producto p2203 = new Producto(109, "Falda larga", 30, Colorc.BLANCO, Talla.L, TipoProducto.FALDA);
			Producto p2204 = new Producto(110,"Falda larga", 30, Colorc.BLANCO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p220,"Fotosproductos/prod22.jpg",5);
			InsertarProducto(p2201,"Fotosproductos/prod22.jpg",5);
			InsertarProducto(p2202,"Fotosproductos/prod22.jpg",5);
			InsertarProducto(p2203,"Fotosproductos/prod22.jpg",5);
			InsertarProducto(p2204,"Fotosproductos/prod22.jpg",5);
			
			//producto23 (mismo producto que el 21 distinto color) 
			Producto p230 = new Producto(111, "Falda larga", 30, Colorc.NEGRO, Talla.XS, TipoProducto.FALDA);
			Producto p2301 = new Producto(112, "Falda larga", 30, Colorc.NEGRO, Talla.S, TipoProducto.FALDA);
			Producto p2302 = new Producto(113, "Falda larga", 30, Colorc.NEGRO, Talla.M, TipoProducto.FALDA);
			Producto p2303 = new Producto(114, "Falda larga", 30, Colorc.NEGRO, Talla.L, TipoProducto.FALDA);
			Producto p2304 = new Producto(115,"Falda larga", 30, Colorc.NEGRO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p230,"Fotosproductos/prod23.jpg",5);
			InsertarProducto(p2301,"Fotosproductos/prod23.jpg",5);
			InsertarProducto(p2302,"Fotosproductos/prod23.jpg",5);
			InsertarProducto(p2303,"Fotosproductos/prod23.jpg",5);
			InsertarProducto(p2304,"Fotosproductos/prod23.jpg",5);
			
			//producto24 
			Producto p240 = new Producto(116, "Falda corta", 30, Colorc.BLANCO, Talla.XS, TipoProducto.FALDA);
			Producto p2401 = new Producto(117, "Falda corta", 30, Colorc.BLANCO, Talla.S, TipoProducto.FALDA);
			Producto p2402 = new Producto(118, "Falda corta", 30, Colorc.BLANCO, Talla.M, TipoProducto.FALDA);
			Producto p2403 = new Producto(119, "Falda corta", 30, Colorc.BLANCO, Talla.L, TipoProducto.FALDA);
			Producto p2404 = new Producto(120,"Falda corta", 30, Colorc.BLANCO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p240,"Fotosproductos/prod24.jpg",2);
			InsertarProducto(p2401,"Fotosproductos/prod24.jpg",2);
			InsertarProducto(p2402,"Fotosproductos/prod24.jpg",2);
			InsertarProducto(p2403,"Fotosproductos/prod24.jpg",2);
			InsertarProducto(p2404,"Fotosproductos/prod24.jpg",2);
			
			//producto25 (mismo producto que el 24 distinto color) 
			Producto p250 = new Producto(121, "Falda corta", 30, Colorc.NEGRO, Talla.XS, TipoProducto.FALDA);
			Producto p2501 = new Producto(122, "Falda corta", 30, Colorc.NEGRO, Talla.S, TipoProducto.FALDA);
			Producto p2502 = new Producto(123, "Falda corta", 30, Colorc.NEGRO, Talla.M, TipoProducto.FALDA);
			Producto p2503 = new Producto(124, "Falda corta", 30, Colorc.NEGRO, Talla.L, TipoProducto.FALDA);
			Producto p2504 = new Producto(125,"Falda corta", 30, Colorc.NEGRO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p250,"Fotosproductos/prod25.jpeg",2);
			InsertarProducto(p2501,"Fotosproductos/prod25.jpeg",2);
			InsertarProducto(p2502,"Fotosproductos/prod25.jpeg",2);
			InsertarProducto(p2503,"Fotosproductos/prod25.jpeg",2);
			InsertarProducto(p2504,"Fotosproductos/prod25.jpeg",2);
			
			//producto26 (mismo producto que el 24 distinto color) 
			Producto p260 = new Producto(126, "Falda corta", 30, Colorc.ROJO, Talla.XS, TipoProducto.FALDA);
			Producto p2601 = new Producto(127, "Falda corta", 30, Colorc.ROJO, Talla.S, TipoProducto.FALDA);
			Producto p2602 = new Producto(128, "Falda corta", 30, Colorc.ROJO, Talla.M, TipoProducto.FALDA);
			Producto p2603 = new Producto(129, "Falda corta", 30, Colorc.ROJO, Talla.L, TipoProducto.FALDA);
			Producto p2604 = new Producto(130,"Falda corta", 30, Colorc.ROJO, Talla.XL, TipoProducto.FALDA);
			InsertarProducto(p260,"Fotosproductos/prod26.jpg",2);
			InsertarProducto(p2601,"Fotosproductos/prod26.jpg",2);
			InsertarProducto(p2602,"Fotosproductos/prod26.jpg",2);
			InsertarProducto(p2603,"Fotosproductos/prod26.jpg",2);
			InsertarProducto(p2604,"Fotosproductos/prod26.jpg",2);
			
			
			//Insertar pedidos
			Date fecha = new Date();
			InsertarPedido("45344345L", "Comprado", fecha.toString(), veces);
			InsertarPedido("74544345L", "Comprado", fecha.toString(), veces);
			InsertarPedido("68458021D", "Comprado", fecha.toString(), veces);
			InsertarPedido("45345678H", "Comprado", fecha.toString(), veces);
			InsertarPedido("78302745L", "Comprado", fecha.toString(), veces);
			InsertarPedido("78370236N", "Comprado", fecha.toString(), veces);
			InsertarPedido("70348021M", "Comprado", fecha.toString(), veces);
			InsertarPedido("74902345J", "Comprado", fecha.toString(), veces);
			InsertarPedido("74920473K", "Comprado", fecha.toString(), veces);
			InsertarPedido("72950639N", "Comprado", fecha.toString(), veces);
			InsertarPedido("75920576H", "Comprado", fecha.toString(), veces);
			InsertarPedido("29576923S", "Comprado", fecha.toString(), veces);
			InsertarPedido("83068219S", "Comprado", fecha.toString(), veces);
			InsertarPedido("83068219S", "Comprado", fecha.toString(), veces);
			InsertarPedido("85234681I", "Comprado", fecha.toString(), veces);
			InsertarPedido("83068219S", "Comprado", fecha.toString(), veces);
			InsertarPedido("45344345L", "Comprado", fecha.toString(), veces);
			InsertarPedido("74544345L", "Comprado", fecha.toString(), veces);
			InsertarPedido("68458021D", "Comprado", fecha.toString(), veces);
			InsertarPedido("45345678H", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("78302745L", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("78370236N", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("70348021M", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("74902345J", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("74920473K", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("72950639N", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("75920576H", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("29576923S", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("83068219S", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("83068219S", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("85234681I", "NO finalizado", fecha.toString(), veces);
			InsertarPedido("83068219S", "NO finalizado", fecha.toString(), veces);
			
			//insertar tiendas.
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
			
			//insertar relacion de compra. 
			InsertarCompra(1,p1.getCodigo());
			InsertarCompra(1,p2.getCodigo());
			InsertarCompra(1,p220.getCodigo());
			InsertarCompra(1,p150.getCodigo());
			InsertarCompra(1,p10.getCodigo());

			InsertarCompra(2,p2.getCodigo());
			InsertarCompra(2,p11.getCodigo());
			InsertarCompra(2,p230.getCodigo());
			InsertarCompra(2,p33.getCodigo());
			InsertarCompra(2,p44.getCodigo());
			InsertarCompra(2,p5.getCodigo());
			InsertarCompra(2,p66.getCodigo());
			InsertarCompra(2,p99.getCodigo());

			InsertarCompra(3,p2604.getCodigo());
			InsertarCompra(3,p5555.getCodigo());
			InsertarCompra(3,p3333.getCodigo());
			InsertarCompra(3,p2204.getCodigo());

			InsertarCompra(4,p88.getCodigo());
			InsertarCompra(4,p2001.getCodigo());

			InsertarCompra(5,p555.getCodigo());
			InsertarCompra(5,p1402.getCodigo());
			InsertarCompra(5,p888.getCodigo());
			InsertarCompra(5,p2102.getCodigo());
			InsertarCompra(5,p2402.getCodigo());
			InsertarCompra(5,p111.getCodigo());
			InsertarCompra(5,p102.getCodigo());
			InsertarCompra(5,p333.getCodigo());
			InsertarCompra(5,p222.getCodigo());

			InsertarCompra(6,p2001.getCodigo());
			InsertarCompra(6,p1101.getCodigo());
			InsertarCompra(6,p2601.getCodigo());
			InsertarCompra(6,p55.getCodigo());

			InsertarCompra(7,p1.getCodigo());
			InsertarCompra(7,p2.getCodigo());
			InsertarCompra(7,p200.getCodigo());
			InsertarCompra(7,p150.getCodigo());
			InsertarCompra(7,p120.getCodigo());
			InsertarCompra(7,p5.getCodigo());

			InsertarCompra(8,p101.getCodigo());
			InsertarCompra(8,p101.getCodigo());
			InsertarCompra(8,p101.getCodigo());

			InsertarCompra(9,p8.getCodigo());
			InsertarCompra(9, p5.getCodigo());
			InsertarCompra(9, p200.getCodigo());
			InsertarCompra(9, p2.getCodigo());
			InsertarCompra(9, p130.getCodigo());
			
			InsertarCompra(10,p2501.getCodigo());
			InsertarCompra(10,p130.getCodigo());
			InsertarCompra(10,p2402.getCodigo());
			InsertarCompra(10,p5.getCodigo());
			InsertarCompra(10,p55.getCodigo());
			
			InsertarCompra(11,p2601.getCodigo());
			InsertarCompra(11,p2601.getCodigo());
			InsertarCompra(11,p10.getCodigo());
			InsertarCompra(11,p7777.getCodigo());
			InsertarCompra(16,p2601.getCodigo());
			
			InsertarCompra(12,p1.getCodigo());
			InsertarCompra(12,p5.getCodigo());
			InsertarCompra(12,p3.getCodigo());
			InsertarCompra(12,p4.getCodigo());
			InsertarCompra(12,p2.getCodigo());
			
			InsertarCompra(13,p5.getCodigo());
			InsertarCompra(13,p8.getCodigo());
			InsertarCompra(13,p66.getCodigo());
			InsertarCompra(13,p4.getCodigo());
			
			InsertarCompra(14,p7.getCodigo());
			InsertarCompra(14,p1503.getCodigo());
			InsertarCompra(14,p1703.getCodigo());
			InsertarCompra(14,p1901.getCodigo());
			InsertarCompra(14,p2101.getCodigo());
			
			InsertarCompra(15,p7777.getCodigo());
			InsertarCompra(15,p2001.getCodigo());
			InsertarCompra(15,p2402.getCodigo());
			InsertarCompra(15,p777.getCodigo());
			InsertarCompra(15,p240.getCodigo());
			
			InsertarCompra(16,p8.getCodigo());
			InsertarCompra(16,p2503.getCodigo());
			InsertarCompra(16,p110.getCodigo());
			InsertarCompra(16,p1703.getCodigo());
			InsertarCompra(16,p222.getCodigo());
			
			InsertarCompra(17,p2101.getCodigo());
			InsertarCompra(17,p2201.getCodigo());
			InsertarCompra(17,p210.getCodigo());
			InsertarCompra(17,p112.getCodigo());
			InsertarCompra(17,p123.getCodigo());
			
			InsertarCompra(18,p444.getCodigo());
			InsertarCompra(18,p11.getCodigo());
			InsertarCompra(18,p134.getCodigo());
			InsertarCompra(18,p1402.getCodigo());
			InsertarCompra(18,p1702.getCodigo());
			
			InsertarCompra(19,p2403.getCodigo());
			InsertarCompra(19,p113.getCodigo());
			InsertarCompra(19,p220.getCodigo());
			InsertarCompra(19,p134.getCodigo());
			InsertarCompra(19,p1404.getCodigo());
			
			InsertarCompra(20,p2501.getCodigo());
			InsertarCompra(20,p114.getCodigo());
			InsertarCompra(20,p1502.getCodigo());
			InsertarCompra(20,p1603.getCodigo());
			InsertarCompra(20,p1803.getCodigo());
			
			InsertarCompra(21,p2101.getCodigo());
			InsertarCompra(21,p140.getCodigo());
			InsertarCompra(21,p190.getCodigo());
			InsertarCompra(21,p1803.getCodigo());
			InsertarCompra(21,p250.getCodigo());
				
			
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
    
    public static boolean InsertarProducto(Producto pro, String rutafoto, int codigo) {
    	String sent = "";
    	try {
    		Statement stmt = abrirlaconexion("DeustoOutlet.db");
        	sent = "insert into producto values(" + 
        			"" + pro.getCodigo() + ", " +
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
    
    public static boolean InsertarPedido(String dni, String estado, String fecha_compra, int codigo_producto) {
    	Statement stmt;
		try {
			stmt = abrirlaconexion("DeustoOutlet.db");
			String p ="insert into pedido (dni, estado, fecha_compra, codigo_producto) values ('"+dni+"', '"+estado+"', '"+fecha_compra+"', "+codigo_producto+");";
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
    
    public static boolean InsertarCompra(int c2, int c3) {
    	String sent = "";
    	try {
    		Statement stmt = abrirlaconexion("DeustoOutlet.db");
        	sent = "insert into compra (id_pedido, id_producto) values("+c2+","+c3+")";
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
   
   public static ArrayList<Pedidos> getPedidos() {
	   String sent = "";
	   ArrayList<Pedidos> lpedidos = new ArrayList<Pedidos>();
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   sent = "select * from pedido";
		   ResultSet rs = stm.executeQuery(sent);
		   while (rs.next()) {
			   @SuppressWarnings("unchecked")
			   Pedidos pe = new Pedidos((ArrayList<Producto>) rs.getArray("lista_pedidos"), rs.getInt("codigo_pedido"));
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
   
   public static int cantidadProductos(TipoProducto tipo, Colorc color, int precio, Talla talla) {
	   String sent = "";
	   int cantidad = 0;
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   sent = "select * from producto where tipo = '" + tipo + "' and color = '" + color + "' and precio <= " + precio + " and talla = '" + talla + "'";
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
   
   
   public static List<Producto> buscarProductoTipo(TipoProducto tipo) {
	   
	 //  if(cantidadProductos(tipo, color, precio, talla) > 0) {
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
//	  }
//	return null; 
   }
   
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
   
   public static Usuario buscarUsuarioNombre(String usuario) {
	   String sent = "select * from usuario where usuario = '" + usuario + "'";
	   try {
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   ResultSet rs = stm.executeQuery( sent );
		   logger.log( Level.INFO, "Lanzada consulta a base de datos: " + sent );
		   if(rs.next()) {
			   Usuario u = new Usuario(rs.getString("nombre"), rs.getString("dni"), rs.getString("fechNa"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("apellido"), rs.getString("contraseña"), rs.getString("usuario"));
			   rs.close();
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
   
   public static Exception getLastError() {
		return lastError;
	}
   
   
 	
	
}
