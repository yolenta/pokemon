package id.jelistina.myapplication.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.jelistina.myapplication.source.pokemon.PokeModel
import id.jelistina.myapplication.source.pokemon.PokemonRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val detailViewModel = module {
    factory { DetailViewModel(get()) }
}

class DetailViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    val isCatch by lazy { MutableLiveData<Int>(0) }

    var title = "Detail"
    fun find(pokeModel: PokeModel){
        viewModelScope.launch {
            isCatch.value = repository.find( pokeModel )
        }
        title = pokeModel.name+" detail"
    }

    fun catch (pokeModel: PokeModel) {
        viewModelScope.launch {
            if (isCatch.value == 0) {
                repository.save(pokeModel)
            }
            else {
                repository.remove(pokeModel)}
            find( pokeModel )
        }
    }

    fun catchNickName (pokeModel: PokeModel) {
        viewModelScope.launch {
            repository.update(pokeModel)
        }
    }

}