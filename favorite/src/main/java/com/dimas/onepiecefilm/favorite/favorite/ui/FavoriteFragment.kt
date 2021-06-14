package com.dimas.onepiecefilm.favorite.favorite.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimas.onepiecefilm.core.domain.model.OnePiece
import com.dimas.onepiecefilm.core.ui.ListItemAdapterss
import com.dimas.onepiecefilm.detail.DetailActivity
import com.dimas.onepiecefilm.favorite.databinding.FragmentFavoriteBinding
import com.dimas.onepiecefilm.favorite.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: ListItemAdapterss
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(favoriteModule)
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.favoriteItems.observe(viewLifecycleOwner, ::setView)
    }

    private fun setView(item: List<OnePiece>) {
        if (item.isEmpty()) {
            binding.movieNotfound.visibility = View.VISIBLE
            adapter = ListItemAdapterss(item)
        } else {
            binding.movieNotfound.visibility = View.GONE
            adapter = ListItemAdapterss(item)
            adapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.ID, selectedData)
                startActivity(intent)
            }
        }
        binding.rvFavItem.adapter = adapter
        adapter.notifyDataSetChanged()
        binding.rvFavItem.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}