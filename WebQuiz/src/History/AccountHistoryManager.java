package History;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountHistoryManager {

	private AccountHistoryManagerDao dao;
	
	public AccountHistoryManager() {
		dao = new AccountHistoryManagerDao();
	}
	
	
	/**
	 * @param accountID 
	 * @param stm
	 * @return 
	 * ArrayList<Strory> - history of account with this accountID
	 * null - for sql error
	 */
	public ArrayList<Story> getAccountHistory(int accountID, Connection con) {
		return dao.getAccountHistory(accountID, con);
	}
	
	/**
	 * @param accountID 
	 * @param stm
	 * @return 
	 * double average - average score of account with this accountID
	 * -1 - for sql error
	 */
	public double getAccountAverageScore(int accountID, Connection con) {
		return dao.getAccountAverageScore(accountID, con);
	}
	
	/**
	 * 
	 * @param accountID
	 * @param stm
	 * @return
	 * int quizTakenCount - quizes count which account took
	 * -1 - for sql error
	 */
	public int getAccountQuizTakenCount(int accountID, Connection con) {
		return dao.getAccountQuizTakenCount(accountID, con);
	}
}
