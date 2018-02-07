package com.banditos.server.bot;

import com.banditos.server.controller.MainController;
import com.banditos.server.model.Tusovka;
import com.banditos.server.orm.TusovkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class BotMessageCreator {

    @Autowired
    private static MainController mc;

    public static SendMessage sentTusovkas() {
        Iterable<Tusovka> tusovkas = mc.getAllTusovkas();
        SendMessage response = new SendMessage();
        StringBuilder sb = new StringBuilder();
        for (Tusovka t : tusovkas) {
            sb.append(t.toString() + "/n");
        }
        response.setText(sb.toString());
        return response;
    }
}
