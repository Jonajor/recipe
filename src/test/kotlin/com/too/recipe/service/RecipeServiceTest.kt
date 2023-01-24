package com.too.recipe.service

import com.too.recipe.dto.RecipeDTO
import com.too.recipe.model.Recipe
import com.too.recipe.repository.RecipeRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.util.*

@ExtendWith(MockKExtension::class)
class RecipeServiceTest {

    @InjectMockKs
    private lateinit var recipeService: RecipeService

    @MockK
    private lateinit var recipeRepository: RecipeRepository

    private lateinit var recipeDTO: RecipeDTO

    private lateinit var recipe: Recipe

    @BeforeEach
    fun setup() {
        recipeDTO = RecipeDTO(
            name = "Ki6rjd",
            ingredients = listOf("ix0", "fmA", "tzKjnjw1", "I4x3", "9OvK5"),
            cuisine = "mixture",
            difficulty = 487
        )

        recipe = Recipe(
            id = 451,
            name = "Ki6rjd",
            ingredients = listOf("ix0", "fmA", "tzKjnjw1", "I4x3", "9OvK5"),
            cuisine = "mixture",
            difficulty = 487
        )
    }

    @Test
    fun `Given a valid recipe, when the addRecipe method is called, then the recipe should be successfully added and returned`() {
        every { recipeRepository.save(any()) } returns recipe

        // We need to execute the method addRecipe inside CoroutineScope
        runBlocking {
            val result = recipeService.addRecipe(recipeDTO)
            //Checking only id stored in database
            assertEquals(451, result.id)
        }
    }

    @ParameterizedTest
    @ValueSource(longs = [54, 250, 227, 762, 789])
    fun `Given a valid recipe ID, when the getRecipeById method is called, then the corresponding recipe should be returned`(input: Long) {
        recipe.id = input
        every { recipeRepository.findById(any()) } returns Optional.of(recipe)

        // We need to execute the method getRecipeById inside CoroutineScope
        runBlocking {
            val result = recipeService.getRecipeById(input)
            assertEquals(input, result.get().id)
        }
    }

    @Test
    fun `Given a valid recipe ID, when the updateRecipe method is called, then the corresponding recipe should be successfully updated and returned`() {

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a valid recipe ID, when the deleteRecipe method is called, then the corresponding recipe should be successfully deleted`() {

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a list of recipes in the database, when the getAllRecipes method is called, then the list of all recipes should be returned`() {

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a non-existing recipe ID, when the getRecipeById method is called, then an exception should be thrown`() {

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a non-existing recipe ID, when the updateRecipe method is called, then an exception should be thrown`() {

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a non-existing recipe ID, when the deleteRecipe method is called, then an exception should be thrown`() {

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given an invalid recipe object, when the addRecipe method is called, then an exception should be thrown`() {

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given an invalid recipe object, when the updateRecipe method is called, then an exception should be thrown`() {

        TODO("Not Implemented yet")
    }
}