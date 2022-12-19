package id.jelistina.myapplication.source.pokemon

import id.jelistina.myapplication.source.network.ApiClient
import org.koin.dsl.module

val repositoryModule = module{
    factory { PokemonRepository(get(),get()) }
}

class PokemonRepository(
    private val api:ApiClient,
    val db:PokemonDoa
){
    suspend fun fetch(
    ):PokemonModel{
        return api.fetchPokemon()
    }

    suspend fun find(pokeModel: PokeModel) = db.find(pokeModel.url)

    suspend fun save(pokeModel: PokeModel) {
        db.save( pokeModel )
    }

    suspend fun remove(pokeModel: PokeModel) {
        db.remove( pokeModel )
    }

    suspend fun update(pokeModel: PokeModel) = db.update(pokeModel.nickName,pokeModel.url)
}