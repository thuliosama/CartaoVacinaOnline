package br.com.cartao.vacina.online.fronteira;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.cartao.vacina.online.entidade.Usuario;

@WebServlet("/menu")
public class Menu extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("chamou servlet menu ");
		recuperarPessoas(request, response);
	}

	private void recuperarPessoas(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			System.out.println("o usuario é null = "+usuario.toString());
//			Informacoes obj = new Informacoes();
			Gson gson = new Gson();
		 
			// converte objetos Java para JSON e retorna JSON como String
			String json = gson.toJson(usuario);
			out.write(json);
			out.close();
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}