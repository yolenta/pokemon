package id.jelistina.myapplication.ui.mine

import androidx.lifecycle.ViewModel
import id.jelistina.myapplication.source.pokemon.PokemonRepository
import org.koin.dsl.module

val mineViewModel = module {
    factory { MineViewModel(get()) }
}

class MineViewModel(
    repository:PokemonRepository
) : ViewModel() {

    val title = "My Pokemon List"
    val pokemons = repository.db.findAll()

}