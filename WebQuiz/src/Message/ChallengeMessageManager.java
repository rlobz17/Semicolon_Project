package Message;

import java.sql.Connection;

public class ChallengeMessageManager {

	private ChallengeMessageManagerDao dao;
	
	public ChallengeMessageManager() {
		dao = new ChallengeMessageManagerDao();
	}
	
	/**
	 * @return
	 * ChallengeMessage - message with id - messageID
	 * null - if sql Error
	 */
	public ChallengeMessage getChallengeMessage(int messageID, Connection con) {
		return dao.getChallengeMessage(messageID, con);
	}
	
	/**
	 * @return
	 *  0 - message was not added
	 *  messageID - if message was added successfully
	 * -1 - if sql Error
	 */
	public int addChallengeMessage(ChallengeMessage newChallengeMessage, Connection con) {
		int res = dao.addChallengeMessage(newChallengeMessage, con);
		return res;	
	}
}
