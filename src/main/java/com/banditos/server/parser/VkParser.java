package com.banditos.server.parser;

import com.banditos.server.model.Tusovka;
import com.banditos.server.orm.MessageRepository;
import com.banditos.server.orm.TusovkaRepository;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.objects.wall.responses.GetExtendedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class VkParser extends AbstractVkParser {

     //Parse only powerhouse for now

    VkParser(Environment env, TusovkaRepository tusovkaRepository) {
        super(env, tusovkaRepository);
    }

    @PostConstruct
    private void init() {
        domain = "pwrhs";
    }
}
