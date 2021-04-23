package com.bbbdev.pokebase.pokedata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bbbdev.pokebase.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class PokemonAllAdapter(
    private val context: Context,
    private val dataset: List<resultsList>,
//    private val onItemClicked: onCardPokeClickListener,
): RecyclerView.Adapter<PokemonAllAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.lName)
        //val url: TextView = view.findViewById(R.id.lUrl)

        val oId: TextView = view.findViewById(R.id.lNumber)
        val artwork: ImageView = view.findViewById(R.id.artwork)
        var card: CardView = view.findViewById(R.id.pokemon_card)

        val chipgroup: ChipGroup = view.findViewById(R.id.chipGroup)
        val chip_a: Chip = view.findViewById(R.id.chip_test_A)
        val chip_b: Chip = view.findViewById(R.id.chip_test_B)

        val cardOnload: ProgressBar = view.findViewById(R.id.card_onload)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PokemonAllAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke_card, parent, false)


        return ItemViewHolder(adapterLayout)
    }



    override fun onBindViewHolder(holder: PokemonAllAdapter.ItemViewHolder, position: Int) {
        val items = dataset[position]


        val pokeApi = PokemonApi.retrofitService.getDetailWithString(items.name)
        pokeApi.enqueue(object : retrofit2.Callback<PokemonResource> {
            override fun onResponse(
                call: Call<PokemonResource>,
                response: Response<PokemonResource>
            ) {
                val data = response.body()!!

                val regex = "\\W+".toRegex()
                val beautiful =  data.species.url.toString().dropLast(1)
                val numberPokemon = regex.split(beautiful).last().toInt()

                holder.name.text = data.name.toUpperCase()
                holder.oId.text = "#" + String.format("%03d", numberPokemon)

                Glide.with(context)
                    .load(data.sprites.other.officialartwork.front_default)
                    .override(200, 200)
                    .into(holder.artwork);



            }
            override fun onFailure(call: Call<PokemonResource>, t: Throwable) {

            }
        })

    }

    override fun getItemCount() = dataset.size


}
