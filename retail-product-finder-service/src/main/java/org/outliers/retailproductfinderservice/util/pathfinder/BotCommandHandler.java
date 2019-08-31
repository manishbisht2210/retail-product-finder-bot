package org.outliers.retailproductfinderservice.util.pathfinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class BotCommandHandler {

    @Autowired
    RestTemplate restTemplate;

    @Value("${bot.url.forward}")
    String forwardUrl;

    @Value("${bot.url.left}")
    String leftUrl;

    @Value("${bot.url.right}")
    String rightUrl;

    @Value("${bot.url.stop}")
    String stopUrl;

    @Value("${bot.turn.delay}")
    int turnDelay;

    public void callBotInstruction(String action, int metric) throws InterruptedException {

            String key = action;
            int value = metric;
            if(key.equalsIgnoreCase("B")) {
                takeLeft(leftUrl);
                stop(stopUrl);
                takeLeft(leftUrl);
                stop(stopUrl);
            }else if(key.equalsIgnoreCase("R")) {
                takeLeft(leftUrl);
                stop(stopUrl);
            }else if(key.equalsIgnoreCase("L")) {
                takeRight(rightUrl);
                stop(stopUrl);
            }
            goForward(forwardUrl, value);
            stop(stopUrl);
       // System.out.println("Reached to item");
    }

    @Async
    public void goForward(String forwardUrl, int steps) throws InterruptedException {
        System.out.println("Moving forward for " + steps + " steps");
        restTemplate.getForObject(forwardUrl, String.class);
        //restTemplate.exchange(forwardUrl, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class);
        TimeUnit.SECONDS.sleep(steps);
    }

    @Async
    public void takeLeft(String leftUrl) throws InterruptedException {
        System.out.println("Taking Right");
        restTemplate.getForObject(leftUrl, String.class);

        //restTemplate.exchange(leftUrl, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class);
        TimeUnit.MILLISECONDS.sleep(turnDelay);
    }

    @Async
    public void takeRight(String rightUrl) throws InterruptedException {
        System.out.println("Taking Left");
        restTemplate.getForObject(rightUrl, String.class);
        //restTemplate.exchange(rightUrl, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class);
        TimeUnit.MILLISECONDS.sleep(turnDelay);
    }

    @Async
    public void stop(String stopUrl) throws InterruptedException {
        System.out.println("Stopping");
        restTemplate.getForObject(stopUrl, String.class);
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
