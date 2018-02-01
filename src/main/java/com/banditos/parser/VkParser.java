package com.banditos.parser;

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
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class VkParser {

    private final String domain = "pwrhs"; //Parse only powerhouse for now

    private VkApiClient vk;
    private ServiceActor actor;

    @Autowired
    private TusovkaRepository tusovkaRepository;

    public VkParser(String token, Integer app_id)
    {
        TransportClient transportClient = new HttpTransportClient();
        this.vk = new VkApiClient(transportClient);
        this.actor = new ServiceActor(app_id, token);
    }

    public List<Tusovka> get_tusovkas(String domain) throws ApiException, ClientException
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
        return tusovkas;
    }

    public static void main(String[] args) throws ApiException, ClientException {
        VkParser vk_parser = new VkParser("5c346d945c346d945c346d948b5c548ed655c345c346d940659466b88db3e410d77f01a", 6355707);
        vk_parser.get_tusovkas("pwrhs");
    }
}
