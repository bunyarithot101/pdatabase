package com.bbbdev.pokebase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bbbdev.pokebase.pokedata.pokemon
import com.bbbdev.pokebase.pokedata.pokemonMoveData


@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPokemon(pokemon: pokemon)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMove(pokemonMoveData: pokemonMoveData)

//    UPDATE
    @Query("UPDATE pokemon_table SET imgResource =:img WHERE number = :number")
    suspend fun updateImgPokemon(number: Int?, img: String?)

    @Query("UPDATE pokemon_table SET color =:color WHERE number = :number")
    suspend fun updateColorPokemon(number: Int?, color: String?)

    @Query("UPDATE pokemon_table SET typeA = :typeA WHERE number = :number")
    suspend fun updateTypeAPokemon(number: Int?, typeA: String?)

    @Query("UPDATE pokemon_table SET typeB = :typeB WHERE number = :number")
    suspend fun updateTypeBPokemon(number: Int?,  typeB: String?)

    @Query("UPDATE pokemon_table SET gen =:gen WHERE number = :number")
    suspend fun updateGenPokemon(number: Int?, gen: String?)

    @Query("UPDATE pokemon_table SET isReady = :text ")
    suspend fun updateIsReady(text: String?)



    @Query("DELETE FROM pokemon_table")
    fun clear()

    @Query("select * from pokemon_table where isReady Like 'isReady' order by number ASC")
    fun readAllData(): LiveData<List<pokemon>>


    @Query("SELECT COUNT(id) FROM pokemon_table")
    fun count(): LiveData<Int?>?

    @Query("SELECT COUNT(id) FROM pokemon_table where isReady Like 'isReady' ")
    fun countforOnload(): LiveData<Int?>?

    @Query("SELECT COUNT(id) FROM move_table where name LIKE :pokeName ")
    fun countMove(pokeName: String?): LiveData<Int?>?

    @Query("SELECT * FROM move_table where name LIKE :pokeName ")
    fun getThisPokemonMove(pokeName: String?): LiveData<List<pokemonMoveData>>


    @Query("select * from pokemon_table where gen = :thisGen order by number ASC")
    fun getGenaration(thisGen: String): LiveData<List<pokemon>>

    @Query("select * from pokemon_table where typeA LIKE :thisType or typeB LIKE :thisType order by number ASC")
    fun getType(thisType: String): LiveData<List<pokemon>>


    @Query("select * from pokemon_table where gen = :thisGen AND typeA LIKE :thisType or typeB LIKE :thisType order by number ASC")
    fun getFilterdGenAndType(thisGen: String?, thisType: String?): LiveData<List<pokemon>>

    @Query("select * from pokemon_table where gen = :thisGen order by number ASC")
    fun getFilterdGen(thisGen: String?): LiveData<List<pokemon>>

    @Query("select * from pokemon_table where typeA LIKE :thisType or typeB LIKE :thisType order by number ASC")
    fun getFilterdType(thisType: String?): LiveData<List<pokemon>>

    @Query("Select * from pokemon_table  WHERE name LIKE :textSearch or number Like :textSearch  order by number ASC")
    fun getSearch(textSearch: String?): LiveData<List<pokemon>>


    @Query("Select * from pokemon_table  WHERE  ( name LIKE :thisSearch  or number LIKE :thisSearch ) AND gen = :thisGen AND (typea LIKE :thisType or typeb LIKE :thisType) order by number ASC")
    fun getFilterdGenAndTypeIsSearch(thisGen: String?, thisType: String?, thisSearch:String?): LiveData<List<pokemon>>


    @Query("Select * from pokemon_table  WHERE  ( name LIKE :thisSearch  or number LIKE :thisSearch ) AND gen = :thisGen order by number ASC")
    fun getFilterdGenIsSearch(thisGen: String?, thisSearch:String?): LiveData<List<pokemon>>

    @Query("Select * from pokemon_table  WHERE  ( name LIKE :thisSearch  or number LIKE :thisSearch ) AND (typeA LIKE :thisType or typeB LIKE :thisType) order by number ASC")
    fun getFilterdTypeIsSearch(thisType: String?, thisSearch:String?): LiveData<List<pokemon>>


    @Query("select * from pokemon_table where name LIKE :name")
    fun getPokemon(name: String?): LiveData<List<pokemon>>



    @Update
    suspend fun updatePokemonCondition(pokemon: pokemon)


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addDetail(detail: pokemonDetail)

}