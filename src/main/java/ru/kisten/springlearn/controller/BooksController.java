package ru.kisten.springlearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kisten.springlearn.dao.BookDAO;
import ru.kisten.springlearn.dao.ReaderDAO;
import ru.kisten.springlearn.models.Book;
import ru.kisten.springlearn.models.Reader;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final ReaderDAO readerDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, ReaderDAO readerDAO) {
        this.bookDAO = bookDAO;
        this.readerDAO = readerDAO;
    }

    @GetMapping
    public String showBooks(Model model){

        model.addAttribute("books", bookDAO.showAll());

        return "books/show";

    }

    @GetMapping("/{id}")
    public String showBookByIndex(@PathVariable("id") int id, Model model){

        Book book = bookDAO.showByIndex(id);

        int readerId = book.getReaderID();

        Reader reader = null;

        if (readerId != 0){
            reader = readerDAO.showByReferenceId(readerId);
        }

        model.addAttribute("readers", readerDAO.showAll());
        model.addAttribute("reader", reader);
        model.addAttribute("readerForList", new Reader());
        model.addAttribute("book", bookDAO.showByIndex(id));

        return "books/index";

    }

    @GetMapping("/new")
    public String inputBook(Model model){

        model.addAttribute("book", new Book());

        return "books/new";
    }

    @PostMapping
    public String addBook(@ModelAttribute @Valid Book book, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "books/new";
        }

        bookDAO.insert(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){

        model.addAttribute("book", bookDAO.showByIndex(id));

        return "books/edit";

    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute @Valid Book book, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "books/edit";
        }

        bookDAO.update(id, book);

        return "redirect:/books";

    }

    @DeleteMapping("{id}")
    public String deleteReader(@PathVariable("id") int id){

        bookDAO.delete(id);

        return "redirect:/books";

    }
}