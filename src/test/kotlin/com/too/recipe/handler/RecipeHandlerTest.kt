package com.too.recipe.handler

import com.too.recipe.service.RecipeService
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class RecipeHandlerTest {

    @InjectMockKs
    private lateinit var recipeHandler: RecipeHandler

    @MockK
    private lateinit var recipeService: RecipeService

    @Test
    fun `Given a list of recipes, when the getAllRecipes function is called, then it should return a list of all recipes in the repository`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a specific recipe ID, when the getRecipeById function is called, then it should return that specific recipe`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a new recipe, when the addRecipe function is called, then it should add the recipe to the repository and return the created recipe`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given an existing recipe and updated recipe details, when the updateRecipe function is called, then it should update the recipe in the repository and return the updated recipe`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a specific recipe ID, when the deleteRecipe function is called, then it should delete the recipe from the repository`(){

        TODO("Not Implemented yet")
    }





}