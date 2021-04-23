package com.bbbdev.pokebase.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bbbdev.pokebase.detailPokemonFragmentArgs
import com.bbbdev.pokebase.pokedata.*

class pokeModel: ViewModel()  {
    var generationfiltered:String? = null
    var typefiltered:String? = null
    var searchfiltered:String? = null

    val positionList = MutableLiveData<Any>()
    val serachingText = MutableLiveData<Any>()
    var type:Boolean = false
    //    val numberPokemonOnDetail = MutableLiveData<Any>()
    var numberPokemonOnDetail: String? = null
    var numberPokemonOnDetailList = MutableLiveData<Any>()

    var reArrangedSet: List<pokemon>? = null

    val typePoke = MutableLiveData<Any>()

    var aFirst: pokemon1stForm? = null
    var aSecond: pokemon2ndForm? = null
    var aThird: pokemon3ndForm? = null

    var evolChainDataset: PokemonEvolution? = null

    var colorInDetail:String? = null

    var detailArgsDetail: detailPokemonFragmentArgs? = null

    var totalPokeForOnload: Int = 0
    var totalMoveForOnload: Int = 0


    fun addTotalMove(number: Int){
        this.totalMoveForOnload = this.totalMoveForOnload + number
    }

    fun setArgsDetail(pokemon: detailPokemonFragmentArgs){
        this.detailArgsDetail = pokemon
    }

    fun setNumberPokemonOnDetailList(number: String?){
        this.numberPokemonOnDetailList.setValue(number)
    }

    fun setType(type:String?){
        this.typefiltered = type
    }

    fun setSearch(text:String?){
        this.searchfiltered = text
    }

    fun setGeneration(gen: String?){
        this.generationfiltered = gen
    }

    fun setisType(data: Boolean){
        this.type = data
    }

    fun setcolorInDetail(color:String){

        this.colorInDetail = color
    }


    fun setTypePokemon(data: String){
        this.typePoke.setValue(data)
    }

    fun setPositionList(position: Int){
        this.positionList.setValue(position)
    }

    fun setserachingText(text: String?){
        this.serachingText.setValue(text)
    }

    fun setNumberPokemonDetail(number: String?){
//        this.numberPokemonOnDetail.setValue(number)
        this.numberPokemonOnDetail = number
    }

    fun setFirst(data: pokemon1stForm){
        this.aFirst = data
    }

    fun setSecond(data: pokemon2ndForm){
        this.aSecond = data
    }

    fun setThird(data: pokemon3ndForm){
        this.aThird = data
    }

    fun setEvolDataset(data: PokemonEvolution){

        this.evolChainDataset = data
    }

    fun setdataReArrangedSet(data: List<pokemon>){

        this.reArrangedSet = data
    }






}