package com.sinabro.presentation.ui.vocalearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinabro.databinding.ItemVocaLearningBinding
import com.sinabro.databinding.ItemVocaSearchBinding
import com.sinabro.domain.model.response.vocalearning.VocaGetLearningData

class VocaLearningAdapter : RecyclerView.Adapter<VocaLearningAdapter.VocaLearningViewHolder>() {

    var vocaLearningData : MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VocaLearningAdapter.VocaLearningViewHolder {
        val binding = ItemVocaLearningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VocaLearningViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: VocaLearningAdapter.VocaLearningViewHolder,
        position: Int
    ) {
        holder.onBind(vocaLearningData[position])
    }

    override fun getItemCount(): Int {
        return vocaLearningData.size
    }


    class VocaLearningViewHolder(
        val binding : ItemVocaLearningBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(vocaLearningData : String){
            binding.apply {
                vocaLearning = vocaLearningData
                executePendingBindings()
            }
        }
    }


    fun setVocaLearning(vocaLearningData: MutableList<String>){
        this.vocaLearningData = vocaLearningData
        notifyDataSetChanged()
    }
}