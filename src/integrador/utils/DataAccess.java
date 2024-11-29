package integrador.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Clase estica para conexion con la base de datos MySql (En caso de tener datos diferentes alos siguientes, por favor modificar).

public class DataAccess {
	private static final String host = "jdbc:mysql://localhost:3306/dbbanco";
	private static final String user = "root";
	private static final String pass = "root";

	public static Connection GetConnection() throws SQLException {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		return DriverManager.getConnection(host, user, pass);
	}
	
	// Método para ejecutar un procedimiento almacenado
    public static void executeStoredProcedure(String procedureName, Object... params) {
        String call = buildProcedureCall(procedureName, params.length);

        try (Connection conn = GetConnection();
             CallableStatement stmt = conn.prepareCall(call)) {

            // Asigna los parámetros al CallableStatement si hay alguno
            setParameters(stmt, params);

            // Ejecuta el procedimiento almacenado
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Construye la llamada al procedimiento según el número de parámetros
    private static String buildProcedureCall(String procedureName, int paramCount) {
        StringBuilder call = new StringBuilder("{CALL ");
        call.append(procedureName).append("(");

        for (int i = 0; i < paramCount; i++) {
            if (i > 0) call.append(", ");
            call.append("?");
        }

        call.append(")}");
        return call.toString();
    }

    // Asigna los parámetros al CallableStatement
    private static void setParameters(CallableStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            Object param = params[i];

            if (param instanceof java.sql.Date) {
                stmt.setDate(i + 1, (java.sql.Date) param);
            } else if (param instanceof java.util.Date) {
                // Si por error recibes java.util.Date, conviértelo
                stmt.setDate(i + 1, new java.sql.Date(((java.util.Date) param).getTime()));
            } else {
                stmt.setObject(i + 1, param);
            }
        }
    }
    
    public static ResultSet executeQuery(String query) throws SQLException {
    	Connection conn = GetConnection();
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
        	
            e.printStackTrace();
            return null;
        }
       /* finally {
			conn.close();
		}*/ // DEBE CERRARSE EN CADA RECURSO
    }
}
