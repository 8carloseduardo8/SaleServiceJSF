package controller;

import javax.annotation.PostConstruct;

import com.util.Mensagem;

import entity.AnuncioEntity;

public class CadastroAnuncioController {

	private AnuncioEntity anuncio;

	@PostConstruct
	public void init() {
		if (anuncio == null)
			anuncio = new AnuncioEntity();
	}

	public String novo() {
		anuncio = new AnuncioEntity();
		return "cadastroAnuncio";
	}

	public String registrar() {
		try {
			anuncio.registrarAnuncio();
		} catch (Exception e) {
			Mensagem.Erro(e.getMessage(), "Erro!");
			return "";
		}

		Mensagem.Info("ANUNCIO REGISTRADO COM SUCESSO!", "");
		anuncio = new AnuncioEntity();

		return "";
	}

	public AnuncioEntity getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(AnuncioEntity anuncio) {
		this.anuncio = anuncio;
	}

}
