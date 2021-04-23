package com.bbbdev.pokebase.pokedata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.TintableBackgroundView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bbbdev.pokebase.R
import com.bbbdev.pokebase.detailPokemonFragmentArgs
import com.google.android.material.chip.Chip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class PokemonEvolAdapter(
    private val context: Context,
    private val dataset: chainList,
    private val color: String,
    private val args: detailPokemonFragmentArgs?,
    private val onItemCardEvoleClick: onCardEvol1st
) : RecyclerView.Adapter<PokemonEvolAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val number: TextView = view.findViewById(R.id.evol_number)
        val name: TextView = view.findViewById(R.id.evol_name)
        val imgView: ImageView = view.findViewById(R.id.evol_image)
        val condition: TextView = view.findViewById(R.id.evol_condition)

        val chip_a: Chip = view.findViewById(R.id.evol_chip_A)
        val chip_b: Chip = view.findViewById(R.id.evol_chip_B)
        val frame: FrameLayout = view.findViewById(R.id.evol_frame)
        val evolCard :CardView = view.findViewById(R.id.evol_card)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.poke_evol, parent, false)



        return ItemViewHolder(adapterLayout)
    }




    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val items = dataset.species.name[position]
        holder.name.text = dataset.species.name.toUpperCase()
        holder.condition.text = dataset.evolvesTo[position].evolutionDetails.first().trigger.name

        holder.condition.isVisible = false

        loadArtwork(holder, dataset.species.name)
        getNumber(dataset.species.name,holder)

        loadColor(color,holder)

//        holder.itemView.setOnClickListener {
//            onItemCardEvoleClick.onCardEvol1stClick(dataset.species.name)
//        }


        if(args!!.pokemon.name != dataset.species.name){
            holder.itemView.setOnClickListener {
                onItemCardEvoleClick.onCardEvol1stClick(dataset.species.name)
            }
        }

    }

    fun loadColor(color: String, holder: ItemViewHolder){
                when (color) {
                    "black" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_black))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_black)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_black)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_black))
                    }
                    "blue" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_blue))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_blue)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_blue)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_blue))
                    }
                    "brown" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_brown))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_brown)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_brown)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_brown))
                    }
                    "gray" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_gray))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_gray)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_gray)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_gray))

                    }
                    "green" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_green))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_green)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_green)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_green))
                    }
                    "pink" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_pink))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_pink)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_pink)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_pink))
                    }
                    "purple" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_purple))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_purple)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_purple)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_purple))
                    }
                    "red" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_red))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_red)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_red)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_red))
                    }
                    "white" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_white))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_white)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_white)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_white))
                    }
                    "yellow" -> {
                        holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_yellow))
                        holder.chip_a.setChipBackgroundColorResource(R.color.type_yellow)
                        holder.chip_b.setChipBackgroundColorResource(R.color.type_yellow)
                        holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_yellow))
                    }

                }


    }



    fun loadArtwork(holder: ItemViewHolder, Pokename: String) {
        var pokeAPi = PokemonApi.retrofitService.getDetailWithString(Pokename)

        pokeAPi.enqueue(object : retrofit2.Callback<PokemonResource> {
            override fun onResponse(call: Call<PokemonResource>, response: Response<PokemonResource>) {
                if (!response.isSuccessful) {

                    return
                }

                val data = response.body()!!

                Glide.with(context)
                    .load(data.sprites.other.officialartwork.front_default)
                    .dontAnimate()
                    .override(200, 200)
                    .into(holder.imgView);

            }

            override fun onFailure(call: Call<PokemonResource>, t: Throwable) {
               Timber.i("ERR")
            }
        })


    }

    fun getNumber(name: String, holder: ItemViewHolder){
        var pokeApi = PokemonApi.retrofitService.getDetailWithString(name)
        pokeApi.enqueue(object : Callback<PokemonResource> {
            override fun onResponse(
                call: Call<PokemonResource>,
                response: Response<PokemonResource>,
            ) {
                if (!response.isSuccessful) {

                    holder.number.text = ""

                    return
                }
                var data =  response.body()!!

                holder.number.text = "#"+ String.format("%03d", data.id.toInt())

//                holder.itemView.setOnClickListener {
//                    onItemCardEvoleClick.onCardEvol1stClick(0,data.id.toInt())
//                }

                getTypes(data.id.toInt(), holder)
            }

            override fun onFailure(call: Call<PokemonResource>, t: Throwable) {
                Timber.i("ERR")
            }

        })



    }

    fun getTypes(PokemonNumber: Int, holder: ItemViewHolder){

        val pokeApi = PokemonApi.retrofitService.getDetail(PokemonNumber)

        pokeApi.enqueue(object : Callback<PokemonResource> {
            override fun onFailure(call: Call<PokemonResource>, t: Throwable) {
                Timber.i("err")
            }

            override fun onResponse(call: Call<PokemonResource>, response: Response<PokemonResource>) {
                if (!response.isSuccessful) {

                    return
                }
                var data = response.body()!!

                /*TYPE*/
                val scale = context.resources.displayMetrics.density

                when (data.types.size) {
                    1 -> {
                        for ((position, case) in data.types.withIndex()) {
                            when (position) {
                                0 -> {
                                    holder.chip_a.text = case.type.name
                                    holder.chip_a.setVisibility(View.VISIBLE)
                                    holder.chip_b.setVisibility(View.GONE)
                                }
                            }

                        }
                    }
                    2 -> {
                        for ((position, case) in data.types.withIndex()) {
                            when (position) {
                                0 -> {
                                    holder.chip_a.text = case.type.name
                                    holder.chip_a.setVisibility(View.VISIBLE)

                                }
                                1 -> {
                                    holder.chip_b.text = case.type.name
                                    holder.chip_b.setVisibility(View.VISIBLE)

                                }
                            }

                        }
                    }
                }


            }

        })

    }


}








//package com.bbbdev.pokebase.pokedata
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.FrameLayout
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.cardview.widget.CardView
//import androidx.core.content.ContentProviderCompat.requireContext
//import androidx.core.content.ContextCompat
//import androidx.core.view.isVisible
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bbbdev.pokebase.R
//import com.bbbdev.pokebase.data.PokemonViewModel
//import com.bbbdev.pokebase.detailPokemonFragmentArgs
//import com.bbbdev.pokebase.detailPokemonFragmentDirections
//import com.google.android.material.chip.Chip
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import timber.log.Timber
//
//
//class PokemonEvolAdapter(
//    private val context: Context,
//    private val onCardEvolClick:onCardEvolClick
//) : RecyclerView.Adapter<PokemonEvolAdapter.ItemViewHolder>() {
//
//    private var pokemonList:detailPokemonFragmentArgs? = null
//    private var chainlist = emptyList<chainList>()
//
//    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//
//        val number: TextView = view.findViewById(R.id.evol_number)
//        val name: TextView = view.findViewById(R.id.evol_name)
//        val imgView: ImageView = view.findViewById(R.id.evol_image)
//        val condition: TextView = view.findViewById(R.id.evol_condition)
//
//        val chip_a: Chip = view.findViewById(R.id.evol_chip_A)
//        val chip_b: Chip = view.findViewById(R.id.evol_chip_B)
//        val frame: FrameLayout = view.findViewById(R.id.evol_frame)
//        val evolCard :CardView = view.findViewById(R.id.evol_card)
//
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//        val adapterLayout = LayoutInflater.from(parent.context)
//            .inflate(R.layout.poke_evol, parent, false)
//
//
//
//        return ItemViewHolder(adapterLayout)
//    }
//
//    fun setData(list: detailPokemonFragmentArgs?, data:chainList){
//        this.pokemonList = list
//        this.chainlist = listOf(data)
//
//
//        notifyDataSetChanged()
//    }
//
//
//
//    override fun getItemCount() = 1
//
//    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//
//        val items = chainlist[position]
//        holder.name.text = items.species.name.toUpperCase()
//        holder.condition.text = items.evolvesTo[position].evolutionDetails.first().trigger.name
//        holder.condition.isVisible = false
//
//        loadArtwork(holder, items.species.name)
//        getNumber(items.species.url,holder)
//
//        var color = this.pokemonList!!.pokemon.color
//
//        loadColor(color,holder)
//
//        getTypes(holder)
//
//        holder.itemView.setOnClickListener {
//            val action = detailPokemonFragmentDirections.actionDetailPokemonFragmentSelf(this.pokemonList!!.pokemon)
//            holder.itemView.findNavController().navigate(action)
//        }
//
//    }
//
//    fun loadColor(color: String, holder: ItemViewHolder){
//
//        when (color) {
//            "black" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_black))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_black)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_black)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_black))
//            }
//            "blue" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_blue))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_blue)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_blue)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_blue))
//            }
//            "brown" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_brown))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_brown)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_brown)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_brown))
//            }
//            "gray" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_gray))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_gray)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_gray)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_gray))
//
//            }
//            "green" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_green))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_green)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_green)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_green))
//            }
//            "pink" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_pink))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_pink)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_pink)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_pink))
//            }
//            "purple" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_purple))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_purple)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_purple)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_purple))
//            }
//            "red" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_red))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_red)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_red)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_red))
//            }
//            "white" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_white))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_white)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_white)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_white))
//            }
//            "yellow" -> {
//                holder.evolCard.setCardBackgroundColor(context.getResources().getColor(R.color.type_yellow))
//                holder.chip_a.setChipBackgroundColorResource(R.color.type_yellow)
//                holder.chip_b.setChipBackgroundColorResource(R.color.type_yellow)
//                holder.frame.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext, R.drawable.evol_yellow))
//            }
//
//        }
//
//
//    }
//
//
//
//    fun loadArtwork(holder: ItemViewHolder, Pokename: String) {
//        var pokeAPi = PokemonApi.retrofitService.getDetailWithString(Pokename)
//
//        pokeAPi.enqueue(object : retrofit2.Callback<PokemonResource> {
//            override fun onResponse(call: Call<PokemonResource>, response: Response<PokemonResource>) {
//                if (!response.isSuccessful) {
//
//                    return
//                }
//
//                val data = response.body()!!
//
//                Glide.with(context)
//                    .load(data.sprites.other.officialartwork.front_default)
//                    .dontAnimate()
//                    .override(200, 200)
//                    .into(holder.imgView);
//
//            }
//
//            override fun onFailure(call: Call<PokemonResource>, t: Throwable) {
//                Timber.i("ERR")
//            }
//        })
//
//
//    }
//
//    fun getNumber(url: String, holder: ItemViewHolder){
//        val regex = "\\W+".toRegex()
//        val beautiful = url.toString().dropLast(1)
//        val genThisPokemon = regex.split(beautiful).last().toInt()
//
//        holder.number.text = "#"+ String.format("%03d", genThisPokemon)
//
//    }
//
//    fun getTypes(holder: ItemViewHolder){
//        holder.chip_a.text =  this.pokemonList!!.pokemon.typeA
//        holder.chip_a.setVisibility(View.VISIBLE)
//
//        holder.chip_b.text = this.pokemonList!!.pokemon.typeB
//        holder.chip_b.setVisibility(View.VISIBLE)
//        if(this.pokemonList!!.pokemon.typeB == null){
//            holder.chip_b.setVisibility(View.GONE)
//        }
//    }
//
//
//
//
//}