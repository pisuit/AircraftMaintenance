package am.manager;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import am.model.SpareParts;
import am.utils.FacesUtils;

@ManagedBean(name= "navigationManager")
@SessionScoped
public class NavigationManager {
	private String targetPage = "createaircraft";
	private int activeTab = -1;
	
	public void test(TabChangeEvent event){
		String tabid = event.getTab().getId();
		
		if(tabid.equals("tab1"))
			activeTab = 0;
		if(tabid.equals("tab2"))
			activeTab = 1;
		if(tabid.equals("tab3"))
			activeTab = 2;
		if(tabid.equals("tab4"))
			activeTab = 3;
		if(tabid.equals("tab5"))
			activeTab = 4;
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
		
		if(targetPage.equals("createaircraft")){
			AircraftManager aircraftManager = (AircraftManager) FacesUtils.getSessionObject("aircraftManager", AircraftManager.class);
			if(aircraftManager != null){
				aircraftManager.createAllList();
			}
		}
		if(targetPage.equals("createengine")){
			EngineManager engineManager = (EngineManager) FacesUtils.getSessionObject("engineManager", EngineManager.class);
			if(engineManager != null){
				engineManager.createAllList();
			}
		}
		if(targetPage.equals("createpropeller")){
			PropellerManager propellerManager = (PropellerManager) FacesUtils.getSessionObject("propellerManager", PropellerManager.class);
			if(propellerManager != null){
				propellerManager.createAllList();
			}
		}
		if(targetPage.equals("viewaircraft")){
			ViewAircraftManager viewAircraftManager = (ViewAircraftManager) FacesUtils.getSessionObject("viewAircraftManager", ViewAircraftManager.class);
			if(viewAircraftManager != null){
				viewAircraftManager.createAllList();
			}
		}
		if(targetPage.equals("viewengine")){
			ViewEngineManager viewEngineManager = (ViewEngineManager) FacesUtils.getSessionObject("viewEngineManager", ViewEngineManager.class);
			if(viewEngineManager != null){
				viewEngineManager.createAllList();
			}
		}
		if(targetPage.equals("bindchapter")){
			BindingManager bindingManager = (BindingManager) FacesUtils.getSessionObject("bindingManager", BindingManager.class);
			if(bindingManager != null){
				bindingManager.createAllList();
			}
		}
		if(targetPage.equals("createchapter")){
			ChapterManager chapterManager = (ChapterManager) FacesUtils.getSessionObject("chapterManager", ChapterManager.class);
			if(chapterManager != null){
				chapterManager.createAllList();
			}
		}
		if(targetPage.equals("createinspection")){
			InspectionManager inspectionManager = (InspectionManager) FacesUtils.getSessionObject("inspectionManager", InspectionManager.class);
			if(inspectionManager != null){
				inspectionManager.createAllList();
			}
		}
		if(targetPage.equals("createspareparts")){
			PartsManager partsManager = (PartsManager) FacesUtils.getSessionObject("partsManager", PartsManager.class);
			if(partsManager != null){
				partsManager.createAllList();
			}
		}
		
		if(targetPage.equals("workcard")){
			WorkCardManager workCardManager = (WorkCardManager) FacesUtils.getSessionObject("workCardManager", WorkCardManager.class);
			if(workCardManager != null){
				workCardManager.createAllList();
			}
		}
		if(targetPage.equals("schedule")){
			ScheduleManager scheduleManager = (ScheduleManager) FacesUtils.getSessionObject("scheduleManager", ScheduleManager.class);
			if(scheduleManager != null){
				scheduleManager.createAllList();
			}
		}
	}

	public int getActiveTab() {
		return activeTab;
	}
	
	public String getTargetPage() {
		return targetPage;
	}

	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}
}
