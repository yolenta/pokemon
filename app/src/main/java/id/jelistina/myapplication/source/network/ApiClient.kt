package id.jelistina.myapplication.source.network

import id.jelistina.myapplication.source.pokemon.PokemonDetailModel
import id.jelistina.myapplication.source.pokemon.PokemonModel
import retrofit2.http.*

interface ApiClient {
    @GET("pokemon")
    suspend fun fetchPokemon(
    ):PokemonModel
    @GET("pokemon/{id}/")
    suspend fun fetchPokemonDetail(
        @Path("id") id: String,
    ):PokemonDetailModel
}