package com.example.demowebjpa;

import static java.util.Collections.singletonList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/calc")
public class CalculatorController {

    public static final String METRIC_NAME = "calc.add";

    private MeterRegistry meterRegistry;

    public CalculatorController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {

        incrementCounter(a);
        incrementCounter(b);

        return a + b;
    }

    private void incrementCounter(int number) {
        meterRegistry.counter(METRIC_NAME, singletonList(new ImmutableTag("number", Integer.toString(number))))
            .increment();
    }
}
