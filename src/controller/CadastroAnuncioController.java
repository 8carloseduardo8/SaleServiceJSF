package controller;

import javax.annotation.PostConstruct;

import com.util.Mensagem;

import entity.CadastroAnuncioEntity;

public class CadastroAnuncioController {

	
	private CadastroAnuncioEntity cadastroAnuncio;

	@PostConstruct
	public void init() {
		if (cadastroAnuncio == null)
			cadastroAnuncio = new CadastroAnuncioEntity();
	}
	
	public String registrar() {
		try {
			cadastroAnuncio.registrarAnuncio();
		} catch (Exception e) {
			Mensagem.Erro(e.getMessage(), "");
			return "";
		}
		
		Mensagem.Info("ANUNCIO REGISTRADO COM SUCESSO!", "");
		cadastroAnuncio = new CadastroAnuncioEntity();
		
		return "";
	}
}
