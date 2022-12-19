package id.jelistina.myapplication.ui.pokemon

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.jelistina.myapplication.databinding.AdapterPokemonBinding
import id.jelistina.myapplication.source.pokemon.PokeModel
class PokemonAdapter(
    val pokemonList: ArrayList<PokeModel>,
    var listener: OnAdapterListener,
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

    private  val items = arrayListOf<TextView>()

    class ViewHolder (val binding: AdapterPokemonBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(pokemon:PokeModel){
            binding.pokemon=pokemon
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent,false
        )
    )

    @SuppressLint("BinaryOperationInTimber")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon)
        holder.binding.tvName.text = pokemonList.get(position).name
        holder.itemView.setOnClickListener {
            listener.onClick( pokemon )
        }
    }

    override fun getItemCount()=pokemonList.size

    fun add(data: List<PokeModel>){
        pokemonList.clear()
        pokemonList.addAll(data)

        for (item in pokemonList) {
            val id = item.url.toString().substringAfter("https://pokeapi.co/api/v2/pokemon/").substringBefore("/")
            item.urlToImage="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png"
            item.id=id
        }
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(pokeModel: PokeModel)
    }
}