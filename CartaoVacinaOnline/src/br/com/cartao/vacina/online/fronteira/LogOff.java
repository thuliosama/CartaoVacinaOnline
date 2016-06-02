package br.com.cartao.vacina.online.fronteira;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logOff")
public class LogOff extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String comando = request.getParameter("logOff");
		System.out.println("comando sair = "+comando);
		if(comando.equals("sair")){
			System.out.println("entrou em sair!!!");
			HttpSession session = request.getSession();
			session.removeAttribute("usuario");
		}
		
	}
}
