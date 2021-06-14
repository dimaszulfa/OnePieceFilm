package com.dimas.onepiecefilm.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import com.dimas.onepiecefilm.R
import com.dimas.onepiecefilm.core.domain.model.OnePiece
import com.dimas.onepiecefilm.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID = "ID"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<OnePiece>(ID)

        binding.btnBack.setOnClickListener {
            finish()
        }

        bind(data)

    }

    private fun bind(data: OnePiece?) {
        with(binding) {
            data.let {
                if (it != null) {
                    var isFavorite = it.isFavorite
                    state(isFavorite)
                    binding.btnSave.setOnClickListener { view ->
                        isFavorite = !isFavorite
                        viewModel.setFavorite(it, isFavorite)
                        state(isFavorite)
                        if (isFavorite){
                            Snackbar.make(view, "Added to Favorite", Snackbar.LENGTH_SHORT).show()
                        } else {
                            Snackbar.make(view, "Deleted From Favorite", Snackbar.LENGTH_SHORT).show()

                        }

                    }
                    progressBar.isVisible = false
                    tvTittle.text = it.title
                    tvStoryline.text = it.synopsis
                    tvRated.text = it.rated
                    tvStar.text = it.score.toString()
                    if (it.airing) {
                        val isAiring = "Is Airing"
                        tvStatus.text = isAiring
                    } else {
                        val isNotAiring = "Is Not Airing"
                        tvStatus.text = isNotAiring
                    }
                    Glide.with(this@DetailActivity)
                        .load(it.image_url)
                        .transform(CenterCrop(), RoundedCorners(16))
                        .placeholder(ColorDrawable(Color.CYAN))
                        .into(ivPoster)
                }
            }


        }
    }

    private fun state(state: Boolean) {
        if (state == true) {
            binding.btnSave.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_favorite_active
                )
            )


        } else if (state == false) {
            binding.btnSave.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_favorite_inactive
                )
            )
        }

    }
}