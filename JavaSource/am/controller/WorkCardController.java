package am.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import am.model.Binding;
import am.model.WorkCard;
import am.model.WorkCardInspection;
import am.model.WorkCardParts;
import am.utils.HibernateUtil;

public class WorkCardController {
	public void saveWorkCard(WorkCard workCard){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(workCard);
			
			if(workCard.getWorkCardInspections() != null){
				for(WorkCardInspection binding : workCard.getWorkCardInspections()){
					binding.setWorkCard(workCard);
					session.saveOrUpdate(binding);
				}
			}
			
			if(workCard.getWorkCardParts() != null){
				for(WorkCardParts part : workCard.getWorkCardParts()){
					part.setWorkCard(workCard);
					session.saveOrUpdate(part);
				}
			}
					
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
	
	public void saveWorkCard(WorkCard workCard, List<WorkCardParts> parts){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(workCard);
			
			if(workCard.getWorkCardParts() != null){
				for(WorkCardParts part : workCard.getWorkCardParts()){
					session.delete(part);
				}
			}
			
			if(parts != null){
				for(WorkCardParts part : parts){
					part.setId(null);
					part.setWorkCard(workCard);
					session.saveOrUpdate(part);
				}
			}
					
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
	
	public List<WorkCard> getWorkCardList(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<WorkCard> workcards = session.createQuery(
					"select distinct workcard " +
					"from WorkCard workcard " +
					"left join fetch workcard.aircraft " +
					"left join fetch workcard.workCardInspections insp " +
					"left join fetch insp.binding " +
					"left join fetch workcard.workCardParts part " +
					"left join fetch part.spareParts " +
					"where workcard.status = 'NORMAL' " +
					"order by workcard.createDate desc")
					.list();
		
			tx.commit();
			
			return workcards;
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
