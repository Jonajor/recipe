package com.too.recipe.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class RecipeNotFoundException(override val message: String?) : RuntimeException(message)