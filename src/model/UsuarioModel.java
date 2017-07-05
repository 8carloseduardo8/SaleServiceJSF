package model;

import java.util.ArrayList;
import java.util.List;

import conect.Conector;
import conect.Conexao;
import conect.Oracle;
import conect.Resultado;
import entity.UsuarioEntity;

public class UsuarioModel {
	private Conexao con;

	public UsuarioModel() {
		con = Conector.getConexao();
	}

	public void createTable() {
		con.executarNoEx("CREATE TABLE USUARIO (   id             INTEGER PRIMARY KEY )");
		con.executarNoEx("ALTER TABLE  USUARIO ADD login          VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  USUARIO ADD nome           VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  USUARIO ADD senha          VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  USUARIO ADD cidade         VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  USUARIO ADD email          VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  USUARIO ADD endereco       VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  USUARIO ADD uf             VARCHAR(100)");
		con.executarNoEx("ALTER TABLE  USUARIO ADD telefone       VARCHAR(100)");

	}

	public List<UsuarioEntity> getUsuarios() throws Exception {
		String sql = "SELECT * FROM USUARIO ORDER BY DESCRICAO";

		Resultado result = con.consultar(sql);

		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
		while (result.next())
			lista.add(parse(result));

		result.close();

		return lista;
	}

	public List<UsuarioEntity> getUsuariosCargo(String cargo) throws Exception {

		Resultado rs = con.consultar(
				"SELECT * FROM FUNCIONARIO WHERE CARGO = " + Oracle.strInsert(cargo) + " AND DATAEXCLUIDO IS NULL");

		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
		while (rs.next()) {
			lista.add(parse(rs));
		}
		rs.close();

		return lista;
	}

	public void salvar(UsuarioEntity item) throws Exception {
		String sql;

		if (item.id == 0) {
			int id = 0;
			sql = "SELECT MAX(ID) FROM USUARIO";
			Resultado res = con.consultar(sql);
			if (res.next())
				id = res.getInt(1);
			res.close();
			item.id = id + 1;
			sql = "INSERT INTO USUARIO (id) values (" + Oracle.strInsert(item.id) + ")";
			con.executar(sql);
		}

		sql = "UPDATE USUARIO SET ";
		sql += "  login     = " + Oracle.strInsert(item.login);
		sql += " ,nome      = " + Oracle.strInsert(item.nome);
		sql += " ,cidade    = " + Oracle.strInsert(item.cidade);
		sql += " ,email     = " + Oracle.strInsert(item.email);
		sql += " ,endereco  = " + Oracle.strInsert(item.endereco);
		sql += " ,telefone  = " + Oracle.strInsert(item.telefone);
		sql += " ,senha     = " + Oracle.strInsert(item.senha);
		sql += " ,uf        = " + Oracle.strInsert(item.uf);
		sql += " where id = " + Oracle.strInsert(item.id);
		con.executar(sql);
		return;
	}

	public UsuarioEntity getUsuario(int id) throws Exception {
		String sql = "SELECT * FROM USUARIO WHERE id = " + Oracle.strInsert(id);

		Resultado res = con.consultar(sql);

		UsuarioEntity Usuario = null;
		if (res.next())
			Usuario = parse(res);
		res.close();
		return Usuario;
	}

	public UsuarioEntity getUsuario(String login) throws Exception {
		String sql = "SELECT * FROM USUARIO WHERE login = " + Oracle.strInsert(login);

		Resultado res = con.consultar(sql);

		UsuarioEntity Usuario = null;
		if (res.next())
			Usuario = parse(res);
		res.close();
		return Usuario;
	}

	public static UsuarioEntity parse(Resultado res) throws Exception {
		UsuarioEntity item = new UsuarioEntity();
		item.id = res.getInt("id");
		item.nome = res.getString("nome");
		item.login = res.getString("login");
		item.senha = res.getString("senha");
		item.cidade = res.getString("cidade");
		item.email = res.getString("email");
		item.endereco = res.getString("endereco");
		item.telefone = res.getString("telefone");
		item.uf = res.getString("uf");

		return item;
	}
}