package am.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import am.model.Chapter;
import am.model.Engine;
import am.utils.HibernateUtil;

public class ChapterController {
	
	public void saveChapter(Chapter chapter){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(chapter);
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
	public ArrayList<Chapter> getAllChapter(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Chapter> chapterList = session.createQuery(
					"SELECT DISTINCT chapter " +
					"FROM Chapter chapter " +
					"WHERE chapter.dataStatus = 'NORMAL' " +
					"ORDER BY chapter.number ")
					.list();
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
	
	public Chapter getChapter(Long chapterID){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Chapter chapter = (Chapter) session.createQuery(
					"SELECT DISTINCT chapter " +
					"FROM Chapter chapter " +
					"where chapter.id = :pchapterid " +
					"AND chapter.dataStatus = 'NORMAL' ")
					.setParameter("pchapterid", chapterID)
					.uniqueResult();
			tx.commit();
			
			return chapter;
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
