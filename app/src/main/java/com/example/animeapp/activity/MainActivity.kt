package com.example.animeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.animeapp.AnimeDetailsBottomSheet
import com.example.animeapp.R
import com.example.animeapp.SearchAdapter
import com.example.animeapp.Service.AnimeService
import com.example.animeapp.databinding.ActivityMainBinding
import com.example.animeapp.pojo.Data
import com.example.animeapp.pojo.DataX
import com.example.animeapp.pojo.SearchAnime
import com.example.animeapp.pojo.TopAnime
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val animeService = AnimeService.create()
            val callTop = animeService.getTopAnime()
            callTop.enqueue(object : Callback<TopAnime> {
                override fun onResponse(call: Call<TopAnime>, response: Response<TopAnime>) {
                    if (response.isSuccessful && response.body() != null) {
                        val top = response.body()!!.data
                        if (top != null) {
                            animeRecyclerView.adapter = AnimeAdapter(this@MainActivity, top)
                        } else {
                            Toast.makeText(this@MainActivity, "Top Anime list is null", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Failed to fetch top anime", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<TopAnime>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })


            binding.btnSearch.setOnClickListener {
                val searchAnime = searchInputEditText.text.toString()
                val callSearchAnime = animeService.getSearchAnime(searchAnime)

                callSearchAnime.enqueue(object  : Callback<SearchAnime>{
                    override fun onResponse(
                        call: Call<SearchAnime>,
                        response: Response<SearchAnime>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            val searchAnime = response.body()!!.data
                            if (searchAnime != null) {
                                Toast.makeText(this@MainActivity, searchAnime.toString(), Toast.LENGTH_SHORT).show()
                                Log.i("Show", "onResponse: $searchAnime")

                                animeRecyclerView.adapter = SearchAdapter(this@MainActivity, searchAnime)
                            } else {
                                Toast.makeText(this@MainActivity, "Top Anime list is null", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@MainActivity, "Failed to fetch top anime", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<SearchAnime>, t: Throwable) {
                    }

                })
            }
        }
    }
}

class AnimeAdapter(
    private val parentActivity :AppCompatActivity,
    private val animes : List<Data>
) : RecyclerView.Adapter<AnimeAdapter.CustomViewHolder>(){
    inner class CustomViewHolder(view: View) : ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item_layout,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val anime = animes[position]
        val view = holder.itemView

        val name = view.findViewById<TextView>(R.id.name)
        val image = view.findViewById<ImageView>(R.id.image)

        name.text = anime.title
        val imageUrl = anime.images?.jpg?.imageUrl.toString()
        Picasso.get().load(imageUrl).into(image)

        view.setOnClickListener {
            AnimeDetailsBottomSheet(anime).apply {
                show(parentActivity.supportFragmentManager,"AnimeDetailsBottomSheet")
            }
        }
    }
    override fun getItemCount(): Int  = animes.size
}