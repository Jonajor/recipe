package com.too.recipe.model

import jakarta.persistence.*

@Entity
@Table(name = "recipe")
data class Recipe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    val ingredients: List<String>,
    val cuisine: String,
    val difficulty: Int
)