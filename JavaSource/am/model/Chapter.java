package am.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import am.customtype.DataStatus;
import am.customtype.InspectionType;

@Entity
@Table(name="AMCHAPTER")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class Chapter {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@Column(name="CHAPTER_NUMBER")
	private int number;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private InspectionType type = InspectionType.MINOR;
	
	@Column(name="DATA_STATUS")
	@Enumerated(EnumType.STRING)
	private DataStatus dataStatus = DataStatus.NORMAL;
	
	@OneToMany(mappedBy="chapter")
	private Set<Binding> bindings;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InspectionType getType() {
		return type;
	}
	public void setType(InspectionType type) {
		this.type = type;
	}
	public DataStatus getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getChapterName(){
		return number+"  "+name;
	}
	
	public String toString(){
		return number+"  "+name;				
	}
	public Set<Binding> getBindings() {
		return bindings;
	}
	public void setBindings(Set<Binding> bindings) {
		this.bindings = bindings;
	}
}
