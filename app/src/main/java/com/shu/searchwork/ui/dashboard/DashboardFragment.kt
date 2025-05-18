package com.shu.searchwork.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shu.searchwork.databinding.FragmentDashboardBinding
import com.shu.searchwork.ui.holders.AdapterClickListenerById
import com.shu.searchwork.ui.holders.ItemTypes
import com.shu.searchwork.ui.holders.ViewHoldersManager
import com.shu.searchwork.ui.holders.ViewHoldersManagerImpl
import com.shu.searchwork.ui.holders.viewHolders.ButtonViewHolder
import com.shu.searchwork.ui.holders.viewHolders.HeaderViewHolder
import com.shu.searchwork.ui.holders.viewHolders.OffersCardViewHolder
import com.shu.searchwork.ui.holders.viewHolders.VacanciesCardViewHolder
import com.shu.searchwork.ui.home.GalleryAdapter
import com.shu.searchwork.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DashboardViewModel>()
    private lateinit var viewHoldersManager: ViewHoldersManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewHoldersManager = ViewHoldersManagerImpl().apply {
            registerViewHolder(ItemTypes.VACANCY, VacanciesCardViewHolder())
            registerViewHolder(ItemTypes.CARD, OffersCardViewHolder())
            registerViewHolder(ItemTypes.HEADER, HeaderViewHolder())
            registerViewHolder(ItemTypes.BUTTON, ButtonViewHolder())
        }

        val galleryAdapter =
            GalleryAdapter(viewHoldersManager, AdapterClickListenerById { clickState ->

                if (clickState.itemTypes == ItemTypes.BUTTON) {
                    viewModel.clickButton()
                }
                if (clickState.itemTypes == ItemTypes.VACANCY) {
                    if(clickState.isFavorite) {
                        viewModel.updateFavorite(clickState.itemId, clickState.favorite)
                    }

                    //TODO click on favorite
                }
            })

        binding.recycler.also { view ->

            view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            view.adapter = galleryAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateUi
                    .collect { state ->
                        galleryAdapter.submitList(state)
                    }
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}