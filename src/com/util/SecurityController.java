package com.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import controller.Login;

public class SecurityController implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private static final Logger logger = Logger
	// .getLogger(SecurityController.class.getName());

	public static final String JSF_SECURITY_KEY = "__jsfsecurity__";
	public static final String VIEWID_KEY = "__viewId";

	// private static String[] protectedPaths = null;

	// public static Login usuarioSessao;

	// public static Servidor server = new Servidor();

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		Login usuarioSessao = (Login) fc.getExternalContext().getSessionMap().get("login");

		Sessao.setLogin(usuarioSessao);

		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();
		System.out.println("##### P�GINA: " + currentPage);
		// System.out.println("");
		boolean isLoginPage;

		isLoginPage = currentPage.equals("/login.xhtml") || currentPage.equals("/_Mobilelogin.xhtml");

		boolean isDownPage = currentPage.equals("/Download.xhtml");
		// HttpSession session = (HttpSession) facesContext.getExternalContext()
		// .getSession(true);
		// Login currentUser = (Login) session.getAttribute("login");

		boolean publicPage = currentPage.equals("/registrar.xhtml");

		boolean temUsuario = true;
		if (usuarioSessao == null || usuarioSessao.isLogado() == false)
			temUsuario = false;

		// System.out.println("LOGIN PAGE -> " + isLoginPage);
		// System.out.println("TEM USUARIO -> " + temUsuario);

		mobilePage(event);

		if (isDownPage)
			return;
		else if (isLoginPage)
			return;
		else if (publicPage)
			return;
		else if (temUsuario == false) {
			// System.out.println("PAGINA DE LOGIN");
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "login");
			mobilePage(event);
			return;
		} else {
			boolean isAdmin = true;
			// if (usuarioSessao.getSetorLogin().getAdmin() == null)
			isAdmin = false;
			// else if (usuarioSessao.getSetorLogin().getAdmin().equals("N"))
			isAdmin = false;

			if (isAdmin == false) {
				if (currentPage.startsWith("/CadastroUsuario.xhtml")
						|| currentPage.startsWith("/ConsultaUsuario.xhtml")) {

					NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
					nh.handleNavigation(facesContext, null, "Menu");
					mobilePage(event);
					return;
				}
			}
		}

	}

	public void mobilePage(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();

		if (currentPage.toUpperCase().contains("MENU") == false
				&& currentPage.toUpperCase().contains("LOGIN") == false) {
			return;
		}

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");

		Login usuarioSessao = (Login) facesContext.getExternalContext().getSessionMap().get("login");

		System.out.println("AFTER PHARSE: CURRENTPAGE: " + currentPage);
		System.out.println("AFTER PHARSE: USER-AGENT : " + userAgent);

		// if (userAgent.toUpperCase().contains("WINDOWS") == false
		// && userAgent.toUpperCase().contains("MACINTOSH") == false) {
		// if (usuarioSessao != null)
		// usuarioSessao.setSmartphone(true);
		//
		// if (currentPage.toUpperCase().contains("MENU")
		// || currentPage.toUpperCase().contains("LOGIN")) {
		//
		// NavigationHandler nh = facesContext.getApplication()
		// .getNavigationHandler();
		//
		// String novaPagina = "";
		// novaPagina = currentPage.replaceAll(".xhtml", "");
		// novaPagina = novaPagina.replaceAll("/_Mobile", "");
		//
		// novaPagina = "_Mobile" + novaPagina.substring(1);
		// System.out.println("AFTER PHARSE: NOVA PAGINA: " + novaPagina);
		// nh.handleNavigation(facesContext, null, novaPagina);
		// }
		// }

	}

	// public void beforePhase2(PhaseEvent event) {
	// FacesContext fc = event.getFacesContext();
	//
	// NavigationHandler nh = fc.getApplication().getNavigationHandler();
	//
	// Usuario userSesion = (Usuario) fc.getExternalContext().getSessionMap()
	// .get("usuarioLogin");// Pega o seu usuário da sessão (o que está
	// // logado).
	//
	// String currentPage = fc.getViewRoot().getViewId();// Pega a pagina que o
	// // usuario esta
	// // tentando acessar
	// if (currentPage.contains("admin/")) { // Digamos que suas paginas de
	// // acesso ADMIN estão dentro da
	// // pasta "admin"
	// if (!userSession.getRole().equals("ADMIN")) {// Digamos que vc tenha
	// // uma String
	// // dizendo a
	// // permissao do
	// // usuario logado,
	// // se não for
	// // ADMIN...
	// nh.handleNavigation(fc, null, "login");// Redireciona pra tela
	// // de login (String
	// // "login" mapeada no
	// // faces-config)
	// }
	// }
	// }

	@Override
	public void afterPhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
		// return PhaseId.RESTORE_VIEW;
	}
}