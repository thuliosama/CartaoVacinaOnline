package br.com.cartao.vacina.online.fronteira;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class FiltroLogin
 */
@WebFilter("/aba")
public class FiltroLogin implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltroLogin() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try{
			HttpServletRequest httpReq = (HttpServletRequest)request;
			HttpServletResponse httpRes = (HttpServletResponse)response;
			HttpSession session = httpReq.getSession(true);
			if(session.getAttribute("usuario")==null 
					&& !httpReq.getRequestURL().toString().contains("entrar.html")
					&& !httpReq.getRequestURL().toString().contains("aviso-cadastro.html")
					&& !httpReq.getRequestURL().toString().contains("cadastro.html")
					&& !httpReq.getRequestURL().toString().endsWith(".css")
					&& !httpReq.getRequestURL().toString().endsWith(".js")
					&& !httpReq.getRequestURL().toString().endsWith(".jpg")
					&& !httpReq.getRequestURL().toString().endsWith(".png")
					&& !httpReq.getRequestURL().toString().endsWith(".gif")){
				
				httpRes.sendRedirect(httpReq.getContextPath()+"/entrar.html");
				System.out.println("usuario bloqueado pelo filtro!");
			}else{
				chain.doFilter(request, response);
				System.out.println("Filtro OK!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
