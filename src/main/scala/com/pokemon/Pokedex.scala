package com.pokemon

import com.pokemon.services.PokemonService
import com.pokemon.services.PokemonServiceOnHttp
import com.pokemon.services.PokemonAsyncServiceOnHttp
import monix.execution.Scheduler.Implicits.global

object Pokedex {
  /**
   * API url  https://pokeapi.co/api/v2/
   */
  def main(args: Array[String]): Unit =
    val pokemonSvc = new PokemonAsyncServiceOnHttp(http = new AsyncHttp)

    pokemonSvc.getPokemons()
      .runAsync {
        case Left(err) =>
          println(s"Got err: ${err.toString}")
        case Right(pokemons) =>
          println(pokemons.head)
      }
}
