package br.com.cartao.vacina.online.fronteira;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.cartao.vacina.online.controle.ControladorDeUsuario;
import br.com.cartao.vacina.online.entidade.Usuario;

@WebServlet("/entrar")  
public class CadastroDependente extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario responsavel = (Usuario) session.getAttribute("usuario");
		new ControladorDeUsuario().cadastrarDependente(responsavel,request.getParameter("nomeC"), request.getParameter("dataNascimento"));
		
	}
}
