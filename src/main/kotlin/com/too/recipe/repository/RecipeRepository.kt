package com.too.recipe.repository

import com.too.recipe.model.Recipe
import org.springframework.data.repository.CrudRepository

interface RecipeRepository : CrudRepository<Recipe, Long> {
}