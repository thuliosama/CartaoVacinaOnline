package teste;


import br.com.cartao.vacina.online.controle.ControladorDeUsuario;


public class Teste {

	public static void main(String[] args) {
		System.out.println(new ControladorDeUsuario().criarUsuario("Thulio Amaral", "Francisco", "Claudia", "08/04/1988", "123.456.456-45"));
		System.out.println(new ControladorDeUsuario().criarUsuario("Thulio A", "Francisco", "Claudia", "01/01/1111", "111.111.111-11"));
		
		
	
	}
}
