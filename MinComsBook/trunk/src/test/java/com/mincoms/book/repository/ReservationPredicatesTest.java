package com.mincoms.book.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.QBookInfo;
import com.mincoms.book.domain.QBookReservation;
import com.mincoms.book.domain.vo.VoBookInfo;
import com.mincoms.test.TestApplicationContext;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.Predicate;


public class ReservationPredicatesTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(ReservationPredicatesTest.class);
	
	@Autowired
	ReservationRepository repo;
	@PersistenceContext
	private EntityManager em;
	
	@Ignore
	public void test() {
		Predicate predicate = ReservationPredicates.lastNameIsLike();
		String columns[]={"id"};
		Paging page = new Paging(columns,0,10,0,"desc","");
		
		Iterable<BookReservation> list = repo.findAll(ReservationPredicates.lastNameIsLike(), page.getPageable());
	
		for(BookReservation b : list){
			logger.info(b.getId()+"");
		}
	}
	@Test
	public void test2(){
		/*QBookReservation  bookReservation = QBookReservation.bookReservation;
		QBookInfo  bookInfo = QBookInfo.bookInfo;
		String columns[]={"title","authors"};
		Paging page = new Paging(columns,1,10,0,"desc","");
		logger.info("현재페이지{}",page.getCurrentPage());
		JPQLQuery query = new JPAQuery(em);
		List<Testvo> rs = query.from(bookReservation)							
							 .groupBy(bookReservation.id, bookReservation.bookInfo.title)
							.limit(10) //가져오는 행
							.offset(0) //스팁 0 10 20
							.orderBy()
							.list(ConstructorExpression.create(Testvo.class, bookReservation.id, bookReservation.bookInfo.title));
		
		for(Testvo rows : rs){
			VoBookInfo  voBookInfo = new VoBookInfo();
			//BookInfo bookInfo = new BookInfo();
			//bookInfo.setTitle(rows[1].toString());
			//bookInfo.setIsbn(rows[0].toString());
			logger.info(rows.toString());
			//voBookInfo.setBookInfo(bookInfo);
			//logger.info(rows[0].toString() + rows[1].toString());
			
		}
		
		logger.info("토탈카운트:{}",query.countDistinct());
		
		// DataTable<BookReservation> table = new DataTable<BookReservation>(null);
		// table.setRows(rs);
*/	}
	 @Ignore
	public void test4(){
		QBookReservation  bookReservation = QBookReservation.bookReservation;
		Predicate predicate;
		JPQLQuery query = new JPAQuery(em);
		List<BookInfo> rs = query.from(bookReservation).limit(1)
								 .list(bookReservation.bookInfo);
		System.out.println("=========="+query.count());
		for(BookInfo b : rs){
		
			logger.info(b.getTitle());
		}
		
	
	}
	
	@Ignore
	public void test3(){
		TypedQuery<BookReservation> query = em.createQuery("select c.bookInfo from BookReservation c group by c.bookInfo", BookReservation.class);

	//	query.setParameter(1, lastname);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<BookReservation> bs = query.getResultList();
		for(BookReservation b : bs){
			logger.info(b.getBookInfo().getTitle());
		}
		
	}

}
