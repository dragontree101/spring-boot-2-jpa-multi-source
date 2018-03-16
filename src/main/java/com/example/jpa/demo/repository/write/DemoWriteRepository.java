package com.example.jpa.demo.repository.write;

import com.example.jpa.demo.entity.DemoInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/3/15.
 */
@Repository
public interface DemoWriteRepository extends JpaRepository<DemoInfo, Long> {
  List<DemoInfo> findByName(String name);
}
