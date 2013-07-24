package am.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import am.customtype.DataStatus;
import am.customtype.DeviceType;
import am.model.Aircraft;
import am.model.Engine;
import am.model.Inspection;
import am.model.PartsCompability;
import am.model.Propeller;
import am.model.SpareParts;
import am.utils.HibernateUtil;

public class PartsController {
	
	public void saveParts(SpareParts spareParts, ArrayList<Aircraft> aircraftList, ArrayList<Engine> engineList, ArrayList<Propeller> propellerList){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		PartsCompability compability;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(spareParts);
			
			if(spareParts.getCompabilityList() != null && spareParts.getCompabilityList().size() != 0){
				for(PartsCompability partsCom : spareParts.getCompabilityList()){
					session.delete(partsCom);
				}
			}
			
			if(spareParts.getDataStatus().equals(DataStatus.NORMAL)){
				for(Aircraft aircraft : aircraftList){
					compability = new PartsCompability();
					compability.setSpareParts(spareParts);
					compability.setAircraft(aircraft);
					compability.setDeviceType(DeviceType.AIRCRAFT);
					session.saveOrUpdate(compability);
				}
				
				for(Engine engine : engineList){
					compability = new PartsCompability();
					compability.setSpareParts(spareParts);
					compability.setEngine(engine);
					compability.setDeviceType(DeviceType.ENGINE);
					session.saveOrUpdate(compability);
				}
				
				for(Propeller propeller : propellerList){
					compability = new PartsCompability();
					compability.setSpareParts(spareParts);
					compability.setPropeller(propeller);
					compability.setDeviceType(DeviceType.PROPELLER);
					session.saveOrUpdate(compability);
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<SpareParts> getAllParts(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<SpareParts> partsList = session.createQuery(
					"SELECT DISTINCT parts " +
					"FROM SpareParts parts " +
					"left join fetch parts.compabilityList compability " +
					"left join fetch compability.aircraft " +
					"left join fetch compability.engine " +
					"left join fetch compability.propeller " +
					"WHERE parts.dataStatus = 'NORMAL' " +
					"ORDER BY parts.name ")
					.list();
			tx.commit();
			
			return new ArrayList<SpareParts>(partsList);
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
	
	public SpareParts getPart(Long id){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			SpareParts part = (SpareParts) session.createQuery(
					"SELECT DISTINCT parts " +
					"FROM SpareParts parts " +
					"left join fetch parts.compabilityList compability " +
					"left join fetch compability.aircraft " +
					"left join fetch compability.engine " +
					"left join fetch compability.propeller " +
					"WHERE parts.id = :pid " +
					"ORDER BY parts.name ")
					.setParameter("pid", id)
					.uniqueResult();
			tx.commit();
			
			return part;
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
