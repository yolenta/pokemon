package id.jelistina.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import id.jelistina.myapplication.databinding.CustomToolbarBinding
import id.jelistina.myapplication.databinding.FragmentHomeBinding
import id.jelistina.myapplication.source.pokemon.PokeModel
import id.jelistina.myapplication.ui.detail.DetailActivity
import id.jelistina.myapplication.ui.detail.DetailFromMineActivity
import id.jelistina.myapplication.ui.pokemon.PokemonAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar : CustomToolbarBinding

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater, container, false)
        bindingToolbar =binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingToolbar.textTitle.text = viewModel.title
        firstPage()
        binding.listPokemons.adapter = pokemonsAdapter
        viewModel.pokemons.observe(viewLifecycleOwner,{
            pokemonsAdapter.add(it.results)
        })

    }


    private val pokemonsAdapter by lazy {
        PokemonAdapter(arrayListOf(), object : PokemonAdapter.OnAdapterListener {
            override fun onClick(pokeModel: PokeModel) {
                startActivity(
                    Intent(requireActivity(), DetailActivity::class.java)
                        .putExtra("detail", pokeModel)
                )
            }
        })
    }

    private fun firstPage(){
        binding.scroll.scrollTo(0, 0)
        viewModel.page = 1
        viewModel.total = 1
        viewModel.fetch()
    }

}