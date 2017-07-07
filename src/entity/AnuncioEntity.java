package entity;

import java.util.ArrayList;

import model.AnuncioModel;

public class AnuncioEntity {

	public static final String CATEGORIA_DOMESTICA = "Doméstica";
	public static final String CATEGORIA_MECANICO = "Mecânico";
	public static final String CATEGORIA_LAZER = "Lazer";

	public int id;
	public String titulo;
	public String categoria;
	public String descricao;
	public double preco;
	public byte[] imagem;
	
	public void registrarAnuncio() throws Exception {

		if (titulo == null || titulo.equals("")) {
			throw new Exception("Insira um titulo!");
		}

		if (categoria == null || categoria.equals("")) {
			throw new Exception("Insira um E-mail!");
		}

		if (descricao == null || descricao.equals("")) {
			throw new Exception("Insira uma descricao!");
		}

		if (preco <= 0) {
			throw new Exception("Insira o preco!");
		}

		// SALVAR NO BANCO
		new AnuncioModel().registrar(this);
	}

	public ArrayList<AnuncioEntity> get(String filtro) throws Exception {
		return new AnuncioModel().get(filtro);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public static ArrayList<String> getListaCategorias() {
		ArrayList<String> lista = new ArrayList<>();
		lista.add("Doméstica");
		lista.add("Mecânico");
		lista.add("Festa");
		lista.add("Informática");
		lista.add("Auditoria");
		lista.add("Celular");
		return lista;
	}

}
