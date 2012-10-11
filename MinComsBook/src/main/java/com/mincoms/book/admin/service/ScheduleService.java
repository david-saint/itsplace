package com.mincoms.book.admin.service;

public interface ScheduleService {
	/**
	 * <b>대출정지 자동 해제 스케쥴러 </b> <br />
	 * 
	 * <pre>
	 * 매일 오전 7시  
	 * </pre>
	 * @author 김동훈
	 * @version 2.0
	 * @since 2012. 8. 24
	 * @return JsonResponse
	 * @Exception Exception 
	 * @throws 
	 * @see ssss
	 */
	public void solveRestriction();
	/**
	 * <b>반납 예정일에서 1일이 지난 도서는 대출자에게 메일 발송 </b> <br />
	 * <pre>
	 * 매일 오전 7시  
	 * </pre>
	 * @author 김동훈
	 * @version 2.0
	 * @since 2012. 10. 10
	 * @return JsonResponse
	 * @Exception Exception 
	 * @throws 
	 * @see ssss
	 */
	public void returnBookOverUsers();
}
