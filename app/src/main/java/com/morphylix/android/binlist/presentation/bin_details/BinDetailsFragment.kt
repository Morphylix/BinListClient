package com.morphylix.android.binlist.presentation.bin_details

import android.app.Notification.Action
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.morphylix.android.binlist.R
import com.morphylix.android.binlist.databinding.FragmentBinDetailsBinding
import com.morphylix.android.binlist.domain.model.cache.CardBinDataCacheEntity
import com.morphylix.android.binlist.domain.model.domain.CardBinData
import com.morphylix.android.binlist.presentation.Application
import com.morphylix.android.binlist.util.handleException
import com.morphylix.android.binlist.util.splitEveryFourDigits
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "BinDetailsFragment"

class BinDetailsFragment : Fragment() {

    lateinit var binding: FragmentBinDetailsBinding

    @Inject
    lateinit var binDetailsViewModel: BinDetailsViewModel
    private val args: BinDetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        (requireActivity().application as Application).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bin = args.bin
        binDetailsViewModel.loadCardBinData(bin)


        lifecycleScope.launch {
            binDetailsViewModel.loadingState.collect { state ->
                when (state) {
                    is LoadingState.Sleeping -> {

                    }
                    is LoadingState.Loading -> {
                        changeViewVisibility(state)
                    }
                    is LoadingState.Success<*> -> {
                        changeViewVisibility(state)
                        displayLoadedData(state.data as CardBinData)
                        provideListeners(state.data)
                    }
                    is LoadingState.Error -> {
                        Snackbar.make(
                            view,
                            handleException(state.exception, requireContext()),
                            Snackbar.LENGTH_SHORT
                        ).show()
                        val action =
                            BinDetailsFragmentDirections.actionBinDetailsFragmentToBinInputFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    private fun changeViewVisibility(state: LoadingState) {
        with(binding) {
            if (state is LoadingState.Loading) {
                binDetailsProgressBar.visibility = View.VISIBLE
                dataGroup.visibility = View.INVISIBLE
            } else if (state is LoadingState.Success<*>) {
                binDetailsProgressBar.visibility = View.GONE
                dataGroup.visibility = View.VISIBLE
            }
        }
    }

    private fun displayLoadedData(cardBinData: CardBinData) {
        with(binding) {
            currentCardBinTextView.text = args.bin.splitEveryFourDigits()
            schemeNetworkDataTextView.text = cardBinData.scheme
            brandDataTextView.text = cardBinData.brand
            cardLengthDataTextView.text = cardBinData.length.toString()
            cardLuhnDataTextView.text = cardBinData.luhn.toString()
            cardTypeDataTextView.text = cardBinData.type
            prepaidDataTextView.text = cardBinData.isPrepaid
            countryEmojiTextView.text = cardBinData.countryEmoji
            countryDataTextView.text = cardBinData.country
            coordinatesTextView.text = getString(
                R.string.coordinates,
                cardBinData.countryLatitude,
                cardBinData.countryLongitude
            )
            bankNameAndCityDataTextView.text = getString(
                R.string.bank_and_city,
                cardBinData.bank, cardBinData.bankCity
            )
            bankUrlTextView.text = cardBinData.bankUrl
            bankPhoneTextView.text = cardBinData.bankPhone
        }
    }

    private fun provideListeners(cardBinData: CardBinData) {
        with(binding) {
            coordinatesTextView.setOnClickListener {
                Log.i(TAG, "Clicked on coordinates")
                if (cardBinData.countryLatitude != null && cardBinData.countryLongitude != null) {
                    startActivity(
                        startGeoIntent(
                            cardBinData.countryLatitude,
                            cardBinData.countryLongitude
                        )
                    )
                }
            }
            bankPhoneTextView.setOnClickListener {
                startActivity(cardBinData.bankPhone?.let { it1 -> startPhoneIntent(it1) })
            }
            bankUrlTextView.setOnClickListener {
                startActivity(cardBinData.bankUrl?.let { it1 -> startBrowserIntent(it1) })
            }
        }
    }

    private fun startPhoneIntent(phone: String): Intent {
        val phoneUri: Uri = Uri.parse("tel:$phone")
        return Intent(Intent.ACTION_DIAL, phoneUri)
    }

    private fun startBrowserIntent(url: String): Intent {
        val browserUri: Uri = Uri.parse("https://$url")
        return Intent(Intent.ACTION_VIEW, browserUri)
    }

    private fun startGeoIntent(latitude: Int, longitude: Int): Intent {
        val mapUri: Uri = Uri.parse("geo:$latitude, $longitude")
        return Intent(Intent.ACTION_VIEW, mapUri)
    }
}