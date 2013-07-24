package am.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import am.model.Threshold;
import am.utils.HibernateUtil;

public class ScheduleController {
	public Threshold getThreshold(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Threshold threshold = (Threshold) session.createQuery(
					"SELECT threshold " +
					"FROM Threshold threshold ")
					.uniqueResult();
			tx.commit();
			
			return threshold;
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
	
	public void saveThreshold(Threshold threshold){
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = null;
			Transaction tx = null;
			
			try {
				session = sf.openSession();
				tx = session.beginTransaction();
				
				session.saveOrUpdate(threshold);
				
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
}
