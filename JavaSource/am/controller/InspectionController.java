package am.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import am.model.Chapter;
import am.model.Engine;
import am.model.Inspection;
import am.utils.HibernateUtil;

public class InspectionController {
	@SuppressWarnings("unchecked")
	public ArrayList<Inspection> getAllInspection(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Inspection> inspectionList = session.createQuery(
					"SELECT DISTINCT inspection " +
					"FROM Inspection inspection " +
					"WHERE inspection.dataStatus = 'NORMAL' " +
					"ORDER BY inspection.name ")
					.list();
			tx.commit();
			
			return new ArrayList<Inspection>(inspectionList);
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
	
	public void saveInspection(Inspection inspection){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(inspection);
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
	
	public Inspection getInspection(Long inspectionID){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Inspection inspection = (Inspection) session.createQuery(
					"SELECT DISTINCT inspection " +
					"FROM Inspection inspection " +
					"where inspection.id = :pinspectionid " +
					"AND inspection.dataStatus = 'NORMAL' ")
					.setParameter("pinspectionid", inspectionID)
					.uniqueResult();
			tx.commit();
			
			return inspection;
		}  catch (Exception e) {
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
	public ArrayList<Inspection> getInspectionByChapter(Chapter chapter){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Inspection> inspections = session.createQuery(
					"SELECT DISTINCT inspection " +
					"FROM Inspection inspection " +
					"where inspection.chapter = :pchapter " +
					"AND inspection.dataStatus = 'NORMAL' ")
					.setParameter("pchapter", chapter)
					.list();
			tx.commit();
			
			return new ArrayList<Inspection>(inspections);
		}  catch (Exception e) {
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
