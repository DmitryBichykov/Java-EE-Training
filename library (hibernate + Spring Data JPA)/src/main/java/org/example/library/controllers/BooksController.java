package org.example.library.controllers;

import com.sun.net.httpserver.Request;
import jdk.internal.joptsimple.util.RegexMatcher;
import org.example.library.models.Book;
import org.example.library.models.Person;
import org.example.library.services.BooksService;
import org.example.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }


    @GetMapping
    public String index(Model model,
                        @RequestParam(value = "page", required = false) @Min(0) Integer page,
                        @RequestParam(value = "books_per_page", required = false) @Min(1) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {
        if (page != null & booksPerPage != null & sortByYear) {
            model.addAttribute("books", booksService.findAll(page, booksPerPage, "year"));
        } else if (page != null & booksPerPage != null) {
            model.addAttribute("books", booksService.findAll(page, booksPerPage));
        } else if (sortByYear) {
            model.addAttribute("books", booksService.findAll("year"));
        } else {
            model.addAttribute("books", booksService.findAll());
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = booksService.findOne(id);
        model.addAttribute("book", book);
        Person owner = book.getOwner();
        if (owner == null) {
            model.addAttribute("people", peopleService.findAll());
        } else {
            model.addAttribute("owner", book.getOwner());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign_book")
    public String assignBookForPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.assign(booksService.findOne(id), peopleService.findOne(person.getId()));
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        booksService.release(booksService.findOne(id));
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "s", required = false) String searchString,
                             Model model) {
        if (searchString != null) {
            boolean bookNotFound = false;
            List<Book> books = booksService.searchBook(searchString);
            model.addAttribute("searchResults", books);
            if (books.isEmpty()) {
                bookNotFound = true;
            }
            model.addAttribute("nextSearch", bookNotFound);
        }
        return "books/search";
    }
}
