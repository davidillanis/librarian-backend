package com.microservice.library.service.other;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.apache.http.client.HttpClient;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class UtilsServiceImpl {
    public LocalDate localDatePeru(){
        String url="https://worldtimeapi.org/api/timezone/America/Lima";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        try {
            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String json = EntityUtils.toString(responseEntity);
                JSONObject jsonObject = new JSONObject(json);
                String dateTime = jsonObject.getString("datetime");
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

                return zonedDateTime.toLocalDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return LocalDate.EPOCH;
    }

    public String generateRandomKey(int size, boolean isUpper){
        Random rand = new Random();
        String random=IntStream.range(0, size)
                .mapToObj(obj->rand.nextInt(2)==0?
                        String.valueOf((char)('a'+rand.nextInt(26))) :
                        String.valueOf(rand.nextInt(10)))
                .collect(Collectors.joining());

        random=isUpper?random.toUpperCase():random.toLowerCase();
        return random;
    }
}
