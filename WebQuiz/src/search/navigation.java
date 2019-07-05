package search;

import java.util.ArrayList;

public class navigation {
	int quizNumber;
	int currentPage;
	
	static final int quizPerPage = 10;
	
	/** constructor:
	 * passed parameters -
	 * quizNumber: number of quizes in the database
	 * currentPage: page where we are now
	 */
	
	public navigation(int quizNumber, int currentPage) {
		this.quizNumber = quizNumber;
		this.currentPage = currentPage;
	}
	
	/**
	 * getPageNumber:
	 * @return number of pages that should be on the web-page
	 */
	
	public int getPageNumber() {
		int result = 0;
		
		int remainder = quizNumber%quizPerPage;
		
		if(remainder==0) {
			result = quizNumber/quizPerPage;
		} else {
			result = (quizNumber-remainder)/quizPerPage + 1;
		}
		
		return result;
	}
	
	/**
	 * PagesToShow:
	 * @return pages that should be on the foot line of the page
	 * For example: currently on the web-page are 180 pages,
	 * but we can't show all of the, there should be some of them,
	 * if we are on the first page, we have to show:
	 * 1, 2, 3, 4, 5, 180.
	 */
	
	public ArrayList<Integer> pagesToShow(){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int pagesNum = getPageNumber();
		
		arr.add(1);
		
		int start = currentPage - 4;
		
		if(start > 1) {
			for(int i=start; i<currentPage; i++) {
				if(!arr.contains(i)) {
					arr.add(i);
				}
			}
		} else {
			for(int i=2; i<currentPage; i++) {
				if(!arr.contains(i)) {
					arr.add(i);
				}
			}
		}
		
		
		int end = currentPage + 4;
		
		if(end <= pagesNum) {
			for(int i=currentPage; i<=end; i++) {
				if(!arr.contains(i)) {
					arr.add(i);
				}
			}
		} else {
			for(int i=currentPage; i<=pagesNum; i++) {
				if(!arr.contains(i)) {
					arr.add(i);
				}
			}
		}
		
		if(!arr.contains(pagesNum)) {
			arr.add(pagesNum);
		}
		
		return arr;
	}
	
}
