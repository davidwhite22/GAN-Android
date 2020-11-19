package com.gomobi.breakingbad.ui.home

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gomobi.breakingbad.R
import com.gomobi.breakingbad.model.CharacterFilter
import com.gomobi.breakingbad.ui.base.BaseFragment
import com.gomobi.breakingbad.ui.custom.LoadingView
import com.gomobi.breakingbad.viewmodel.BadViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


class HomeFragment: BaseFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var badViewModel: BadViewModel
    private lateinit var adapter: CharacterAdapter
    private lateinit var spAdapter: SeriesSpinnerAdapter
    private lateinit var edtName: EditText
    private lateinit var spSeries: Spinner
    private lateinit var loadingView: LoadingView

    override fun layoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingView = view.loadingView
        loadingView.hide()
        recyclerView = view.recyclerView
        edtName = view.edtName
        spSeries = view.spSeries
        initSpinner()
        initList()
        initViewModel()
    }

    private fun getSeries(): List<String> {
        val ls = ArrayList<String>()
        ls.add(getString(R.string.spAnySeries))
        for (idx in 1..MAXSERIES) {
            ls.add(getString(R.string.spSeries, idx))
        }
        return ls
    }

    private fun initSpinner() {
        spAdapter = SeriesSpinnerAdapter(requireContext(), getSeries())
        spSeries.adapter = spAdapter
        spSeries.onItemSelectedListener = SpinnerSelectedListener(0,
                object : SpinnerSelectedListener.OnSpinnerItemSelected{
                    override fun onSelectedItem(position: Int) {
                        performFilter()
                    }
                }
        )
    }

    private fun initList() {
        adapter = CharacterAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initViewModel() {
        badViewModel = ViewModelProvider(this).get(BadViewModel::class.java)
        badViewModel.apiError.observe(viewLifecycleOwner, {exception ->
            exception?.let {
                loadingView.hide()
                showInternetError()
            }
        })
        badViewModel.characterList.observe(viewLifecycleOwner, {characters ->
            adapter.setListItems(characters)
            loadingView.hide()
        })
        loadingView.show()
        badViewModel.loadCharacters()
        edtName.doAfterTextChanged {
            performFilter()
        }

    }

    private fun showInternetError() {
        val yesClick = DialogInterface.OnClickListener { _, _ ->
            badViewModel.loadCharacters()
        }
        val builder = AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.error)
            setMessage(R.string.error_Msg)
            setNegativeButton(R.string.error_no, null)
            setPositiveButton(R.string.error_yes, yesClick)
            setCancelable(false)
        }
        builder.create().show()
    }

    private fun performFilter() {
        loadingView.show()
        val filter = CharacterFilter(edtName.text.toString(), spSeries.selectedItemPosition)
        badViewModel.filter(filter)
    }

    companion object {
        private const val MAXSERIES = 5
        //region Keyboard Stuff
        fun dismissKeyboard(view: View?) {
            view?.let {
                val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        //endregion
    }

}