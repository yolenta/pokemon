package id.jelistina.myapplication.source.network

import id.jelistina.myapplication.source.pokemon.PokemonModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("pokemon")
    suspend fun fetchPokemon(
    ):PokemonModel
}