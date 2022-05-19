package com.sinabro.presentation.ui.vocasearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.sinabro.databinding.ItemVocaSearchBinding
import com.sinabro.domain.model.response.vocasearch.VocaSearchData

class VocaSearchAdapter : RecyclerView.Adapter<VocaSearchAdapter.VocaSearchViewHolder>() {
    var vocaSentenceData : MutableList<String> = mutableListOf()
    var vocaKeywordSourceData : MutableList<String> = mutableListOf()
    var vocaTitle : MutableList<String> = mutableListOf()
    var view : Boolean = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VocaSearchAdapter.VocaSearchViewHolder {
        val binding = ItemVocaSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VocaSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VocaSearchViewHolder, position: Int) {
        holder.onBind(vocaSentenceData[position], vocaKeywordSourceData[position], vocaTitle[position])
        holder.binding.clVocaSearchSentenceTop.setOnClickListener{
            if(view){
                holder.binding.clVocaSearchSentenceContent.visibility = View.VISIBLE
            }else{
                holder.binding.clVocaSearchSentenceContent.visibility = View.GONE
            }
            view = !view
        }
    }

    override fun getItemCount(): Int {
        return vocaSentenceData.size
    }

    class VocaSearchViewHolder(
        val binding : ItemVocaSearchBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(vocaSentenceData : String, vocaKeywordSourceData : String, vocaTitle : String) {
            binding.apply {
                voca = vocaTitle
                vocaSentence = vocaSentenceData
                vocaKeywordSource = vocaKeywordSourceData
                executePendingBindings()
            }
        }
    }

    fun setVocaSearchData(vocaSentenceData : MutableList<String>, vocaKeywordSourceData : MutableList<String>){
        for(i in 1..vocaSentenceData.size){
            vocaTitle.add("문장$i")
        }
        this.vocaSentenceData = vocaSentenceData
        this.vocaKeywordSourceData = vocaKeywordSourceData
        notifyDataSetChanged()
    }

}