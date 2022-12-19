package id.jelistina.myapplication.source.pokemon

import androidx.room.PrimaryKey

data class PokemonDetailModel(
    @PrimaryKey(autoGenerate = false)
val abilities:List<AbilitiesModel>?,
val base_experience:Int? = 0,
//val forms:List<NameURLModel>,
//val game_indices:List<GameIndicesModel>?,
val height:Int? = 0,
val id:Int,
//val is_default:Boolean?,
//val location_area_encounters:String? = "",
val moves:List<MovesModel>,
val name:String? = "",
//val order:Int? = 0,
val species:NameURLModel,
val weight:String? = "",
)

class AbilitiesModel (
    val ability:NameURLModel,
    val is_hidden:Boolean?,
    val slot:Int?,
)

class NameURLModel (
    val name:String? = "",
    val url:String? = "",
)

class GameIndicesModel (
    val ability:NameURLModel,
    val game_index:Int?,
)

class MovesModel(
    val move:NameURLModel,
//    val version_group_details:List<VersionGroupDetailsModel>,
)

class VersionGroupDetailsModel (
    val level_learned_at:Int?,
    val move_learn_method:List<NameURLModel>,
    val version_group:List<NameURLModel>,
)


