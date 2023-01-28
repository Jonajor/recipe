package com.too.recipe.service

import com.too.recipe.dto.RecipeDTO
import com.too.recipe.exception.RecipeNotFoundException
import com.too.recipe.model.Recipe
import com.too.recipe.repository.RecipeRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.util.*

/**
 *  This class contains test cases for RecipeService
 *  @ExtendWith(MockKExtension::class) annotation is used to enable the MockK library
 *  @InjectMockKs and @MockK annotations are used to create mock objects
 */
@ExtendWith(MockKExtension::class)
class RecipeServiceTest {

    @InjectMockKs
    private lateinit var recipeService: RecipeService

    @MockK
    private lateinit var recipeRepository: RecipeRepository

    private lateinit var recipeDTO: RecipeDTO

    private lateinit var recipe: Recipe

    /**
     * setup method is run before each test to initialize variables
     */
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

    /**
     * Test case for addRecipe method
     * Given a valid recipe, when the addRecipe method is called,
     * then the recipe should be successfully added and returned
     */
    @Test
    fun `Given a valid recipe, when the addRecipe method is called, then the recipe should be successfully added and returned`() {
        //configuring the mock object to return the recipe when the save() method is called
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
    fun `Given a valid recipe ID, when the getRecipeById method is called, then the corresponding recipe should be returned`(
        input: Long
    ) {
        // Change the value of recipe.id to storage the input value
        recipe.id = input
        //configuring the mock object to return an optional recipe when the findById() method is called
        every { recipeRepository.findById(any()) } returns Optional.of(recipe)

        // We need to execute the method getRecipeById inside CoroutineScope
        runBlocking {
            val result = recipeService.getRecipeById(input)
            //Checking only id stored in database
            assertEquals(input, result.id)
        }
    }

    @Test
    fun `Given a valid recipe ID, when the updateRecipe method is called, then the corresponding recipe should be successfully updated and returned`() {
        //Creating a list of ingredients
        val ingredients = listOf("engineer", "very", "smooth", "happy", "propose")

        // Mock object to return an optional recipe when the findById() method is called
        every { recipeRepository.findById(any()) } returns Optional.of(recipe)
        // Mock object to return the recipe when the save() method is called
        every { recipeRepository.save(any()) } returns recipe.copy(ingredients = ingredients)

        //Creating a copy of recipeDTO changing the ingredients
        val dto = recipeDTO.copy(ingredients = ingredients)

        // Running recipeService.updateRecipe() inside CoroutineScope
        runBlocking {
            val result = recipeService.updateRecipe(451, dto)
            // Using assertAll to do all assertions
            assertAll(Executable {
                assertEquals("engineer", result.ingredients[0])
                assertEquals("very", result.ingredients[1])
                assertEquals("smooth", result.ingredients[2])
                assertEquals("happy", result.ingredients[3])
                assertEquals("propose", result.ingredients[4])
            })
        }
    }

    @ParameterizedTest
    @ValueSource(longs = [365, 926, 143, 319, 682])
    fun `Given a valid recipe ID, when the deleteRecipe method is called, then the corresponding recipe should be successfully deleted`(input: Long) {
        //Mocking the existsById method that will return a boolean type
        every { recipeRepository.existsById(any()) } returns true
        //Mocking the deleteById method that only run
        every { recipeRepository.deleteById(any()) } just Runs

        //Running the deleteRecipe method in a CoroutineScope
        runBlocking {
            recipeService.deleteRecipe(input)
        }

        //Checking how many times the deleteById method is run
        verify(exactly = 1) { recipeRepository.deleteById(input) }
    }

    @Test
    fun `Given a list of recipes in the database, when the getAllRecipes method is called, then the list of all recipes should be returned`() {
        //Mocking the findAll() method that will return a list of Recipe
        every { recipeRepository.findAll() } returns listOf(recipe)

        //Running the getAllRecipes() method in a CoroutineScope
        runBlocking {
            val allRecipes = recipeService.getAllRecipes()
            //Checking the size of list
            assertTrue(allRecipes.size == 1)
        }
    }

    @ParameterizedTest
    @ValueSource(longs = [215, 953, 264, 124, 373])
    fun `Given a non-existing recipe ID, when the getRecipeById method is called, then an exception should be thrown`(input: Long) {
        //Mocking the findById() method that will return an Optional type of Recipe or Empty
        every { recipeRepository.findById(any()) } returns Optional.empty()

        //Checking the exception in case of findById method return null or empty should return RecipeNotFoundException
        val notFoundException = assertThrows(RecipeNotFoundException::class.java) {
            runBlocking {
                recipeService.getRecipeById(input)
            }
        }

        //Checking the exception message
        assertEquals("Recipe with id $input not found", notFoundException.message)
    }

    @ParameterizedTest
    @ValueSource(longs = [915, 940, 958, 136, 881])
    fun `Given a non-existing recipe ID, when the updateRecipe method is called, then an exception should be thrown`(input: Long) {
        //Mocking the findById() method that will return an Optional type of Recipe or Empty
        every { recipeRepository.findById(any()) } returns Optional.empty()

        //Checking the exception in case of findById method return null or empty should return RecipeNotFoundException
        val notFoundException = assertThrows(RecipeNotFoundException::class.java) {
            runBlocking {
                recipeService.updateRecipe(input, recipeDTO)
            }
        }

        //Checking the exception message
        assertEquals("Recipe with id $input not found", notFoundException.message)
    }

    @ParameterizedTest
    @ValueSource(longs = [39, 250, 808, 109, 270])
    fun `Given a non-existing recipe ID, when the deleteRecipe method is called, then an exception should be thrown`(input: Long) {
        //Mocking the existsById method that will return a boolean type
        every { recipeRepository.existsById(any()) } returns false

        //Mocking the deleteById method that only run
        every { recipeRepository.deleteById(any()) } just Runs

        //Checking the exception in case of findById method return null or empty should return RecipeNotFoundException
        val notFoundException = assertThrows(RecipeNotFoundException::class.java) {
            runBlocking {
                recipeService.deleteRecipe(input)
            }
        }

        //Checking the exception message
        assertEquals("Recipe with id $input not found", notFoundException.message)
    }
}