package id.jelistina.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import id.jelistina.myapplication.source.network.networkModul
import id.jelistina.myapplication.source.persistence.databaseModule
import id.jelistina.myapplication.source.pokemon.repositoryModule
import id.jelistina.myapplication.ui.detail.detailMineModule
import id.jelistina.myapplication.ui.detail.detailModule
import id.jelistina.myapplication.ui.detail.detailViewModel
import id.jelistina.myapplication.ui.home.homeModule
import id.jelistina.myapplication.ui.home.homeViewModel
import id.jelistina.myapplication.ui.mine.mineViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PokemonApp :Application(){
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Timber.e("Run base App")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin{
            androidLogger()
            androidContext(this@PokemonApp)
            modules(
                networkModul,
                repositoryModule,
                homeViewModel,
                homeModule,
                detailModule,
                detailViewModel,
                databaseModule,
                mineViewModel,
                detailMineModule
            )
        }
    }
}