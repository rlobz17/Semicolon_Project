package Temp;



import java.util.ArrayList;
import java.util.Date;

import account.Message;

public class Account {
	//ivars
	private int userID;
	private int quizesTaken;
	private int quizesCreated;
	
	private double quizesAverage;
	
	private boolean isAdmin;

	private Date registrationDate;
	private String mail;
	private String firstName;
	private String lastName;
	private String userName;
	private String imgUrl;
	
	
	private ArrayList<String> friendsList;
	private ArrayList<Message> notifications;
	
	public Account(int userID, Date registrationDate, String mail, String userName, String imgUrl, String firstName, String lastName, int quizesCreated, int quizesTaken,double quizesAverage, boolean isAdmin) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
		this.mail = mail;
		this.imgUrl = imgUrl;
		this.userName = userName;
		this.quizesCreated = quizesCreated;
		this.quizesTaken = quizesTaken;
		this.quizesAverage = quizesAverage;
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}
	
	/**
	 * set the registrationDate
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
	 * set the mail
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
	 * set the userName
	 */	
	public void setUserName(String userName) {
        this.userName = userName;
    }

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	
	/**
	 * set the imgUrl
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set the firstName
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
	 * set the lastName
	 */	
	public void setLastName(String lastName) {
        this.lastName = lastName;
    }


	/**
	 * @return the quizesCreted
	 */
	public int getQuizesCreated() {
		return quizesCreated;
	}
	
	/**
	 * @return the quizesAverage
	 */
	public double getQuizesAverage() {
		return quizesAverage;
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
	 * set the friendsList
	 */	
	public void setFriendsList(ArrayList<String> friendsList) {
        this.friendsList = friendsList;
    }
	
	
	/**
	 * @param string name of the friend
	 * checks if given name is in the friend list
	 * */
	public boolean isFriendsWith(String name) {
		return this.friendsList.contains(name);
	}
	
	

	/**
	 * @return the notifications
	 * 
	 */
	public ArrayList<Message> getNotifications() {
		return notifications;
	}
	
	/**
	 * set the friendsList
	 */	
	public void setNotifications(ArrayList<Message> notifications) {
        this.notifications = notifications;
    }


	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	
	public static String whitespaceChange(String username) {
		String withoutSpace = "";
		
		for(int i=0; i<username.length(); i++) {
			char ch = username.charAt(i);
			
			if(ch!='_') {
				withoutSpace += ch;
			} else {
				withoutSpace += ' ';
			}
		}
		
		return withoutSpace;
	}
	
	
	@Override
	public String toString() {
		return "ID " + userID +" | RegDate "+registrationDate + " | Mail " + mail + 
				" | Username " + userName +" | ImgUrl "+ imgUrl + " | FirstName " + firstName + " | LastName " + lastName +
				" | quizesCreated " + quizesCreated + " | quizesTaken " + quizesTaken + " | quizesAverage " + quizesAverage +" | isAdmin " + isAdmin;
	}

}
