package com.too.recipe.handler

import com.too.recipe.dto.RecipeDTO
import com.too.recipe.service.RecipeService
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.mock.web.reactive.function.server.MockServerRequest
import reactor.kotlin.core.publisher.toMono

@ExtendWith(MockKExtension::class)
class RecipeHandlerTest {

    @InjectMockKs
    private lateinit var recipeHandler: RecipeHandler

    @MockK
    private lateinit var recipeService: RecipeService

    private lateinit var recipeDTO: RecipeDTO

    private lateinit var mockServerRequest: MockServerRequest

    @BeforeEach
    fun setup() {
        recipeDTO = RecipeDTO(
            name = "court",
            ingredients = listOf("wake", "read", "improve", "scenery", "generous"),
            cuisine = "mixture",
            difficulty = 399
        )

        mockServerRequest = MockServerRequest.builder().body(recipeDTO)
    }

    @Test
    fun `Given a list of recipes, when the getAllRecipes function is called, then it should return a list of all recipes in the repository`() {
        coEvery { recipeService.getAllRecipes() } returns listOf(recipeDTO)

        runBlocking {
            val result = recipeHandler.getAllRecipes(mockServerRequest)
            assertEquals(HttpStatus.OK, result.statusCode())
        }
    }

    @Test
    fun `Given a specific recipe ID, when the getRecipeById function is called, then it should return that specific recipe`() {
        coEvery { recipeService.getRecipeById(any()) } returns recipeDTO

        mockServerRequest = MockServerRequest.builder()
            .pathVariable("id", "399")
            .body(recipeDTO)

        runBlocking {
            val result = recipeHandler.getRecipeById(mockServerRequest)
            assertEquals(HttpStatus.OK, result.statusCode())
        }
    }

    @Test
    fun `Given a new recipe, when the addRecipe function is called, then it should add the recipe to the repository and return the created recipe`() {
        coEvery { recipeService.addRecipe(any()) } returns recipeDTO

        mockServerRequest = MockServerRequest.builder()
            .pathVariable("id", "399")
            .body(recipeDTO.toMono())

        runBlocking {
            val result = recipeHandler.addRecipe(mockServerRequest)
            assertEquals(HttpStatus.CREATED, result.statusCode())
        }
    }

    @Test
    fun `Given an existing recipe and updated recipe details, when the updateRecipe function is called, then it should update the recipe in the repository and return the updated recipe`() {
        coEvery { recipeService.updateRecipe(any(), any()) } returns recipeDTO

        val dto = recipeDTO.copy(ingredients = listOf("shower", "mention", "both", "daylight", "corn"))

        mockServerRequest = MockServerRequest.builder()
            .pathVariable("id", "399")
            .body(dto.toMono())

        runBlocking {
            val result = recipeHandler.updateRecipe(mockServerRequest)
            assertEquals(HttpStatus.OK, result.statusCode())
        }
    }

    @Test
    fun `Given a specific recipe ID, when the deleteRecipe function is called, then it should delete the recipe from the repository`() {
        coEvery { recipeService.deleteRecipe(any()) } just Runs

        mockServerRequest = MockServerRequest.builder()
            .pathVariable("id", "399")
            .body(recipeDTO)

        runBlocking {
            val result = recipeHandler.deleteRecipe(mockServerRequest)
            assertEquals(HttpStatus.NO_CONTENT, result.statusCode())
        }
    }

}