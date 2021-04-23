package com.bbbdev.pokebase.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbbdev.pokebase.R
import com.bbbdev.pokebase.introFragment
import com.bbbdev.pokebase.pokedata.onCardPokeClickListener
import timber.log.Timber

class ViewPagerAdapter(
    private var title: List<String>,
    private var detail: List<String>,
    private var images: List<Int>,
    private val onPage2Click: onPage2Clicked,
): RecyclerView.Adapter<ViewPagerAdapter.Page2ViewHolder>() {

    inner class Page2ViewHolder(itenView: View): RecyclerView.ViewHolder(itenView){
        val title: TextView = itenView.findViewById(R.id.titlefa)
        val detail: TextView = itenView.findViewById(R.id.detailac)
        val image: ImageView = itenView.findViewById(R.id.imagea)
        val btn: Button = itenView.findViewById(R.id.btn_gotolist)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewPagerAdapter.Page2ViewHolder {

        return Page2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_welcome,parent,false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Page2ViewHolder, position: Int) {
        holder.title.text = title[position]
        holder.detail.text = detail[position]
        holder.image.setImageResource(images[position])


        holder.btn.setOnClickListener {
            onPage2Click.onClick(position)
        }

        if(position + 1 == title.size){
            holder.btn.setVisibility(View.VISIBLE)
        }

    }

    override fun getItemCount(): Int {
        return title.size
    }
}
