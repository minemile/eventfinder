package com.banditos.server.parser;

import com.banditos.server.model.Tusovka;
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
import org.springframework.core.env.Environment;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractVkParser {
    private VkApiClient vk;
    private ServiceActor actor;
    String domain;

    private final TusovkaRepository tusovkaRepository;

    @Autowired
    AbstractVkParser(Environment env, TusovkaRepository tusovkaRepository)
    {
        TransportClient transportClient = new HttpTransportClient();
        this.vk = new VkApiClient(transportClient);

        Integer app_id = Integer.valueOf(env.getProperty("vk.appid"));
        String token = env.getProperty("vk.token");
        this.actor = new ServiceActor(app_id, token);

        this.tusovkaRepository = tusovkaRepository;
    }

    public void getTusovkas() throws ApiException, ClientException
    {
        GetExtendedResponse response = vk.wall().getExtended(actor).domain(domain).count(5).execute();
        List<Tusovka> tusovkas = new ArrayList<>();
        List<GroupFull> lgf = response.getGroups();
        int i = 0;
        for (WallPostFull wpf : response.getItems())
        {
            GroupFull gf = lgf.get(i);
            tusovkas.add(new Tusovka(Date.from(Instant.ofEpochSecond(wpf.getDate())), gf.getName(), wpf.getText(), "Таганка", 0));
            i++;
        }
        tusovkaRepository.save(tusovkas);
    }

    private Date getLastTusovka(String place) {
        Tusovka tusovka = tusovkaRepository.findByPlaceOrderByDateAsc(place);
        return tusovka.getDate();
    }
}
