package conect;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Oracle {

	public static Connection conectar() {

		// ABRE O ARQUIVO DE CONFIGURA��O
		// PARA IDENTIFICAR O IP, USU�RIO E SENHA

		String ip = null;
		String usuario = null;
		String senha = null;
		String sid = null;
		String porta = null;

		try {
			Scanner in = new Scanner(new File("C:\\config_visitacao.ini"));
			String linha;
			while (in.hasNextLine()) {
				linha = in.nextLine();
				String s[] = linha.split("=");
				if (s[0].equals("ip"))
					ip = s[1];
				else if (s[0].equals("usuario"))
					usuario = s[1];
				else if (s[0].equals("senha"))
					senha = s[1];
				else if (s[0].equals("sid"))
					sid = s[1];
				else if (s[0].equals("porta"))
					porta = s[1];
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		if (ip == null || usuario == null || senha == null || sid == null
				|| porta == null) {
			System.out
					.println("N�O FOI POSS�VEL INICIAR CONEX�O COM O BANCO DE DADOS, ALGUNS CAMPOS EST�O FALTANDO NO ARQUIVO DE CONFIGURA��O 'C:\\config_visitacao.ini'");
			return null;
		}

		try {
			// Load the Oracle JDBC driver
			// System.out.println("INICIANDO A CONEX�O COM O BANCO\n");
			Class.forName("oracle.jdbc.OracleDriver");
			// System.out.println("Oracle JDBC driver loaded ok.");

			// "jdbc:oracle:thin:@" + host + ":" + porta + ":" + banco;
			System.out.println("jdbc:oracle:thin:@" + ip + ":" + porta + ":"
					+ sid + " # usuario:" + usuario + " # senha:" + senha);

			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + ip + ":" + porta + ":" + sid,
					usuario, senha);
			// System.out.println("Connected with @10.5.100.240:1521:DADOUNDG");
			// connection.close();
			return connection;
		} catch (Exception e) {
			System.err.println("Exception AO CRIAR CONEXAO: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static String strInsert(String campo) {
		if (campo == null) {
			return "null";
		} else if (campo.toUpperCase().equals("NULL")) {
			return "null";
		} else {
			return "'" + campo + "'";
		}
	}

	public static String strInsert(int campo) {
		return String.valueOf(campo);
	}

	public static String strInsert(Date campo) {
		if (campo == null) {
			return "null";
		} else {
			return "to_date('"
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(campo)
					+ "','yyyy/MM/dd hh24:mi:ss')";
		}
	}

	public static String strInsert(Double campo, int casas) {
		if (campo == null) {
			return "null";
		} else {
			return "'" + campo.toString().replace(".", ",") + "'";
			// return new DecimalFormat("###0." + repeat("0", casas))
			// .format(campo).replace(",", ".");
		}
	}

	public static String strInsert(Double campo) {
		return strInsert(campo, 2);
	}

	public static String repeat(String s, int times) {
		if (times <= 0)
			return "";
		else
			return s + repeat(s, times - 1);
	}
}
