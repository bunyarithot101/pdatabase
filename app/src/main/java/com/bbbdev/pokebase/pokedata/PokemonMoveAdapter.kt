package com.bbbdev.pokebase.pokedata

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bbbdev.pokebase.R
import retrofit2.Response
import timber.log.Timber

class PokemonMoveAdapter(
    private val context: Context,
//    private val dataset: List<moveList>,
):RecyclerView.Adapter<PokemonMoveAdapter.ItemViewHolder>() {

//    private var pokemonMoveData : List<pokemonMoveData>? =  null
    private var pokemonMoveData = emptyList<pokemonMoveData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PokemonMoveAdapter.ItemViewHolder {

        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
//            .inflate(R.layout.table_move_layout, parent, false)
            .inflate(R.layout.table_move_layout, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val move: TextView = view.findViewById(R.id.row_move)
        val power: TextView = view.findViewById(R.id.row_power)
        val acc: TextView = view.findViewById(R.id.row_acc)
        val type: TextView = view.findViewById(R.id.row_type)

    }

    override fun onBindViewHolder(holder: PokemonMoveAdapter.ItemViewHolder, position: Int) {
        val item = pokemonMoveData[position]

        holder.move.text =  item.move.toUpperCase()
        holder.power.text = item.power
        holder.acc.text = item.accuracy
        holder.type.text = item.type

        getColor(item.type, holder)


        val regex = "\\W+".toRegex()
        val beautiful = item.move.toString()

        val head = regex.split(beautiful).first()
        val body = regex.split(beautiful).last()

        if(head != body){
            holder.move.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        }

    }

    fun setData(pokemonMoveData: List<pokemonMoveData>){
        this.pokemonMoveData = pokemonMoveData

        notifyDataSetChanged()
    }


    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }

    override fun getItemCount() = pokemonMoveData.size

    fun getColor(type: String, holder: ItemViewHolder) {
        setFadeAnimation(holder.itemView);

        when (type) {
            "normal" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_normal))
            }
            "fighting" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_fighting))
            }
            "flying" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_flying))
            }
            "poison" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_poison))
            }
            "ground" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_ground))
            }
            "rock" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_rock))
            }
            "bug" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_bug))
            }
            "ghost" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_ghost))
            }
            "steel" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_steel))
            }
            "fire" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_fire))
            }
            "water" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_water))
            }
            "grass" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_grass))
            }
            "electric" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_electric))
            }
            "psychic" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_psychic))
            }
            "ice" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_ice))
            }
            "dragon" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_dragon))
            }
            "dark" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_dark))
            }
            "fairy" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_fairy))
            }
            "unknown" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_unknown))
            }
            "shadow" -> {
                holder.type.setBackgroundDrawable(ContextCompat.getDrawable(context.applicationContext,
                    R.drawable.table_type_shadow))
            }


        }
    }
}

