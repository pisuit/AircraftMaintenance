package am.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.jboss.resteasy.spi.touri.MappedBy;

import am.customtype.CategoryType;
import am.customtype.DataStatus;
import am.customtype.DeviceType;

@Entity
@Table(name="AMPARTS")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class SpareParts {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="INSPECTION_ID" ,referencedColumnName="ID")
	private Inspection inspection;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CATEGORY_TYPE")
	@Enumerated(EnumType.STRING)
	private CategoryType categoryType;
	
	@Column(name="PART_NUMBER")
	private String number;
	
	@Column(name="SERIAL_NAME")
	private String serialName;
	
	@Column(name="MIN_STOCK")
	private int minStock;
	
	@Column(name="MAX_STOCK")
	private int maxStock;
	
	@Column(name="DATA_STATUS")
	@Enumerated(EnumType.STRING)
	private DataStatus dataStatus = DataStatus.NORMAL;
	
	@OneToMany(mappedBy="spareParts")
	private Set<PartsCompability> compabilityList;
	
	@Column(name="SUPERSEDE1")
	private String supersede1;
	
	@Column(name="SUPERSEDE2")
	private String supersede2;
	
	@Column(name="SUPERSEDE3")
	private String supersede3;
	
	@Column(name="STOCK_CODE")
	private String stockCode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Inspection getInspection() {
		return inspection;
	}
	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryType getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getMinStock() {
		return minStock;
	}
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	public int getMaxStock() {
		return maxStock;
	}
	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}
	public DataStatus getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}
	public Set<PartsCompability> getCompabilityList() {
		return compabilityList;
	}
	public void setCompabilityList(Set<PartsCompability> compabilityList) {
		this.compabilityList = compabilityList;
	}
	public String getSupersede1() {
		return supersede1;
	}
	public void setSupersede1(String supersede1) {
		this.supersede1 = supersede1;
	}
	public String getSupersede2() {
		return supersede2;
	}
	public void setSupersede2(String supersede2) {
		this.supersede2 = supersede2;
	}
	public String getSupersede3() {
		return supersede3;
	}
	public void setSupersede3(String supersede3) {
		this.supersede3 = supersede3;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
	public String toString(){
		List<String> stringList = new ArrayList<String>();
		for(PartsCompability compability : compabilityList){
			if(compability.getDeviceType().equals(DeviceType.AIRCRAFT)){
				stringList.add(compability.getAircraft().getRegister());
			}
			if(compability.getDeviceType().equals(DeviceType.ENGINE)){
				stringList.add(compability.getEngine().getUniqeName());
			}
			if(compability.getDeviceType().equals(DeviceType.PROPELLER)){
				stringList.add(compability.getPropeller().getUniqeName());
			}
		}
		return StringUtils.join(stringList, "<br/>");
	}
	public String getSerialName() {
		return serialName;
	}
	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}		
}
