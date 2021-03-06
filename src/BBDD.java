
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jano
 */
public class BBDD {

    /**
     * Atributo al que invocaremos desde el proyecto una vez la librería
     * esté creada que será la conexión realizada a la BBDD
     */
    public static Connection conexion;
     /**
     * Atributo al que que invocaremos desde el proyecto una vez la librería
     * esté creada para realizar las consultas
     */
    public static Statement st;

    /**
     * Este método gener la conexión a la base de datos a través de tres
     * parámetros Si se realiza correctamente saldrá un mensaje de éxito, de lo
     * contrario uno de fracaso. Con este método también inicializamos el
     * atributo 'STATEMENT' que se empleará en las consultas.
     *
     * @param dbName Nombre de la base de datos a la que queremos conectarnos
     * @param user Usuario que se empleará en la conexión
     * @param pass Contraseña del usuario
     * @throws Exception
     */
    public static void conectarse(String dbName, String user, String pass) throws Exception {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, user, pass);
            JOptionPane.showMessageDialog(null, "Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "NO e ha iniciado la conexión");
        }
        st = (Statement) conexion.createStatement();

    }

    /**
     * Este método cierra la conexión a la base de datos
     */
    public static void cerrarConexion() {
        try {
            conexion.close();
            JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    *Inserta en la BBDD unos valores que habrán de definirse más adelante.
    Se le pasa como parámetro la tabla con la que queremos trabjar.
    */
    public static void insertDatos(String tabla) throws SQLException{
        PreparedStatement pst;
    pst = conexion.prepareStatement("INSERT INTO "+tabla+" VALUES (?,?,?,?,?)");
    pst.executeUpdate();
    
    }
    /*
    *Actualiza los datos de una tabla elegida, un campo elegido con un valor deseado, poniendo una condición al campo.
    */
     public static void updateDatos(String tabla, String campo, String valor, String condicion) throws SQLException{
        PreparedStatement pst;
    pst = conexion.prepareStatement("update "+tabla+" set "+campo+"=\""+valor+"\" where "+campo+"=\""+condicion+"\";");
    pst.executeUpdate();
    
    }
     /*
     *Borramos de una tabla los datops que cumplan la condición que se pasa como parámetro
     */
       public static void deleteDatos(String tabla, String campo, String condicion) throws SQLException{
        PreparedStatement pst;
    pst = conexion.prepareStatement("delete from "+tabla+" where "+campo+"=\""+condicion+"\";");
    pst.executeUpdate();
    
    }
     
   
}
