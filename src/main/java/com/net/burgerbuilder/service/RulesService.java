package com.net.burgerbuilder.service;

import com.net.burgerbuilder.entity.Rules;
import com.net.burgerbuilder.repository.RulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Search service for validation constraints
 */
@Service
@RequiredArgsConstructor
public class RulesService {

  private final RulesRepository rulesRepository;

  public List<Rules> getValidationInputs() {
    return rulesRepository.findAllWithOrderASC();
  }
}
