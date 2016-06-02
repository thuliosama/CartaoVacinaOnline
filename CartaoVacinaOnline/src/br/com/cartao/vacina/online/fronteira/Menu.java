package br.com.cartao.vacina.online.fronteira;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

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

			// converte objetos Java para JSON e retorna JSON como String
			ObjectMapper mapper = new ObjectMapper();
			String json = null;
			System.out.println("usuario logado  = "+usuario);
			json = mapper.writeValueAsString(usuario);
			out.println(json);
			out.close();

		} catch (IOException e) {
			System.out.println("ERRO  = " + e.getMessage());
		}
	}

}