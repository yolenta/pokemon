package id.jelistina.myapplication.source.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.jelistina.myapplication.source.pokemon.PokeModel
import id.jelistina.myapplication.source.pokemon.PokemonDoa
import id.jelistina.myapplication.util.SourceConverter

@Database(
    entities = [PokeModel::class],
version = 2,
    exportSchema = false
)

abstract class DatabaseClient: RoomDatabase() {
    abstract val pokemonDoa:PokemonDoa
}