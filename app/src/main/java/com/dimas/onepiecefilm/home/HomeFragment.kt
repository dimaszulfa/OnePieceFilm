package com.dimas.onepiecefilm.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimas.onepiecefilm.core.source.Resource
import com.dimas.onepiecefilm.core.ui.ListItemAdapterss
import com.dimas.onepiecefilm.databinding.FragmentHomeBinding
import com.dimas.onepiecefilm.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: ListItemAdapterss
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity.apply {
            viewModel.items.observe(viewLifecycleOwner) {
                when (it) {
                 is Resource.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        it.data?.let { item ->
                            adapter = ListItemAdapterss(item)
                            adapter.notifyDataSetChanged()
                            binding.rvItems.adapter = adapter
                            adapter.onItemClick = { selectedData ->
                                val intent = Intent(activity, DetailActivity::class.java)
                                intent.putExtra(DetailActivity.ID, selectedData)
                                startActivity(intent)
                            }
                        }

                    }
                    is Resource.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            requireContext(),
                            "Error when Load a Data",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            binding.rvItems.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL,false)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}