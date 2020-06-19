package com.net.burgerbuilder.service;

import com.net.burgerbuilder.entity.Ingredient;
import com.net.burgerbuilder.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Search service for ingredients
 */
@RequiredArgsConstructor
@Service
public class IngredientService {

  private final IngredientRepository ingredientRepository;

  /**
   * Find ingredient by name
   *
   * @param name - Ingredient name
   * @return - Ingredient
   */
  public Ingredient findIngredientByName(final String name) {
    return ingredientRepository.findByName(name);
  }

  /**
   * Find all ingredients
   *
   * @return - Ingredients list
   */
  public List<Ingredient> findAllIngredients() {
    return ingredientRepository.findAll();
  }
}
