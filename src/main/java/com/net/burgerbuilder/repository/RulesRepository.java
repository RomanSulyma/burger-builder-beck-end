package com.net.burgerbuilder.repository;

import com.net.burgerbuilder.entity.Rules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RulesRepository extends JpaRepository<Rules, Long> {

  @Query("select r from Rules r order by r.id asc")
  List<Rules> findAllWithOrderASC();
}
