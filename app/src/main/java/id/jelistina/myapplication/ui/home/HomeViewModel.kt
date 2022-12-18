package id.jelistina.myapplication.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.jelistina.myapplication.source.pokemon.PokemonModel
import id.jelistina.myapplication.source.pokemon.PokemonRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel (
    val repository: PokemonRepository
): ViewModel() {

    val title = "Pokemon List"
    var query = ""
    var page = 1
    var total = 1


    val pokemons by lazy { MutableLiveData<PokemonModel>() }
    val message by lazy {MutableLiveData<String>()}
    val loading by lazy {MutableLiveData<Boolean>()}

    init {
        message.value = ""
        fetch()
    }

    fun fetch(){
        loading.value=true
        viewModelScope.launch {
            try {
                val respone = repository.fetch()
                pokemons.value = respone
                loading.value=false
            }catch (e :Exception){
                message.value= "Terjadi Kesalahan"
            }
        }
    }


}


