package com.pokemon.models


case class Pokemon (
    name: String,
    type1: String,
    type2: String,
    attack: Int,
    defense: Int,
    spAttack: Int,
    spDef: Int,
    legendary: Boolean,
    generation: Int,
    moveset: String,
    evolutionPath: String
)