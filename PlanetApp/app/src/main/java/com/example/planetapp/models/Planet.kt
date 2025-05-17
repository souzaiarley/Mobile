package com.example.planetapp.models

import com.example.planetapp.R

data class Planet(
    val id: Int,
    val name: String,
    val type: String,
    val galaxy: String,
    val distanceFromSun: String,
    val diameter: String,
    val characteristics: String,
    val imageRes: Int,
    var isFavorite: Boolean = false
)

val planetList = listOf(
    Planet(
        id = 1,
        name = "Mercury",
        type = "Terrestrial",
        galaxy = "Milky Way",
        distanceFromSun = "57.9 million km",
        diameter = "4,879 km",
        characteristics = "Smallest planet, no atmosphere, very hot surface.",
        imageRes = R.drawable.mercurio
    ),
    Planet(
        id = 2,
        name = "Venus",
        type = "Terrestrial",
        galaxy = "Milky Way",
        distanceFromSun = "108.2 million km",
        diameter = "12,104 km",
        characteristics = "Thick toxic atmosphere, hottest planet.",
        imageRes = R.drawable.venus
    ),
    Planet(
        id = 3,
        name = "Earth",
        type = "Terrestrial",
        galaxy = "Milky Way",
        distanceFromSun = "149.6 million km",
        diameter = "12,742 km",
        characteristics = "Supports life, has water and atmosphere.",
        imageRes = R.drawable.terra
    ),
    Planet(
        id = 4,
        name = "Mars",
        type = "Terrestrial",
        galaxy = "Milky Way",
        distanceFromSun = "227.9 million km",
        diameter = "6,779 km",
        characteristics = "Known as the Red Planet, has the largest volcano in the solar system.",
        imageRes = R.drawable.marte
    ),
    Planet(
        id = 5,
        name = "Jupiter",
        type = "Gas Giant",
        galaxy = "Milky Way",
        distanceFromSun = "778.5 million km",
        diameter = "139,820 km",
        characteristics = "Largest planet, mostly hydrogen and helium, has a giant red storm.",
        imageRes = R.drawable.jupiter
    ),
    Planet(
        id = 6,
        name = "Saturn",
        type = "Gas Giant",
        galaxy = "Milky Way",
        distanceFromSun = "1.43 billion km",
        diameter = "116,460 km",
        characteristics = "Known for its prominent ring system.",
        imageRes = R.drawable.saturno
    ),
    Planet(
        id = 7,
        name = "Uranus",
        type = "Ice Giant",
        galaxy = "Milky Way",
        distanceFromSun = "2.87 billion km",
        diameter = "50,724 km",
        characteristics = "Has a blue-green color due to methane, rotates on its side.",
        imageRes = R.drawable.urano
    ),
    Planet(
        id = 8,
        name = "Neptune",
        type = "Ice Giant",
        galaxy = "Milky Way",
        distanceFromSun = "4.5 billion km",
        diameter = "49,244 km",
        characteristics = "Farthest known planet, strong winds and storms.",
        imageRes = R.drawable.netuno
    )
)
