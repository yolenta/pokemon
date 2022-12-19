package id.jelistina.myapplication.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import id.jelistina.myapplication.R
import id.jelistina.myapplication.databinding.ActivityDetailBinding
import id.jelistina.myapplication.databinding.CustomToolbarBinding
import id.jelistina.myapplication.source.pokemon.PokeModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { DetailFromMineActivity() }
}

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var bindingToolbar : CustomToolbarBinding
    private val viewModel: DetailViewModel by viewModel()
    private val detail by lazy { intent.getSerializableExtra("detail") as PokeModel }

    val nickName by lazy { MutableLiveData<String>("") }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindingToolbar = binding.toolbar
        setSupportActionBar(bindingToolbar.container)
        supportActionBar!!.apply {
            title=""
            setDisplayHomeAsUpEnabled(true)
        }
        viewModel.fetch(detail.id)
        viewModel.pokemonDetail.observe(this,{
            var textmoves=""
            var koma=","
            for (i in it.moves) {
                if(it.moves.indexOf(i)==(it.moves.size-1)){
                    koma="."
                }
                textmoves+= it.moves.get(it.moves.indexOf(i)).move.name+koma
            }
            binding.tvDetail.setText(
                "Name :"+it.name+"\n"+
                        "Weight :"+it.weight+"\n"+
                        "Base experience :"+it.base_experience+"\n"+
                        "Height :"+it.height+"\n"+
                        "Species : "+it.species.name+"\n"+
                        "Moves : \n"+textmoves
            )

        })

        detail?.let {

            viewModel.find(it)
            bindingToolbar.textTitle.text = viewModel.title
            binding.tvName.text = detail.name
            binding.pokemon=detail
            binding.progressTop.visibility = View.GONE
            val btCatchAction= binding.btCatch
            val btReleaseAction= binding.btRelease

            btCatchAction.setOnClickListener(){
                var probability = Math.random()
                if(probability<0.5) {
                    viewModel.catch(
                        detail
                    )
                    Toast.makeText(applicationContext,"Caught Success", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(applicationContext,"Try again", Toast.LENGTH_SHORT).show()
                }
            }

            btReleaseAction.setOnClickListener(){
                viewModel.catch(
                    detail
                )
                Toast.makeText(applicationContext,"Release Success", Toast.LENGTH_LONG).show()
            }

            val etNickName = binding.etNickName
            val btNickName = binding.btNickName
            etNickName.visibility = View.GONE
            btNickName.visibility = View.GONE
            viewModel.isCatch.observe(this,{
                if(it==0) {
                    binding.btCatch.isEnabled = true
                    binding.btCatch.setBackgroundColor(R.color.teal_200)
                    binding.btRelease.isEnabled = false
                    binding.btRelease.setBackgroundColor(R.color.purple_500)
                }
                else {
                    binding.btCatch.isEnabled = false
                    binding.btCatch.setBackgroundColor(R.color.purple_500)
                    binding.btRelease.isEnabled = true
                    binding.btRelease.setBackgroundColor(R.color.teal_200)
                }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}