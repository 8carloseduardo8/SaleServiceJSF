package model;

import java.util.ArrayList;
import java.util.List;

import conect.Conector;
import conect.Conexao;
import conect.Oracle;
import conect.Resultado;
import entity.FaleConoscoEntity;

public class FaleConoscoModel {
	private Conexao con;

	public FaleConoscoModel() {
		con = Conector.getConexao();
	}

	public void createTable() {
		con.executarNoEx("CREATE TABLE FALECONOSCO (   id             INTEGER PRIMARY KEY)");
		con.executarNoEx("ALTER TABLE  FALECONOSCO (   nome           VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  FALECONOSCO ADD email          VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  FALECONOSCO ADD assunto        VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  FALECONOSCO ADD mensagem       VARCHAR(1000)");
	}

	public List<FaleConoscoEntity> get() throws Exception {
		String sql = "SELECT * FROM FALECONOSCO ORDER BY id";

		Resultado result = con.consultar(sql);

		List<FaleConoscoEntity> lista = new ArrayList<FaleConoscoEntity>();
		while (result.next())
			lista.add(parse(result));

		result.close();

		return lista;
	}

	public FaleConoscoEntity get(int id) throws Exception {
		String sql = "SELECT * FROM FALECONOSCO WHERE id = " + Oracle.strInsert(id);

		Resultado res = con.consultar(sql);

		FaleConoscoEntity Usuario = null;
		if (res.next())
			Usuario = parse(res);
		res.close();
		return Usuario;
	}

	public void salvar(FaleConoscoEntity item) throws Exception {
		String sql;

		if (item.id == 0) {
			int id = 0;
			sql = "SELECT MAX(ID) FROM FALECONOSCO";
			Resultado res = con.consultar(sql);
			if (res.next())
				id = res.getInt(1);
			res.close();
			item.id = id + 1;
			sql = "INSERT INTO FALECONOSCO (id) values (" + Oracle.strInsert(item.id) + ")";
			con.executar(sql);
		}

		sql = "UPDATE FALECONOSCO SET ";
		sql += "  nome      = " + Oracle.strInsert(item.nome);
		sql += " ,email     = " + Oracle.strInsert(item.email);
		sql += " ,assunto     = " + Oracle.strInsert(item.assunto);
		sql += " ,mensagem        = " + Oracle.strInsert(item.mensagem);
		sql += " where id =  " + Oracle.strInsert(item.id);
		con.executar(sql);
		return;
	}

	public static FaleConoscoEntity parse(Resultado res) throws Exception {
		FaleConoscoEntity item = new FaleConoscoEntity();
		item.id = res.getInt("id");
		item.nome = res.getString("nome");
		item.email = res.getString("email");
		item.assunto = res.getString("assunto");
		item.mensagem = res.getString("mensagem");

		return item;
	}
}