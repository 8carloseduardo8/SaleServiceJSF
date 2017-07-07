package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;

import com.util.Mensagem;
import com.util.Read;

import entity.AnuncioEntity;

public class CadastroAnuncioController {

	private Part arquivo;
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
			InputStream input = arquivo.getInputStream();
			anuncio.imagem = Read.readBytes(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public ArrayList<String> getListaCategorias() {
		return AnuncioEntity.getListaCategorias();
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}

}
