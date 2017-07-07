package controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.util.Mensagem;

import entity.AnuncioEntity;
import model.AnuncioModel;

public class ConsultarAnuncioController {

	private ArrayList<AnuncioEntity> listaAnuncio;

	@PostConstruct
	public void init() {
		if (listaAnuncio == null)
			listaAnuncio = new ArrayList<>();
	}

	public String novo() {
		listaAnuncio = new ArrayList<>();
		return "cadastroAnuncio";
	}

	public String filtrar() {

		try {
			listaAnuncio = new AnuncioEntity().get();
		} catch (Exception e) {
			Mensagem.Erro(e.getMessage(), "");
			e.printStackTrace();
		}

		if (listaAnuncio == null)
			listaAnuncio = new ArrayList<>();

		return "";
	}

	public ArrayList<AnuncioEntity> getListaAnuncio() {
		return listaAnuncio;
	}

	public void setListaAnuncio(ArrayList<AnuncioEntity> listaAnuncio) {
		this.listaAnuncio = listaAnuncio;
	}

	public ArrayList<String> getListaCategorias() {
		return AnuncioEntity.getListaCategorias();
	}
}
