package am.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="AMWORKCARDINSPECTION")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class WorkCardInspection {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "WORKCARD_ID", referencedColumnName = "ID")
	private WorkCard workCard;
	
	@ManyToOne
	@JoinColumn(name = "BINDING_ID", referencedColumnName = "ID")
	private Binding binding;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WorkCard getWorkCard() {
		return workCard;
	}

	public void setWorkCard(WorkCard workCard) {
		this.workCard = workCard;
	}

	public Binding getBinding() {
		return binding;
	}

	public void setBinding(Binding binding) {
		this.binding = binding;
	}

}
