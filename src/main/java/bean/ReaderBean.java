package bean;

import java.io.Serializable;

public class ReaderBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7867817488842522598L;
	
	private Long id;
	private String name;
	private String lastname;
	private String email;
	private String phone;
	private String address;
	private String illustration;
	private int nbPretActuel;
	private String cin;
	
	public ReaderBean() {
		super();
	}

	public ReaderBean(String name, String lastname, String email, String phone, String address, String illustration, String cin) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.illustration = illustration;
		this.cin = cin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIllustration() {
		return illustration;
	}

	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}

	public int getNbPretActuel() {
		return nbPretActuel;
	}

	public void setNbPretActuel(int nbPretActuel) {
		this.nbPretActuel = nbPretActuel;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}
	
}
