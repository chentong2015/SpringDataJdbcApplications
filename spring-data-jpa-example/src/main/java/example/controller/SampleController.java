package example.controller;

import example.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SampleController {

    private SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/samples")
    public String listSamples() {
        sampleService.saveSample();
        List<Object[]> samples = sampleService.getAllSample();
        String result = samples.get(0)[0] + " -- " + samples.get(0)[1];
        return "OK: " + result;
    }
}
