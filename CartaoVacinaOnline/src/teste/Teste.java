package teste;


import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cartao.vacina.online.controle.ControladorDeUsuario;
import br.com.cartao.vacina.online.controle.ControladorDeVacina;
import br.com.cartao.vacina.online.entidade.Usuario;
import br.com.cartao.vacina.online.entidade.Vacina;

public class Teste {

	public static void main(String[] args) {
		Teste t = new Teste();
//		t.criarUsuarios();
//		t.criaVacina();
		ControladorDeUsuario c = new ControladorDeUsuario();
		ControladorDeVacina v = new ControladorDeVacina();
		
		Usuario um = c.getUsuario(1L);
		Usuario dois = c.getUsuario(2L);
		Usuario tres = c.getUsuario(3L);
		Usuario quatro = c.getUsuario(4L);
		System.out.println("recuperou os 2 usuarios");
		Vacina umV = v.getVacina(5L);
		Vacina doisV = v.getVacina(9L);
		
		System.out.println("vacinou usuario um");
//		c.adicionarVacina(um, umV);
//		c.adicionarVacina(um, doisV);
		System.out.println("recuperou as vacinas tomadas");
		
	
		List<Vacina> vacinasTomadas = v.vacinasTomadas(c.getUsuario(1L));
		for (Vacina vacina : vacinasTomadas) {
			System.out.println("vacinas tomdas === "+vacina.getNomeVacina());
		}
		System.out.println("juntar paretesco");

		System.out.println("fim");

	}

	 void juntaParentesco(Usuario um, Usuario dois) {
		ControladorDeUsuario c = new ControladorDeUsuario();
//		c.adicionaDependente(um, dois);
	}

	 void criaVacina() {
		ControladorDeVacina c = new ControladorDeVacina();
		c.criarVacina("BCG");
		c.criarVacina("Hepatite B");
		c.criarVacina("Penta/DTP");
		c.criarVacina("VIP/VOP");
		c.criarVacina("Pneumocócica 10V (conjugada)");
		c.criarVacina("Rotavirus Humano");
		c.criarVacina("MeningocócicaC (conjugada)");
		c.criarVacina("Febre Amarela");
		c.criarVacina("Hepatite A");
		c.criarVacina("Triplice Viral");
		c.criarVacina("Tetra Viral");
		c.criarVacina("HPV");
		c.criarVacina("Dupla Adulto");
		c.criarVacina("dTpa");
	}

	 void criarUsuarios() {
		ControladorDeUsuario c = new ControladorDeUsuario();

		System.out.println(c.criarUsuario("Thulio A1", "F1", "C1", "01/01/1111", "222.222.222-22"));
		System.out.println(c.criarUsuario("Thulio A2", "F2", "C2", "01/01/1111", "111.111.111-11"));
		System.out.println(c.criarUsuario("Thulio A3", "F3", "C3", "01/01/1111", "333.333.333-33"));
		System.out.println(c.criarUsuario("Thulio A4", "F4", "C4", "01/01/1111", "444.444.444-44"));
	}

	 void json(Usuario usu) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		String jsonInString2 = null;
		try {
			jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usu);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonInString);
		// System.out.println(jsonInString2);
		System.out.println(usu.getListaDependentes().get(0).getNome());
	}
}
