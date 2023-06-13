package bean;

import java.io.Serializable;

/**
 * @author hery
 *
 */
public class BookBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7788488795276235378L;
	
	private Long id;
	private String title;
	private String description;
	private String author;
	private String illustration;
	private String publishedAt;
	private boolean status;
	private int nbPret;
	
	public BookBean() {
		super();
		this.status = true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getIllustration() {
		return illustration;
	}
	
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}
	
	public String getPublishedAt() {
		return publishedAt;
	}
	
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getNbPret() {
		return nbPret;
	}

	public void setNbPret(int nbPret) {
		this.nbPret = nbPret;
	}
}
