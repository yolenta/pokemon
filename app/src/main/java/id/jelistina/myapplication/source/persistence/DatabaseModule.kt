package id.jelistina.myapplication.source.persistence

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import id.jelistina.myapplication.source.pokemon.PokemonDoa
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { providePokemon(get()) }
}

fun provideDatabase(application: Application):DatabaseClient{
    return Room.databaseBuilder(
        application,
        DatabaseClient::class.java,
        "myPokemons.db"
    ).fallbackToDestructiveMigration()
        .build()
}
fun providePokemon(databaseClient: DatabaseClient):PokemonDoa{
    return databaseClient.pokemonDoa
}