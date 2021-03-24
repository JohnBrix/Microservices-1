package com.example.observable_johnbrix.controller;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getHello")
    public String hello() {
       /* this:: your method
         this merge are method*/

        Observable<String> dataObservable = Observable.fromCallable(this::getNameFromJohnBrixMS)
                .subscribeOn(Schedulers.newThread());

        Observable<String> helloObservable = Observable.fromCallable(this::getHelloFromJohnBrixMs)
                .subscribeOn(Schedulers.newThread());

        /*manual ang pag code ng dataObservable,helloObservable for private string method*/
        String response = Observable.zip(dataObservable, helloObservable, this::merge).blockingFirst();

        return response;
    }
    private String merge(String dataObservable, String helloObservable) {
        return "This is return of merge: "+ dataObservable+ ", " + helloObservable;
    }
    //THIS IS URI if your localhost:6969 pwedi ka makatawag ng method sa kanila
    private String getNameFromJohnBrixMS() {
        return restTemplate.getForEntity("http://localhost:6969/getHello", String.class).getBody();
    }
    //if example lng to pwedi mo tawagin din ibang microservices
    private String getHelloFromJohnBrixMs() {
        return restTemplate.getForEntity("http://localhost:6969/getName", String.class).getBody();
    }


}
