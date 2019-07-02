package History;

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
	public ArrayList<Story> getAccountHistory(int accountID, Statement stm) {
	
		return null;
	}
}
