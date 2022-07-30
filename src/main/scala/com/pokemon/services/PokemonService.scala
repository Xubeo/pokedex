package com.pokemon.services

import com.pokemon.models.Pokemon
import monix.eval.Task
import com.pokemon.Http
import spray.json._
import DefaultJsonProtocol._
import com.pokemon.json.JsonFormatters._
import com.pokemon.AsyncHttp

trait PokemonService {

  def getPokemons(): Seq[Pokemon]


}

trait PokemonAsyncService {

  def getPokemons(): Task[Seq[Pokemon]]

}

class PokemonServiceOnHttp(http: Http) extends PokemonService {

  def getPokemons(): Seq[Pokemon] = {
    http.get("https://pokeapi.co/api/v2/pokemon")
      .parseJson
      .asJsObject
      .fields("pokemons")
      .convertTo[Seq[Pokemon]]
  }
  
}

class PokemonAsyncServiceOnHttp(http: AsyncHttp) extends PokemonAsyncService {

  def getPokemons(): Task[Seq[Pokemon]] =
    http.get("https://pokeapi.co/api/v2/pokemon")
      .map(content => content
        .parseJson.asJsObject
        .fields("pokemons")
        .convertTo[Seq[Pokemon]]
      )
   
}