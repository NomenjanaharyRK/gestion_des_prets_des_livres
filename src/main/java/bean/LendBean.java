package bean;

import java.io.Serializable;
import java.sql.Date;

public class LendBean implements Serializable{

	private static final long serialVersionUID = -1337155240943672794L;
	
	private Long id;
	private BookBean book;
	private ReaderBean reader;
	private Date startDate;
	private Date endDate;
	private boolean finished;
	
	public LendBean() {
		super();
	}

	public LendBean(BookBean book, ReaderBean reader, Date startDate, Date endDate) {
		super();
		this.book = book;
		this.reader = reader;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public ReaderBean getReader() {
		return reader;
	}

	public void setReader(ReaderBean reader) {
		this.reader = reader;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
