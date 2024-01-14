package Models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionDB {

    public static final String HOSTDB = "localhost";
    public static final String NOMBREDB = "proyecto_prog_3ev";
    public static final String USUARIODB = "root";
    public static final String CLAVEDB = "Luis_vaq_10";
    private static final String OPCIONESHORA = "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    // las opciones de hora tambien las puedes poner en mysql
    // SET GLOBAL time_zone = '+1:00';
    public static final String PUERTOMYSQL = "3306";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB +":"+PUERTOMYSQL + "/" + NOMBREDB + OPCIONESHORA;

    //----------------------------------------------------------....
    public static Connection conectarConBaseDeDatos() {
        try {
            Connection conexion = DriverManager.getConnection(URLMYSQL, USUARIODB, CLAVEDB);
            return conexion;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no se pudo establecer la conexion con la base de datos");
            return null;
        }
    }
    //-----------------------------------------------------------
}
