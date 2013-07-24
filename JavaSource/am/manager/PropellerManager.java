package am.manager;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import am.controller.PropellerController;
import am.customtype.DataStatus;
import am.customtype.Status;
import am.model.Propeller;

@ManagedBean(name="propellerManager")
@SessionScoped
public class PropellerManager {
	
	private String inputFieldLegend = "Create Propeller";
	private ArrayList<Propeller> propellerList = new ArrayList<Propeller>();
	private Propeller editPropeller = new Propeller();
	private Propeller deletedPropeller = new Propeller();
	private PropellerController propellerController = new PropellerController();
	
	public String getInputFieldLegend() {
		return inputFieldLegend;
	}

	public void setInputFieldLegend(String inputFieldLegend) {
		this.inputFieldLegend = inputFieldLegend;
	}

	public ArrayList<Propeller> getPropellerList() {
		return propellerList;
	}

	public void setPropellerList(ArrayList<Propeller> propellerList) {
		this.propellerList = propellerList;
	}

	public Propeller getEditPropeller() {
		return editPropeller;
	}

	public void setEditPropeller(Propeller editPropeller) {
		this.editPropeller = editPropeller;
	}

	public Propeller getDeletedPropeller() {
		return deletedPropeller;
	}

	public void setDeletedPropeller(Propeller deletedPropeller) {
		this.deletedPropeller = deletedPropeller;
	}

	public PropellerManager() {
		createPropellerList();
	}
	
	private void createPropellerList(){
		if(propellerList != null) propellerList.clear();
		propellerList = propellerController.getAllPropeller();
	}
	
	public void savePropeller(){
		propellerController.savePropeller(editPropeller);
		createPropellerList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is saved"));
	}
	
	public void deletePropeller(){
		deletedPropeller.setDataStatus(DataStatus.DELETED);
		propellerController.savePropeller(deletedPropeller);
		createPropellerList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is deleted"));
	}
	
	public void clearData(){
		editPropeller = new Propeller();
		deletedPropeller = new Propeller();
		inputFieldLegend = "Create Propeller";
	}
	
	public void setLegend(){
		inputFieldLegend = "Edit Propeller "+ "[" +editPropeller.getUniqeName()+ "]";
	}
	
	public void createAllList(){
		createPropellerList();
	}
}
