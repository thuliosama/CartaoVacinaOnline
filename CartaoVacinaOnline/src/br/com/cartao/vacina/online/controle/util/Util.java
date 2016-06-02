package br.com.cartao.vacina.online.controle.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	// metodo responsavel por converter String para data data!
	public Date formataData(String data) {
		// se o valor da variavel recebida pelo metodo for vazia ou nula o
		// metodo retornara null
		if (data == null || data.equals(""))
			return null;
		Date date = new Date();
		try {
			// formato da data que deve vir 20/12/2016
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (Date) formatter.parse(data);
			// caso o formato da data nao seja o informado acima da erro!
		} catch (ParseException e) {

		}
		// retorna a data
		return date;
	}

	// converte o cpf de String para integer tirando caracteres especiais
	public Long convesorDeCpf(String cpf) {
		Long retorno = null; 
		try {
		// retira o ponto da string
		cpf = cpf.replaceAll("\\.", "");
		// retira o menos da string
		cpf = cpf.replaceAll("-", "");
		// converte a strign para integer e retorna o valor
		
		
			 retorno = Long.parseLong(cpf);
		} catch (Exception e) {
			System.out.println("nao consegui converter o cpf em Integer! "+e.getMessage());
		}

		return retorno;
	}

}
