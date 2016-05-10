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


@WebServlet("/entrar")  
public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("consultando cpf = "+request.getParameter("cpf")+" data nascimento = "+ request.getParameter("dataNascimento") );
		//consulta se tem o usuario no banco de dados
		Usuario usuario = new ControladorDeUsuario().login(request.getParameter("cpf"), request.getParameter("dataNascimento"));
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
//		se o usuario existir
		if (usuario != null) {
			//coloco o usuario na sessao!
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			//redireciona o usuario para o menu!
			System.out.println("redirecionando o usuario!");
//			request.getRequestDispatcher("menu.html").include(request, response);
			out.println("true");
		} else {
			//caso o login esteja errado
			
			out.print("false");
//			out.print("<script type=\"text/javascript\">");
//			out.print("alert('CPF ou data de nascimento errada!');");
//			out.print("</script>");
//			out.close();
//			
//			
//			response.sendRedirect("/entrar.html");
		}
		out.close();
	}
	

	
}
