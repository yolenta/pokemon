package id.jelistina.myapplication.ui.mine

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.jelistina.myapplication.databinding.CustomToolbarBinding
import id.jelistina.myapplication.databinding.FragmentMineBinding
import id.jelistina.myapplication.source.pokemon.PokeModel
import id.jelistina.myapplication.ui.detail.DetailFromMineActivity
import id.jelistina.myapplication.ui.pokemon.PokemonAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MineFragment : Fragment() {

    private lateinit var binding: FragmentMineBinding
    private lateinit var bindingToolbar : CustomToolbarBinding

    private val viewModel: MineViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMineBinding.inflate(inflater, container, false)
        bindingToolbar =binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingToolbar.textTitle.text = viewModel.title
        binding.listPokemons.adapter =pokemonsAdapter
        viewModel.pokemons.observe(viewLifecycleOwner,{
            pokemonsAdapter.add(it)
        })
    }

    private val pokemonsAdapter by lazy {
        PokemonAdapter(arrayListOf(), object : PokemonAdapter.OnAdapterListener {
            override fun onClick(pokeModel: PokeModel) {
                startActivity(
                    Intent(requireActivity(), DetailFromMineActivity::class.java)
                        .putExtra("detail", pokeModel)
                )
            }
        })
    }
}