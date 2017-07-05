package entity;

import model.FaleConoscoModel;

public class FaleConoscoEntity {

	public int id;
	public String nome;
	public String email;
	public String assunto;
	public String mensagem;

	public void enviarEmail() throws Exception {

		if (nome == null || nome.equals("")) {
			throw new Exception("Digite o Nome!");
		}

		if (email == null || email.equals("")) {
			throw new Exception("Digite o E-mail!");
		}

		if (assunto == null || assunto.equals("")) {
			throw new Exception("Digite o Assunto!");
		}

		if (mensagem == null || mensagem.equals("")) {
			throw new Exception("Digite a Mensagem!");
		}

		// SALVAR NO BANCO
		new FaleConoscoModel().salvar(this);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
