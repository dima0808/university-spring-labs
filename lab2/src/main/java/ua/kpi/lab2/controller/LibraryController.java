package ua.kpi.lab2.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.kpi.lab2.dto.SearchRequest;
import ua.kpi.lab2.entity.Book;
import ua.kpi.lab2.service.BookService;

@Controller
@RequiredArgsConstructor
public class LibraryController {
  private final BookService bookService;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("books", bookService.findAll());
    model.addAttribute("searchRequest", new SearchRequest());
    return "index";
  }

  @PostMapping("/searchBooks")
  public String searchBooks(@ModelAttribute SearchRequest searchRequest, Model model) {
    List<Book> books = bookService.findAllBySearchRequest(searchRequest);
    model.addAttribute("books", books);
    model.addAttribute("searchRequest", searchRequest);
    return "index";
  }
}