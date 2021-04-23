package com.bbbdev.pokebase.pokedata

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bbbdev.pokebase.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class testAdapter(
    private val context: Context,
    private var data: List<String>
): RecyclerView.Adapter<testAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): testAdapter.ItemViewHolder {

        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.poke_card,parent,false))
    }

    override fun onBindViewHolder(holder: testAdapter.ItemViewHolder, position: Int) {
//       holder.artwork

        holder.oId.text = "#00"+ data[position]
        holder.name.text = "??????"

        holder.chip_a.text = "Fire"
        holder.chip_a.setVisibility(View.VISIBLE)
        holder.chip_b.text = "Dragon"
        holder.chip_b.setVisibility(View.VISIBLE)
        holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_red))
        holder.chip_a.setChipBackgroundColorResource(R.color.type_red)
        holder.chip_b.setChipBackgroundColorResource(R.color.type_red)
        holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_red))


        if(data[position].toInt() > 3){
            holder.chip_a.text = "Grass"
            holder.chip_a.setVisibility(View.VISIBLE)
            holder.chip_a.text = "Poison"
            holder.chip_b.setVisibility(View.VISIBLE)
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.type_green))
            holder.chip_a.setChipBackgroundColorResource(R.color.type_green)
            holder.chip_b.setChipBackgroundColorResource(R.color.type_green)
            holder.shadowArtwork.setBackground(context.getResources().getDrawable(R.drawable.card_green))
        }


        Glide.with(context.applicationContext)
            .load(R.drawable.quest12)
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

        holder.cardOnload.setVisibility(View.INVISIBLE)

    }

    override fun getItemCount(): Int {
//        return title.size

        return data.size
    }
}

