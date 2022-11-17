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
			
			String com = "create table IF NOT EXISTS usuario(nombre String, dni String, fechNa bigint, telefono int(9), direccion String, apellido String, contraseña String, usuario String)";
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
			String com3 = "create table IF NOT EXISTS producto(codigo_producto INTEGER PRIMARY KEY AUTOINCREMENT, nombre String, precio int, color String, talla String, tipo String, id_pedido int )";
			int veces4=stmt.executeUpdate(com3);
			if (veces4==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			String com5 = "create table IF NOT EXISTS pertenece(codigo_pertenece INTEGER PRIMARY KEY AUTOINCREMENT, int id_tienda, int id_pedido  )";
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
			// insertar producto. 
			String inpr = "insert into producto values(1, 'Chaqueta de pelo', 25, '" + Colorc.NEGRO + "', '" + Talla.M + "', '" + TipoProducto.CHAQUETAS + "' );";
			stmt.executeUpdate(inpr);
			
			
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log( Level.SEVERE, "No se pudo crear la base de datos", e );
		}
		}
    
    
   public static ArrayList<Producto> getProductos(){
	   String sent = "";
	   try {
		   ArrayList<Producto> lproducto = new ArrayList<Producto>();
		   sent = "select * from producto";
		   Statement stm = abrirlaconexion("DeustoOutlet.db");
		   ResultSet rs = stm.executeQuery(sent);
		   while (rs.next()) {
			   Producto p = new Producto(rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("precio"), rs.getString("color"), rs.getString("talla"), rs.getString("tipo"));
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
