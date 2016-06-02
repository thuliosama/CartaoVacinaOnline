package br.com.cartao.vacina.online.fronteira;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cartao.vacina.online.controle.ControladorDeUsuario;
import br.com.cartao.vacina.online.controle.ControladorDeVacina;
import br.com.cartao.vacina.online.entidade.Usuario;
import br.com.cartao.vacina.online.entidade.Vacina;

@WebServlet("/cartao")  
public class CartaoVacina extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("chamou servlet cartao ");
		allVacinas(request, response);
	}
	
	private void allVacinas(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 List<Vacina> vacinas = new ControladorDeVacina().allVacinas();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		json = mapper.writeValueAsString(vacinas);
		System.out.println("enviou o json = "+json);
		out.println(json);
		out.close();
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("chamou servlet cartao ");
		vacinas(request, response);
	}

	private void vacinas(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			String idUser = request.getParameter("user");
			List<Vacina> vacinasTomadas = new ControladorDeVacina().vacinasTomadas(new ControladorDeUsuario().getUsuario(Long.parseLong(idUser)));
			
			// converte objetos Java para JSON e retorna JSON como String
			ObjectMapper mapper = new ObjectMapper();
			String json = null;
			json = mapper.writeValueAsString(vacinasTomadas);
			System.out.println("enviou o json = "+json);
			out.println(json);
			out.close();
			

		} catch (IOException e) {
			System.out.println("ERRO  = " + e.getMessage());
		}
	}

}