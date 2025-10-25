package com.example.learnreacetiveweb;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;

public class ReactiveTutorial {

    private Mono<String> testMono() {
        return Mono.just("Java");
    }

    private Flux<String> testFlux() {
        List<String> numbers = List.of("1", "2", "3", "4");
        return Flux.fromIterable(numbers);
    }

    private Flux<Integer> testMage() {
        Flux<Integer> flux1 = Flux.range(1, 20)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> flux2 = Flux.range(101, 20)
                .delayElements(Duration.ofMillis(500));
        return Flux.merge(flux1, flux2);
    }

    private Flux<Tuple2<Integer, Integer>> testZip() {
        Flux<Integer> flux1 = Flux.range(1, 20)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> flux2 = Flux.range(101, 20)
                .delayElements(Duration.ofMillis(500));
        return Flux.zip(flux1, flux2);
    }

    public static void main(String[] args) throws InterruptedException {
        ReactiveTutorial reactiveTutorial = new ReactiveTutorial();
        reactiveTutorial.testZip()
                .subscribe(System.out::println);

        Thread.sleep(30_000);
    }
}
