package com.shu.searchwork.ui.favorite

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
import com.shu.searchwork.databinding.FragmentFavoriteBinding
import com.shu.searchwork.ui.holders.AdapterClickListenerById
import com.shu.searchwork.ui.holders.ItemTypes
import com.shu.searchwork.ui.holders.ViewHoldersManager
import com.shu.searchwork.ui.holders.ViewHoldersManagerImpl
import com.shu.searchwork.ui.holders.viewHolders.ButtonViewHolder
import com.shu.searchwork.ui.holders.viewHolders.HeaderViewHolder
import com.shu.searchwork.ui.holders.viewHolders.OffersCardViewHolder
import com.shu.searchwork.ui.holders.viewHolders.VacanciesCardViewHolder
import com.shu.searchwork.ui.home.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DashboardViewModel>()
    private lateinit var viewHoldersManager: ViewHoldersManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewHoldersManager = ViewHoldersManagerImpl().apply {
            registerViewHolder(ItemTypes.VACANCY, VacanciesCardViewHolder())
            registerViewHolder(ItemTypes.CARD, OffersCardViewHolder())
            registerViewHolder(ItemTypes.HEADER, HeaderViewHolder())
            registerViewHolder(ItemTypes.BUTTON, ButtonViewHolder())
        }

        val galleryAdapter =
            MainAdapter(viewHoldersManager, AdapterClickListenerById { clickState ->

                if (clickState.itemTypes == ItemTypes.BUTTON) {
                    viewModel.clickButton()
                }
                if (clickState.itemTypes == ItemTypes.VACANCY) {
                    if (clickState.isFavorite) {
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
                        binding.count.text = countToString(state.size)
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

    private fun countToString(count: Int): String {
        return when(count) {
            0 -> ""
            1,21,31,41,51 -> "$count вакансия"
            2,3,4,22,23,24,32,33,34,42,43,44,52,53,54 -> "$count вакансии"
            else -> "$count вакансий"
        }
    }

}