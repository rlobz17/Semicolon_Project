package users;

import java.sql.Date;

public class User {
	private Date registrationDate;
	private String mail;
	private String userName;
	private String passwordHash;
	private String imgUrl;
	private int quizesTaken;
	
	public User(Date registrationDate, String mail, String userName, String password, String imgUrl, int quizesTaken) {
		this.registrationDate = registrationDate;
		this.mail = mail;
		this.passwordHash = password;
		this.quizesTaken = quizesTaken;
		this.imgUrl = imgUrl;
		this.userName = userName;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return the quizesTaken
	 */
	public int getQuizesTaken() {
		return quizesTaken;
	}

	/**
	 * @param quizesTaken the quizesTaken to set
	 */
	public void setQuizesTaken(int quizesTaken) {
		this.quizesTaken = quizesTaken;
	}
}
