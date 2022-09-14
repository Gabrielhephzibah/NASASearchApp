package com.cherish.nasasearchapp.presentation.milkyway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.domain.model.MilkyWay
import com.cherish.nasasearchapp.common.extensions.collect
import com.cherish.nasasearchapp.common.extensions.executeWithAction
import com.cherish.nasasearchapp.databinding.FragmentMilkyWayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MilkyWayFragment : Fragment() {
    private lateinit var binding: FragmentMilkyWayBinding
    private var milkyWayAdapter: MilkyWayAdapter? = null
    private val milkyWayViewModel: MilkyWayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMilkyWayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        milkyWayAdapter = MilkyWayAdapter {
            findNavController().navigate(
                MilkyWayFragmentDirections.actionMilkyWayFragmentToMilkyWayDetailFragment(it)
            )
        }
        setupAdapter()
    }

    private fun setupAdapter() {
       viewLifecycleOwner.collect(
            flow = milkyWayViewModel.getMilkyWayImages(),
            action = ::setMilkyWayImagesState,
            display = ::getMilkWayImages
       )
        binding.recyclerView.adapter = milkyWayAdapter
    }

    private fun getMilkWayImages(response: Resource<MilkyWay?>) {
        val data = response.data?.collection?.items?.map { it ->
            MilkyWayItem(
                it.data?.map { it.nasaId }?.firstOrNull(),
                it.data?.map { it.title }?.firstOrNull(),
                it.data?.map { it.center }?.firstOrNull(),
                it.data?.map { it.dateCreated }?.firstOrNull(),
                it.links?.map { it.href }?.firstOrNull(),
                it.data?.map { it.description }?.firstOrNull()
            )
        }
        milkyWayAdapter?.submitList(data)
    }

    private fun setMilkyWayImagesState(viewState: Resource<MilkyWay?>) {
        binding.executeWithAction {
            milkyWayUI = MilkyWayViewState(viewState)
        }
    }

}