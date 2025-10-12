package example.controller;

import example.service.MyConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

    private MyConfigurationService myConfigurationService;

    public ConfigurationController(@Autowired MyConfigurationService myConfigurationService) {
        this.myConfigurationService = myConfigurationService;
    }
}
