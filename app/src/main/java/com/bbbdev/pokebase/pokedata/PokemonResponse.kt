package com.bbbdev.pokebase.pokedata

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val pokemon_species: List<pokemon>,
)

data class PokemonList(
    val name: String,
    val url: String,
)



data class PokemonResource(
    val id: String,
    val name: String,
    val species: speciesList,
    private var speciesused: String? = null,
    val types: List<typeList>,
    private var typesUsed: List<String>? = null,
    val sprites:spritesList,
    val stats: List<statsList>,
    val moves: List<moveList>,


){

    fun getSpecies(): String? {
        return species.url
    }

    fun getType():List<typeList>{

        return types
    }


}


data class versionGroupDetailsList(
    @SerializedName("level_learned_at")
    val levelLearnedAt:String,
    @SerializedName("move_learn_method")
    val moveLearnMethod: moveLearnMethodList,
    @SerializedName("version_group")
    val versionGroup: versionGroupList
)

data class moveLearnMethodList(
    val name: String,
    val url:String
)

data class versionGroupList(
    val name: String,
    val url:String
)

data class moveList(
    val move: moveAttr,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<versionGroupDetailsList>
)

data class moveAttr(
    val name: String,
    val url:String
)

data class statsList(
    @SerializedName("base_stat")
    val baseStat:String,
    val effort:String,
    val stat:statsub
)

data class statsub(
    val name:String,
    val url:String
)


data class spritesList(
    val other: otherList
)

data class otherList(
    @SerializedName("official-artwork")
    val officialartwork:artworkList
)

data class artworkList(
    val front_default: String
)

data class speciesList(
    val name: String,
    val url: String,
)

data class typeList(
    val slot: String,
    val type: type
)

data class type(
    val name: String,
    val url: String,
)


data class PokemonSpeciesFromUrl(
    val id: Int,
    val name: String,
    val color: colorList,
    val generation: generationList
)

data class PokemonSpecies(
    val id: Int,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<flavorText>,
    @SerializedName("evolution_chain")
    val evolutionChain: urlEvolution,
    val color : colorList,
    val generation: generationList
)
data class colorList(
    val name:String,
    val url:String
)
data class flavorText(
    @SerializedName("flavor_text")
    val flavorText: String,
    val language: flavorTextLang,
    val version: flavorVersion
)
data class flavorTextLang(
    val name: String,
    val url: String
)

data class flavorVersion(
    val name: String,
    val url: String
)
data class urlEvolution(
    val url: String
)

data class PokemonEvolution(
    val id: String,
    val chain: chainList
)

data class chainList(
    val is_baby :String,
    // 1st form
    val species: speciesList,
    @SerializedName("evolves_to")
    val evolvesTo: List<evolvesToList>,
    @SerializedName("evolution_details")
    val evolutionDetails: List<evolutionDetailsList>,
    ) {


}

data class evolvesToList(
    // 2nd form
    val species: speciesList,
    @SerializedName("evolves_to")
    val evolvesTo: List<evolvesToList>,
    @SerializedName("evolution_details")
    val evolutionDetails: List<evolutionDetailsList>
)

data class storageEvolvesTo3rd(
    @SerializedName("evolves_to")
    val evolvesTo: List<evolvesToList>,
)

data class evolutionDetailsList(
    val trigger :triggerList,
    val gender :String,
    val item : itemList,
    @SerializedName("held_item")
    val heldItem :heldItemList,
    @SerializedName("min_level")
    val minLevel :String,
    @SerializedName("known_move")
    val knownMove : knownMoveList,
    @SerializedName("known_move_type")
    val knownMoveType  : knownMoveTypeList,
    val location : locationList,
    @SerializedName("min_affection")
    val minAffection : Int,
    @SerializedName("min_beauty")
    val minBeauty : Int,
    @SerializedName("min_happiness")
    val minHappiness : Int,

    @SerializedName("needs_overworld_rain")
    val needsOverworldRain : Boolean,
    @SerializedName("party_species")
    val partySpecies : partySpeciesList,
    @SerializedName("party_type")
    val partyType : partyTypeList,
    @SerializedName("relative_physical_stats")
    val relativePhysicalStats : Int,
    @SerializedName("time_of_day")
    val timeOfDay : String,
    @SerializedName("trade_species")
    val tradeSpecies : tradeSpeciesList,
    
)

data class triggerList(
    val name :String,
    val url :String
)
data class itemList(
    val name :String,
    val url :String
)

data class heldItemList(
    val name :String,
    val url :String
)

data class knownMoveList(
    val name :String,
    val url :String
)

data class knownMoveTypeList(
    val name :String,
    val url :String
)

data class locationList(
    val name :String,
    val url :String
)

data class partySpeciesList(
    val item :String,
    val url :String
)

data class partyTypeList(
    val item :String,
    val url :String
)

data class tradeSpeciesList(
    val item :String,
    val url :String
)

/**/
data class pokemon1stForm(
    var name: String,
    var detail: List<evolutionDetailsList>
)

data class pokemon2ndForm(
    val name: List<evolvesToList>,
    var detail: List<evolutionDetailsList>
)

data class pokemon3ndForm(
    val name: List<evolvesToList>?,
    var detail: List<evolutionDetailsList>?
)

data class pokemonItems(
    val name: String,
    val names: List<nameItem>
)

data class nameItem(
    val language: nameItemTextLang,
    val name: String
)

data class nameItemTextLang(
    val name: String,
    val url: String
)

data class pokemonLocation(
    val name:String,
    val names:List<nameItem>
)


data class pokemonMove(
    val id: String,
    val name:String,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<flavorText>,
    val names: List<nameItem>,
    val type: type,
    val generation: generationList,
    val power: String,
    val accuracy: String,

    var number: Int? = null
){
    fun getNumber(): Int {
        //val urlPartes = url!!.split("/".toRegex()).toTypedArray()
        //return urlPartes[urlPartes.size - 1].toInt()

        val regex = "\\W+".toRegex()
        val beautiful = this.generation.url.toString().dropLast(1)
        val numberPokemon = regex.split(beautiful).last().toInt()


        return numberPokemon
    }

    fun setNumber(number: Int) {
        this.number = number
    }
}

data class pokemonTypes(
    val id: String,
    val name:String,
    val names: List<nameItem>,
    val pokemon: List<pokemonByType>
)

data class generationList(
    var gen: Int,
    val name: String,
    val url: String,
){
    fun getNumber(): Int {
        //val urlPartes = url!!.split("/".toRegex()).toTypedArray()
        //return urlPartes[urlPartes.size - 1].toInt()

        val regex = "\\W+".toRegex()
        val beautiful = this.url.toString().dropLast(1)
        val numberPokemon = regex.split(beautiful).last().toInt()

        return numberPokemon
    }
}

data class pokemonByType(
    val pokemon: pokemonByTypeList

)
{
    fun getNumber(): Int {
        //val urlPartes = url!!.split("/".toRegex()).toTypedArray()
        //return urlPartes[urlPartes.size - 1].toInt()

        val regex = "\\W+".toRegex()
        val beautiful = this.pokemon.url.toString().dropLast(1)
        val numberPokemon = regex.split(beautiful).last().toInt()

        return numberPokemon
    }
}

data class pokemonByTypeList(
    var number: Int? = null,
    val name: String,
    val url: String
)
