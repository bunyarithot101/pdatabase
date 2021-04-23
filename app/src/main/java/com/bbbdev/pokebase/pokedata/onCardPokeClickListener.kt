package com.bbbdev.pokebase.pokedata

import com.bbbdev.pokebase.detailPokemonFragmentArgs
import timber.log.Timber

interface onCardPokeClickListener {
    fun onItemClick(args: pokemon, position: Int)
}

interface onCardEvol1st {
    fun onCardEvol1stClick(name: String)
}

interface onCardEvol2nd {
    fun onCardEvol2ndClick(name: String)

}

interface onCardEvol3rd {
    fun onCardEvol3rdClick(name: String)
}


interface onCardEvolClick {
    fun onCardEvolClick(id: String)
}

interface onCard2EvolClick {
    fun onCard2EvolClick(id: String)
}