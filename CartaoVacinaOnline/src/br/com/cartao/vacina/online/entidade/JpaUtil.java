package br.com.cartao.vacina.online.entidade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil   {

    
    private static JpaUtil instancia = new JpaUtil();
    private EntityManagerFactory factory;

    private JpaUtil() {
        factory = Persistence.createEntityManagerFactory("CartaoDeVacinaOnlinePU");
    }
    public static JpaUtil getInstancia(){
    	if(instancia == null){
    		instancia = new JpaUtil();
    	}
    	return instancia;
    }

    public EntityManager getEntidadeManager() {
        return factory.createEntityManager();
    }
}