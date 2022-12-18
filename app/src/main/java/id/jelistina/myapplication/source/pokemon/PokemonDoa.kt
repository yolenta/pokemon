package id.jelistina.myapplication.source.pokemon

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PokemonDoa {

    @Query("SELECT * FROM tablePokemon")
    fun findAll(): LiveData<List<PokeModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(pokeModel: PokeModel)

    @Query("SELECT COUNT(*) FROM tablePokemon WHERE url=:urlid")
    suspend fun find(urlid: String): Int

    @Delete
    suspend fun remove(pokeModel: PokeModel)
}