package id.jelistina.myapplication.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import id.jelistina.myapplication.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, urlString: String?){
    urlString?.let {
        Glide.with(imageView)
            .load( urlString )
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into( imageView )
    }
}