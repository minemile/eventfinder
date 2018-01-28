package com.banditos.server;

import com.banditos.server.model.Tusovka;
import com.banditos.server.orm.TusovkaRepository;
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

    @GetMapping(path="/tusovkas")
    public @ResponseBody
    Iterable<Tusovka> getAllTusovkas() {
        return tusovkaRepository.findAll();
    }

}
