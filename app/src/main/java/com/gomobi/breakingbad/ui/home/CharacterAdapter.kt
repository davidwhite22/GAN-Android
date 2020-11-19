package com.gomobi.breakingbad.ui.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.gomobi.breakingbad.R
import com.gomobi.breakingbad.model.BadCharacter
import com.gomobi.breakingbad.ui.base.GenericListAdapter
import com.gomobi.breakingbad.ui.base.GenericListViewHolder
import kotlinx.android.synthetic.main.row_character.view.*

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


class CharacterAdapter(context: Context): GenericListAdapter<BadCharacter, CharacterAdapter.CharacterViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return createHolder(parent, R.layout.row_character, ::CharacterViewHolder)
    }

    inner class CharacterViewHolder(itemView: View): GenericListViewHolder<BadCharacter>(itemView) {
        private val image = itemView.imgCharacter
        private val tvName = itemView.tvName

        override fun bind(item: BadCharacter, position: Int) {
            tvName.text = item.name
            Glide.with(itemView)
                    .load(item.img)
                    .placeholder(R.drawable.placeholder)
                    .into(image)
            itemView.setOnClickListener {
                val directions = HomeFragmentDirections.actionNavHomeToNavCharacter(item)
                itemView.findNavController().navigate(directions)
            }
        }

    }
}