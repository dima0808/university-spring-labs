package ua.kpi.lab2.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kpi.lab2.dto.BookDto;
import ua.kpi.lab2.dto.SearchRequest;
import ua.kpi.lab2.entity.Book;
import ua.kpi.lab2.repository.BookRepository;
import ua.kpi.lab2.service.BookService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public List<Book> findAllBySearchRequest(SearchRequest searchRequest) {
    Set<Book> books = new HashSet<>(
        bookRepository.findByTitleContaining(searchRequest.getSearchRequestPrefixes()));
    if (searchRequest.isSearchByAuthor()) {
      books.addAll(bookRepository.findByAuthorContaining(searchRequest.getSearchRequestPrefixes()));
    }
    if (searchRequest.isSearchByKeywords()) {
      books.addAll(
          bookRepository.findByKeywordsContaining(searchRequest.getSearchRequestPrefixes()));
    }
    return books.stream().toList();
  }

  @Override
  public Book findById(Long id) {
    return bookRepository.findById(id).orElse(null);
  }

  @Override
  public Book save(BookDto bookDto) {
    Book book = Book.builder()
        .title(bookDto.getTitle())
        .author(bookDto.getAuthor())
        .year(bookDto.getYear())
        .keywords(List.of(bookDto.getKeywords().split(",")))
        .build();
    return bookRepository.save(book);
  }

  @Override
  public Book save(Long id, BookDto bookDto) {
    Book book = Book.builder()
        .id(id)
        .title(bookDto.getTitle())
        .author(bookDto.getAuthor())
        .year(bookDto.getYear())
        .keywords(List.of(bookDto.getKeywords().split(",")))
        .build();
    return bookRepository.save(book);
  }

  @Override
  public void deleteById(Long id) {
    bookRepository.deleteById(id);
  }
}