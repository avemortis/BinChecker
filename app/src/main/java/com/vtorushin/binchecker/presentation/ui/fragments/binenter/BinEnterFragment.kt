package com.vtorushin.binchecker.presentation.ui.fragments.binenter

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vtorushin.binchecker.databinding.FragmentBinEnterBinding

class BinEnterFragment : Fragment() {
    private lateinit var viewModel: BinEnterViewModel
    private lateinit var binding: FragmentBinEnterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinEnterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[BinEnterViewModel::class.java]

        binding.binEnterButton.setOnClickListener {
            val action = BinEnterFragmentDirections.actionBinEnterFragmentToBinCheckFragment(
                binding.binEnterTextInput.text.toString()
            )
            findNavController().navigate(action)
        }

        binding.historyWatch.setOnClickListener {
            val action = BinEnterFragmentDirections.actionBinEnterFragmentToBinCheckFragment(null)
            findNavController().navigate(action)
        }

        return binding.root
    }
}