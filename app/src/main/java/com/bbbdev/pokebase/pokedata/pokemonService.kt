package com.bbbdev.pokebase.pokedata

import com.bbbdev.pokebase.HTTPLogger
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://pokeapi.co/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(HTTPLogger.getLogger())
    .build()

private val retrofit2 = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(HTTPLogger.getLogger())
    .build()




interface pokemonService {
    @GET("api/v2/generation/{id}")
    fun getResponse(
        @Path("id") id: String?,
    ): Call<PokemonResponse>


    @GET("api/v2/pokemon/{id}")
    fun getDetail(
        @Path("id") id: Int?,
    ): Call<PokemonResource>

    @GET("api/v2/pokemon/{name}/")
    fun getDetailWithString(
        @Path("name") name: String?,
    ): Call<PokemonResource>

    @GET
    fun getDetailWithURL(@Url url: String?): Call<PokemonResource>



    @GET("api/v2/pokemon-species/{id}")
    fun getPokeSpecies(@Path("id") id: String?): Call<PokemonSpecies>

    @GET("api/v2/pokemon-species/{name}")
    fun getPokeSpeciesText(@Path("name") id: String?): Call<PokemonSpecies>

//     fun getEvolution(@Path("id") id: String?): Call<PokemonEvolution>
    @GET
    fun getEvolution(@Url url: String?): Call<PokemonEvolution>


    //    https://pokeapi.co/api/v2/pokemon-species/{id}/
    @GET
    fun getSpecieswithUrl(@Url url: String?): Call<PokemonSpeciesFromUrl>


    @GET("api/v2/item/{name}")
    fun getItems(@Path("name") name: String?): Call<pokemonItems>

    @GET("api/v2/location/{name}")
    fun getLocation(@Path("name") name: String?): Call<pokemonLocation>

    @GET("api/v2/move/{name}")
    fun getMove(@Path("name") name: String): Call<pokemonMove>

    @GET
    fun getMoveWithUrl(@Url url: String?): Call<pokemonMove>

//    normal
    @GET("api/v2/type/{name}")
    fun getType(@Path("name") name: String): Call<pokemonTypes>


    /*ALL POKE ZONE*/
    @GET("api/v2/pokemon")
    fun getAllPokemon(
    //@Path("limit") id: Int?,
    @Query("limit") aParam: String?
    ): Call<pokemonAll>

    @GET("api/v2/type/{type}")
    fun getAllPokemonWithType(
        @Path("type") id: String?,
//    ): Call<pokemonAllWithType>
    ): Call<PokemonResponse>

    @GET
    fun getPokemonwithUrl(@Url url: String?): Call<PokemonResource>


}


object PokemonApi {
    val retrofitService : pokemonService by lazy { retrofit.create(pokemonService::class.java) }
}

