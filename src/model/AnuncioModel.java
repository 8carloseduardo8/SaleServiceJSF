package model;

import java.util.ArrayList;

import conect.Conector;
import conect.Conexao;
import conect.Oracle;
import conect.Resultado;
import entity.AnuncioEntity;

public class AnuncioModel {
	private Conexao con;

	public AnuncioModel() {
		con = Conector.getConexao();
		createTable();
	}

	public void createTable() {
		con.executarNoEx("CREATE TABLE CADASTROANUNCIO (   id             INTEGER PRIMARY KEY)");
		con.executarNoEx("ALTER TABLE  CADASTROANUNCIO ADD titulo         VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  CADASTROANUNCIO ADD categoria      VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  CADASTROANUNCIO ADD descricao      VARCHAR(1000)");
		con.executarNoEx("ALTER TABLE  CADASTROANUNCIO ADD preco       	  DECIMAL(12,2)");
	}

	public ArrayList<AnuncioEntity> get() throws Exception {
		String sql = "SELECT * FROM CADASTROANUNCIO ORDER BY id";

		Resultado result = con.consultar(sql);

		ArrayList<AnuncioEntity> lista = new ArrayList<AnuncioEntity>();
		while (result.next())
			lista.add(parse(result));

		result.close();

		return lista;
	}

	public AnuncioEntity get(int id) throws Exception {
		String sql = "SELECT * FROM CADASTROANUNCIO WHERE id = " + Oracle.strInsert(id);

		Resultado res = con.consultar(sql);

		AnuncioEntity Usuario = null;
		if (res.next())
			Usuario = parse(res);
		res.close();
		return Usuario;
	}

	public ArrayList<AnuncioEntity> get(String filtro) throws Exception {

		filtro = "%" + filtro.toUpperCase() + "%";

		String sql = "SELECT * FROM CADASTROANUNCIO ";
		sql += " WHERE UPPER(titulo) like " + Oracle.strInsert(filtro);
		sql += " OR   UPPER(descricao) like " + Oracle.strInsert(filtro);

		Resultado res = con.consultar(sql);

		ArrayList<AnuncioEntity> lista = new ArrayList<>();
		while (res.next())
			lista.add(parse(res));
		res.close();
		return lista;
	}

	public void registrar(AnuncioEntity item) throws Exception {
		String sql;

		if (item.id == 0) {
			int id = 0;
			sql = "SELECT MAX(ID) FROM CADASTROANUNCIO";
			Resultado res = con.consultar(sql);
			if (res.next())
				id = res.getInt(1);
			res.close();
			item.id = id + 1;
			sql = "INSERT INTO CADASTROANUNCIO (id) values (" + Oracle.strInsert(item.id) + ")";
			con.executar(sql);
		}

		sql = "UPDATE CADASTROANUNCIO SET ";
		sql += "  titulo      = " + Oracle.strInsert(item.titulo);
		sql += " ,categoria     = " + Oracle.strInsert(item.categoria);
		sql += " ,descricao     = " + Oracle.strInsert(item.descricao);
		sql += " ,preco        = " + Oracle.strInsert(item.preco);
		sql += " where id =  " + Oracle.strInsert(item.id);
		con.executar(sql);
		return;
	}

	public static AnuncioEntity parse(Resultado res) throws Exception {
		AnuncioEntity item = new AnuncioEntity();
		item.id = res.getInt("id");
		item.titulo = res.getString("titulo");
		item.categoria = res.getString("categoria");
		item.descricao = res.getString("descricao");
		item.preco = res.getDouble("preco");

		return item;
	}
}
