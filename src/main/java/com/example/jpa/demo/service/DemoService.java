package com.example.jpa.demo.service;

import com.example.jpa.demo.entity.DemoInfo;
import com.example.jpa.demo.repository.read.DemoReadRepository;
import com.example.jpa.demo.repository.write.DemoWriteRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/3/15.
 */
@Service
public class DemoService {

  @Autowired
  private DemoReadRepository demoReadRepository;

  @Autowired
  private DemoWriteRepository demoWriteRepository;

  public List<DemoInfo> testDemoRead(String name) {
    List<DemoInfo> demoInfoList = demoReadRepository.findByName(name);
    demoInfoList.forEach(System.out::println);
    DemoInfo demoInfo = DemoInfo.builder().name(UUID.randomUUID().toString()).age(100).score(123).build();
    demoReadRepository.save(demoInfo);
    return demoInfoList;
  }

  public void testDemoWrite() {
    List<DemoInfo> demoInfoList = demoWriteRepository.findByName("dragon");
    demoInfoList.forEach(System.out::println);
    DemoInfo demoInfo = DemoInfo.builder().name(UUID.randomUUID().toString()).age(100).score(123).build();
    demoWriteRepository.save(demoInfo);
  }
}
