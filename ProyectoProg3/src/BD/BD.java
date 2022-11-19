package BD;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Clasesprincipales.Colorc;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
import Clasesprincipales.TipoProducto;

public class BD {
	private static Exception lastError = null;
	
	/** Inicializa una BD SQLITE y devuelve una conexiï¿½n con ella
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexiï¿½n con la base de datos indicada. Si hay algï¿½n error, se devuelve null
	 */
	public static Statement abrirlaconexion( String nombreBD) { 
		
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
			String dburl = "jdbc:sqlite:" + nombreBD;
			Connection con = DriverManager.getConnection(dburl);
				 
			Statement st = con.createStatement();
			log( Level.INFO, "Conectada base de datos " + nombreBD, null );
			return st;
		} catch (ClassNotFoundException | SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en conexión de base de datos " + nombreBD, e );
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
			log( Level.SEVERE, "Error en uso de base de datos", e );
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
			log( Level.INFO, "Cierre de base de datos", null );
		} catch (SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en cierre de base de datos", e );
			e.printStackTrace();
		}
	}
	
	
	
	//Logging
	public static Logger logger = null;
	private static void log( Level level, String msg, Throwable excepcion ) {
		if (logger==null) {  // Logger por defecto local:
			logger = Logger.getLogger( BD.class.getName() );  // Nombre del logger - el de la clase
			logger.setLevel( Level.ALL );  // Loguea todos los niveles
			try {
				// logger.addHandler( new FileHandler( "bd-" + System.currentTimeMillis() + ".log.xml" ) );  // Y saca el log a fichero xml
				logger.addHandler( new FileHandler( "bd.log.xml", true ) );  // Y saca el log a fichero xml
			} catch (Exception e) {
				logger.log( Level.SEVERE, "No se pudo crear fichero de log", e );
			}
		}
		if (excepcion==null)
			logger.log( level, msg );
		else
			logger.log( level, msg, excepcion );
		
	}
    public static void initDatos() { //mirar porque igual tenemos que cambiar la relacion de producto y de pedido . 
		
		try {
			
			String com = "create table IF NOT EXISTS usuario(nombre String, dni String, fechNa bigint, telefono integer(9), direccion String, apellido String, contraseña String, usuario String)";
			Statement stmt = abrirlaconexion("DeustoOutlet.db");
			int veces1=stmt.executeUpdate(com);
			if (veces1==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com4 = "create table IF NOT EXISTS pedido(codigo_pedido INTEGER PRIMARY KEY AUTOINCREMENT, dni String)"; //clave externa del dni del usuario. 
			int veces2=stmt.executeUpdate(com4);
			if (veces2==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com2 = "create table IF NOT EXISTS tienda(codigo_tienda INTEGER PRIMARY KEY AUTOINCREMENT, nombre String, franquicia String)";
			int veces3=stmt.executeUpdate(com2);
			if (veces3==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com3 = "create table IF NOT EXISTS producto(codigo_producto integer, nombre String, precio integer, color String, talla String, tipo String, id_pedido integer )";
			int veces4=stmt.executeUpdate(com3);
			if (veces4==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com5 = "create table IF NOT EXISTS pertenece(codigo_pertenece INTEGER PRIMARY KEY AUTOINCREMENT, id_tienda integer, id_pedido integer )";
			int veces=stmt.executeUpdate(com5);
			if (veces==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			//poner un mensaje para un caso en el que la tabla ya estaria creada. 
			
			// insertar usuarios. 
			long fecha = 0;
			String inst = "insert into usuario values('Maria', '45344345L',"+fecha+ ",767665543, 'Calle Rodriguez Arias','Rodriguez','trabajoprogram','mariarodriguez5' );";
			stmt.executeUpdate(inst);
			long fecha2 = 0;
			String inst2 = "insert into usuario values('Aritz', '74544345L',"+fecha2+ ",644665543, 'Calle De Mar','Yero','trabajoprogram','yero55' );";
			stmt.executeUpdate(inst2);
			// insertar producto/ mismo producto en diferentes tallas
			//producto1. 
			String inpr = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "M" + "', '" + "chaqueta'," + null +" );";
			String inpr11 = "insert into producto values(6, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "XS" + "', '" + "chaqueta'," + null + " );";
			String inpr1111 = "insert into producto values(7, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "S" + "', '" + "chaqueta'," + null + " );";
			String inpr111 = "insert into producto values(8, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "XL" + "', '" + "chaqueta'," + null + " );";
			String inpr11111 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "L" + "', '" + "chaqueta'," + null + " );";
			String inpr111111 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "S" + "', '" + "chaqueta'," + null + " );";
			//producto2
			String inpr2 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "S" + "', '" + "pantalones'," + null + " );";
			String inpr22 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "XS" + "', '" + "pantalones'," + null + " );";
			String inpr222 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "XL" + "', '" + "pantalones'," + null + " );";
			String inpr2222 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "M" + "', '" + "pantalones'," + null + " );";
			String inpr22222 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "L" + "', '" + "pantalones'," + null + " );";
			String inpr222222 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "XS" + "', '" + "pantalones'," + null + " );";
			//producto3.
			String inpr3 = "insert into producto values(3, 'camisa con volantes', 20, '" + "Blanco" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			String inpr33 = "insert into producto values(3, 'camisa con volantes', 20, '" + "Blanco" + "', '" + "S" + "', '" + "chaqueta'," + null + " );";
			String inpr333 = "insert into producto values(3, 'camisa con volantes', 20, '" + "Blanco" + "', '" + "XL" + "', '" + "chaqueta'," + null + " );";
			String inpr3333 = "insert into producto values(3, 'camisa con volantes', 20, '" + "Blanco" + "', '" + "L" + "', '" + "chaqueta'," + null + " );";
			String inpr33333 = "insert into producto values(3, 'camisa con volantes', 20, '" + "Blanco" + "', '" + "XS" + "', '" + "chaqueta'," + null + " );";
			String inpr333333 = "insert into producto values(3, 'camisa con volantes', 20, '" + "Blanco" + "', '" + "S" + "', '" + "chaqueta'," + null + " );";
			//producto4.
			String inpr4 = "insert into producto values(4, 'pantalones parachute', 30, '" + "Negro" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			String inpr44 = "insert into producto values(4, 'pantalones parachute', 30, '" + "Negro" + "', '" + "S" + "', '" + "chaqueta'," + null + " );";
			String inpr444 = "insert into producto values(4, 'pantalones parachute', 30, '" + "Negro" + "', '" + "XS" + "', '" + "chaqueta'," + null + " );";
			String inpr4444 = "insert into producto values(4, 'pantalones parachute', 30, '" + "Negro" + "', '" + "L" + "', '" + "chaqueta'," + null + " );";
			String inpr44444 = "insert into producto values(4, 'pantalones parachute', 30, '" + "Negro" + "', '" + "XL" + "', '" + "chaqueta'," + null + " );";
			String inpr444444 = "insert into producto values(4, 'pantalones parachute', 30, '" + "Negro" + "', '" + "XS" + "', '" + "chaqueta'," + null + " );";
			//producto5.
			String inpr5 = "insert into producto values(5, 'vestido de verano', 27, '" + "Blanco" + "', '" + "M" + "', '" + "chaqueta" + null + " );";
			String inpr55 = "insert into producto values(5, 'vestido de verano', 27, '" + "Blanco" + "', '" + "S" + "', '" + "chaqueta" + null + " );";
			String inpr555 = "insert into producto values(5, 'vestido de verano', 27, '" + "Blanco" + "', '" + "XS" + "', '" + "chaqueta" + null + " );";
			String inpr5555 = "insert into producto values(5, 'vestido de verano', 27, '" + "Blanco" + "', '" + "XL" + "', '" + "chaqueta" + null + " );";
			String inpr55555 = "insert into producto values(5, 'vestido de verano', 27, '" + "Blanco" + "', '" + "L" + "', '" + "chaqueta" + null + " );";
			String inpr555555 = "insert into producto values(5, 'vestido de verano', 27, '" + "Blanco" + "', '" + "M" + "', '" + "chaqueta" + null + " );";
			/**
			 * desde este punto hay que seguir creando productos.
			 */
			//producto6
			String inpr6 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			String inpr66 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "S" + "', '" + "chaqueta'," + null + " );";
			String inpr666 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "XS" + "', '" + "chaqueta'," + null + " );";
			String inpr6666 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "XL" + "', '" + "chaqueta'," + null + " );";
			String inpr66666 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "L" + "', '" + "chaqueta'," + null + " );";
			String inpr666666 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "S" + "', '" + "chaqueta'," + null + " );";

			//producto6
			String inpr7 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "S" + "', '" + "pantalones'," + null + " );";
			String inpr77 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "M" + "', '" + "pantalones'," + null + " );";
			String inpr777 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "XS" + "', '" + "pantalones'," + null + " );";
			String inpr7777 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "L" + "', '" + "pantalones'," + null + " );";
			String inpr77777 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "XL" + "', '" + "pantalones'," + null + " );";
			String inpr777777 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "L" + "', '" + "pantalones'," + null + " );";

			//producto6
			String inpr8 = "insert into producto values(3, 'camisa con volantes', 20, '" + "Blanco" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr8);
			//producto6
			String inpr9 = "insert into producto values(4, 'pantalones parachute', 30, '" + "Negro" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr9);
			//producto6
			String inpr10 = "insert into producto values(5, 'vestido de verano', 27, '" + "Blanco" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr10);
			//producto6
			String inpr00 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr00);
			//producto6
			String inpr12 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "S" + "', '" + "pantalones'," + null + " );";
			stmt.executeUpdate(inpr12);
			//producto6
			String inpr13 = "insert into producto values(3, 'camisa con volantes', 20, '" + "Blanco" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr13);
			//producto6
			String inpr14 = "insert into producto values(4, 'pantalones parachute', 30, '" + "Negro" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr14);
			//producto6
			String inpr15 = "insert into producto values(5, 'vestido de verano', 27, '" + "Blanco" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr15);
			//producto6
			String inpr16 = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + "Negro" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr16);
			//producto6
			String inpr17 = "insert into producto values(2, 'Pantalones vaqueros', 15, '" + "Azul" + "', '" + "S" + "', '" + "pantalones'," + null + " );";
			stmt.executeUpdate(inpr17);
			//producto6
			String inpr18 = "insert into producto values(3, 'camisa con volantes', 20, '" + "Blanco" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr18);
			//producto6
			String inpr19 = "insert into producto values(4, 'pantalones parachute', 30, '" + "Negro" + "', '" + "M" + "', '" + "chaqueta'," + null + " );";
			stmt.executeUpdate(inpr19);
			//producto6
			String inpr20 = "insert into producto values(5, 'vestido de verano', 27, '" + "Blanco" + "', '" + "M" + "', '" + "chaqueta'," +  null +" );";
			stmt.executeUpdate(inpr20);
			
			//Insertar pedidos
			String p1 = "insert into pedido values (1, 45344345L);";
			stmt.executeUpdate(p1);
			String p2 = "insert into pedido values (2, 74544345L);";
			stmt.executeUpdate(p2);
			String p3 = "insert into pedido values (3, 45344345L);";
			stmt.executeUpdate(p3);
			String p4 = "insert into pedido values (4, 74544345L);";
			stmt.executeUpdate(p4);
			String p5 = "insert into pedido values (5, 45344345L);";
			stmt.executeUpdate(p5);
			String p6 = "insert into pedido values (6, 45344345L);";
			stmt.executeUpdate(p6);
			String p7 = "insert into pedido values (7, 74544345L);";
			stmt.executeUpdate(p7);
			
			for (int i=0;i<=10;i++) {
				stmt.executeUpdate(inpr4);
				stmt.executeUpdate(inpr44);
				stmt.executeUpdate(inpr444);
				stmt.executeUpdate(inpr4444);
				stmt.executeUpdate(inpr44444);
				stmt.executeUpdate(inpr444444);
				stmt.executeUpdate(inpr33333);
				stmt.executeUpdate(inpr3);
				stmt.executeUpdate(inpr33);
				stmt.executeUpdate(inpr333);
				stmt.executeUpdate(inpr3333);
				stmt.executeUpdate(inpr333333);
				stmt.executeUpdate(inpr2);
				stmt.executeUpdate(inpr22);
				stmt.executeUpdate(inpr222);
				stmt.executeUpdate(inpr2222);
				stmt.executeUpdate(inpr22222);
				stmt.executeUpdate(inpr222222);
				stmt.executeUpdate(inpr11);
				stmt.executeUpdate(inpr);
				stmt.executeUpdate(inpr111);
				stmt.executeUpdate(inpr111111);
				stmt.executeUpdate(inpr11111);
				stmt.executeUpdate(inpr1111);
				stmt.executeUpdate(inpr5);
				stmt.executeUpdate(inpr55);
				stmt.executeUpdate(inpr555);
				stmt.executeUpdate(inpr55555);
				stmt.executeUpdate(inpr5555);
				stmt.executeUpdate(inpr555555);
				stmt.executeUpdate(inpr6);
				stmt.executeUpdate(inpr66);
				stmt.executeUpdate(inpr666);
				stmt.executeUpdate(inpr6666);
				stmt.executeUpdate(inpr66666);
				stmt.executeUpdate(inpr666666);
				stmt.executeUpdate(inpr7);
				stmt.executeUpdate(inpr77);
				stmt.executeUpdate(inpr777);
				stmt.executeUpdate(inpr7777);
				stmt.executeUpdate(inpr77777);
				stmt.executeUpdate(inpr777777);
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log( Level.SEVERE, "No se pudo crear la base de datos", e );
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
