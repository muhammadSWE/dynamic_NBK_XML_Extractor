package com.NBK.xmlextractor.Controller;


import com.NBK.xmlextractor.Service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping(value = "/xml")
public class AppController {
    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportXml() {
        return new ResponseEntity<>("<data><test>Success</test></data>", HttpStatus.OK);
    }
}

