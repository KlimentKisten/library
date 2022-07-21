package ru.kisten.springlearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kisten.springlearn.dao.BookDAO;
import ru.kisten.springlearn.dao.ReaderDAO;
import ru.kisten.springlearn.models.Reader;

import javax.validation.Valid;

@Controller
@RequestMapping("/readers")
public class ReadersController {

    private final ReaderDAO readerDAO;
    private final BookDAO bookDAO;

    @Autowired
    public ReadersController(ReaderDAO readerDAO, BookDAO bookDAO) {
        this.readerDAO = readerDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String showReaders(Model model){

        model.addAttribute("readers", readerDAO.showAll());

        return "readers/show";
    }

    @GetMapping("{id}")
    public String showReaderByIndex(@PathVariable("id") int id, Model model){

        model.addAttribute("book", bookDAO.showByReferenseId(id));
        model.addAttribute("books", bookDAO.showAllBooksByReferenceId(id));
        model.addAttribute("reader", readerDAO.showByIndex(id));

        return "readers/index";
    }

    @GetMapping("/new")
    public String inputReader(Model model){

        model.addAttribute("reader", new Reader());

        return "readers/new";
    }

    @PostMapping
    public String addReader(@ModelAttribute @Valid Reader reader, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "readers/new";
        }

        readerDAO.insert(reader);

        return "redirect:/readers";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){

        model.addAttribute("reader", readerDAO.showByIndex(id));

        return "readers/edit";

    }

    @PatchMapping("/{id}")
    public String updateReader(@PathVariable("id") int id, @ModelAttribute @Valid Reader reader, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "readers/edit";
        }

        readerDAO.update(id, reader);

        return "redirect:/readers";

    }

    @DeleteMapping("{id}")
    public String deleteReader(@PathVariable("id") int id){

        readerDAO.delete(id);

        return "redirect:/readers";

    }

    @PatchMapping("/add/{id}")
    public String addBook(@ModelAttribute Reader reader, @PathVariable("id") int bookId){

        int readerId = reader.getId();

        bookDAO.setBookToReader(readerId, bookId);

        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/remove/{id}")
    public String removeBookFromReader(@PathVariable("id") int id){
        bookDAO.removeBookFromReader(id);
        return "redirect:/books/" + id;
    }
}