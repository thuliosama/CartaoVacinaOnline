package br.com.cartao.vacina.online.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
		@NamedQuery(name = "Usuario.findLogin", query = "Select u from Usuario u where u.cpf = :cpf AND u.dataNascimento = :dataNascimento "),
		@NamedQuery(name = "Usuario.consultaCPF", query = "Select u from Usuario u where u.cpf = :cpf ")
})


@Entity
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String filiacaoPai;
	private String filiacaoMae;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	@Column(unique=true)
	private Long cpf;
	@OneToMany
	@JoinColumn(name = "idFiliacao")
	private List<Usuario> listaDependentes;

	
	
	public Usuario() {
	}

	public Usuario(String nome, String filiacaoPai, String filiacaoMae, Date dataNascimento, Long cpf) {
		this.nome = nome;
		this.filiacaoPai = filiacaoPai;
		this.filiacaoMae = filiacaoMae;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", filiacaoPai=" + filiacaoPai + ", filiacaoMae=" + filiacaoMae
				+ ", dataNascimento=" + dataNascimento + ", cpf=" + cpf + "]";
	}

}
