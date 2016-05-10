package br.com.cartao.vacina.online.fronteira;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cartao.vacina.online.controle.ControladorDeUsuario;
import br.com.cartao.vacina.online.entidade.Usuario;

@WebServlet("/cadastro")
public class Cadastro extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String comando = request.getParameter("comando");
		System.out.println("comando == " + comando);
		

			System.out.println("pedido de cadastro aceito!! iniciando cadastro");
			if (

					new ControladorDeUsuario().criarUsuario(request.getParameter("nomeC"),
					request.getParameter("filiacaoPaterna"), request.getParameter("filiacaoMaterna"),
					request.getParameter("dataNascimento"), request.getParameter("cpf")).getId() != null

			) {
				System.out.println("O usuario esta criado!");
				out.println("true");
				out.close();
			} else {
				// ero ao cadastrar o usuario
				out.print("<script type=\"text/javascript\">");
				out.print("alert('Erro ao cadastrar, tente novamente mais tarde!');");
				out.print("</script>");
				out.close();
				request.getRequestDispatcher("cadastro.html").include(request, response);
			}

		}
}
