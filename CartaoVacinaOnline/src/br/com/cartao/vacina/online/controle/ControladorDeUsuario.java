package br.com.cartao.vacina.online.controle;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cartao.vacina.online.controle.util.Util;
import br.com.cartao.vacina.online.entidade.JpaUtil;
import br.com.cartao.vacina.online.entidade.Usuario;

public class ControladorDeUsuario {

	public Usuario login(String cpf, String dataNascimento) {

		Util util = new Util();
		// converte o cpf String para Long e data String para Date e faz o login
		// retornando um usuario caso exista!
		return fazerLogin(util.convesorDeCpf(cpf), util.formataData(dataNascimento));

	}

	private Usuario fazerLogin(Long cpf, Date dataNascimento) {
		// se a o cpf ou a data de nascimento for nulo o metodo retorna null
		if (cpf == null || dataNascimento == null) {
			return null;
		}
		// iniciar um gerenciamento de entidade pegando da clase JpaUtil que e
		// um singleton
		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		// a consulta feita esta no metodo da classe usuario
		Query q = em.createNamedQuery("Usuario.findLogin", Usuario.class);
		// paramentro a ser comparado
		q.setParameter("cpf", cpf);
		q.setParameter("dataNascimento", dataNascimento);

		Usuario usuario = null;
		// caso tenha 2 usuarios repetido, o metodo retornara um erro
		try {
			usuario = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("Erro ao buscar o usuario = " + e.getMessage());
		}
		// fecha a coneção
		em.close();
		// retorna o usuario
		return usuario;

	}

	public Usuario criarUsuario(String nome, String filiacaoPai, String filiacaoMae, String dataNascimento,
			String cpf) {
		// instancia da classe auxiliadora util
		Util util = new Util();
		// cria um novo usuario com os parametros passado
		Date d = util.formataData(dataNascimento);

		return cadastrarNovoUsuario(new Usuario(nome, filiacaoPai, filiacaoMae, d, util.convesorDeCpf(cpf)));
	}
	
	private Usuario cadastrarNovoUsuario(Usuario usuario) {
		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			em.refresh(usuario);
			
		} catch (Exception e) {
			System.err.println("erro na gravaçao do usuario  =" + e.getMessage());
		}
		em.close();
		return usuario;
	}

	public boolean verficaCPF(String cpf) {

		Long cpfLong = new Util().convesorDeCpf(cpf);

		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		Query q = em.createNamedQuery("Usuario.consultaCPF", Usuario.class);
		q.setParameter("cpf", cpfLong);
		Usuario u = null;
		try {
			u = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (u != null) {
			return true;
		}
		return false;
	}

	public void cadastrarDependente(Usuario responsavel, String nome, String dataNascimento, String filiacaoPaterna, String filiacaoMaterna) {
		
		// instancia da classe auxiliadora util
		Util util = new Util();
		// cria um novo usuario com os parametros passado
		Date data = util.formataData(dataNascimento);
		//cria o dependente e cadastra o dependente
		Usuario dependente = new Usuario(nome, filiacaoPaterna, filiacaoMaterna, data);
		cadastrarNovoUsuario(dependente);
		
		//busca a lista de filhos do responsavel caso ele tenha!
		List<Usuario> dependentes = getDependentes(responsavel);
		
		
		
		
//		dependentes.add();
		
//		responsavel.setListaDependentes(dependentes);
//		alterarUsuario(responsavel);
	}

	private void alterarUsuario(Usuario responsavel) {
		// TODO Auto-generated method stub
		
	}

	private List<Usuario> getDependentes(Usuario responsavel) {

		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		Query q = em.createNamedQuery("Usuario.consultaCPF", Usuario.class);
		q.setParameter("responsavel", responsavel);
		List<Usuario> u = q.getResultList();
		em.close();
		return u;
	}

}
