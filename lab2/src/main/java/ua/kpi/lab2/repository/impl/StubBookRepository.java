package ua.kpi.lab2.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import ua.kpi.lab2.entity.Book;
import ua.kpi.lab2.repository.BookRepository;

@Repository
public class StubBookRepository implements BookRepository {

  private final List<Book> books = new ArrayList<>();

  @Override
  public List<Book> findAll() {
    return books;
  }

  @Override
  public List<Book> findByTitleContaining(String titlePrefixes) {
    List<String> titlePrefixesList = List.of(titlePrefixes.trim().split("[,.; ]"));
    List<Book> foundBooks = new ArrayList<>();
    if (!titlePrefixesList.isEmpty()) {
      for (Book book : books) {
        boolean matches = titlePrefixesList.stream()
            .anyMatch(
                prefix -> book.getTitle().toLowerCase().contains(prefix.toLowerCase().trim()));
        if (matches) {
          foundBooks.add(book);
        }
      }
    }
    return foundBooks;
  }

  @Override
  public List<Book> findByAuthorContaining(String authorPrefixes) {
    List<String> authorPrefixesList = List.of(authorPrefixes.trim().split("[,.; ]"));
    List<Book> foundBooks = new ArrayList<>();
    if (!authorPrefixesList.isEmpty()) {
      for (Book book : books) {
        boolean matches = authorPrefixesList.stream()
            .anyMatch(
                prefix -> book.getAuthor().toLowerCase().contains(prefix.toLowerCase().trim()));
        if (matches) {
          foundBooks.add(book);
        }
      }
    }
    return foundBooks;
  }

  @Override
  public List<Book> findByKeywordsContaining(String keywords) {
    List<String> keywordsList = List.of(keywords.trim().split("[,.; ]"));
    List<Book> foundBooks = new ArrayList<>();
    if (!keywordsList.isEmpty()) {
      for (Book book : books) {
        boolean matches = keywordsList.stream()
            .anyMatch(prefix -> book.getKeywords().stream()
                .anyMatch(keyword -> keyword.toLowerCase().contains(prefix.toLowerCase().trim())));
        if (matches) {
          foundBooks.add(book);
        }
      }
    }
    return foundBooks;
  }

  @Override
  public Optional<Book> findById(Long id) {
    return books.stream().filter(book -> book.getId().equals(id)).findFirst();
  }

  @Override
  public Book save(Book book) {
    if (book.getId() == null) {
      book.setId((long) ((Math.random() * (Long.MAX_VALUE - 1_000_000_000_000_000_000L)) +
          1_000_000_000_000_000_000L));
    } else {
      Optional<Book> repoBook = findById(book.getId());
      repoBook.ifPresent(books::remove);
    }
    books.add(book);
    return book;
  }

  @Override
  public void deleteById(Long id) {
    books.removeIf(book -> book.getId().equals(id));
  }
}