package com.banditos.server.bot;

import com.banditos.server.controller.MainController;
import com.banditos.server.model.Tusovka;
import com.banditos.server.orm.TusovkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;

@Component
public class BotMessageCreator {

    private static TusovkaRepository tusovkaRepository;

    @Autowired
    public BotMessageCreator(TusovkaRepository tusovkaRepository) {
        this.tusovkaRepository = tusovkaRepository;
    }


    public static SendMessage createTusovkasMessage(Long id) {
        Iterable<Tusovka> tusovkas = tusovkaRepository.findAll();
        SendMessage response = new SendMessage();
        response.setChatId(id);

        StringBuilder sb = new StringBuilder();
        for (Tusovka t : tusovkas) {
            sb.append(t.toString());
        }
        response.setText(sb.toString());
        return response;
    }
}
