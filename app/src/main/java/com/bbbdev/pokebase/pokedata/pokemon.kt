package com.bbbdev.pokebase.pokedata

import android.os.Parcelable
import androidx.lifecycle.ViewModelProviders
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbbdev.pokebase.model.pokeModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


//data class pokemon(
//    var number: Int? = null,
//    private var name: String? = null,
//    private var url: String? = null,
//    //////
//    var type: List<typeList>,
//    var imgResource: String? = null,
//    var color: String? = null,
//
////G1 - 1-151
////G2 - 152-252
////G3 - 253-386
////G4 - 387-493
////G5 - 494-649
////G6 - 650-721
////G7 - 722-809
////G8 - 810-898
//
//
//    ) {
//
//    fun getName(): String? {
//        return name?.toUpperCase()
//    }
//
//    fun setName(name: String?) {
//        this.name = name
//    }
//
//    fun getUrl(): String? {
//        return url
//    }
//
//    fun setUrl(url: String?) {
//        this.url = url
//    }
//    fun getNumber(): Int {
//        //val urlPartes = url!!.split("/".toRegex()).toTypedArray()
//        //return urlPartes[urlPartes.size - 1].toInt()
//
//        val regex = "\\W+".toRegex()
//        val beautiful = this.url.toString().dropLast(1)
//        val numberPokemon = regex.split(beautiful).last().toInt()
//
//        return numberPokemon
//    }
//
//    fun setNumber(number: Int) {
//        this.number = number
//    }
//
//
//}




@Parcelize
@Entity(tableName = "pokemon_table")
data class pokemon(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    var number: Int,
    var name: String,
    var url: String,
    //////

    var gen: Int,
    var color: String,

    var typeA: String,
    var typeB: String?,
    var imgResource: String?,

    var isReady: String?,


): Parcelable {

}


//@Parcelize
//@Entity(tableName = "info_table")
//data class pokemonDetail(
//    @PrimaryKey(autoGenerate = true)
//    val id:Int,
//
//    var speciesQuote: String,
//    var baseHp: Int,
//    var baseAtk: Int,
//    var baseDef: Int,
//    var baseSpAtk: Int,
//    var baseSpDef: Int,
//    var baseSpSpd: Int,
//    var evolChain: String
//
//): Parcelable


@Parcelize
@Entity(tableName = "move_table")
data class pokemonMoveData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    var number: Int,
    var name: String,

    var move: String,
    var power: String,
    var accuracy: String,
    var type: String,

): Parcelable
