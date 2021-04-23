package com.bbbdev.pokebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bbbdev.pokebase.data.PokemonViewModel
import com.bbbdev.pokebase.databinding.FragmentDetailPokemonMoveBinding
import com.bbbdev.pokebase.model.pokeModel
import com.bbbdev.pokebase.pokedata.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception


private var _binding: FragmentDetailPokemonMoveBinding? = null
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [detailPokemonMoveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detailPokemonMoveFragment : Fragment() {
    private lateinit var mPokemonViewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail_pokemon_move, container, false)
        _binding = FragmentDetailPokemonMoveBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(getActivity() == null) return;

        val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)

        val pokeArgs = model.detailArgsDetail?.pokemon

        checkMove(pokeArgs!!)
//        loadMove(pokeArgs!!)

    }

    fun handleError(errCode: String) {
        val bundle = Bundle()
        var errTxt = errCode.toString()
        bundle.putString("error", errTxt)

        val navController = findNavController()
        val destination = navController.currentDestination as FragmentNavigator.Destination
        if (javaClass.name == destination.className) {
            navController.navigate(
                R.id.action_listPokemonFragment_to_notFoundFragment, bundle
            )
        }
    }



    private fun checkMove(pokemon: pokemon){
        mPokemonViewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)
        mPokemonViewModel.getMoveThisPokemonCount(pokemon.name)

        mPokemonViewModel.countMove?.observe(viewLifecycleOwner, Observer { number ->
            if(number?.toInt() == 0){
                insertMove(pokemon!!)
            } else {
                callRecyclerview(pokemon!!)
            }
        })

    }

    fun insertMove(pokemon: pokemon){
        val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)
        val pokeApi = PokemonApi.retrofitService.getDetailWithString(pokemon.number.toString())

        pokeApi.enqueue(object : retrofit2.Callback<PokemonResource> {
            override fun onResponse(
                call: Call<PokemonResource>,
                response: Response<PokemonResource>,
            ) {
                if (!response.isSuccessful) {
                    handleError(response.code().toString())
                }

                val data = response.body()!!
                mPokemonViewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)



                data.moves.forEach {

                    var thisMove = PokemonApi.retrofitService.getMoveWithUrl(it.move.url)
                    thisMove.enqueue(object :Callback<pokemonMove>{
                        override fun onResponse(
                            call: Call<pokemonMove>,
                            response: Response<pokemonMove>
                        ) {
                            try {

                                val data = response.body()!!

                                val insert = pokemonMoveData(0,
                                                pokemon.number,
                                                pokemon.name,
                                                data.name,
                                                data.power,
                                                data.accuracy,
                                                data.type.name
                                            )

                                mPokemonViewModel.addPokemonMove(insert)
                                model.addTotalMove(1)

                            } catch (x: Exception){
                                //404 not found
                            }

                        }
                        override fun onFailure(call: Call<pokemonMove>, t: Throwable) {

                        }
                    })

                }


                callRecyclerview(pokemon!!)

            }

            override fun onFailure(call: Call<PokemonResource>, t: Throwable) {
            }

        })
    }

    fun callRecyclerview(pokemon: pokemon) {
        val adpter = PokemonMoveAdapter(requireContext())

        val recyclerView = binding.recyclerviewPokemonMove
        recyclerView.adapter = adpter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mPokemonViewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)
        mPokemonViewModel.getAllMoveThisPokemon(pokemon.name)


        mPokemonViewModel.dataThisPokemonMove?.observe(viewLifecycleOwner, Observer { move ->

            adpter.setData(move)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}