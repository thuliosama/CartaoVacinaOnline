package teste;


import br.com.cartao.vacina.online.controle.ControladorDeUsuario;
import br.com.cartao.vacina.online.controle.util.Util;

public class Teste {

	public static void main(String[] args) {
		System.out.println(new ControladorDeUsuario().criarUsuario("Thulio Amaral", "Francisco", "Claudia", "08/04/1988", "123.456.456-45"));
		
		String cpf =  "123.456.456-45";
		Util u  =new Util();
		System.out.println(u.convesorDeCpf(cpf));
		
	
	}
}
