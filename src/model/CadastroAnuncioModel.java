package model;

import java.util.ArrayList;
import java.util.List;

import conect.Conector;
import conect.Conexao;
import conect.Oracle;
import conect.Resultado;
import entity.CadastroAnuncioEntity;

public class CadastroAnuncioModel {
	private Conexao con;
	
	public CadastroAnuncioModel() {
		con = Conector.getConexao();
	}
	
	public void createTable() {
		con.executarNoEx("CREATE TABLE CADASTROANUNCIO (   id             INTEGER PRIMARY KEY)");
		con.executarNoEx("ALTER TABLE  CADASTROANUNCIO (   titulo         VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  CADASTROANUNCIO ADD categoria      VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  CADASTROANUNCIO ADD descricao      VARCHAR(1000)");
		con.executarNoEx("ALTER TABLE  CADASTROANUNCIO ADD preco       	  VARCHAR(100)");
	}
	
	public List<CadastroAnuncioEntity> get() throws Exception {
		String sql = "SELECT * FROM CADASTROANUNCIO ORDER BY id";

		Resultado result = con.consultar(sql);

		List<CadastroAnuncioEntity> lista = new ArrayList<CadastroAnuncioEntity>();
		while (result.next())
			lista.add(parse(result));

		result.close();

		return lista;
	}
	
	public CadastroAnuncioEntity get(int id) throws Exception {
		String sql = "SELECT * FROM CADASTROANUNCIO WHERE id = " + Oracle.strInsert(id);

		Resultado res = con.consultar(sql);

		CadastroAnuncioEntity Usuario = null;
		if (res.next())
			Usuario = parse(res);
		res.close();
		return Usuario;
	}
	
	public void registrar(CadastroAnuncioEntity item) throws Exception {
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
	
	public static CadastroAnuncioEntity parse(Resultado res) throws Exception {
		CadastroAnuncioEntity item = new CadastroAnuncioEntity();
		item.id = res.getInt("id");
		item.titulo = res.getString("titulo");
		item.categoria = res.getString("categoria");
		item.descricao = res.getString("descricao");
		item.preco = res.getString("preco");

		return item;
	}
}
