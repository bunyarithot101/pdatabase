package com.bbbdev.pokebase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bbbdev.pokebase.data.PokemonViewModel
import com.bbbdev.pokebase.databinding.FragmentDetailPokemonInfoBinding
import com.bbbdev.pokebase.model.pokeModel
import com.bbbdev.pokebase.pokedata.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

private var _binding: FragmentDetailPokemonInfoBinding? = null
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [detailPokemonInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detailPokemonInfoFragment : Fragment(),onCardEvolClick,onCardEvol1st,onCardEvol2nd,onCardEvol3rd {
    private lateinit var mPokemonViewModel: PokemonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail_pokemon_info, container, false)
        _binding = FragmentDetailPokemonInfoBinding.inflate(inflater, container, false)

        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(getActivity() == null) return;

        if (savedInstanceState == null){
            //anim
            detailPokemonFragment().setSlideDownAnimation(requireContext(), binding.speciesText)
            detailPokemonFragment().setSlideLeftToRightAnimation(requireContext(), binding.baseHpStat)
            detailPokemonFragment().setSlideLeftToRightAnimation(requireContext(), binding.baseDefStat)
            detailPokemonFragment().setSlideLeftToRightAnimation(requireContext(), binding.baseAtkStat)
            detailPokemonFragment().setSlideLeftToRightAnimation(requireContext(), binding.baseSpAtkStat)
            detailPokemonFragment().setSlideLeftToRightAnimation(requireContext(), binding.baseSpDefStat)
            detailPokemonFragment().setSlideLeftToRightAnimation(requireContext(), binding.baseSpdStat)

        }


        val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)

        val pokeArgs = model.detailArgsDetail?.pokemon

        loadSpeciesArea(pokeArgs)
        loadStatsArea(pokeArgs)
        loadEvolutionArea(pokeArgs)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    fun loadSpeciesArea(pokeArgs: pokemon?) {
        val pokeApi = PokemonApi.retrofitService.getPokeSpecies(pokeArgs?.number.toString())
        pokeApi.enqueue(object : Callback<PokemonSpecies> {
            override fun onResponse(
                call: Call<PokemonSpecies>,
                response: Response<PokemonSpecies>,
            ) {
                if (!response.isSuccessful) {
                    handleError(response.code().toString())
                }

                var data = response.body()!!

                var speciesTextSet = data.flavorTextEntries.filter { it.language.name == "en" }
//                var speciesQuote = speciesTextSet[(0..speciesTextSet.size!!).random()]!!.flavorText
                var speciesQuote = speciesTextSet.first().flavorText

                speciesQuote = speciesQuote.replace("\\s+".toRegex(), " ")

                binding.speciesText.text = speciesQuote

                loadColorSpecies(pokeArgs?.color)
            }

            override fun onFailure(call: Call<PokemonSpecies>, t: Throwable) {
                binding.speciesText.text = "text"
            }
        })
    }

    fun loadColorSpecies(color: String?) {
        when (color) {
            "black" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.black))
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_black))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_black))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_black))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_black))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_black))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_black))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_black))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_black))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_black))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_black))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_black))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_black))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_black))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_black))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_black))
            }
            "blue" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_blue))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_blue))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_blue))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_blue))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_blue))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_blue))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_blue))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_blue))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_blue))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_blue))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_blue))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_blue))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_blue))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_blue))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_blue))
            }
            "brown" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_brown))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_brown))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_brown))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_brown))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_brown))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_brown))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_brown))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_brown))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_brown))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_brown))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_brown))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_brown))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_brown))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_brown))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_brown))


            }
            "gray" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_gray))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_gray))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_gray))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_gray))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_gray))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_gray))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_gray))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_gray))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_gray))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_gray))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_gray))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_gray))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_gray))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_gray))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_gray))
            }
            "green" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_green))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_green))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_green))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_green))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_green))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_green))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_green))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_green))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_green))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_green))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_green))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_green))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_green))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_green))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_green))
            }
            "pink" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_pink))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_pink))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_pink))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_pink))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_pink))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_pink))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_pink))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_pink))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_pink))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_pink))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_pink))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_pink))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_pink))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_pink))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_pink))
            }
            "purple" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_purple))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_purple))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_purple))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_purple))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_purple))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_purple))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_purple))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_purple))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_purple))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_purple))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_purple))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_purple))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_purple))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_purple))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_purple))
            }
            "red" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_red))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_red))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_red))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_red))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_red))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_red))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_red))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_red))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_red))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_red))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_red))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_red))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_red))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_red))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_red))
            }
            "white" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_white))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_white))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_white))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_white))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_white))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_white))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_white))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_white))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_white))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_white))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_white))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_white))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_white))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_white))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_white))
            }
            "yellow" -> {
                binding.evolTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_yellow))
                binding.stas.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_yellow))
                binding.speciesTitle.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_yellow))

                binding.baseHpStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_yellow))
                binding.baseAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_yellow))
                binding.baseDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_yellow))
                binding.baseSpAtkStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_yellow))
                binding.baseSpDefStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_yellow))
                binding.baseSpdStat.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.base_yellow))

                binding.baseHp.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_yellow))
                binding.baseAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_yellow))
                binding.baseDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_yellow))
                binding.baseSpAtk.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_yellow))
                binding.baseSpDef.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_yellow))
                binding.baseSpd.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.stat_yellow))
            }

        }

    }

    fun loadStatsArea(pokeArgs: pokemon?) {
        val pokeApi = PokemonApi.retrofitService.getDetail(pokeArgs?.number)

        pokeApi.enqueue(object : Callback<PokemonResource> {
            override fun onFailure(call: Call<PokemonResource>, t: Throwable) {
                Timber.i("1ASFA  onFailure")
            }

            override fun onResponse(
                call: Call<PokemonResource>,
                response: Response<PokemonResource>,
            ) {
                if (!response.isSuccessful) {
                    handleError(response.code().toString())
                }

                var data = response.body()!!

                data.stats.forEach {
                    when (it.stat.name) {
                        "hp" -> {
                            binding.baseHpStat.text = it.baseStat
                            binding.baseHpStat.width = it.baseStat.toInt() +  getResources().getDimensionPixelSize(R.dimen.stat)
                        }
                        "attack" -> {
                            binding.baseAtkStat.text = it.baseStat
                            binding.baseAtkStat.width = it.baseStat.toInt() +  getResources().getDimensionPixelSize(R.dimen.stat)
                        }
                        "defense" -> {
                            binding.baseDefStat.text = it.baseStat
                            binding.baseDefStat.width = it.baseStat.toInt() +  getResources().getDimensionPixelSize(R.dimen.stat)
                        }
                        "special-attack" -> {
                            binding.baseSpAtkStat.text = it.baseStat
                            binding.baseSpAtkStat.width = it.baseStat.toInt() +  getResources().getDimensionPixelSize(R.dimen.stat)
                        }
                        "special-defense" -> {
                            binding.baseSpDefStat.text = it.baseStat
                            binding.baseSpDefStat.width = it.baseStat.toInt() +  getResources().getDimensionPixelSize(R.dimen.stat)
                        }
                        "speed" -> {
                            binding.baseSpdStat.text = it.baseStat
                            binding.baseSpdStat.width = it.baseStat.toInt() +  getResources().getDimensionPixelSize(R.dimen.stat)
                        }

                    }

                }

            }
        })


    }

    fun loadEvolutionArea(pokeArgs: pokemon?) {
        val pokeApi = PokemonApi.retrofitService.getPokeSpecies(pokeArgs?.number.toString())

        pokeApi.enqueue(object : Callback<PokemonSpecies> {
            override fun onResponse(
                call: Call<PokemonSpecies>,
                response: Response<PokemonSpecies>,
            ) {
                if (!response.isSuccessful) {
                    handleError(response.code().toString())
                }

                val data = response.body()!!
                val url = data.evolutionChain.url

                realPreson(url)
            }

            override fun onFailure(call: Call<PokemonSpecies>, t: Throwable) {
            }
        })


    }


    fun realPreson(url: String){
        if(getActivity() == null) return;

        var poke = PokemonApi.retrofitService.getEvolution(url)
        poke.enqueue(object : Callback<PokemonEvolution> {
            override fun onResponse(
                call: Call<PokemonEvolution>,
                response: Response<PokemonEvolution>,
            ) {
                if (!response.isSuccessful) {
                    handleError(response.code().toString())
                }


                var data = response.body()!!

                var datasetFirst = data.chain
                var datasetSecond = data.chain.evolvesTo
                var datasetThird: storageEvolvesTo3rd? = null
                for (it in data.chain.evolvesTo) {
                    datasetThird = storageEvolvesTo3rd(it.evolvesTo)
                }

                var datasetFirstSize = 1
                var datasetSecondSize = datasetSecond.filter { it.species != null }.size
                var datasetThirdSize = datasetThird?.evolvesTo?.filter { it.species != null }?.size

                if (datasetThirdSize == null) {
                    datasetThirdSize = 0
                }

                var span2nd: Int = 1
                var span3nd: Int = 1

                val model = ViewModelProviders.of(requireActivity()).get(pokeModel::class.java)
                var color =   model.detailArgsDetail?.pokemon?.color.toString()
                mPokemonViewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)




                if (datasetFirstSize != 0) {

                    /*FIRST*/
                    val linearLayoutManager = LinearLayoutManager(context)
                    val recyclerView = binding.recyclerFor1
                    val itemArrayAdapter = PokemonEvolAdapter(
                        requireContext(),
                        datasetFirst,
                        color,
                        model.detailArgsDetail,
                        this@detailPokemonInfoFragment
                    )
                    recyclerView.setLayoutManager(linearLayoutManager)
                    recyclerView.setAdapter(itemArrayAdapter)

                }

                if (datasetSecondSize != 0) {

                    /*Second*/
//                val linearLayoutManager2nd = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    if (datasetSecondSize > 2) {
                        binding.linearEvo.setOrientation(LinearLayout.VERTICAL);
                        span2nd = 3
                    }

                    val linearLayoutManager2nd = GridLayoutManager(context,
                        span2nd,
                        LinearLayoutManager.VERTICAL,
                        false)
                    val recyclerView2nd = binding.recyclerFor2
                    val itemArrayAdapter2nd = PokemonEvol2ndAdapter(
                        requireContext(),
                        datasetSecond,
                        color,
                        model.detailArgsDetail,
                        this@detailPokemonInfoFragment
                    )
                    recyclerView2nd.setLayoutManager(linearLayoutManager2nd)
                    recyclerView2nd.setAdapter(itemArrayAdapter2nd)

                } else {
                    binding.framgeRe2.setVisibility(View.GONE)
                    binding.framgeRe3.setVisibility(View.GONE)
                }


                if (datasetThirdSize != 0) {
                    /* Third*/
                    if (datasetThirdSize > 2) {
                        binding.linearEvo.setOrientation(LinearLayout.VERTICAL);
                        span3nd = 3
                    }
                    val linearLayoutManager3rd = LinearLayoutManager(context,
                        LinearLayoutManager.HORIZONTAL,
                        false)
                    val recyclerView3rd = binding.recyclerFor3
                    val itemArrayAdapter3rd = PokemonEvol3rdAdapter(
                        requireContext(),
                        datasetThird,
                        color,
                        model.detailArgsDetail,
                        this@detailPokemonInfoFragment
                    )
                    recyclerView3rd.setLayoutManager(linearLayoutManager3rd)
                    recyclerView3rd.setAdapter(itemArrayAdapter3rd)

                } else {
                    binding.framgeRe3.setVisibility(View.GONE)
                }


            }

            override fun onFailure(call: Call<PokemonEvolution>, t: Throwable) {
                Timber.i("ERR")
            }

        })
    }

    fun dpToPx(dp: Int, context: Context): Int {
        val density: Float = context.getResources().getDisplayMetrics().density
        return Math.round(dp.toFloat() * density)
    }


    override fun onCardEvol1stClick(name: String) {
        val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)

        mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        mPokemonViewModel.getPokemon(name)

        mPokemonViewModel.data1st.observe(viewLifecycleOwner, Observer { args ->
            val action = detailPokemonFragmentDirections.actionDetailPokemonFragmentSelf(args.first())
            findNavController().navigate(action)

        })

    }

    override fun onCardEvol2ndClick(name: String) {

        mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        mPokemonViewModel.getPokemon(name)

        mPokemonViewModel.data1st.observe(viewLifecycleOwner, Observer { args ->
            val action = detailPokemonFragmentDirections.actionDetailPokemonFragmentSelf(args.first())
            findNavController().navigate(action)
        })

    }

    override fun onCardEvol3rdClick(name: String) {
        val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)

        mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        mPokemonViewModel.getPokemon(name)

        mPokemonViewModel.data1st.observe(viewLifecycleOwner, Observer { args ->
            val action = detailPokemonFragmentDirections.actionDetailPokemonFragmentSelf(args.first())
            findNavController().navigate(action)
        })

    }

    override fun onCardEvolClick(id: String) {
        val model = ViewModelProviders.of(requireActivity()).get(pokeModel::class.java)
        model.setSearch(id)

    }



}