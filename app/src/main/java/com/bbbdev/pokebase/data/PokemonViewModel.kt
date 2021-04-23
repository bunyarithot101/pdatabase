package com.bbbdev.pokebase.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bbbdev.pokebase.pokedata.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


class PokemonViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<pokemon>>
    private val repository: PokemonRepository


    var dataGen: LiveData<List<pokemon>>
    var dataType: LiveData<List<pokemon>>
    var dataFiltered: LiveData<List<pokemon>>
    var isFiltered: Boolean = false

    val count: LiveData<Int?>?
    var countMove: LiveData<Int?>?

    val countforOnload: LiveData<Int?>?

    var nowGen: String? = null
    var nowType: String? = null

    var data1st: LiveData<List<pokemon>>

    var dataThisPokemonMove : LiveData<List<pokemonMoveData>>

    init {
        val userDao = PokemonDatabase.getDatabase(application).pokemonDao()
        repository = PokemonRepository(userDao)
        readAllData = repository.readAllData
        count = repository.count
        dataGen = repository.dataGen("1")
        dataType = repository.dataType("normal")
        dataFiltered = repository.dataFilterdGenAndType(null,null)

        data1st = repository.dataPokemon(null)

        countMove = repository.dataMoveThisPokemon(null)

        dataThisPokemonMove = repository.getThisPokemonMove(null)

        countforOnload = repository.countforOnload


    }

    fun sendFiltered(thisGen: String?, thisType: String?, thisSearch: String?){
        isFiltered = true

        var isGenFilterd = thisGen.isNullOrEmpty()
        var isTypeFilterd = thisType.isNullOrEmpty()
        var isSearchFilterd = thisSearch.isNullOrEmpty()

        nowGen = thisGen
        nowType = thisType



        if(isSearchFilterd == true){

            //filtered type && gen
            if(isGenFilterd == false && isTypeFilterd == false){
                dataFiltered = repository.dataFilterdGenAndType(thisGen, thisType)
            }

            //filtered only gen
            if(isGenFilterd == false && isTypeFilterd == true){
                dataFiltered = repository.dataFilterdGen(thisGen)
            }

            //filtered only type
            if(isTypeFilterd == false && isGenFilterd == true){
                dataFiltered = repository.dataFilterdType(thisType)
            }

            //filtered type && gen is All
            if(isTypeFilterd == true && isGenFilterd == true){
                isFiltered = false

                nowGen = null
                nowType = null

            }

        } else if (isSearchFilterd == false){
            //filtered type && gen
            if(isGenFilterd == false && isTypeFilterd == false){


                dataFiltered = repository.dataFilterdGenAndTypeIsSearch(thisGen, thisType, thisSearch)
            }

            //filtered only gen
            if(isGenFilterd == false && isTypeFilterd == true){

                dataFiltered = repository.dataFilterdGenIsSearch(thisGen, thisSearch)
            }


            //filtered only type
            if(isTypeFilterd == false && isGenFilterd == true){
                dataFiltered = repository.dataFilterdTypeIsSearch(thisType, thisSearch)
            }

            //filtered type && gen is All
            if(isTypeFilterd == true && isGenFilterd == true){
                dataFiltered = repository.getSearch(thisSearch)

            }
        }



//        viewModelScope.launch(Dispatchers.IO){
//
//            repository.getFilterdGenAndType(thisGen.toString(), thisType.toString())
//        }
    }



    fun addPokemon(pokemon: pokemon){
        isFiltered = false
        viewModelScope.launch(Dispatchers.IO){
            repository.addPokemon(pokemon)
        }
    }

    fun updatePokemon(type: String, number: Int?, update: String?){
        isFiltered = false
       viewModelScope.launch(Dispatchers.IO){
//           repository.updatePokemon(color, number)

           when(type){
               "img" -> {
                   repository.updateImg(number, update)
               }
               "typeA" -> {
                   repository.updateTypeA(number, update)
               }
               "typeB" -> {
                   repository.updateTypeB(number, update)
               }
               "color" -> {
                   repository.updateColor(number, update)
               }
               "gen" -> {
                   repository.updateGen(number, update)
               }
           }

       }
    }

    fun updateisReadyPokemon(){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateisReady()
        }
    }

    fun updatePokemonCondition(pokemon: pokemon){
        isFiltered = false
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePokemonCondition(pokemon)
        }
    }


    fun clear(){
        isFiltered = false
        viewModelScope.launch(Dispatchers.IO){
            repository.clear()
        }
    }

    fun count(){
        isFiltered = false
        viewModelScope.launch(Dispatchers.IO){
            repository.count()

        }
    }

    fun getPokemon(name:String? ){
        data1st =  repository.dataPokemon(name)

    }


//move
    fun addPokemonMove(pokemonMoveData: pokemonMoveData){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMove(pokemonMoveData)
        }
    }

    fun getMoveThisPokemonCount(name:String? ){
        countMove =  repository.dataMoveThisPokemon(name)

    }

    fun getAllMoveThisPokemon(name: String?){
        dataThisPokemonMove = repository.getThisPokemonMove(name)
    }

    //deatilinfo

//    fun addDetail(detail: pokemonDetail){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.addDetailInfo(detail)
//        }
//    }









}