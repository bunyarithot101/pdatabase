package com.bbbdev.pokebase

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bbbdev.pokebase.data.PokemonViewModel
import com.bbbdev.pokebase.databinding.FragmentDetailPokemonBinding
import com.bbbdev.pokebase.model.pokeModel
import com.bbbdev.pokebase.pokedata.*
import com.google.android.material.chip.ChipGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

private var _binding: FragmentDetailPokemonBinding? = null
private val binding get() = _binding!!


/**
 * A simple [Fragment] subclass.
 * Use the [detailPokemonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detailPokemonFragment : Fragment() {
    private val args by navArgs<detailPokemonFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail_pokemon, container, false)

        _binding = FragmentDetailPokemonBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(getActivity() == null) return;

        val model = ViewModelProvider(requireActivity()).get(pokeModel::class.java)
        model.setArgsDetail(args)

        //avoid null when fragment destroyed
        if (savedInstanceState == null){
            requireActivity().supportFragmentManager.beginTransaction().apply{
                replace(R.id.detail_poke_frag_import, detailPokemonInfoFragment())
                addToBackStack(null)
                commit()
            }

            setFadeAnimation(binding.pokemonCard);
        }

        LoadPokeCard(args.pokemon.number)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.toString()){
                "Info" -> {
                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        replace(R.id.detail_poke_frag_import, detailPokemonInfoFragment())
                        commit()
                    }
                }

                "Move" -> {
                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        replace(R.id.detail_poke_frag_import, detailPokemonMoveFragment())
                        commit()
                    }

                }
            }
            true
        }

    }

    fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 800
        view.startAnimation(anim)
    }

    fun setFallDownAnimation(context: Context,view: View) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.fall_down)
        view.startAnimation(animation)
    }

    fun setSlideDownAnimation(context: Context,view: View) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_down)
        view.startAnimation(animation)
    }

    fun setSlideLeftToRightAnimation(context: Context,view: View) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_left_to_right)
        view.startAnimation(animation)
    }


    fun LoadPokeCard(number: Int?){


        binding.lName.text = args.pokemon.name.toUpperCase()
        binding.lNumber.text = "#" + String.format("%03d", args.pokemon.number)

        Glide.with(requireActivity())
            .load(args.pokemon.imgResource)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    binding.cardOnload.setVisibility(View.INVISIBLE)

                    return false
                }

            })
            .dontAnimate()
            .override(200, 200)
            .into(binding.artwork)

        binding.chipTestA.text = args.pokemon.typeA
        binding.chipTestA.setVisibility(View.VISIBLE)

        binding.chipTestB.text = args.pokemon.typeB
        binding.chipTestB.setVisibility(View.VISIBLE)

        if(args.pokemon.typeB == null){
            binding.chipTestB.setVisibility(View.GONE)
        }

        LoadColorCard(args.pokemon.color)

    }
    fun LoadColorCard(color: String?){

        when (color) {
            "black" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_black))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_black)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_black)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_black))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_black))
            }
            "blue" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_blue))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_blue)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_blue)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_blue))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_blue))
            }
            "brown" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_brown))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_brown)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_brown)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_brown))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_brown))
            }
            "gray" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_gray))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_gray)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_gray)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_gray))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_gray))
            }
            "green" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_green))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_green)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_green)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_green))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_green))

            }
            "pink" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_pink))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_pink)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_pink)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_pink))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_pink))
            }
            "purple" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_purple))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_purple)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_purple)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_purple))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_purple))
            }
            "red" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_red))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_red)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_red)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_red))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_red))
            }
            "white" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_white))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_white)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_white)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_white))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_white))
            }
            "yellow" -> {
                binding.pokemonCard.setCardBackgroundColor(requireActivity().getResources().getColor(R.color.type_yellow))
                binding.chipTestA.setChipBackgroundColorResource(R.color.type_yellow)
                binding.chipTestB.setChipBackgroundColorResource(R.color.type_yellow)
                binding.shadowArtwork.setBackground(requireActivity().getResources().getDrawable(R.drawable.card_yellow))
                binding.bottomNavigation.setBackgroundColor(requireActivity().getResources().getColor(R.color.type_yellow))
            }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}