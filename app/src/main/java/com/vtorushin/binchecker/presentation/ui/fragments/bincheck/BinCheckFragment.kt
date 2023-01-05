package com.vtorushin.binchecker.presentation.ui.fragments.bincheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vtorushin.binchecker.databinding.FragmentBinCheckBinding
import com.vtorushin.binchecker.di.bincheck.binCheckComponent
import com.vtorushin.binchecker.presentation.ui.adapters.BinLookupAdapter
import com.vtorushin.domain.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BinCheckFragment : Fragment() {
    private val args: BinCheckFragmentArgs by navArgs()
    private lateinit var viewModel: BinCheckViewModel
    private val pagingDataAdapter by lazy {
        BinLookupAdapter { intent ->
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Snackbar.make(requireContext(), binding.root, "Error", Snackbar.LENGTH_SHORT)
            }
        }
    }
    private lateinit var binding: FragmentBinCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = binCheckComponent().viewModel()
        if (args.bin != null) viewModel.checkBin(args.bin!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinCheckBinding.inflate(inflater, container, false)
        binding.binCheckRecyclerView.adapter = pagingDataAdapter
        lifecycleScope.launch {
            viewModel.binLookup.collect {
                binding.binCheckProgressBar.isVisible = it.status == Status.LOADING
                if (it.status == Status.SUCCESS) {
                    pagingDataAdapter.refresh()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.pager.collectLatest {
                pagingDataAdapter.submitData(it)
            }
        }
        return binding.root
    }
}