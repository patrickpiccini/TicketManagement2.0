package br.com.tkmanager.dbmanip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.ws.rs.FormParam;

import br.com.tkmanager.models.Chamado;
import br.com.tkmanager.models.Tecnico;
import br.com.tkmanager.util.Bcrypt;

public class Database {
	
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("TicketManagament2.0");
	
	public static Tecnico validateTecnico (String nome) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		Tecnico tec = new Tecnico();

		try {
			tr.begin();
			
			try {
				tec = em.createQuery("SELECT tec FROM TECNICO tec WHERE tec.nome = '"+nome+"'", Tecnico.class).getSingleResult();
			}
			
			catch(NoResultException nr) {
				return null;
			}
		}
		
		finally{
			em.close();
		}
		
		return tec;
	}

	public static Tecnico insertTecnico(String nome, String sobrenome, String senha,  String email, Date nascimento) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		Tecnico tec = new Tecnico();
		
		try {
			tr = em.getTransaction();
			tr.begin();
			
			tec.setNome(nome);
			tec.setSobrenome(sobrenome);
			tec.setSenha(senha);
			tec.setEmail(email);
			tec.setNascimento(nascimento);
			
			em.persist(tec);
			tr.commit();
		}
		
		catch (Exception e) {
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
			return null;
		}
		
		finally {
			em.close();
		}
		
		return tec;
	}
	
	public static Chamado insertChamado(Integer resp, Integer rela, String titu, Character stat, String desc, Character prio, Character impa, Date dtin) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		Chamado cha = new Chamado();
		
		try {
			tr.begin();
			
			cha.setResponsavel(resp);
			cha.setRelator(rela);
			cha.setTitulo(titu);
			cha.setStatus(stat);
			cha.setDescricao(desc);
			cha.setPrioridade(prio);
			cha.setImpacto(impa);
			cha.setDtinclusao(dtin);
			
			em.persist(cha);
			tr.commit();
		}
		
		catch(Exception e) {
			if (tr != null) {
				tr.rollback();
			}
			
			e.printStackTrace();
			return null;
		}
		
		finally {
			em.close();
		}
		
		return cha;
	}

	public static List<Chamado> selectChamados(Integer idResp){
		// idResp <= 0  ir� retornar todos os chamados da tabela
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		List<Chamado> chamados = new ArrayList<Chamado>();
		
		try {
			tr.begin();
			
			try {
				if (idResp > 0) { 
					chamados = em.createQuery("SELECT cha FROM CHAMADO cha WHERE cha.responsavel = "+ idResp, Chamado.class).getResultList();
				}
				else {
					chamados = em.createQuery("SELECT cha FROM CHAMADO cha", Chamado.class).getResultList();

				}
			}
			catch(NoResultException nr) {
				chamados = null;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return chamados;
	}
	
	public static List<List> selectTecnicos(){
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		List<Tecnico> lt;
		List<List> ln = new ArrayList<List>();
		
		try {
			tr.begin();
			
			try {
				lt = em.createQuery("SELECT tec FROM TECNICO tec", Tecnico.class).getResultList();
				
				for (Tecnico tec : lt) {
					List<String> tec_aux = new ArrayList<String>();
					tec_aux.add(tec.getId().toString());
					tec_aux.add(tec.getNome()+ " " + tec.getSobrenome());
					ln.add(tec_aux);
				}
			}
			catch(NoResultException nr) {
				ln = null;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return ln;
	}
	
	
	public static boolean haveTecnico(String nome, String sobrenome) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		boolean exist = false;
		
		tr.begin();
		
		try {
			try {
				exist =  true;
				Tecnico tec = em.createQuery("SELECT tec FROM TECNICO tec WHERE tec.nome = '"+nome+"' AND tec.sobrenome = '"+sobrenome+"'", Tecnico.class).getSingleResult();
			}
			
			catch(NoResultException nr) {
				exist =  false;
			}
			
			catch(NonUniqueResultException nun) {
				exist =  true;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			exist = true;
		}
		
		finally {
			em.close();
		}
		
		return exist;
	}
	
	
	public static void mudarSenhas() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		tr.begin();
		
		try {
			List<Tecnico> listec = em.createQuery("SELECT tec FROM TECNICO tec WHERE tec.senha != null", Tecnico.class).getResultList();
			
			for (Tecnico tec : listec) {
				tec.setSenha(Bcrypt.hashPass(tec.getSenha()));
				em.persist(tec);
			}
			
			
			tr.commit();
			
		}
		
		catch (Exception e ) {
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}
		
		finally {
			em.close();
		}
	}
}













