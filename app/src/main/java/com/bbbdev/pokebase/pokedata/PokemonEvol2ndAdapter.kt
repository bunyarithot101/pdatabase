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
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bbbdev.pokebase.MainActivity
import com.bbbdev.pokebase.R
import com.bbbdev.pokebase.detailPokemonFragmentArgs
import com.bbbdev.pokebase.detailPokemonFragmentDirections
import com.bbbdev.pokebase.model.pokeModel
import com.google.android.material.chip.Chip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class PokemonEvol2ndAdapter(
    private val context: Context,
    private val dataset: List<evolvesToList>,
    private val color:String,
    private val args: detailPokemonFragmentArgs?,
    private val onItemCardEvoleClick:onCardEvol2nd
) : RecyclerView.Adapter<PokemonEvol2ndAdapter.ItemViewHolder>() {

    private var model = ViewModelProviders.of(context as MainActivity).get(pokeModel::class.java)


    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val number: TextView = view.findViewById(R.id.evol_number)
        val name: TextView = view.findViewById(R.id.evol_name)
        val imgView: ImageView = view.findViewById(R.id.evol_image)
        val condition: TextView = view.findViewById(R.id.evol_condition)

        val chip_a: Chip = view.findViewById(R.id.evol_chip_A)
        val chip_b: Chip = view.findViewById(R.id.evol_chip_B)
        val frame: FrameLayout = view.findViewById(R.id.evol_frame)
        val evolCard : CardView = view.findViewById(R.id.evol_card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke_evol, parent, false)

        return ItemViewHolder(adapterLayout)
    }


    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val items = dataset[position]

        holder.name.text = items.species.name.toUpperCase()
        holder.condition.text = dataset[position].evolutionDetails.first().trigger.name

        getCondition(dataset[position].evolutionDetails.first(), holder)
        getNumber(items.species.name, holder)

        loadArtwork(holder, items.species.name)
        loadColor(color, holder)


        if(args!!.pokemon.name != items.species.name){
            holder.itemView.setOnClickListener {
                onItemCardEvoleClick.onCardEvol2ndClick(items.species.name)
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

    fun getNumber(name: String, holder: ItemViewHolder) {
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
                var data = response.body()!!


                holder.number.text = "#" + String.format("%03d", data.id.toInt())
//                holder.itemView.setOnClickListener {
//                    onItemCardEvoleClick.onCardEvol2ndClick(0, data.id.toInt())
//                }

                getTypes(data.id.toInt(), holder)
            }

            override fun onFailure(call: Call<PokemonResource>, t: Throwable) {
                Timber.i("ERR")
            }

        })

    }

    fun getTypes(PokemonNumber: Int, holder: PokemonEvol2ndAdapter.ItemViewHolder){

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

                data.types

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


    fun getCondition(
        condition: evolutionDetailsList,
        holder: PokemonEvol2ndAdapter.ItemViewHolder
    ) {
//        holder.condition.text = text


        var trigger: String = condition.trigger.name
        when (trigger) {
            "level-up" -> {
                setTextWithCondition(condition, holder, "Level up \n")
            }
            "trade" -> {
                setTextWithCondition(condition, holder, "Trade \n")
            }
            "use-item" -> {
                setTextWithCondition(condition, holder, "Use \n")
            }
            "sirfetchd" -> {
                setTextWithCondition(condition, holder, "Sirfetchd \n")
            }
            "other" -> {
                setTextWithCondition(condition, holder, "Other \n")
            }
        }

    }

    fun setTextWithCondition(
        condition: evolutionDetailsList,
        holder: PokemonEvol2ndAdapter.ItemViewHolder,
        Subject: String
    ) {
        var text: String = ""

        //minimux lv
        if (condition.minLevel != null) {
            text = "Level " + condition.minLevel

            holder.condition.append("Level " + condition.minLevel)
        }

        //hold item **
        if (condition.heldItem != null) {
//            text = "Hold item \n " + condition.heldItem.name
            var pokeApi = PokemonApi.retrofitService.getItems(condition.heldItem.name)
            pokeApi.enqueue(object : retrofit2.Callback<pokemonItems> {
                override fun onResponse(
                    call: Call<pokemonItems>,
                    response: Response<pokemonItems>,
                ) {
                    var data = response.body()!!

                    var lang = data.names.filter {
                        it.language.name == "en"
                    }

                    text = Subject + "Hold item \n" + lang.first().name

                    holder.condition.text = text

                }

                override fun onFailure(call: Call<pokemonItems>, t: Throwable) {
                    Timber.i("ERR")
                }

            })
        }

        //poke in there location **
        if (condition.location != null) {
//            text = "Level up near \n " + condition.location.name
            var pokeApi = PokemonApi.retrofitService.getLocation(condition.location.name)
            pokeApi.enqueue(object : retrofit2.Callback<pokemonLocation> {
                override fun onResponse(
                    call: Call<pokemonLocation>,
                    response: Response<pokemonLocation>,
                ) {
                    var data = response.body()!!

                    var lang = data.names.filter {
                        it.language.name == "en"
                    }

                    text = Subject + "Near \n " + lang.first().name

                    holder.condition.text = text
                }

                override fun onFailure(call: Call<pokemonLocation>, t: Throwable) {
                    Timber.i("ERR")
                }

            })

        }
        //use item
        if (condition.item != null) {
//            text = "Use\n " +  condition.item.name

            var pokeApi = PokemonApi.retrofitService.getItems(condition.item.name)
            pokeApi.enqueue(object : retrofit2.Callback<pokemonItems> {
                override fun onResponse(
                    call: Call<pokemonItems>,
                    response: Response<pokemonItems>,
                ) {
                    var data = response.body()!!

                    var lang = data.names.filter {
                        it.language.name == "en"
                    }

                    text = Subject + lang.first().name

                    holder.condition.text = text

                }

                override fun onFailure(call: Call<pokemonItems>, t: Throwable) {
                    Timber.i("ERR")
                }

            })

        }

        //gender male or female
        if (condition.gender != null) {
            text = Subject + "Gender " + condition.gender
        }
        //must has pokemon... in party
        if (condition.partySpecies != null) {
            text = Subject + "Party has \n" + condition.partySpecies
        }

        //must has pokem
        if (condition.partyType != null) {
            text = Subject + "Party has \n" + condition.partyType
        }

        //pokeme must has MOVE ..
        if (condition.knownMove != null) {

            var pokeApi = PokemonApi.retrofitService.getMove(condition.knownMove.name)
            pokeApi.enqueue(object : Callback<pokemonMove> {
                override fun onResponse(call: Call<pokemonMove>, response: Response<pokemonMove>) {
                    var data = response.body()!!

                    var moveTextSet = data.names.filter { it.language.name == "en" }

                    text = Subject + "Known Move \n" + moveTextSet.first().name
                    holder.condition.text = text

                }

                override fun onFailure(call: Call<pokemonMove>, t: Throwable) {

                    text = Subject + "Known Move"
                    holder.condition.text = text
                }
            })

        }

        //poke must has Type Move
        if (condition.knownMoveType != null) {
//            text = Subject +"Known Type Move \n" + condition.knownMoveType.name
            var pokeApi = PokemonApi.retrofitService.getType(condition.knownMoveType.name)
            pokeApi.enqueue(object : Callback<pokemonTypes> {
                override fun onResponse(
                    call: Call<pokemonTypes>,
                    response: Response<pokemonTypes>
                ) {
                    var data = response.body()!!
                    var moveTextSet = data.names.filter { it.language.name == "en" }

                    text = Subject + "Known Type Move \n" + moveTextSet.first().name
                    holder.condition.text = text

                }

                override fun onFailure(call: Call<pokemonTypes>, t: Throwable) {
                    text = Subject + "Known Type Move \n"
                    holder.condition.text = text
                }
            })
        }


        //minimum required level of affection
        if (condition.minAffection != 0) {
            text = "Minimum  \nAffection "
        }


        //minimum required level of minBeauty
        if (condition.minBeauty != 0) {
            text = "Minimum  \nBeauty"
        }

        //minimum required level of minHappiness
        if (condition.minHappiness != 0) {
            text = "Minimum  \nHappiness "
        }

        //Whether or not it must be raining
        if (condition.needsOverworldRain != false) {
            text = Subject + "Whether raining"
        }

        //atk && def = parameter
        if (condition.relativePhysicalStats != 0) {
            text = Subject + "Attack equals Defense"
        }

        //day or night
        if (condition.timeOfDay != null && condition.timeOfDay != "") {

            var subtext = ""
            if (condition.minBeauty != 0) {
                subtext = "Beauty "
            } else if (condition.minHappiness != 0) {
                subtext = "Happiness "
            } else if (condition.minAffection != 0) {
                subtext = "Affection "
            }

            when (condition.timeOfDay) {
                "day" -> text = Subject + "Daytime \n" + subtext
                "night" -> text = Subject + "Nighttime \n" + subtext
            }
        }

        //trade with pokemon type
        if (condition.tradeSpecies != null) {
            text = "Trade with \n pokemon type \n " + condition.tradeSpecies
        }

        holder.condition.text = text

    }



    fun loadArtwork(holder: PokemonEvol2ndAdapter.ItemViewHolder, Pokename: String) {
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


}