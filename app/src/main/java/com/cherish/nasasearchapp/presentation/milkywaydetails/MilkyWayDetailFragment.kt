package com.cherish.nasasearchapp.presentation.milkywaydetails

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.cherish.nasasearchapp.databinding.FragmentMilkyWayDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MilkyWayDetailFragment : Fragment() {
    private lateinit var binding: FragmentMilkyWayDetailBinding
    private val milkyWayDetailViewModel: MilkyWayDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMilkyWayDetailBinding.inflate(inflater, container, false)
        binding.viewModel = milkyWayDetailViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.description.movementMethod = ScrollingMovementMethod()
        onBackClick()
    }

    private fun onBackClick() {
        binding.onBackClick.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }
}