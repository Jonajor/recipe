package com.too.recipe.controller

import com.too.recipe.handler.RecipeHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RecipeRouter(private val recipeHandler: RecipeHandler) {

    @Bean
    fun routes() = coRouter {
        "/api/recipes".nest {
            GET("/", recipeHandler::getAllRecipes)
            GET("/{id}", recipeHandler::getRecipeById)
            POST("/", recipeHandler::addRecipe)
            PUT("/{id}", recipeHandler::updateRecipe)
            DELETE("/{id}", recipeHandler::deleteRecipe)
        }
    }
}