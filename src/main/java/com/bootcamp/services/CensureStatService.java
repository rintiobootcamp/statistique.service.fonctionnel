package com.bootcamp.services;

import com.bootcamp.client.CensureClient;
import com.bootcamp.entities.Censure;
import helpers.Message;
import helpers.Output;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bignon.
 */
@Component
public class CensureStatService {

    CensureClient censureClient;

    @PostConstruct

    public void init() {
        censureClient = new CensureClient();
    }

    public Output getCensures(String entity, int id) throws IOException {
        List<Censure> censures = censureClient.getCensureByEntity(entity,id);
        List<Message> messages = new ArrayList<>();

        Output output = new Output();
        output.setCategorie(censures.get(0).getEntityType());
        output.setIndex(id);
        output.setGravite(censures.size());

        for (int i = 0; i < censures.size(); i++) {
            Message msg = new Message();
            msg.setId(censures.get(i).getId());
            msg.setMessage(censures.get(i).getMessage());

            messages.add(msg);
        }

        output.setMessages(messages);

        return output;
    }
}
