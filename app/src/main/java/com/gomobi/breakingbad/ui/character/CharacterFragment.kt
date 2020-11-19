package com.gomobi.breakingbad.ui.character

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.gomobi.breakingbad.MainActivity
import com.gomobi.breakingbad.R
import com.gomobi.breakingbad.model.BadCharacter
import com.gomobi.breakingbad.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_character.view.*

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


class CharacterFragment: BaseFragment() {

    override fun layoutId(): Int = R.layout.fragment_character

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs = CharacterFragmentArgs.fromBundle(it)
            populateView(view, safeArgs.character)
        }
    }

    private fun populateView(view: View, character: BadCharacter) {
        (requireActivity() as MainActivity).toolbar.title = character.name
        Glide.with(view).load(character.img).into(view.imgCharacter)
        view.tvName.text = character.name
        view.tvStatus.text = character.status
        view.tvNickname.text = character.nickname
        view.tvOccupation.text = character.makeOccupationString()
        view.tvAppearance.text = character.makeApperancesString()
    }
}