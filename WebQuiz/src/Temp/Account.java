package Temp;



import java.util.ArrayList;
import java.util.Date;

public class Account {
	//ivars
	private int userID;
	private int quizesTaken;
	private int quizesCreted;

	private Date registrationDate;
	private String mail;
	private String firstName;
	private String lastName;
	private String userName;
	private String imgUrl;
	
	private ArrayList<String> friendsList;
	
	public Account(int userID,Date registrationDate, String mail, String userName, String imgUrl, String firstName, String lastName) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
		this.mail = mail;
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
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}



	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}


	/**
	 * @return the quizesTaken
	 */
	public int getQuizesTaken() {
		return quizesTaken;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * @return the quizesCreted
	 */
	public int getQuizesCreted() {
		return quizesCreted;
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
	
	@Override
	public String toString() {
		return "ID " + userID +" | RegDate "+registrationDate + " | Mail " + mail + 
				" | Username " + userName +" | ImgUrl "+ imgUrl + " | FirstName " + firstName + " | LastName " + lastName;
	}

}
