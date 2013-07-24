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
@Table(name="AMWORKCARDPARTS")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class WorkCardParts {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "WORKCARD_ID", referencedColumnName = "ID")
	private WorkCard workCard;
	
	@ManyToOne
	@JoinColumn(name = "PARTS_ID", referencedColumnName = "ID")
	private SpareParts spareParts;
	
	@Column(name="QUANTITY")
	private int quantity = 1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SpareParts getSpareParts() {
		return spareParts;
	}

	public void setSpareParts(SpareParts spareParts) {
		this.spareParts = spareParts;
	}

	public WorkCard getWorkCard() {
		return workCard;
	}

	public void setWorkCard(WorkCard workCard) {
		this.workCard = workCard;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
