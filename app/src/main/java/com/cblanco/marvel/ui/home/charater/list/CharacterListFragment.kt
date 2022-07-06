package com.cblanco.marvel.ui.home.charater.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cblanco.marvel.R
import com.cblanco.marvel.databinding.FragmentCharacterListBinding
import com.cblanco.marvel.ui.home.charater.common.gone
import com.cblanco.marvel.ui.home.charater.common.visible
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class CharacterListFragment : Fragment(), AndroidScopeComponent {

    override val scope : Scope by fragmentScope()

    private val viewModel: CharacterListViewModel by viewModel()
    private var binding: FragmentCharacterListBinding? = null
    private lateinit var characterAdapter: CharacterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            it.swiperRefresh.setOnRefreshListener {
                viewModel.getPublicCharacters()
            }

            characterAdapter = CharacterAdapter(viewModel::onCharacterClick)
            it.rvCharacter.adapter = characterAdapter
            viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
            it.rvCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        //TODO add pagination feature
                    }
                }
            })
        }

    }

    override fun onStop() {
        super.onDestroy()
        binding = null
    }
    private fun updateUi(model: CharacterListViewModel.UiModel) {
        binding?.let {
            when (model) {
                is CharacterListViewModel.UiModel.Loading-> {
                    it.laLoader.visible()
                    it.swiperRefresh.isRefreshing = false
                }
                is CharacterListViewModel.UiModel.Content -> {
                    it.laLoader.gone()
                    it.tvError.gone()
                    it.rvCharacter?.visible()
                    characterAdapter.characters = model.character
                }
                is CharacterListViewModel.UiModel.Navigation -> {
                    findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(model.character.id))
                }
                is CharacterListViewModel.UiModel.Error -> handlerError()
                else -> {}
            }
        }
    }

    private fun handlerError() {
        binding?.let {
            it.rvCharacter?.gone()
            it.tvError?.visible()
            it.tvError.text = getString(R.string.generic_error)
        }
    }

}