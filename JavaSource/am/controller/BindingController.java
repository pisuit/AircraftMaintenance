package am.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import am.model.Aircraft;
import am.model.Binding;
import am.model.Chapter;
import am.model.Engine;
import am.model.Propeller;
import am.utils.HibernateUtil;

public class BindingController {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Chapter> getChaptersForAircraft(Aircraft aircraft){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Binding> bindingList = session.createQuery(
					"SELECT DISTINCT binding " +
					"FROM Binding binding " +
					"left join fetch binding.chapter chap " +
					"WHERE binding.aircraft = :paircraft " +
					"AND binding.deviceType = 'AIRCRAFT' " +
					"ORDER BY chap.number")
					.setParameter("paircraft", aircraft)
					.list();
			
			for(Binding binding : bindingList){
				chapterList.add(binding.getChapter());
			}
			tx.commit();
			
			return new ArrayList<Chapter>(chapterList);
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
	
	public void saveBinding(Binding binding){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(binding);
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
	public ArrayList<Binding> getAircraftBinding(Aircraft aircraft){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
	
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Binding> bindingList = session.createQuery(
					"SELECT DISTINCT binding " +
					"FROM Binding binding " +
					"left join fetch binding.chapter chap " +
					"left join fetch binding.inspection ins " +
					"left join fetch ins.partList part " +
					"left join fetch binding.aircraft " +
					"left join fetch binding.engine " +
					"left join fetch binding.propeller " +
					"WHERE binding.aircraft = :paircraft " +
					"ORDER BY chap.number")
					.setParameter("paircraft", aircraft)
					.list();
			
			tx.commit();
			
			return new ArrayList<Binding>(bindingList);
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Binding> getEngineBinding(Engine engine){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
	
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Binding> bindingList = session.createQuery(
					"SELECT DISTINCT binding " +
					"FROM Binding binding " +
					"left join fetch binding.chapter chap " +
					"left join fetch binding.inspection ins " +
					"left join fetch ins.partList part " +
					"left join fetch binding.aircraft " +
					"left join fetch binding.engine " +
					"left join fetch binding.propeller " +
					"WHERE binding.engine = :pengine " +
					"ORDER BY chap.number")
					.setParameter("pengine", engine)
					.list();
			
			tx.commit();
			
			return new ArrayList<Binding>(bindingList);
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Binding> getPropellerBinding(Propeller propeller){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
	
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Binding> bindingList = session.createQuery(
					"SELECT DISTINCT binding " +
					"FROM Binding binding " +
					"left join fetch binding.chapter chap " +
					"left join fetch binding.inspection ins " +
					"left join fetch ins.partList part " +
					"left join fetch binding.aircraft " +
					"left join fetch binding.engine " +
					"left join fetch binding.propeller " +
					"WHERE binding.propeller = :ppropeller " +
					"ORDER BY chap.number")
					.setParameter("ppropeller", propeller)
					.list();
			
			tx.commit();
			
			return new ArrayList<Binding>(bindingList);
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
	
	public void deleteBinding(Binding deletedBinding){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.delete(deletedBinding);
			
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
