package com.example.animeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapp.pojo.Data
import com.example.animeapp.pojo.DataX
import com.squareup.picasso.Picasso

class SearchAdapter (
    private val parentActivity : AppCompatActivity,
    private val animes : List<DataX>
) : RecyclerView.Adapter<SearchAdapter.CustomViewHolder>()
{
    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item_layout, parent, false)
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
    }

    override fun getItemCount(): Int = animes.size
}

