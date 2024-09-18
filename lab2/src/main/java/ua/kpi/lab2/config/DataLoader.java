package ua.kpi.lab2.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.kpi.lab2.dto.BookDto;
import ua.kpi.lab2.service.BookService;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

  private final BookService bookService;

  @Override
  public void run(String... args) throws Exception {
    List<BookDto> books = List.of(
        new BookDto("To Kill a Mockingbird", "Harper Lee", 1960, "classic,fiction"),
        new BookDto("1984", "George Orwell", 1949, "dystopian,fiction"),
        new BookDto("Pride and Prejudice", "Jane Austen", 1813, "romance,classic"),
        new BookDto("The Great Gatsby", "F. Scott Fitzgerald", 1925, "classic,fiction"),
        new BookDto("Moby Dick", "Herman Melville", 1851, "adventure,classic"),
        new BookDto("War and Peace", "Leo Tolstoy", 1869, "historical,classic"),
        new BookDto("The Catcher in the Rye", "J.D. Salinger", 1951, "classic,fiction"),
        new BookDto("The Hobbit", "J.R.R. Tolkien", 1937, "fantasy,adventure"),
        new BookDto("Crime and Punishment", "Fyodor Dostoevsky", 1866, "classic,philosophical"),
        new BookDto("The Brothers Karamazov", "Fyodor Dostoevsky", 1880, "classic,philosophical"),
        new BookDto("Brave New World", "Aldous Huxley", 1932, "dystopian,fiction"),
        new BookDto("The Lord of the Rings", "J.R.R. Tolkien", 1954, "fantasy,adventure"),
        new BookDto("Jane Eyre", "Charlotte Bronte", 1847, "romance,classic"),
        new BookDto("Wuthering Heights", "Emily Bronte", 1847, "romance,classic"),
        new BookDto("The Divine Comedy", "Dante Alighieri", 1320, "classic,poetry")
    );

    books.forEach(bookService::save);
  }
}