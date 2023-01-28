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
        // Setting up the mock recipe service to return a list of recipeDTO when getAllRecipes() is called
        coEvery { recipeService.getAllRecipes() } returns listOf(recipeDTO)

        // Executing the getAllRecipes() function and asserting that the returned status code is HttpStatus.OK
        runBlocking {
            val result = recipeHandler.getAllRecipes(mockServerRequest)
            //assert that the returned status code is OK
            assertEquals(HttpStatus.OK, result.statusCode())
        }
    }

    @Test
    fun `Given a specific recipe ID, when the getRecipeById function is called, then it should return that specific recipe`() {
        //Mock the getRecipeById method from recipeService to return recipeDTO
        coEvery { recipeService.getRecipeById(any()) } returns recipeDTO

        //Create a new mockServerRequest with a path variable "id" set to 399
        mockServerRequest = MockServerRequest.builder()
            .pathVariable("id", "399")
            .body(recipeDTO)

        runBlocking {
            //Call the getRecipeById method from recipeHandler
            val result = recipeHandler.getRecipeById(mockServerRequest)
            //assert that the returned status code is OK
            assertEquals(HttpStatus.OK, result.statusCode())
        }
    }

    @Test
    fun `Given a new recipe, when the addRecipe function is called, then it should add the recipe to the repository and return the created recipe`() {
        // setup the mock service to return a recipeDTO object when addRecipe is called with any parameter
        coEvery { recipeService.addRecipe(any()) } returns recipeDTO

        // setup the mock server request to include a path variable of "id" with value "399" and a body containing the recipeDTO
        mockServerRequest = MockServerRequest.builder()
            .pathVariable("id", "399")
            .body(recipeDTO.toMono())

        runBlocking {
            //Call the addRecipe method from recipeHandler
            val result = recipeHandler.addRecipe(mockServerRequest)
            //assert that the returned status code is CREATED
            assertEquals(HttpStatus.CREATED, result.statusCode())
        }
    }

    @Test
    fun `Given an existing recipe and updated recipe details, when the updateRecipe function is called, then it should update the recipe in the repository and return the updated recipe`() {
        // setup the mock service to return a recipeDTO object when updateRecipe is called with any parameter
        coEvery { recipeService.updateRecipe(any(), any()) } returns recipeDTO

        // change ingredients attribute
        val dto = recipeDTO.copy(ingredients = listOf("shower", "mention", "both", "daylight", "corn"))

        // setup the mock server request to include a path variable of "id" with value "399" and a body containing the recipeDTO
        mockServerRequest = MockServerRequest.builder()
            .pathVariable("id", "399")
            .body(dto.toMono())

        runBlocking {
            //Call the updateRecipe method from recipeHandler
            val result = recipeHandler.updateRecipe(mockServerRequest)
            //assert that the returned status code is OK
            assertEquals(HttpStatus.OK, result.statusCode())
        }
    }

    @Test
    fun `Given a specific recipe ID, when the deleteRecipe function is called, then it should delete the recipe from the repository`() {
        //Mock the recipeService to return a Runs just when the deleteRecipe function is called with any parameter
        coEvery { recipeService.deleteRecipe(any()) } just Runs

        //Creating a new mockServerRequest with path variable "id" set to 399
        mockServerRequest = MockServerRequest.builder()
            .pathVariable("id", "399")
            .body(recipeDTO)

        //run the deleteRecipe function in a runBlocking block
        runBlocking {
            val result = recipeHandler.deleteRecipe(mockServerRequest)
            //assert that the returned status code is NO_CONTENT
            assertEquals(HttpStatus.NO_CONTENT, result.statusCode())
        }
    }

}