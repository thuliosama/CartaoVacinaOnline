package br.com.cartao.vacina.online.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
		@NamedQuery(name = "Usuario.findDependentes", query = "Select u from Usuario u where u.responsavel = :responsavel  ORDER BY u.nome ASC"),
		@NamedQuery(name = "Usuario.findLogin", query = "Select u from Usuario u where u.cpf = :cpf AND u.dataNascimento = :dataNascimento "),
		@NamedQuery(name = "Usuario.consultaCPF", query = "Select u from Usuario u where u.cpf = :cpf ") })

@Entity
@Cacheable(false)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String filiacaoPai;
	private String filiacaoMae;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	@Column(unique = true)
	private Long cpf;

	@ManyToMany
    @JoinTable(name = "usuario_vacina", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_vacina"))
	@JsonBackReference
    private List<Vacina> vacinas;

	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
	@OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Usuario> listaDependentes = new ArrayList<>();

	@JsonBackReference
	@JoinColumn(name = "responsavelId", referencedColumnName = "id")
	private Usuario responsavel;

	public Usuario() {
	}

	public Usuario(String nome, String filiacaoPai, String filiacaoMae, Date dataNascimento, Long cpf) {
		this.nome = nome;
		this.filiacaoPai = filiacaoPai;
		this.filiacaoMae = filiacaoMae;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
	}

	public Usuario(String nome, String filiacaoPai, String filiacaoMae, Date dataNascimento) {
		this.nome = nome;
		this.filiacaoPai = filiacaoPai;
		this.filiacaoMae = filiacaoMae;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFiliacaoPai() {
		return filiacaoPai;
	}

	public void setFiliacaoPai(String filiacaoPai) {
		this.filiacaoPai = filiacaoPai;
	}

	public String getFiliacaoMae() {
		return filiacaoMae;
	}

	public void setFiliacaoMae(String filiacaoMae) {
		this.filiacaoMae = filiacaoMae;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public List<Usuario> getListaDependentes() {
		return listaDependentes;
	}

	public void setListaDependentes(List<Usuario> listaDependentes) {
		this.listaDependentes = listaDependentes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((filiacaoMae == null) ? 0 : filiacaoMae.hashCode());
		result = prime * result + ((filiacaoPai == null) ? 0 : filiacaoPai.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listaDependentes == null) ? 0 : listaDependentes.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((vacinas == null) ? 0 : vacinas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (filiacaoMae == null) {
			if (other.filiacaoMae != null)
				return false;
		} else if (!filiacaoMae.equals(other.filiacaoMae))
			return false;
		if (filiacaoPai == null) {
			if (other.filiacaoPai != null)
				return false;
		} else if (!filiacaoPai.equals(other.filiacaoPai))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaDependentes == null) {
			if (other.listaDependentes != null)
				return false;
		} else if (!listaDependentes.equals(other.listaDependentes))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (vacinas == null) {
			if (other.vacinas != null)
				return false;
		} else if (!vacinas.equals(other.vacinas))
			return false;
		return true;
	}


}
