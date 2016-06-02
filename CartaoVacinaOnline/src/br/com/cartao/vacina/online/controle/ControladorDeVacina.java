package br.com.cartao.vacina.online.controle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cartao.vacina.online.entidade.JpaUtil;
import br.com.cartao.vacina.online.entidade.Usuario;
import br.com.cartao.vacina.online.entidade.Vacina;

public class ControladorDeVacina {

	// metodo para chamar o criador de vacina
	public Vacina criarVacina(String nomeVacina) {

		return cadastrarNovaVacina(new Vacina(nomeVacina));
	}

	// criador de vacina
	private Vacina cadastrarNovaVacina(Vacina vacina) {
		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		try {
			em.getTransaction().begin();
			em.persist(vacina);
			em.getTransaction().commit();
			em.refresh(vacina);

		} catch (Exception e) {
			System.err.println("erro na gravaçao do usuario  =" + e.getMessage());
		}
		em.close();
		return vacina;
	}

	public Vacina getVacina(Long idVacina) {
		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		Vacina resp = em.find(Vacina.class, idVacina);
		em.close();
		return resp;
	}

	
	public List<Vacina> vacinasTomadas(Usuario usuario) {

		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		Query q = em.createNamedQuery("Vacina.findVacinasUsuario", Vacina.class);
		q.setParameter("id", usuario.getId());
		@SuppressWarnings("unchecked")
		List<Vacina> u = q.getResultList();
		em.close();
		return u;
	}

	public List<Vacina> allVacinas() {
		EntityManager em = JpaUtil.getInstancia().getEntidadeManager();
		Query q = em.createNamedQuery("Vacina.findAllVacinas", Vacina.class);
		
		@SuppressWarnings("unchecked")
		List<Vacina> u = q.getResultList();
		em.close();
		return u;
		
	}

}
