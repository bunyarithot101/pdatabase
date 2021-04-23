package com.bbbdev.pokebase.data

import androidx.lifecycle.LiveData
import com.bbbdev.pokebase.pokedata.pokemon
import com.bbbdev.pokebase.pokedata.pokemonMoveData
import timber.log.Timber

class PokemonRepository(
    private val pokemonDao: PokemonDao,
) {

    val readAllData: LiveData<List<pokemon>> = pokemonDao.readAllData()
    val count: LiveData<Int?>? = pokemonDao.count()
    val countforOnload: LiveData<Int?>? = pokemonDao.countforOnload()

    suspend fun addPokemon(pokemon: pokemon){
        pokemonDao.addPokemon(pokemon)
    }

    suspend fun getGeneration(thisGen: String){
        pokemonDao.getGenaration(thisGen)
    }


    fun dataPokemon(name: String?): LiveData<List<pokemon>> {
        var text = "%$name%"

        return pokemonDao.getPokemon(text)
    }


    suspend fun updatePokemonCondition(pokemon: pokemon){
        pokemonDao.updatePokemonCondition(pokemon)
    }

    suspend fun updateColor(number: Int?, color: String?){
        pokemonDao.updateColorPokemon(number, color)
    }
    suspend fun updateGen(number: Int?, gen: String?){
        pokemonDao.updateGenPokemon(number, gen)
    }
    suspend fun updateTypeA(number: Int?, type: String?){
        pokemonDao.updateTypeAPokemon(number,type)
    }
    suspend fun updateTypeB(number: Int?, type: String?){
        pokemonDao.updateTypeBPokemon(number,type)
    }
    suspend fun updateImg(number: Int?, img: String?){
        pokemonDao.updateImgPokemon(number, img)
    }

    suspend fun updateisReady(){

        pokemonDao.updateIsReady("isReady")
    }

    suspend fun clear(){
        pokemonDao.clear()
    }

    suspend fun count(){
        pokemonDao.count()
    }



    fun dataGen(thisGen: String?): LiveData<List<pokemon>> {
        return pokemonDao.getGenaration(thisGen!!)
    }

    fun dataType(thisType: String?): LiveData<List<pokemon>> {
        return pokemonDao.getType(thisType!!)
    }


    suspend fun getFilterdGenAndType(thisGen: String?, thisType: String?){
        pokemonDao.getFilterdGenAndType(thisGen, thisType)
    }

    fun dataFilterdGenAndType(thisGen: String?, thisType:String?): LiveData<List<pokemon>> {

        return pokemonDao.getFilterdGenAndType(thisGen, thisType)
    }

    fun dataFilterdGen(thisGen: String?): LiveData<List<pokemon>> {

        return pokemonDao.getFilterdGen(thisGen)
    }

    fun dataFilterdType(thisType:String?): LiveData<List<pokemon>> {

        return pokemonDao.getFilterdType(thisType)
    }

    fun getSearch(thisSearch:String?): LiveData<List<pokemon>> {

        var text = "%$thisSearch%"

        return pokemonDao.getSearch(text)
    }


    fun dataFilterdGenAndTypeIsSearch(thisGen: String?, thisType:String?, thisSearch:String?): LiveData<List<pokemon>> {

        var text = "%$thisSearch%"

        return pokemonDao.getFilterdGenAndTypeIsSearch(thisGen, thisType, text)
    }

    fun dataFilterdGenIsSearch(thisGen: String?, thisSearch:String?): LiveData<List<pokemon>> {

        var text = "%$thisSearch%"

        return pokemonDao.getFilterdGenIsSearch(thisGen, text)
    }

    fun dataFilterdTypeIsSearch(thisType:String?, thisSearch:String?): LiveData<List<pokemon>> {

        var text = "%$thisSearch%"

        return pokemonDao.getFilterdTypeIsSearch(thisType, text)
    }

    //move

    suspend fun addMove(pokemonMoveData: pokemonMoveData){
        pokemonDao.addMove(pokemonMoveData)
    }

    fun dataMoveThisPokemon(pokeName: String?): LiveData<Int?>? {
        return pokemonDao.countMove(pokeName)
    }

    fun getThisPokemonMove(pokeName: String?):LiveData<List<pokemonMoveData>>{
       return pokemonDao.getThisPokemonMove(pokeName)
    }




    //deatilinfo

//    suspend fun addDetailInfo(detail: pokemonDetail){
//        pokemonDao.addDetail(detail)
//    }







}