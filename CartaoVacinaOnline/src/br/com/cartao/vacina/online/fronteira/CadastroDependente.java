package br.com.cartao.vacina.online.fronteira;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.cartao.vacina.online.controle.ControladorDeUsuario;
import br.com.cartao.vacina.online.entidade.Usuario;

@WebServlet("/cadastroDependente")  
public class CadastroDependente extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1778L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario responsavel = (Usuario) session.getAttribute("usuario");
		Usuario responsavelAtualizado = new ControladorDeUsuario().cadastrarDependente(responsavel,request.getParameter("nomeC"), request.getParameter("dataNascimento"), 
				request.getParameter("filiacaoPaterna"), request.getParameter("filiacaoMaterna"));
		PrintWriter out = response.getWriter();
		session.setAttribute("usuario", responsavelAtualizado);
		
		if(responsavelAtualizado.getId() != null){
			out.println("true");
		} else {
			out.print("false");
		}
		
	}
}
