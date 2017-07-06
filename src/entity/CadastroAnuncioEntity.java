package entity;

import model.CadastroAnuncioModel;

public class CadastroAnuncioEntity {
	
	public int id;
	public String titulo;
	public String categoria;
	public String descricao;
	public String preco;
	
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
		
		if (preco == null || preco.equals("")) {
			throw new Exception("Insira o preco!");
		}
		
		// SALVAR NO BANCO
		new CadastroAnuncioModel().registrar(this);
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
	
	public String getPreco() {
		return preco;
	}
	
	public void setPreco(String preco) {
		this.preco = preco;
	}
}
