package com.example.servingwebcontent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.invoke.MethodHandles;

@Controller
public class TestController {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @RequestMapping("/errorTest")
    public String errorTest() {
        RuntimeException exc = new RuntimeException("errorTest");
        LOGGER.error("Error handling looks good!", exc);
        throw exc;
    }

    @RequestMapping("/airbrakeExample")
    public void airbrakeExample() throws ArithmeticException {
        int i = 1/0;
    }
}
