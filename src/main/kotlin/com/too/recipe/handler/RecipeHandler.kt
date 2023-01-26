package com.too.recipe.handler

import com.too.recipe.dto.RecipeDTO
import com.too.recipe.service.RecipeService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.created
import org.springframework.web.reactive.function.server.ServerResponse.ok
import java.net.URI

@Component
class RecipeHandler(private val recipeService: RecipeService) {

    suspend fun getAllRecipes(serverRequest: ServerRequest): ServerResponse {
        return ok().bodyValueAndAwait(recipeService.getAllRecipes())
    }

    suspend fun getRecipeById(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariable("id").toLong()
        return ok().bodyValueAndAwait(recipeService.getRecipeById(id))
    }

    suspend fun addRecipe(serverRequest: ServerRequest): ServerResponse {
        val body = serverRequest.awaitBody(RecipeDTO::class)
        val recipe = recipeService.addRecipe(body)
        return created(URI.create("/recipe/" + recipe.id)).bodyValueAndAwait(recipe)
    }

    suspend fun updateRecipe(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariable("id").toLong()
        val recipeDTO = serverRequest.awaitBody(RecipeDTO::class)
        val updateRecipe = recipeService.updateRecipe(id, recipeDTO)
        return ok().bodyValueAndAwait(updateRecipe)
    }

    suspend fun deleteRecipe(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariable("id").toLong()
        recipeService.deleteRecipe(id)
        return ServerResponse.noContent().buildAndAwait()
    }
}
