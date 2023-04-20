package org.example.library.controllers;

import org.example.library.dao.BookDAO;
import org.example.library.dao.PersonDAO;
import org.example.library.models.Book;
import org.example.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.show(id));
        Optional<Person> owner = bookDAO.checkPersonId(id);
        if (!owner.isPresent()) {
            model.addAttribute("people", personDAO.index());
        }else {
            model.addAttribute("owner", personDAO.show(owner.get().getPersonId()));
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,  BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign_book")
    public String assignBookForPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.assign(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook (@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
