package am.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import am.model.Engine;
import am.model.Propeller;
import am.utils.HibernateUtil;

public class PropellerController {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Propeller> getAllPropeller(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Propeller> propellerList = session.createQuery(
					"SELECT DISTINCT propeller " +
					"FROM Propeller propeller " +
					"left join fetch propeller.aircraftPropellerOne " +
					"left join fetch propeller.aircraftPropellerTwo " +
					"WHERE propeller.dataStatus = 'NORMAL' " +
					"ORDER BY propeller.id")
					.list();
			tx.commit();
			
			return new ArrayList<Propeller>(propellerList);
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
	
	public void savePropeller(Propeller propeller){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(propeller);
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
	public ArrayList<Propeller> getAllAvailablePropeller(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Propeller> propellerList = session.createQuery(
					"SELECT DISTINCT propeller " +
					"FROM Propeller propeller " +
					"WHERE propeller.dataStatus = 'NORMAL' " +
					"AND propeller.status = 'READY' " +
					"ORDER BY propeller.id")
					.list();
			tx.commit();
			
			return new ArrayList<Propeller>(propellerList);
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
	
	public Propeller getPropeller(Long propellerID){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Propeller propeller = (Propeller) session.createQuery(
					"SELECT DISTINCT propeller " +
					"FROM Propeller propeller " +
					"left join fetch propeller.aircraftPropellerOne " +
					"left join fetch propeller.aircraftPropellerTwo " +
					"WHERE propeller.id = :ppropeller " +
					"AND propeller.dataStatus = 'NORMAL' ")
					.setParameter("ppropeller", propellerID)
					.uniqueResult();
			
			return propeller;
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
