package Modelo;
import java.sql. *;


/**
 *
 * @author Chris
 */
public class conexion {
    private  Connection con=null;
    //datos de la conexion
    private static final String driver="com.mysql.cj.jdbc.Driver";
    //Localhost
    private static final String usuario="root";
    private static final String pass="";
    private static final String url="jdbc:mysql://localhost:3306/tienda";
    
    
    //funcion para conectarse bd
    public Connection conecta(){
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url,usuario,pass);
            //con = (Connection) DriverManager.getConnection(txt); //solo para la nube zure
            /*if (con != null) {
                System.out.println("Conexion establecida");
            }*/

        } catch (ClassNotFoundException | SQLException error) {
            System.out.println("Error en la conexion: " + error);
        }
        return con;

    }
    
    public void desconecta(){
        try {
            this.con.close();
            //System.out.println("Conexion cerrada");
        } catch (SQLException e) {
            System.out.println("Error en la conexion: " + e);
        }

    }
  
}
