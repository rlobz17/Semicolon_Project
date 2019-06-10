package users;

import java.sql.Date;
import java.util.ArrayList;

public class User {
	//ivars
	private int userID;
	private int quizesTaken;
	private int quizesCreted;

	private Date registrationDate;
	private String mail;
	private String firstName;
	private String lastName;
	private String userName;
	private String passwordHash;
	private String imgUrl;
	
	private ArrayList<String> friendsList;
	
	public User(int userID,Date registrationDate, String mail, String userName, String passwordHash, String imgUrl,String lastName,String firstName) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
		this.mail = mail;
		this.passwordHash = passwordHash;
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
		return passwordHash;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String passwordHash) {
		this.passwordHash = passwordHash;
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

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the quizesCreted
	 */
	public int getQuizesCreted() {
		return quizesCreted;
	}

	/**
	 * @param quizesCreted the quizesCreted to set
	 */
	public void setQuizesCreted(int quizesCreted) {
		this.quizesCreted = quizesCreted;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @return the friendsList
	 */
	public ArrayList<String> getFriendsList() {
		return friendsList;
	}
	
	/**
	 * @param string name of the friend
	 * adds givven name to the friend list
	 * */
	public void addFriend(String name) {
		this.friendsList.add(name);
	}
	
	/**
	 * @param string name of the friend
	 * checks if given name is in the friend list
	 * */
	public boolean isFriendsWith(String name) {
		return this.friendsList.contains(name);
	}

	/**
	 * @param friendsList the friendsList to set
	 */
	public void setFriendsList(ArrayList<String> friendsList) {
		this.friendsList = friendsList;
	}

}
