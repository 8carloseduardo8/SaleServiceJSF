package com.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.Login;

public class Sessao {

	// public static Login loginAtivo;

	public static void setLogin(Login login) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("LOGIN", login);
	}

	public static Login getLogin() {
		// HttpServletRequest req;
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		return (Login) session.getAttribute("LOGIN");
	}
}
