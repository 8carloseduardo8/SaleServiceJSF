package controller;

import javax.annotation.PostConstruct;

import com.util.Mensagem;

import entity.UsuarioEntity;
import model.UsuarioModel;

public class Login {

	private UsuarioEntity usuario;
	private boolean logado;

	@PostConstruct
	public void init() {

		new UsuarioModel().createTable();

		if (usuario == null)
			usuario = new UsuarioEntity();
	}

	public String logar() {
		// VERIFICA SE O USUÁIO E SENHA EXISTEM
		try {
			UsuarioEntity user = new UsuarioModel().getUsuario(usuario.login);
			if (user == null) {
				Mensagem.Erro("USUÁRIO NÃO ENCONTRADO!", "");
				return "login";
			} else if (usuario.senha.equals(user.senha) == false) {
				usuario = new UsuarioEntity();
				Mensagem.Erro("SENHA INVÁLIDA!", "");
				return "login";
			}

			// Util.SecurityController.usuarioSessao = this;
			logado = true;
			usuario = user;

		} catch (Exception e) {
			Mensagem.Erro("ERRO: ", e.getMessage());
			e.printStackTrace();
			return "login";
		}

		Mensagem.Info("BEM VINDO!", "USUÁRIO: " + usuario.nome);

		return "home";
	}

	public String deslogar() {
		return "";
	}

	public String registrar() {
		return "registrar";
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

}
