package bean;

import java.io.Serializable;

public class AlmondBean implements Serializable{

	private static final long serialVersionUID = -6961417288853632948L;
	
	private Long id;
	private ReaderBean reader;
	private Double amount;
	private boolean payed;
	
	public AlmondBean() {
		super();
	}

	public AlmondBean(ReaderBean reader, Double amount, boolean payed) {
		super();
		this.reader = reader;
		this.amount = amount;
		this.payed = payed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReaderBean getReader() {
		return reader;
	}

	public void setReader(ReaderBean reader) {
		this.reader = reader;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	
}
