package com.mincoms.book.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.QBookCategory;
import com.mincoms.book.domain.QBookCategoryRoot;
import com.mincoms.book.domain.QBookCategorySub;
import com.mincoms.book.domain.QBookInfo;
import com.mincoms.book.domain.QBookReservation;
import com.mincoms.book.repository.BookRepository;
import com.mincoms.test.TestApplicationContext;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

public class BookServiceImplTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImplTest.class);
	
	@Autowired
	BookService bookService;

	@Autowired
	BookRepository bookRepo;
	@PersistenceContext
	private EntityManager em;
	
	private BookInfo book;
	@Before
	public void setUp() throws Exception {
		
	}

	@Ignore
	public void testSave() {
		bookService.save(book);
	}
	@Ignore
	public void testList() {
		String columns[] = { "title","isbn" };

		Paging page = new Paging(columns,0,10,1,"desc","");
		//DataTable<Book> table =  bookService.getBookList(page);
		Page<BookInfo> books = (Page<BookInfo>)bookRepo.findAll(new PageRequest(page.getiDisplayStart(),page.getiDisplayLength()));
		logger.info("number:{}",books.getNumber());
		logger.info("numbere:{}",books.getNumberOfElements());
		logger.info("numbertotalpage:{}",books.getTotalPages());
		
		//logger.info("로우수{}", table.getiDisplayLength());
		
		
   }
	@Test
	public void test22(){
		//TypedQuery<VoBookInfo> query = em.createQuery("select c from BookInfo c", BookInfo.class);

		//query.setFirstResult(page * pageSize);
		//query.setMaxResults(pageSize);

		//return query.getResultList();
	}
	@Ignore
	public void test2(){
		QBookInfo bookInfo = QBookInfo.bookInfo;
		QBookReservation  bookReservation = QBookReservation.bookReservation;
		QBookCategory  bookCategory = QBookCategory.bookCategory;
		QBookCategorySub  bookCategorySub = QBookCategorySub.bookCategorySub;
		QBookCategoryRoot  bookCategoryRoot = QBookCategoryRoot.bookCategoryRoot;
		
		String columns[]={"id"};
		Paging page = new Paging(columns,0,10,0,"desc","");
		
		logger.info("가져올호우수"+(page.getCurrentPage()*page.getiDisplayLength()));
		JPQLQuery query = new JPAQuery(em);
		List<Object[]> rs = query.from(bookInfo)
								 .leftJoin(bookInfo.bookReservation, bookReservation)
								 .join(bookInfo.bookCategory,bookCategory)
								 .join(bookCategory.bookCategorySub,bookCategorySub)
								 .groupBy(bookInfo.thumbnail,
										   bookCategory.bookCategorySub.name,
										   bookInfo.bookCategory.name,
										   bookInfo.title,
										   bookInfo.authors,
										   bookInfo.count,
										   bookInfo.publisher,
										   bookInfo.publishedDate)
								 .limit(page.getiDisplayLength())
								 .offset(page.getCurrentPage()*page.getiDisplayLength())
								 .list(bookInfo.thumbnail,
									   bookCategory.bookCategorySub.name,
									   bookInfo.bookCategory.name,
									   bookInfo.title,
									   bookInfo.authors,
									   bookInfo.count,
									   bookInfo.publisher,
									   bookInfo.publishedDate,									  
									   bookInfo.count());
		for(Object[] rows : rs){
		/*	VoBookInfo voBookInfo = new VoBookInfo();
			BookInfo book = new BookInfo();
			book.setThumbnail(rows[0].toString());
			book.setIsbn(rows[0].toString());
			book.setTitle(rows[1].toString());
			
			voBookInfo.setBookInfo(book);*/
		
			
			logger.info(rows[0].toString() + rows[1].toString()+ rows[2].toString());
			
		}
		
		logger.info("토탈카운트:{}",query.count());
		
		// DataTable<BookReservation> table = new DataTable<BookReservation>(null);
		// table.setRows(rs);
	}
}
