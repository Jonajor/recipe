package com.too.recipe.service

import com.too.recipe.dto.RecipeDTO
import com.too.recipe.exception.RecipeNotFoundException
import com.too.recipe.repository.RecipeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RecipeService(private val recipeRepository: RecipeRepository) {

    suspend fun getAllRecipes(): List<RecipeDTO> {
        return recipeRepository.findAll().map { RecipeDTO.fromEntity(it) }
    }

    suspend fun getRecipeById(id: Long): Optional<RecipeDTO> {
        return recipeRepository.findById(id).map { RecipeDTO.fromEntity(it) }
    }

    suspend fun addRecipe(recipe: RecipeDTO): RecipeDTO {
        return RecipeDTO.fromEntity(recipeRepository.save(recipe.toEntity()))
    }

    suspend fun updateRecipe(id: Long, recipe: RecipeDTO): RecipeDTO {
        val existingRecipe = recipeRepository.findById(id)
        if (existingRecipe.isPresent) {
            val recipeEntity = recipe.toEntity()
            recipeEntity.id = existingRecipe.get().id
            return RecipeDTO.fromEntity(recipeRepository.save(recipeEntity))
        } else {
            throw RecipeNotFoundException("Recipe with id $id not found")
        }
    }

    suspend fun deleteRecipe(id: Long) {
        recipeRepository.deleteById(id)
    }
}
