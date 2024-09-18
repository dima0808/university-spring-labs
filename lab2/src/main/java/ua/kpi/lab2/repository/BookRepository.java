package ua.kpi.lab2.repository;

import java.util.List;
import java.util.Optional;
import ua.kpi.lab2.entity.Book;

public interface BookRepository {

  List<Book> findAll();

  List<Book> findByTitleContaining(String titlePrefixes);

  List<Book> findByAuthorContaining(String authorPrefixes);

  List<Book> findByKeywordsContaining(String keywords);

  Optional<Book> findById(Long id);

  Book save(Book book);

  void deleteById(Long id);
}
