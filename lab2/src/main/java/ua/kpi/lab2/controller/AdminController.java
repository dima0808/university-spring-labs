package ua.kpi.lab2.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.lab2.dto.BookDto;
import ua.kpi.lab2.dto.SearchRequest;
import ua.kpi.lab2.entity.Book;
import ua.kpi.lab2.service.BookService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
  private final BookService bookService;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("books", bookService.findAll());
    model.addAttribute("searchRequest", new SearchRequest());
    model.addAttribute("bookToAdd", new BookDto());
    return "admin";
  }

  @PostMapping("/searchBooks")
  public String searchBooks(@ModelAttribute SearchRequest searchRequest, Model model) {
    List<Book> books = bookService.findAllBySearchRequest(searchRequest);
    model.addAttribute("books", books);
    model.addAttribute("searchRequest", new SearchRequest());
    model.addAttribute("bookToAdd", new BookDto());
    return "admin";
  }

  @PostMapping("/addBook")
  public String addBook(@ModelAttribute BookDto bookDto) {
    bookService.save(bookDto);
    return "redirect:/admin";
  }

  @PostMapping("/deleteBook/{id}")
  public String deleteBook(@PathVariable Long id) {
    bookService.deleteById(id);
    return "redirect:/admin";
  }

  @GetMapping("/changeBook")
  public String changeBookPage(@RequestParam Long id, Model model) {
    Book book = bookService.findById(id);
    model.addAttribute("bookToChange", BookDto.builder()
        .title(book.getTitle())
        .author(book.getAuthor())
        .year(book.getYear())
        .keywords(String.join(",", book.getKeywords()))
        .build());
    return "book-change";
  }

  @PostMapping("/changeBook/{id}")
  public String changeBook(@PathVariable Long id, @ModelAttribute BookDto bookDto) {
    bookService.save(id, bookDto);
    return "redirect:/admin";
  }
}