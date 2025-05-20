package com.shu.searchwork.ui.home

import android.os.Bundle
import android.util.Log
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
import com.shu.entity.models.Vacancy
import com.shu.searchwork.databinding.FragmentHomeBinding
import com.shu.searchwork.databinding.HeaderTwoItemBinding
import com.shu.searchwork.ui.holders.AdapterClickListenerById
import com.shu.searchwork.ui.holders.ItemTypes
import com.shu.searchwork.ui.holders.ViewHoldersManager
import com.shu.searchwork.ui.holders.ViewHoldersManagerImpl
import com.shu.searchwork.ui.holders.viewHolders.ButtonViewHolder
import com.shu.searchwork.ui.holders.viewHolders.HeaderViewHolder
import com.shu.searchwork.ui.holders.viewHolders.OffersCardViewHolder
import com.shu.searchwork.ui.holders.viewHolders.TwoHeaderViewHolder
import com.shu.searchwork.ui.holders.viewHolders.VacanciesCardViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var viewHoldersManager: ViewHoldersManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewHoldersManager = ViewHoldersManagerImpl().apply {
            registerViewHolder(ItemTypes.VACANCY, VacanciesCardViewHolder())
            registerViewHolder(ItemTypes.CARD, OffersCardViewHolder())
            registerViewHolder(ItemTypes.HEADER, HeaderViewHolder())
            registerViewHolder(ItemTypes.BUTTON, ButtonViewHolder())
            registerViewHolder(ItemTypes.HEADER_TWO, TwoHeaderViewHolder())
        }

        /* val offerAdapter =
             OfferAdapter { offer ->
                 onClick(offer)
             }

         binding.recycler.also { view ->
             view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
             view.adapter = offerAdapter
         }*/

        val galleryAdapter =
            GalleryAdapter(viewHoldersManager, AdapterClickListenerById { clickState ->

                if (clickState.itemTypes == ItemTypes.BUTTON) {
                    binding.arrow.visibility = View.VISIBLE
                    viewModel.clickButton()
                    binding.recycler.post { // Мгновенная прокрутка к первому элементу
                        binding.recycler.scrollToPosition(0)
                    }
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

      //  val layoutManager = binding.recycler.layoutManager
        binding.arrow.setOnClickListener {
            binding.arrow.visibility = View.GONE
            viewModel.clickButton()
           // layoutManager?.scrollToPosition(0)
            binding.recycler.post { // Мгновенная прокрутка к первому элементу
                binding.recycler.scrollToPosition(0)
            }
        }

        /*val vacancyAdapter =
            VacancyAdapter { vacancy ->
                onClickVacancy(vacancy)
            }

        binding.recyclerVacancy.also { view ->
            view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            view.adapter = vacancyAdapter
        }*/

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateUi
                    .collect { state ->
                        galleryAdapter.submitList(state)
                    }
            }
        }

        /*  viewLifecycleOwner.lifecycleScope.launch {
              repeatOnLifecycle(Lifecycle.State.STARTED) {
                  viewModel.vacancies
                      .collect { vacancies ->
                          vacancyAdapter.submitList(vacancies)
                      }
              }
          }*/

        return root
    }

    private fun onClick(offer: Offer) {
        /*val bundle = bundleOf("photochka" to photo)
        findNavController().navigate(
            R.id.action_FirstFragment_to_SecondFragment,
            bundle
        )*/
    }

    private fun onClickVacancy(vacancy: Vacancy) {
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