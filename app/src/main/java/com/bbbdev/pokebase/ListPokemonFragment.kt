package com.bbbdev.pokebase

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bbbdev.pokebase.data.PokemonViewModel
import com.bbbdev.pokebase.databinding.FragmentListPokemonBinding
import com.bbbdev.pokebase.model.pokeModel
import com.bbbdev.pokebase.pokedata.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import timber.log.Timber
import java.text.FieldPosition


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private var _binding:  FragmentListPokemonBinding? = null
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [ListPokemonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListPokemonFragment : Fragment(), onCardPokeClickListener {
    private lateinit var mPokemonViewModel: PokemonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_list_pokemon, container, false)
        _binding = FragmentListPokemonBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(false);

        val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)

        val filterdGen = model.generationfiltered.isNullOrEmpty()
        val filterdType = model.typefiltered.isNullOrEmpty()
        val filterdSearch = model.searchfiltered.isNullOrEmpty()


        // not filtered at all
        if(filterdGen == true && filterdType == true && filterdSearch == true){

            model.setPositionList(0)

            mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
            mPokemonViewModel.countforOnload?.observe(viewLifecycleOwner, Observer { number ->

                if(number != 0){
                    loadallPokemon()
                }

            })

        } else {

            var genTrigger:String? = null
            var TypeTrigger:String? = null
            if(model.generationfiltered == null){
                genTrigger = "ALL GENERATION"
            } else {
                genTrigger = "GENERATION " + model.generationfiltered
            }

            if(model.typefiltered == null){
                TypeTrigger = "ALL TYPE"
            } else {
                TypeTrigger = model.typefiltered
            }

            binding.listDialogsGenTrigger.text = genTrigger
            binding.listDialogsTypeTrigger.text = TypeTrigger

            binding.searchInput.setQuery(model.searchfiltered, false);

            loadPokemonFiltered(model.generationfiltered,model.typefiltered,model.searchfiltered)

        }


        val items = arrayOf("ALL GENERATION",
                            "GENERATION 1",
                            "GENERATION 2",
                            "GENERATION 3",
                            "GENERATION 4",
                            "GENERATION 5",
                            "GENERATION 6",
                            "GENERATION 7",
                            "GENERATION 8")


        binding.listDialogsGenTrigger.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme_Center)
                .setTitle(resources.getString(R.string.dialogs_gen))
                .setItems(items) { dialog, which ->
                    // Respond to item chosen
//                    items[which.toString().toInt()]
                    binding.progressRecyclerViewListPoke.setVisibility(View.VISIBLE)
                    binding.recyclerviewPokeList.setVisibility(View.INVISIBLE)

                    when(items[which.toString().toInt()]){
                        "ALL GENERATION" -> {
                            model?.setGeneration(null)
                            binding.listDialogsGenTrigger.text = "ALL GENERATION"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)
                        }

                        "GENERATION 1" -> {
                            model?.setGeneration("1")
                            binding.listDialogsGenTrigger.text = "GENERATION 1"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)
                        }

                        "GENERATION 2" -> {
                            model?.setGeneration("2")
                            binding.listDialogsGenTrigger.text = "GENERATION 2"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)
                        }

                        "GENERATION 3" -> {
                            model?.setGeneration("3")
                            binding.listDialogsGenTrigger.text = "GENERATION 3"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)
                        }

                        "GENERATION 4" -> {
                            model?.setGeneration("4")
                            binding.listDialogsGenTrigger.text = "GENERATION 4"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)
                        }

                        "GENERATION 5" -> {
                            model?.setGeneration("5")
                            binding.listDialogsGenTrigger.text = "GENERATION 5"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)
                        }

                        "GENERATION 6" -> {
                            model?.setGeneration("6")
                            binding.listDialogsGenTrigger.text = "GENERATION 6"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)
                        }

                        "GENERATION 7" -> {
                            model?.setGeneration("7")
                            binding.listDialogsGenTrigger.text = "GENERATION 7"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)
                        }

                        "GENERATION 8" -> {
                            model?.setGeneration("8")
                            binding.listDialogsGenTrigger.text = "GENERATION 8"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)
                        }

                    }

                }
                .show()
        }

        val itemsType = arrayOf("ALL TYPE",
                                "NORMAL",
                                "FIGHTING",
                                "FLYING",
                                "POISON",
                                "GROUND",
                                "ROCK",
                                "BUG",
                                "GHOST",
                                "STEEL",
                                "FIRE",
                                "WATER",
                                "GRASS",
                                "ELECTRIC",
                                "PSYCHIC",
                                "ICE",
                                "DRAGON",
                                "DARK",
                                "FAIRY",
                                "UNKNOWN",
                                "SHADOW", )

        //type

        binding.listDialogsTypeTrigger.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme_Center)
                .setTitle(resources.getString(R.string.dialogs_type))
                .setItems(itemsType) { dialog, which ->
                    // Respond to item chosen
//                    items[which.toString().toInt()]
                    binding.progressRecyclerViewListPoke.setVisibility(View.VISIBLE)
                    binding.recyclerviewPokeList.setVisibility(View.INVISIBLE)
                    when(itemsType[which]){

                        "ALL TYPE" -> {
                            model?.setType(null)
                            binding.listDialogsTypeTrigger.text = "ALL TYPE"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }
                        "NORMAL" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "NORMAL"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "FIGHTING" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "FIGHTING"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "FLYING" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "FLYING"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "POISON" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "POISON"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "GROUND" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "GROUND"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "ROCK" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "ROCK"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "BUG" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "BUG"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "GHOST" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "GHOST"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "STEEL" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "STEEL"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "FIRE" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "FIRE"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "WATER" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "WATER"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "GRASS" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "GRASS"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "ELECTRIC" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "ELECTRIC"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "PSYCHIC" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "PSYCHIC"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "ICE" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "ICE"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "DRAGON" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "DRAGON"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "DARK" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "DARK"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        "FAIRY" -> {
                            model?.setType(itemsType[which])
                            binding.listDialogsTypeTrigger.text = "FAIRY"

                            val type = model.typefiltered
                            val gen = model.generationfiltered
                            val search = model.searchfiltered
                            loadPokemonFiltered(gen, type, search)

                        }

                        // pokemon not has type UNKNOWN , SHADOW

                    }

                }
                .show()
        }



        binding.searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                model?.setSearch(newText)
                loadPokemonFiltered(mPokemonViewModel.nowGen, mPokemonViewModel.nowType, newText)

                return true
            }

        })

    }


//    private fun test(){
//
//
//        var text = mutableListOf<String>()
//        text.add("1")
//        text.add("2")
//        text.add("3")
//        text.add("4")
//        text.add("5")
//        text.add("6")
//
//
//        val adpter = testAdapter(requireContext(),text)
//
//        val recyclerView = binding.recyclerviewPokeList
//        recyclerView.adapter = adpter
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        binding.progressRecyclerViewListPoke.setVisibility(View.INVISIBLE)
//
//
//    }


    private fun loadallPokemon(){

        val adpter = PokemonAdapter(
            requireContext(),
            this@ListPokemonFragment)

        val recyclerView = binding.recyclerviewPokeList
        recyclerView.adapter = adpter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        mPokemonViewModel.readAllData.observe(viewLifecycleOwner, Observer { pokemon ->
            adpter.setData(pokemon)

            recyclerView.apply {
                binding.progressRecyclerViewListPoke.setVisibility(View.INVISIBLE)
                binding.recyclerviewPokeList.setVisibility(View.VISIBLE)
                layoutAnimation = AnimationUtils.loadLayoutAnimation(
                    context,
                    R.anim.layout_animation_from_bottom
                )
                layoutAnimationListener = object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        layoutManager?.findViewByPosition(0)?.clearAnimation()
                    }
                    override fun onAnimationEnd(animation: Animation?) { }
                    override fun onAnimationRepeat(animation: Animation?) { }
                }
                adapter?.notifyDataSetChanged()
                scheduleLayoutAnimation()
            }

            val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)
            model.positionList.observe(requireActivity(), object : Observer<Any> {
                override fun onChanged(t: Any?) {
                    recyclerView.scrollToPosition(t.toString().toInt())
                }
            })

        })


    }


    fun loadPokemonFiltered(gen:String? ,type: String?, searchText: String?){
        val adpter = PokemonAdapter(
            requireContext(),
            this@ListPokemonFragment)

        val recyclerView = binding.recyclerviewPokeList
        recyclerView.adapter = adpter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mPokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        mPokemonViewModel.sendFiltered(gen, type, searchText)

        if(mPokemonViewModel.isFiltered == true){

            mPokemonViewModel.dataFiltered.observe(viewLifecycleOwner, Observer { pokemon ->
                adpter.setData(pokemon)

                //default
                binding.nodataFrame.setVisibility(View.GONE)
                binding.recyclerviewPokeList.setVisibility(View.VISIBLE)

                //if nodata call nodata frame and hide recyclerview
                if(1 > pokemon.size){
                    binding.nodataFrame.setVisibility(View.VISIBLE)
                    binding.recyclerviewPokeList.setVisibility(View.GONE)
                }


                recyclerView.apply {
                    binding.progressRecyclerViewListPoke.setVisibility(View.INVISIBLE)
                    binding.recyclerviewPokeList.setVisibility(View.VISIBLE)
                    layoutAnimation = AnimationUtils.loadLayoutAnimation(
                        context,
                        R.anim.layout_animation_from_bottom
                    )
                    layoutAnimationListener = object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            layoutManager?.findViewByPosition(0)?.clearAnimation()
                        }
                        override fun onAnimationEnd(animation: Animation?) { }
                        override fun onAnimationRepeat(animation: Animation?) { }
                    }
                    adapter?.notifyDataSetChanged()
                    scheduleLayoutAnimation()
                }

                val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)
                model.positionList.observe(requireActivity(), object : Observer<Any> {
                    override fun onChanged(t: Any?) {
                        recyclerView.scrollToPosition(t.toString().toInt())
                    }
                })

            })

        } else if (mPokemonViewModel.isFiltered == false){
            loadallPokemon()
        }

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

//    fun callRecyclerView(pokemonSpecies: List<pokemon>){
//
//        var recyclerView: RecyclerView = binding.recyclerviewPokeList
//        val model = ViewModelProviders.of(requireActivity()).get(pokeModel::class.java)
//        val linearLayoutManager = LinearLayoutManager(context?.applicationContext)
//        val itemArrayAdapter = PokemonAdapter(
//            requireContext(),
//            pokemonSpecies,
//            this@ListPokemonFragment
//        )
//        recyclerView.setHasFixedSize(true)
//        recyclerView.setLayoutManager(linearLayoutManager)
//        recyclerView.setAdapter(itemArrayAdapter)
//
//
//        /*set position when comeback*/
//        model.positionList.observe(requireActivity(), object : Observer<Any> {
//            override fun onChanged(t: Any?) {
//                recyclerView.scrollToPosition(t.toString().toInt())
//            }
//        })
//
//        recyclerView.apply {
//            binding.progressRecyclerViewListPoke.setVisibility(View.INVISIBLE)
//            binding.recyclerviewPokeList.setVisibility(View.VISIBLE)
//            layoutAnimation = AnimationUtils.loadLayoutAnimation(
//                context,
//                R.anim.layout_animation_from_bottom
//            )
//            layoutAnimationListener = object : Animation.AnimationListener {
//                override fun onAnimationStart(animation: Animation?) {
//                    layoutManager?.findViewByPosition(0)?.clearAnimation()
//                }
//                override fun onAnimationEnd(animation: Animation?) { }
//                override fun onAnimationRepeat(animation: Animation?) { }
//            }
//            adapter?.notifyDataSetChanged()
//            scheduleLayoutAnimation()
//        }
//
//
//        model.serachingText.observe(requireActivity(), object : Observer<Any> {
//            override fun onChanged(newText: Any?) {
//                recyclerView.setLayoutManager(linearLayoutManager)
//                recyclerView.setAdapter(itemArrayAdapter)
//
//                var filteredPokemonSpeciesNumber = pokemonSpecies.filter {
//                    it.number.toString().contains(newText.toString())
//                }
//
//                var filteredPokemonSpeciesText = pokemonSpecies.filter {
//                    it.getName().toString().contains(newText.toString().toUpperCase())
//                }
//
//                binding.nodataFrame.setVisibility(View.GONE)
//                binding.recyclerviewPokeList.setVisibility(View.VISIBLE)
//
//                if (1 > filteredPokemonSpeciesNumber.size && 1 > filteredPokemonSpeciesText.size) {
////                   nothing found
//                    binding.nodataFrame.setVisibility(View.VISIBLE)
//                    binding.recyclerviewPokeList.setVisibility(View.INVISIBLE)
//
//
//                } else if (1 > filteredPokemonSpeciesNumber.size) {
////                    found  with text
//                    val itemArrayAdapter = PokemonAdapter(
//                        requireContext(),
//                        filteredPokemonSpeciesText,
//                        this@ListPokemonFragment
//                    )
//                    recyclerView.setLayoutManager(linearLayoutManager)
//                    recyclerView.setAdapter(itemArrayAdapter)
//                } else if (1 > filteredPokemonSpeciesText.size) {
////                  found  with number
//                    val itemArrayAdapter = PokemonAdapter(
//                        requireContext(),
//                        filteredPokemonSpeciesNumber,
//                        this@ListPokemonFragment
//                    )
//                    recyclerView.setLayoutManager(linearLayoutManager)
//                    recyclerView.setAdapter(itemArrayAdapter)
//                }
//            }
//        })
//
//        binding.searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//
//                model.setserachingText(newText)
//
//                recyclerView.setLayoutManager(linearLayoutManager)
//                recyclerView.setAdapter(itemArrayAdapter)
//
//                var filteredPokemonSpeciesNumber = pokemonSpecies.filter {
//                    it.number.toString().contains(newText.toString())
//                }
//
//                var filteredPokemonSpeciesText = pokemonSpecies.filter {
//                    it.getName().toString().contains(newText.toString().toUpperCase())
//                }
//
//                binding.nodataFrame.setVisibility(View.GONE)
//                binding.recyclerviewPokeList.setVisibility(View.VISIBLE)
//
//                if (1 > filteredPokemonSpeciesNumber.size && 1 > filteredPokemonSpeciesText.size) {
//                    Timber.i("ASDSAD nothing found")
//                    binding.nodataFrame.setVisibility(View.VISIBLE)
//                    binding.recyclerviewPokeList.setVisibility(View.INVISIBLE)
//
//
//                } else if (1 > filteredPokemonSpeciesNumber.size) {
//                    Timber.i("ASDSAD  found  with text")
//                    val itemArrayAdapter = PokemonAdapter(
//                        requireContext(),
//                        filteredPokemonSpeciesText,
//                        this@ListPokemonFragment
//                    )
//                    recyclerView.setLayoutManager(linearLayoutManager)
//                    recyclerView.setAdapter(itemArrayAdapter)
//                } else if (1 > filteredPokemonSpeciesText.size) {
//                    Timber.i("ASDSAD  found  with number")
//                    val itemArrayAdapter = PokemonAdapter(
//                        requireContext(),
//                        filteredPokemonSpeciesNumber,
//                        this@ListPokemonFragment
//                    )
//                    recyclerView.setLayoutManager(linearLayoutManager)
//                    recyclerView.setAdapter(itemArrayAdapter)
//                }
//                return false
//            }
//
//        })
//
//
////        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
////            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
////                super.onScrolled(recyclerView, dx, dy)
////
////                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
////                val firstVisible = layoutManager.findFirstVisibleItemPosition()
////                val b = layoutManager.findFirstCompletelyVisibleItemPosition()
////                val lastVisible = layoutManager.findLastVisibleItemPosition()
////                val d = layoutManager.findLastCompletelyVisibleItemPosition()
////
////            }
////        })
//
//
////        var newIndex:Int = pokemonSpecies.size
////        recyclerView.scrollToPosition(60)
//
//    }
//
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.main_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater)
//
//
//        val menuItem = menu!!.findItem(R.id.search)
//        val linearLayoutManager = LinearLayoutManager(context)
//        val recyclerView = binding.recyclerviewPokeList
//        val model = ViewModelProviders.of(requireActivity()).get(pokeModel::class.java)
//
//
//
//        if(menuItem != null){
//            val searchView = menuItem.actionView as SearchView
////            searchView.setQuery(model.serachingText.toString(), false);
//            model.serachingText.observe(requireActivity(), object : Observer<Any> {
//                override fun onChanged(textSearhing: Any?) {
//                    if (1 > textSearhing.toString().length) {
//                        searchView.setQuery("", false);
//                        searchView.queryHint = "search"
//                        searchView.setIconifiedByDefault(true)
//
//                    } else {
//                        searchView.setQuery(textSearhing.toString(), false);
//                        searchView.setIconifiedByDefault(false)
//                    }
//                }
//
//            })
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(newText: String?): Boolean {
//
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//
//                    model.setserachingText(newText)
//
//
//                    var data = model.reArrangedSet
//
//                    var filteredPokemonSpeciesNumber = data?.filter {
//                        it.number.toString().contains(newText.toString())
//                    }
//
//                    var filteredPokemonSpeciesText = data?.filter {
//                        it.getName().toString().contains(newText.toString().toUpperCase())
//                    }
//
//                    if (1 > filteredPokemonSpeciesNumber!!.size && 1 > filteredPokemonSpeciesText!!.size) {
//                        Timber.i("ASDSAD nothing found")
//                        binding.nodataFrame.setVisibility(View.VISIBLE)
//                        binding.recyclerviewPokeList.setVisibility(View.INVISIBLE)
//
//                    } else if (1 > filteredPokemonSpeciesNumber!!.size) {
//                        Timber.i("ASDSAD  found  with text")
//                        binding.nodataFrame.setVisibility(View.GONE)
//                        callRecyclerView(filteredPokemonSpeciesText!!)
//                    } else if (1 > filteredPokemonSpeciesText!!.size) {
//                        Timber.i("ASDSAD  found  with number")
//                        binding.nodataFrame.setVisibility(View.GONE)
//                        callRecyclerView(filteredPokemonSpeciesNumber!!)
//                    } else if (1 > newText?.length!!) {
//                        callRecyclerView(data!!)
//                    }
//
//
//
//                    return true
//                }
//
//
//            })
//
//        }
//
//    }
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
//        return when (item.itemId) {
//            R.id.search -> {
//                //newGame()
//
//                activity?.onBackPressed()
//
//                Timber.i("option clicked")
//                true
//            }
//
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//


    override fun onItemClick(args: pokemon, position: Int) {
        val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)
        model.setPositionList(position)

//        val bundle = Bundle()
//        bundle.putString("number", number.toString())
//
//        val model = ViewModelProviders.of(requireActivity()).get(pokeModel::class.java)
//        model?.setPositionList(position)
//        model.setNumberPokemonDetail(number.toString())
//        model.setNumberPokemonOnDetailList(number.toString())
//
//
//        val navController = findNavController()
//        val destination = navController.currentDestination as FragmentNavigator.Destination
//        if (javaClass.name == destination.className) {
//            navController.navigate(
//                 R.id.action_listPokemonFragment_to_detailPokemonFragment, bundle
//            )
//        }


    }



}