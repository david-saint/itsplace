package com.mincoms.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mincoms.book.admin.controller.AdminController;
import com.mincoms.book.admin.repository.ExceptionSpecs;
import com.mincoms.book.admin.service.RestrictionService;
import com.mincoms.book.dao.BookDao;
import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;

import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.QBookCategory;
import com.mincoms.book.domain.QBookCategoryRoot;
import com.mincoms.book.domain.QBookInfo;
import com.mincoms.book.domain.QBookRental;
import com.mincoms.book.domain.QBookReservation;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.domain.dto.DtoBookInfo;
import com.mincoms.book.domain.vo.VoBookInfo;
import com.mincoms.book.repository.BookRepository;
import com.mincoms.book.repository.BookSpecs;
import com.mincoms.book.repository.RentalRepository;
import com.mincoms.book.repository.ReservationRepository;
import com.mincoms.book.security.SignedUser;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateSubQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPASubQuery;
import com.mysema.query.types.ConstructorExpression;

@Transactional
@Service
public class BookServiceImpl implements BookService {
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	@Autowired
	BookRepository bookRepo;
	@Autowired 
	RentalRepository rentalRepo;
	@Autowired 
	ReservationRepository reservationRepo;
	@Autowired
	RestrictionService restrictionService;
	@Autowired
	BookDao bookDao;
	
	@Autowired
	MessageSource messageSource;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public BookInfo save(BookInfo book) throws DataAccessException{
		BookInfo saved = bookRepo.save(book);
		
		return saved;
	}
	
	@Override
	public DataTable<BookInfo> findBookList(Paging paging,String  isDeleted) {
		
          DataTable<BookInfo> table = new DataTable<BookInfo>(paging);
          //Page<BookInfo> books = (Page<BookInfo>)bookRepo.findByTitleContainingOrAuthors(paging.getsSearch(),paging.getsSearch(), paging.getPageable());
          
          Specifications<BookInfo> spec = null;
       
          spec = Specifications.where(BookSpecs.AuthorsContaining(paging.getsSearch()) );
          spec = spec.or(BookSpecs.TitleContaining(paging.getsSearch()));
          spec = spec.and(BookSpecs.IsDeleted(isDeleted));
          
    		
    		
          Page<BookInfo> books = bookRepo.findAll(spec, paging.getPageable());
          for(BookInfo book:books){
        	  logger.info(book.toString());
          }
						 
          
		  table.setRows(books.getContent()); 
		  
		  table.setiTotalDisplayRecords(books.getTotalElements());
		  logger.info("결과:{}",table.getiDisplayLength());
		  logger.info("결과:{}",table.getiTotalRecords());
		  return table;
	}

	@Override
	public BookInfo findByIsbn(String isbn) {
		
		return bookRepo.findByIsbn(isbn);
	}

	@Override
	public JsonResponse isRental(String isbn, UserInfo userInfo) {
		 
		JsonResponse json =  restrictionService.isRestriction(userInfo);		
		if(json.getStatus() == json.SUCCEESS){
			json.setFail();
			logger.info("제재중입니다");
			return json;
		}
		BookInfo bookInfo = bookRepo.findByIsbn(isbn);
		if( bookInfo == null){
			json.setResult(messageSource.getMessage("not.register.book",null, Locale.getDefault()));
			json.setFail();
			return json;
		}
		
		int bookTotalCount = bookInfo.getCount();
		
		List<BookRental> rentaledBooks= rentalRepo.findByBookInfoAndReturnDateIsNull(bookInfo);
		int retaledBookCount = rentaledBooks.size();
		
		List<BookReservation> reservationBooks = reservationRepo.findByReservationBook(bookInfo.getIsbn());
		int reservationBookCount = reservationBooks.size();
		
		logger.debug("도서총수량:{}",bookTotalCount);
		logger.debug("대출중수량:{}",retaledBookCount);
		logger.debug("예약중수량:{}",reservationBookCount);
		
		if(bookTotalCount>retaledBookCount+reservationBookCount){
			 json.setResult(bookInfo);
			 json.setSuccess();
		}else{
			json.setResult(messageSource.getMessage("can.not.find.rental.book",null, Locale.getDefault()));
			json.setFail();
		}
		return json;
	}
	@Override
	public DataTable getReservationGroupByBooks(Paging paging) {
		DataTable<DtoBookInfo> table = new DataTable<DtoBookInfo>(paging);
		
		List<DtoBookInfo> results = new ArrayList();
		results = bookDao.getBookInfoReservationList(paging.getParameter());
					
		table.setRows(results); 
		table.setiTotalDisplayRecords(bookDao.getBookInfoReservationListCount(paging.getParameter()));
					
	
		return table;
		
	}
	/*@Override
	public DataTable findReservationGroupByBooks(Paging paging) {
		DataTable<DtoBookInfo> table = new DataTable<DtoBookInfo>(paging);
		
		QBookInfo bookInfo = QBookInfo.bookInfo;
		QBookReservation  bookReservation = QBookReservation.bookReservation;
		QBookRental  bookRental = QBookRental.bookRental;
		QBookCategory  bookCategory = QBookCategory.bookCategory;
		QBookCategoryRoot  bookCategoryRoot = QBookCategoryRoot.bookCategoryRoot;
		int bookCategoryRootCode=0;
		int bookCategoryCode=0;
		List<DtoBookInfo> results = new ArrayList();
		JPASubQuery sub = new JPASubQuery();
		HibernateSubQuery subQuery = new HibernateSubQuery();
		subQuery.from(bookRental).where(bookRental.bookInfo.isbn.eq(bookInfo.isbn)).groupBy(bookRental.bookInfo);
		try{
			JPQLQuery query = new JPAQuery(em);
			query = query.from(bookInfo);
			//query = query.innerJoin(subQuery.list(bookRental.bookInfo.authors));
			query = query.leftJoin(bookInfo.bookReservation, bookReservation)
						 
						
						 .leftJoin(bookInfo.bookRentals, bookRental)
						 .join(bookInfo.bookCategory, bookCategory)
						 .join(bookCategory.bookCategoryRoot,bookCategoryRoot)						 
						 .where(bookReservation.isCanceled.eq(false).and(bookReservation.bookRental.isNull()))
						 .groupBy(bookInfo.thumbnail,
								   bookCategory.bookCategoryRoot.name,
								   bookInfo.bookCategory.name,
								   bookInfo.isbn,
								   bookInfo.title,
								   bookInfo.authors,
								   bookInfo.count,
								   bookInfo.publisher,
								   bookInfo.publishedDate)
						 .limit(paging.getiDisplayLength())
						 .offset(paging.getCurrentPage()*paging.getiDisplayLength());
					
					if(paging.getParameter().get("bookCategoryRoot") !=null){
						bookCategoryRootCode = (Integer) paging.getParameter().get("bookCategoryRoot");
						query = query.where(bookCategory.bookCategoryRoot.id.eq(bookCategoryRootCode));	
					}
					if(paging.getParameter().get("bookCategory") !=null){
						bookCategoryCode = (Integer) paging.getParameter().get("bookCategory");
						query = query.where(bookCategory.id.eq(bookCategoryCode));	
					}
				    results = query.list(ConstructorExpression.create(DtoBookInfo.class,
														   bookInfo.thumbnail,
														   bookCategory.bookCategoryRoot.name,
														   bookInfo.bookCategory.name,
														   bookInfo.isbn,
														   bookInfo.title,
														   bookInfo.authors,
														   bookInfo.count,
														   bookInfo.publisher,
														   bookInfo.publishedDate,
														   bookInfo.count(),
														   bookRental.count())
															);
					for(DtoBookInfo rows : results){
						rows.toString();

					}
					
					table.setRows(results); 
					logger.debug("토탈:{}",query.countDistinct());
					table.setiTotalDisplayRecords(query.count());
					
		}catch (NoResultException e) {
			table.setRows(results);
			table.setiTotalDisplayRecords(0);
		}
		
		return table;
		
	}*/


	@Override
	public List<BookInfo> findAll() {
		return bookRepo.findAll();
	}
	
/*	@Override
	public DataTable findReservationGroupByBooks(Paging paging) {
		DataTable<VoBookInfo> table = new DataTable<VoBookInfo>(paging);
		
		QBookInfo bookInfo = QBookInfo.bookInfo;
		QBookReservation  bookReservation = QBookReservation.bookReservation;
		QBookCategory  bookCategory = QBookCategory.bookCategory;
		QBookCategoryRoot  bookCategoryRoot = QBookCategoryRoot.bookCategoryRoot;
		
		JPQLQuery query = new JPAQuery(em);
		List<Object[]> rs = query.from(bookInfo)
								 .leftJoin(bookInfo.bookReservation, bookReservation)
								 .join(bookInfo.bookCategory,bookCategory)
								 .join(bookCategory.bookCategoryRoot,bookCategoryRoot)
								 .groupBy(bookInfo.thumbnail,
										   bookCategory.bookCategoryRoot.name,
										   bookInfo.bookCategory.name,
										   bookInfo.isbn,
										   bookInfo.title,
										   bookInfo.authors,
										   bookInfo.count,
										   bookInfo.publisher,
										   bookInfo.publishedDate)
								 .limit(paging.getiDisplayLength())
								 .offset(paging.getCurrentPage()*paging.getiDisplayLength())
								 .list(bookInfo.thumbnail,
									   bookCategory.bookCategoryRoot.name,
									   bookInfo.bookCategory.name,
									   bookInfo.isbn,
									   bookInfo.title,
									   bookInfo.authors,
									   bookInfo.count,
									   bookInfo.publisher,
									   bookInfo.publishedDate,
									   bookInfo.count());
		
		List<VoBookInfo> voList =  new ArrayList();
		for(Object[] rows : rs){
			VoBookInfo vo = new VoBookInfo();
			BookInfo book = new BookInfo();
			book.setThumbnail(rows[0].toString());
			book.setIsbn(rows[3].toString());
			book.setTitle(rows[4].toString());
			book.setAuthors(rows[5].toString());
			book.setCount((Integer) rows[6]);
			book.setPublisher(rows[7]+"");
			book.setPublishedDate(rows[8].toString());
			vo.setBookInfo(book);
			vo.setReservationCount((Long)rows[9]);
			vo.setCategoryRoot(rows[1].toString());
			vo.setCategory(rows[2].toString());

			voList.add(vo);
		}
		 
		table.setRows(voList); 
		logger.debug("토탈:{}",query.countDistinct());
		table.setiTotalDisplayRecords(query.count());
		return table;
	}*/
	
}
