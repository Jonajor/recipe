package com.too.recipe.service

import com.too.recipe.repository.RecipeRepository
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class RecipeServiceTest {
    
    @InjectMockKs
    private lateinit var recipeService: RecipeService
    
    @MockK
    private lateinit var recipeRepository: RecipeRepository
    
    @Test
    fun `Given a valid recipe, when the addRecipe method is called, then the recipe should be successfully added and returned`(){
        
        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a valid recipe ID, when the getRecipeById method is called, then the corresponding recipe should be returned`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a valid recipe ID, when the updateRecipe method is called, then the corresponding recipe should be successfully updated and returned`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a valid recipe ID, when the deleteRecipe method is called, then the corresponding recipe should be successfully deleted`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a list of recipes in the database, when the getAllRecipes method is called, then the list of all recipes should be returned`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a non-existing recipe ID, when the getRecipeById method is called, then an exception should be thrown`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a non-existing recipe ID, when the updateRecipe method is called, then an exception should be thrown`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given a non-existing recipe ID, when the deleteRecipe method is called, then an exception should be thrown`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given an invalid recipe object, when the addRecipe method is called, then an exception should be thrown`(){

        TODO("Not Implemented yet")
    }

    @Test
    fun `Given an invalid recipe object, when the updateRecipe method is called, then an exception should be thrown`(){

        TODO("Not Implemented yet")
    }
}