package br.com.cartao.vacina.online.fronteira;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cartao.vacina.online.controle.ControladorDeUsuario;
@WebServlet("/validaCPF")
public class ValidadorDeCPF extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		System.out.println("valor enviado do cpf = " + request.getParameter("cpf"));
			if (!(request.getParameter("cpf").equals("") || request.getParameter("cpf") == null)) {

			System.out.println("entrou aqui pra verificar o cpf");
				if (new ControladorDeUsuario().verficaCPF(request.getParameter("cpf"))) {
					// o cpf existe enviar false para a pagina!
					System.out.println("o CPF ja existe se fudeu!");
					out.print("false");
					out.close();
				}else{
					//o cpf nao existe pode ser cadastrado!
					out.print("true");
					out.close();
				}
			}
		
	
	}
}
