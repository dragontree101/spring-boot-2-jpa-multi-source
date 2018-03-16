package com.example.jpa.demo.controller;

import com.example.jpa.demo.service.DemoService;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/3/15.
 */
@RestController
@Slf4j
public class JpaController {

  @Autowired
  private DemoService demoService;


  @RequestMapping(value = "/read", method = RequestMethod.GET)
  public String readJpa() {
    String name = "dragon";
    log.info("demo info is {}, name is {}", demoService.testDemoRead(name), name);
    return "ok";
  }

  @RequestMapping(value = "/write", method = RequestMethod.POST)
  public String writeJpa() throws SQLException {
    demoService.testDemoWrite();
    return "ok";
  }
}
