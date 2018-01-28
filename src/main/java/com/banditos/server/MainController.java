package com.banditos.server;

import com.banditos.server.model.Tusovka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private TusovkaRepository tusovkaRepository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Tusovka> getAllUsers() {
        return tusovkaRepository.findAll();
    }
}
