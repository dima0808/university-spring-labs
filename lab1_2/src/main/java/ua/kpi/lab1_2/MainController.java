package ua.kpi.lab1_2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping("/")
  public String helloWorld() {
    return "Hello, world!";
  }
}
