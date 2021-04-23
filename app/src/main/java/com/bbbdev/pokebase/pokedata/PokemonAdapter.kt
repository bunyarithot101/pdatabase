package com.bbbdev.pokebase.pokedata

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bbbdev.pokebase.ListPokemonFragmentDirections
import com.bbbdev.pokebase.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class PokemonAdapter(
    private val context: Context,
//    private val dataset: List<pokemon>,
    private val onItemClicked: onCardPokeClickListener,
) : RecyclerView.Adapter<PokemonAdapter.ItemViewHolder>(){

    private var pokemonList = emptyList<pokemon>()



    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.lName)
        //val url: TextView = view.findViewById(R.id.lUrl)

        val oId: TextView = view.findViewById(R.id.lNumber)
        val artwork: ImageView = view.findViewById(R.id.artwork)
        val shadowArtwork: FrameLayout = view.findViewById(R.id.shadowArtwork)
        var card: CardView = view.findViewById(R.id.pokemon_card)

        val chipgroup: ChipGroup = view.findViewById(R.id.chipGroup)
        val chip_a: Chip = view.findViewById(R.id.chip_test_A)
        val chip_b: Chip = view.findViewById(R.id.chip_test_B)

        val cardOnload: ProgressBar = view.findViewById(R.id.card_onload)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke_card, parent, false)

        return ItemViewHolder(adapterLayout)
    }


    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 300
        view.startAnimation(anim)
    }

    fun setData(list: List<pokemon>){
        this.pokemonList = list


        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        setFadeAnimation(holder.itemView);

        val items = pokemonList[position]

        holder.name.text = items.name.toUpperCase()
        holder.oId.text = "#" + String.format("%03d", items.number?.toInt())
        holder.cardOnload.setVisibility(View.VISIBLE)

        holder.itemView.setOnClickListener {
            onItemClicked.onItemClick(items, position)
            val action = ListPokemonFragmentDirections.actionListPokemonFragmentToDetailPokemonFragment(items)
            holder.itemView.findNavController().navigate(action)

        }

        loadingMore(holder, position)
        loadingColor(holder, items.color)




    }


    override fun getItemCount() = pokemonList.size



    fun loadingMore(holder: ItemViewHolder, position: Int){
        val data = pokemonList[position]

        Glide.with(context.applicationContext)
            .load(data.imgResource)
            //            .load("https://storage.googleapis.com/swapgap-bucket/post/5190314163699712-260a6c54-19e7-4248-a0a3-65d97b8e2cbc")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            //            .placeholder(circularProgressDrawable)
            //            .error(R.drawable.error)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    holder.cardOnload.setVisibility(View.INVISIBLE)

                    return false
                }


            })
            .dontAnimate()
            .override(200, 200)
            .into(holder.artwork);

        /*TYPE*/

        holder.chip_a.text = data.typeA
        holder.chip_a.setVisibility(View.VISIBLE)
        holder.chip_b.text = data.typeB
        holder.chip_b.setVisibility(View.VISIBLE)

        if(data.typeB == null){
            holder.chip_b.setVisibility(View.GONE)
        }

    }

    fun loadingColor(holder: ItemViewHolder, color: String) {

        when (color) {
            "black" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_black))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_black)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_black)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_black))
            }
            "blue" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_blue))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_blue)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_blue)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_blue))
            }
            "brown" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_brown))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_brown)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_brown)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_brown))
            }
            "gray" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_gray))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_gray)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_gray)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_gray))
            }
            "green" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_green))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_green)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_green)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_green))
            }
            "pink" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_pink))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_pink)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_pink)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_pink))
            }
            "purple" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_purple))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_purple)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_purple)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_purple))
            }
            "red" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_red))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_red)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_red)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_red))
            }
            "white" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_white))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_white)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_white)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_white))
            }
            "yellow" -> {
                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_yellow))
                holder.chip_a.setChipBackgroundColorResource(R.color.type_yellow)
                holder.chip_b.setChipBackgroundColorResource(R.color.type_yellow)
                holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_yellow))
            }

        }

    }


}
