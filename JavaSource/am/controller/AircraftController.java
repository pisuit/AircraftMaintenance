package am.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import am.customtype.DataStatus;
import am.customtype.Status;
import am.model.Aircraft;
import am.model.Engine;
import am.model.Propeller;
import am.utils.HibernateUtil;

public class AircraftController {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Aircraft> getAllAircraft(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Aircraft> aircraftList = session.createQuery(
					"SELECT DISTINCT aircraft " +
					"FROM Aircraft aircraft " +
					"left join fetch aircraft.engineOne " +
					"left join fetch aircraft.engineTwo " +
					"left join fetch aircraft.propellerOne " +
					"left join fetch aircraft.propellerTwo " +
					"WHERE aircraft.dataStatus = 'NORMAL' " +
					"ORDER BY aircraft.id")
					.list();
			tx.commit();
			
			return new ArrayList<Aircraft>(aircraftList);
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
	
	public void saveAircraft(Aircraft aircraft, Engine oldEngineOne, Engine oldEngineTwo, Propeller oldPropellerOne, Propeller oldPropellerTwo){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(aircraft);
			
			if(oldEngineOne != null && oldEngineOne.getId() != null){
				oldEngineOne.setStatus(Status.READY);
				session.saveOrUpdate(oldEngineOne);
			}
			if(oldEngineTwo != null && oldEngineTwo.getId() != null){
				oldEngineTwo.setStatus(Status.READY);
				session.saveOrUpdate(oldEngineTwo);
			}
			if(oldPropellerOne != null && oldPropellerOne.getId() != null){
				oldPropellerOne.setStatus(Status.READY);
				session.saveOrUpdate(oldPropellerOne);
			}
			if(oldPropellerTwo != null && oldPropellerTwo.getId() != null){
				oldPropellerTwo.setStatus(Status.READY);
				session.saveOrUpdate(oldPropellerTwo);
			}
			
			if(aircraft.getEngineOne() != null && aircraft.getEngineOne().getId() != null){
				aircraft.getEngineOne().setStatus(Status.ATTACHED);
				session.saveOrUpdate(aircraft.getEngineOne());
			}
			if(aircraft.getEngineTwo() != null && aircraft.getEngineTwo().getId() != null){
				aircraft.getEngineTwo().setStatus(Status.ATTACHED);
				session.saveOrUpdate(aircraft.getEngineTwo());
			}
			if(aircraft.getPropellerOne() != null && aircraft.getPropellerOne().getId() != null){
				aircraft.getPropellerOne().setStatus(Status.ATTACHED);
				session.saveOrUpdate(aircraft.getPropellerOne());
			}
			if(aircraft.getPropellerTwo() != null && aircraft.getPropellerTwo().getId() != null){
				aircraft.getPropellerTwo().setStatus(Status.ATTACHED);
				session.saveOrUpdate(aircraft.getPropellerTwo());
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
	
	public void deleteAircraft(Aircraft aircraft){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			aircraft.setDataStatus(DataStatus.DELETED);
			session.saveOrUpdate(aircraft);
			
			if(aircraft.getEngineOne() != null && aircraft.getEngineOne().getId() != null){
				aircraft.getEngineOne().setStatus(Status.READY);
				session.saveOrUpdate(aircraft.getEngineOne());
			}
			if(aircraft.getEngineTwo() != null && aircraft.getEngineTwo().getId() != null){
				aircraft.getEngineTwo().setStatus(Status.READY);
				session.saveOrUpdate(aircraft.getEngineTwo());
			}
			if(aircraft.getPropellerOne() != null && aircraft.getPropellerOne().getId() != null){
				aircraft.getPropellerOne().setStatus(Status.READY);
				session.saveOrUpdate(aircraft.getPropellerOne());
			}
			if(aircraft.getPropellerTwo() != null && aircraft.getPropellerTwo().getId() != null){
				aircraft.getPropellerTwo().setStatus(Status.READY);
				session.saveOrUpdate(aircraft.getPropellerTwo());
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
	
	public Aircraft getAircraft(Long aircraftID){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Aircraft aircraft = (Aircraft) session.createQuery(
					"SELECT DISTINCT aircraft " +
					"FROM Aircraft aircraft " +
					"left join fetch aircraft.engineOne " +
					"left join fetch aircraft.engineTwo " +
					"left join fetch aircraft.propellerOne " +
					"left join fetch aircraft.propellerTwo " +
					"WHERE aircraft.id = :paircraftid")
					.setParameter("paircraftid", aircraftID)
					.uniqueResult();
			
			tx.commit();
			return aircraft;
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

