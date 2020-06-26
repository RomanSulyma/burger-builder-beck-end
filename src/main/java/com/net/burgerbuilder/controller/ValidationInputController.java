package com.net.burgerbuilder.controller;

import com.net.burgerbuilder.entity.Rules;
import com.net.burgerbuilder.service.RulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Validation constraints controller
 */
@RequiredArgsConstructor
@RestController
public class ValidationInputController {

  private final RulesService rulesService;

  /**
   * Return validation constraints and regexp
   *
   * @return - validation constraints and regexp
   */
  @RequestMapping(method = RequestMethod.GET, value = "/validation-inputs")
  public ResponseEntity<List<Rules>> getValidationInputs() {
    return new ResponseEntity<>(rulesService.getValidationInputs(), HttpStatus.OK);
  }
}
