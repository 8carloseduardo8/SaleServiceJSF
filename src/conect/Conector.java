package conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conector {

	private static Conexao con;

	public static Conexao getConexao() {

		try {
			if (con != null && con.connection.isClosed()) {
				con = null;
			}
		} catch (SQLException e1) {
			con = null;
		}

		if (con != null)
			return con;

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			Properties props = new Properties();
			props.setProperty("user", "system");
			props.setProperty("password", "oracle");

			String strConexao = "jdbc:oracle:thin:@localhost:1521:XE";
			Connection connection = DriverManager.getConnection(strConexao, props);
			con = new Conexao(connection, Conexao.CONEXAO);

			return con;
		} catch (Exception e) {
			System.err.println("Exception AO CRIAR CONEXAO: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}