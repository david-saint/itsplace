package com.mincoms.book.service;

import java.util.List;

import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;

public interface ReservationService {

	public DataTable findReservationBooks(Paging page);
	public List<BookReservation> findByReservationBooks(int userId);
	public JsonResponse reservation(String isbn);
	public JsonResponse reservationCancel(int id);
}
