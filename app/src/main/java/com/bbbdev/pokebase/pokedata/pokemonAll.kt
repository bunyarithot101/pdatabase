package com.bbbdev.pokebase.pokedata

import com.google.gson.annotations.SerializedName

//private fun urlToId(url: String): Int {
//    return "/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isDigit() || it == '-' }.toInt()
//}
//
//private fun urlToCat(url: String): String {
//    return "/[a-z\\-]+/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isLetter() || it == '-' }
//}

data class pokemonAll(
    val count:String,
    val next: String,
    val previous:String,
    val results: List<resultsList>,

)

data class resultsList(
    var id:Int,
    val name: String,
    val url: String,
){
    fun getuId(): Int {
        val regex = "\\W+".toRegex()
        val beautiful = this.url.toString().dropLast(1)
        val numberPokemon = regex.split(beautiful).last().toInt()

        return numberPokemon

    }
}

data class pokemonAllWithType(
    val name:String,
//    val pokemon: List<listPokemon>,
    val pokemon:List<listPokemon>,
)


data class listPokemon(
    val pokemon: pokemonAttr,
//    val slot:String
){
    fun getNumber(): Int {
        val regex = "\\W+".toRegex()
        val beautiful = this.pokemon.url.toString().dropLast(1)
        val numberPokemon = regex.split(beautiful).last().toInt()

        return numberPokemon

    }
}

data class pokemonAttr(
    var id:Int,
    val name: String,
    val url: String,
){
    fun getuId(): Int {
        //val urlPartes = url!!.split("/".toRegex()).toTypedArray()
        //return urlPartes[urlPartes.size - 1].toInt()

        val regex = "\\W+".toRegex()
        val beautiful = this.url.toString().dropLast(1)
        val number = regex.split(beautiful).last().toInt()

        return number
    }
}

data class pokemonAllWithGen(
    val name: String,
    @SerializedName("pokemon_species")
    val pokemonSpecies: List<pokemonAttr>
)


data class pokemontest(
    val number: String,
    val name: String,
)




