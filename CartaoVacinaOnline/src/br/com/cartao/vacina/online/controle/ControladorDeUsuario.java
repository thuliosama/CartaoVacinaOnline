package br.com.cartao.vacina.online.controle;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cartao.vacina.online.controle.util.Util;
import br.com.cartao.vacina.online.entidade.JpaUtil;
import br.com.cartao.vacina.online.entidade.Usuario;
import br.com.cartao.vacina.online.entidade.Vacina;

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

	public Usuario cadastrarDependente(Usuario responsavel, String nome, String dataNascimento, String filiacaoPaterna,
			String filiacaoMaterna) {

		// instancia da classe auxiliadora util
		Util util = new Util();
		// cria um novo usuario com os parametros passado
		Date data = util.formataData(dataNascimento);
		// cria o dependente e cadastra o dependente
		Usuario dependente = cadastrarNovoUsuario(new Usuario(nome, filiacaoPaterna, filiacaoMaterna, data));

		// busca a lista de filhos do responsavel caso ele tenha!
		dependente.setResponsavel(responsavel);
		alterarUsuario(dependente);
		return getUsuario(responsavel.getId());

	}

	public void adicionarVacina(Usuario usuario, Vacina vacina) {
		List<Vacina> listaVacinasTomada = new ControladorDeVacina().vacinasTomadas(usuario);
		listaVacinasTomada.add(vacina);
		usuario.setVacinas(listaVacinasTomada);
		alterarUsuario(usuario);
	}

	

	public void adicionarDependente(Usuario responsavel, Usuario dependente) {

		List<Usuario> listaDepdenetes = getDependentes(responsavel);
		listaDepdenetes.add(dependente);
		dependente.setResponsavel(responsavel);
		// adiciona os filhos ao pai;
		responsavel.setListaDependentes(listaDepdenetes);
		alterarUsuario(dependente);
	}

	private  Usuario alterarUsuario(Usuario usuario) {

		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		try {
			em.getTransaction().begin();
			Usuario aux = em.find(Usuario.class, usuario.getId());
			aux = usuario;
			em.merge(aux);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("erro na edicao do usuario =" + e.getMessage());
		}
		em.close();
		return usuario;
	}

	public List<Usuario> getDependentes(Usuario responsavel) {

		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		Query q = em.createNamedQuery("Usuario.findDependentes", Usuario.class);
		q.setParameter("responsavel", responsavel);
		@SuppressWarnings("unchecked")
		List<Usuario> u = q.getResultList();
		em.close();
		return u;
	}

	public Usuario getUsuario(Long idUsuario) {
		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		Usuario resp = em.find(Usuario.class, idUsuario);
		em.close();
		return resp;
	}

}
