package com.too.recipe.dto

import com.too.recipe.model.Recipe

data class RecipeDTO(
    val id: Long? = null,
    val name: String,
    val ingredients: List<String>,
    val cuisine: String,
    val difficulty: Int
) {

    fun toEntity(): Recipe {
        return Recipe(id, name, ingredients, cuisine, difficulty)
    }

    companion object {
        fun fromEntity(recipe: Recipe) = RecipeDTO(recipe.id, recipe.name, recipe.ingredients, recipe.cuisine, recipe.difficulty)
    }
}
