package br.unisul.projeto.core.dao.conexao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {

	private static EntityManagerFactory emf;
	
	private EntityManagerProvider(){
		
	}
	
	public static EntityManagerFactory getEntityManagerFactory(){
		
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("projeto");
		}
		
		return emf;
	}
}
