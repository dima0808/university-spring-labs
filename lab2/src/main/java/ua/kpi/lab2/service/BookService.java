package ua.kpi.lab2.service;

import ua.kpi.lab2.dto.BookDto;
import ua.kpi.lab2.dto.SearchRequest;
import ua.kpi.lab2.entity.Book;
import java.util.List;

public interface BookService {

  List<Book> findAll();

  List<Book> findAllBySearchRequest(SearchRequest searchRequest);

  Book findById(Long id);

  Book save(BookDto bookDto);

  Book save(Long id, BookDto bookDto);

  void deleteById(Long id);
}