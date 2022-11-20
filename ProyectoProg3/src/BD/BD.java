package BD;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Clasesprincipales.Colorc;
import Clasesprincipales.Pedidos;
import Clasesprincipales.Producto;
import Clasesprincipales.Talla;
import Clasesprincipales.TipoProducto;
import Clasesprincipales.Usuario;

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
			String com2 = "create table tienda(codigo_tienda INTEGER PRIMARY KEY AUTOINCREMENT, nombre String, franquicia String)";
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
			String com5 = "create table IF NOT EXISTS pertenece(codigo_pertenece INTEGER PRIMARY KEY AUTOINCREMENT, id_tienda integer, id_pedido integer )";
			stmt.executeUpdate("drop table pertenece");
			int veces=stmt.executeUpdate(com5);
			if (veces==1) {
				logger.log( Level.FINEST, "Tabla creada" );
			}
			
			
			// insertar usuarios. 
			InsertarUsuario(new Usuario("Maria", "45344345L", "13/04/1997", "767665543", "Callse Rodrigeuz Arias", "Rodriguez", "trabajoprogram", "mariarodriguez5"));
			InsertarUsuario(new Usuario("Aritz", "74544345L", "10/10/2003", "644665543", "Calle De Mar", "Yero", "trabajoprogram", "yero55"));
		
			
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
//			
//			
//			//Insertar pedidos
//			String pe1 = "insert into pedido values (1, 45344345L);";
//			stmt.executeUpdate(pe1);
//			String pe2 = "insert into pedido values (2, 74544345L);";
//			stmt.executeUpdate(pe2);
//			String pe3 = "insert into pedido values (3, 45344345L);";
//			stmt.executeUpdate(pe3);
//			String pe4 = "insert into pedido values (4, 74544345L);";
//			stmt.executeUpdate(pe4);
//			String pe5 = "insert into pedido values (5, 45344345L);";
//			stmt.executeUpdate(pe5);
//			String pe6 = "insert into pedido values (6, 45344345L);";
//			stmt.executeUpdate(pe6);
//			String pe7 = "insert into pedido values (7, 74544345L);";
//			stmt.executeUpdate(pe7);

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
    
    
   public static List<Producto> getProductos(){
	   String sent = "";
	   List<Producto> lproducto = new ArrayList<>();
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
