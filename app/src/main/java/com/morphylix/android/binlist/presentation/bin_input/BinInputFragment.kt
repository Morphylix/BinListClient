package com.morphylix.android.binlist.presentation.bin_input

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.morphylix.android.binlist.databinding.FragmentBinInputBinding
import com.morphylix.android.binlist.domain.model.domain.CardBinData
import com.morphylix.android.binlist.presentation.Application
import com.morphylix.android.binlist.presentation.bin_details.LoadingState
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "BinInputFragment"

class BinInputFragment : Fragment() {
    private lateinit var binding: FragmentBinInputBinding

    @Inject
    lateinit var binInputViewModel: BinInputViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as Application).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            binHistoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binInputViewModel.getCachedCardList()
            lifecycleScope.launch {
                binInputViewModel.loadingState.collect { state ->
                    when(state) {
                        is LoadingState.Sleeping -> {

                        }
                        is LoadingState.Loading -> {

                        }
                        is LoadingState.Success<*> -> {
                            binHistoryRecyclerView.adapter = BinInputAdapter((state.data as List<CardBinData>).reversed())
                            if (state.data.isEmpty()) {
                                historyIsEmptyTextView.visibility = View.VISIBLE
                            } else {
                                historyIsEmptyTextView.visibility = View.GONE
                            }
                        }
                        is LoadingState.Error -> {

                        }
                    }
                }
            }

            cardBinEditText.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence, start: Int, before: Int, count: Int) {
                    if (p0.isNotEmpty()) cardBinInputLayout.error = null
                    Log.i(TAG, "$p0 text, start $start, before $before, count $count")
                }

                override fun afterTextChanged(p0: Editable) {

                }
            })

            submitBinButton.setOnClickListener {
                if (cardBinEditText.text == null || cardBinEditText.text!!.isEmpty()) {
                    cardBinInputLayout.error = "The field can't be empty"
                } else {
                    val bin = cardBinEditText.text!!.filterNot { it.isWhitespace() }.toString()
                    val action =
                        BinInputFragmentDirections.actionBinInputFragmentToBinDetailsFragment(bin)
                    findNavController().navigate(action)
                }
            }

            clearHistoryTextView.setOnClickListener {
                lifecycleScope.launch {
                    binInputViewModel.clearAllCachedData()
                }
            }
        }
    }
}