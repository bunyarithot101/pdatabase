package com.bbbdev.pokebase

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bbbdev.pokebase.data.PokemonViewModel
import com.bbbdev.pokebase.databinding.FragmentIntroBinding
import com.bbbdev.pokebase.model.pokeModel
import com.bbbdev.pokebase.pager.ViewPagerAdapter
import com.bbbdev.pokebase.pager.onPage2Clicked
import com.bbbdev.pokebase.pokedata.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var _binding: FragmentIntroBinding? = null
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [introFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class introFragment : Fragment(),onPage2Clicked {
    // TODO: Rename and change types of parameters
    private var titleList = mutableListOf<String>()
    private var detailList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    private var countPoke :Int? = 0

    private lateinit var mPokemonViewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_intro, container, false)
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        val view = binding.root

        mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        mPokemonViewModel.count?.observe(viewLifecycleOwner, Observer { number ->
            if(number?.toInt() == 0){
                insertDataToDatabase()
//                createMoveTable()
            }
        })


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(requireActivity()).get(pokeModel::class.java)

        if(model.positionList.value == null){
            model?.setPositionList(0)
        }


        val sharedPreference =  requireActivity().getSharedPreferences("isFirstTimeCome",Context.MODE_PRIVATE)

        //for test
//        var editor = sharedPreference.edit()
//        editor.putBoolean("FirstTime",true)
//        editor.commit()

        val firstTime =  sharedPreference.getBoolean("FirstTime",true)

        if(firstTime == false){
            findNavController().navigate(
                R.id.action_introFragment_to_listPokemonFragment
            )
        }


        addToList("Fan Application", "The Application is not supported from NINTENDO", R.drawable.welcome1)
//        addToList("Pdatabse Move", "Know Pokémon more", R.drawable.welcome2)

        val viewPager = binding.pager
        viewPager.adapter = ViewPagerAdapter(titleList, detailList, imageList,this)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//            }
//        })


//        val navController = findNavController()
//        val destination = navController.currentDestination as FragmentNavigator.Destination
//        if (javaClass.name == destination.className) {
//            navController.navigate(directions)
//        }
//        binding.layoutIntro.setOnClickListener{
//            findNavController().navigate(
//                R.id.action_introFragment_to_listPokemonFragment
//            )
//        }

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
//        Handler().postDelayed({
//            findNavController().navigate(
//                R.id.action_introFragment_to_listPokemonFragment
//            )
//        }, 1000)
//        1000
    }


    private fun insertDataToDatabase(){
        mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)

        val pokeApi = PokemonApi.retrofitService.getAllPokemon("1118") /*1118*/
        pokeApi.enqueue(object : Callback<pokemonAll> {
            override fun onResponse(call: Call<pokemonAll>, response: Response<pokemonAll>) {

                var data = response.body()!!

                var allSize = data.results.size




                data.results.forEach { pokemon ->

                    val regex = "\\W+".toRegex()
                    val beautiful = pokemon.url.dropLast(1)
                    val numberPokemon = regex.split(beautiful).last().toInt()


                    val insert = pokemon(0,
                        numberPokemon,
                        pokemon.name,
                        pokemon.url,
                        0,
                        "",
                        "",
                        "",
                        "",
                        null)

                    mPokemonViewModel.addPokemon(insert)


                    val thisPokemon = PokemonApi.retrofitService.getDetailWithURL(pokemon.url)
                    thisPokemon.enqueue(object : Callback<PokemonResource> {
                        override fun onResponse(
                            call: Call<PokemonResource>,
                            response: Response<PokemonResource>
                        ) {
                            //thisPokemon
                            try {
                                val data = response.body()!!
                                val species = data.species.url
                                val artwork = data.sprites.other.officialartwork.front_default
                                val type = data.types


                                mPokemonViewModel.updatePokemon("img",numberPokemon, artwork)


                                when (type.size) {
                                    1 -> {
                                        val typeA = type.first().type.name
                                        val typeB = null

                                        mPokemonViewModel.updatePokemon("typeA",numberPokemon, typeA)
                                        mPokemonViewModel.updatePokemon("typeB",numberPokemon, typeB)
                                    }
                                    2 -> {
                                        val typeA = type.first().type.name
                                        val typeB = type.last().type.name

                                        mPokemonViewModel.updatePokemon("typeA",numberPokemon, typeA)
                                        mPokemonViewModel.updatePokemon("typeB",numberPokemon, typeB)
                                    }
                                }

                                val thisSpecies = PokemonApi.retrofitService.getSpecieswithUrl(species)
                                thisSpecies.enqueue(object :Callback<PokemonSpeciesFromUrl>{
                                    override fun onResponse(
                                        call: Call<PokemonSpeciesFromUrl>,
                                        response: Response<PokemonSpeciesFromUrl>
                                    ) {
                                        //thisSpecies
                                        try {
                                            val data = response.body()!!

                                            val color = data.color.name
                                            val beautiful = data.generation.url.toString().dropLast(1)
                                            val genThisPokemon = regex.split(beautiful).last()

                                            mPokemonViewModel.updatePokemon("color",numberPokemon, color)
                                            mPokemonViewModel.updatePokemon("gen",numberPokemon,genThisPokemon)

                                            var new = countPoke!! + 1
                                            countPoke = new

                                            callReady()


                                        } catch (x: Exception){

                                        }

                                    }
                                    override fun onFailure(
                                        call: Call<PokemonSpeciesFromUrl>,
                                        t: Throwable
                                    ) {

                                    }
                                })
                            } catch (x: Exception){

                            }

                        }
                        override fun onFailure(call: Call<PokemonResource>, t: Throwable) {

                        }
                    })


                }

            }

            override fun onFailure(call: Call<pokemonAll>, t: Throwable) {

            }
        })


    }

    private fun callReady(){
        mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        mPokemonViewModel.updateisReadyPokemon()
    }

    private fun clear(){
        mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        mPokemonViewModel.clear()
    }

    private fun addToList(title: String, detail: String, image: Int){
        titleList.add(title)
        detailList.add(detail)
        imageList.add(image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment introFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            introFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(position: Int) {
        val sharedPreference =  requireActivity().getSharedPreferences("isFirstTimeCome",Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putBoolean("FirstTime",false)
        editor.commit()

        Handler().postDelayed({
            findNavController().navigate(
                    R.id.action_introFragment_to_listPokemonFragment
                    )
            }, 1000)

    }

}