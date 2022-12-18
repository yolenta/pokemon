package id.jelistina.myapplication.source.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class PokemonModel(
val count:Int,
val next:String,
val previous:String,
val results:List<PokeModel>,
)

@Entity(tableName = "tablePokemon")
data class PokeModel(
    @PrimaryKey(autoGenerate = false)
    val name:String,
    val url:String,
    var urlToImage:String? = "",
    var nickName:String? = "",
) : Serializable