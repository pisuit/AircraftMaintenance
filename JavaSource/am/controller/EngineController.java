package am.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import am.model.Aircraft;
import am.model.Engine;
import am.utils.HibernateUtil;

public class EngineController {
	@SuppressWarnings("unchecked")
	public ArrayList<Engine> getAllEngine(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Engine> engineList = session.createQuery(
					"SELECT DISTINCT engine " +
					"FROM Engine engine " +
					"left join fetch engine.aircraftEngineOne " +
					"left join fetch engine.aircraftEngineTwo " +
					"WHERE engine.dataStatus = 'NORMAL' " +
					"ORDER BY engine.id")
					.list();
			tx.commit();
			
			return new ArrayList<Engine>(engineList);
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.clear();
			session.close();
		}
	}
	
	public void saveEngine(Engine engine){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(engine);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.clear();
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Engine> getAllAvailableEngine(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Engine> engineList = session.createQuery(
					"SELECT DISTINCT engine " +
					"FROM Engine engine " +
					"WHERE engine.dataStatus = 'NORMAL' " +
					"AND engine.status = 'READY' " +
					"ORDER BY engine.id")
					.list();
			tx.commit();
			
			return new ArrayList<Engine>(engineList);
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.clear();
			session.close();
		}
	}
	
	public Engine getEngine(Long engineID){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Engine engine = (Engine) session.createQuery(
					"SELECT DISTINCT engine " +
					"FROM Engine engine " +
					"left join fetch engine.aircraftEngineOne " +
					"left join fetch engine.aircraftEngineTwo " +
					"WHERE engine.id = :pengine " +
					"AND engine.dataStatus = 'NORMAL' ")
					.setParameter("pengine", engineID)
					.uniqueResult();
			
			return engine;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.clear();
			session.close();
		}
	}
}
