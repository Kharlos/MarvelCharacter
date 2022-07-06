package com.cblanco.marvel.ui.home.charater.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cblanco.domain.Character
import com.cblanco.marvel.R
import com.cblanco.marvel.databinding.ItemCharacterBinding
import com.cblanco.marvel.ui.home.charater.common.basicDiffUtil
import com.cblanco.marvel.ui.home.charater.common.inflate
import com.cblanco.marvel.ui.home.charater.common.loadUrl

class CharacterAdapter(private val listener: (Character) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    var characters: List<Character> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_character, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
        holder.itemView.setOnClickListener { listener(character) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCharacterBinding.bind(view)
        fun bind(character: Character) = with(binding) {
            tvCharacterName.text = character.characterName
            tvDescription.text = character.description
            ivCharacterImage.loadUrl(character.characterImageUrl)
        }
    }
}