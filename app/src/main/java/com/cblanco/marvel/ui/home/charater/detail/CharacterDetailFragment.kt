package com.cblanco.marvel.ui.home.charater.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.cblanco.marvel.R
import com.cblanco.marvel.databinding.FragmentCharacterDetailBinding
import com.cblanco.marvel.ui.home.charater.common.loadUrl
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class CharacterDetailFragment : Fragment(), AndroidScopeComponent {

    override val scope : Scope by fragmentScope()

    private var binding: FragmentCharacterDetailBinding? = null
    private val args:CharacterDetailFragmentArgs by navArgs()

    private val viewModel: CharacterDetailViewModel by viewModel {
        parametersOf(args.characterId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

    }

    private fun updateUi(model: CharacterDetailViewModel.UiModel){
        binding?.let {
            val marvelCharacter = model.character
            it.ivCharacterImage.loadUrl(marvelCharacter.characterImageUrl)
            it.tvCharacterName.text = marvelCharacter.characterName
            it.tvDescription.text = marvelCharacter.description.ifEmpty { getString(R.string.no_description_avaliable) }
        }
    }

    override fun onStop() {
        super.onStop()
        binding = null
    }
}