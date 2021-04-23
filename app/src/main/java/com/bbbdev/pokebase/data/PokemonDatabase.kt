package com.bbbdev.pokebase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bbbdev.pokebase.pokedata.pokemon
import com.bbbdev.pokebase.pokedata.pokemonMoveData


//@Database(entities = [pokemon::class], version = 1,exportSchema = false)
@Database(entities = [pokemon::class, pokemonMoveData::class], version = 1,exportSchema = false)
abstract class PokemonDatabase:RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object{
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getDatabase(context: Context):PokemonDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instanct = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    "pokemon_database"
                ).build()
                INSTANCE = instanct
                return instanct
            }

        }

    }


}