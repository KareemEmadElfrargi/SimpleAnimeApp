package com.example.animeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.animeapp.databinding.AnimeDetailsBottomSheetLayoutBinding
import com.example.animeapp.pojo.Data
import com.example.animeapp.pojo.DataX
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class AnimeDetailsBottomSheet (
    private val anime : Data

) :BottomSheetDialogFragment(){


    lateinit var binding : AnimeDetailsBottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AnimeDetailsBottomSheetLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Picasso.get().load(anime.images?.jpg?.imageUrl.toString()).into(image)
            name.text = anime.title
            rating.text = anime.score.toString()
            pgRating.text = anime.rating
            episodes.text = "${anime.episodes} episodes"
            synopsis.text = anime.synopsis
            dates.text = anime.year.toString()


        }
    }
}