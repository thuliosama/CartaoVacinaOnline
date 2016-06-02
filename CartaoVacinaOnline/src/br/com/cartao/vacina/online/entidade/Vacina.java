package br.com.cartao.vacina.online.entidade;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "Vacina.findVacinasUsuario",query = "select v from Vacina va JOIN FETCH va.usuarios us JOIN FETCH us.vacinas v WHERE va.id = :id"),
	@NamedQuery(name = "Vacina.findAllVacinas",query = "select v from Vacina v")
	})

@Entity
@Table(name = "vacina")
@Cacheable(false)
public class Vacina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeVacina;

	@ManyToMany(mappedBy = "vacinas")
	private List<Usuario> usuarios;

	public Vacina() {
	}

	public Vacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
