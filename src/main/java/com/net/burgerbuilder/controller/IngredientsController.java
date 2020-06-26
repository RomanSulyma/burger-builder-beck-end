package com.net.burgerbuilder.controller;

import com.net.burgerbuilder.entity.Ingredient;
import com.net.burgerbuilder.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Ingredients controller, get all ingredients and ingredient by name
 */
@RequiredArgsConstructor
@RestController
public class IngredientsController {

  private final IngredientService ingredientService;

  /**
   * Get all ingredients
   *
   * @return - All ingredients
   */
  @RequestMapping(method = RequestMethod.GET, value = "/ingredients")
    public ResponseEntity<List<Ingredient>> getIngredients() {
    return new ResponseEntity<>(ingredientService.findAllIngredients(), HttpStatus.OK);
  }

  /**
   * Get ingredient by name
   *
   * @param name - Ingredient name
   * @return - Ingredient by name
   */
  @RequestMapping(method = RequestMethod.GET, value = "/ingredients/{name}")
    public ResponseEntity<Ingredient> getIngredientsByName(@PathVariable final String name) {
    return new ResponseEntity<>(ingredientService.findIngredientByName(name), HttpStatus.OK);
  }
}
