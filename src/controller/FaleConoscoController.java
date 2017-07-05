package controller;

import javax.annotation.PostConstruct;

import com.util.Mensagem;

import entity.FaleConoscoEntity;

public class FaleConoscoController {

	private FaleConoscoEntity faleConosco;

	@PostConstruct
	public void init() {
		if (faleConosco == null)
			faleConosco = new FaleConoscoEntity();
	}

	public String enviar() {
		try {
			faleConosco.enviarEmail();
		} catch (Exception e) {
			Mensagem.Erro(e.getMessage(), "");
			return "";
		}

		Mensagem.Info("MENSAGEM ENVIADA COM SUCESSO!", "");
		faleConosco = new FaleConoscoEntity();

		return "";
	}

	public FaleConoscoEntity getFaleConosco() {
		return faleConosco;
	}

	public void setFaleConosco(FaleConoscoEntity faleConosco) {
		this.faleConosco = faleConosco;
	}
}
