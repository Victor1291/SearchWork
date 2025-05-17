package com.shu.searchwork.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shu.entity.models.Offer
import com.shu.searchwork.databinding.FragmentHomeBinding
import com.shu.searchwork.ui.adapter.OfferAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val offerAdapter =
            OfferAdapter { offer ->
                onClick(offer)
            }

        binding.recycler.also { view ->
            view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            view.adapter = offerAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.offers
                    .collect { photoList ->
                        offerAdapter.submitList(photoList)
                    }
            }
        }

        return root
    }

    private fun onClick(offer: Offer) {
        /*val bundle = bundleOf("photochka" to photo)
        findNavController().navigate(
            R.id.action_FirstFragment_to_SecondFragment,
            bundle
        )*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}