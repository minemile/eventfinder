package com.banditos.server;

import com.banditos.parser.VkParser;
import com.banditos.server.model.Tusovka;
import com.banditos.server.orm.TusovkaRepository;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParserController {

    @Autowired
    private TusovkaRepository tusovkaRepository;

    @GetMapping(path="/tusovkas")
    public @ResponseBody
    Iterable<Tusovka> getAllTusovkas() {
        return tusovkaRepository.findAll();
    }

    @RequestMapping("/vk_parser")
    public Iterable<Tusovka> vk_parser() throws ApiException, ClientException
    {
        VkParser vk_parser = new VkParser("5c346d945c346d945c346d948b5c548ed655c345c346d940659466b88db3e410d77f01a", 555555);
        return vk_parser.get_tusovkas("pwrhs");
    }

}
