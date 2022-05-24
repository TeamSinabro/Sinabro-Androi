package com.sinabro.presentation.ui.vocalearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinabro.databinding.ItemVocaLearningBinding
import com.sinabro.databinding.ItemVocaSearchBinding
import com.sinabro.domain.model.response.vocalearning.VocaGetLearningData

class VocaLearningAdapter : RecyclerView.Adapter<VocaLearningAdapter.VocaLearningViewHolder>() {

    var vocaLearningData : MutableList<String> = mutableListOf()
    var vocaLearningCheck : MutableList<Boolean> = mutableListOf(false, false, false, false)

    private var onItemClickListener : ((String) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VocaLearningViewHolder {
        val binding = ItemVocaLearningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VocaLearningViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: VocaLearningViewHolder,
        position: Int
    ) {
        holder.onBind(vocaLearningData[position], vocaLearningCheck[position])
        holder.binding.checkboxProblem.setOnClickListener{
            setVocaLearningCheck(position)
            onItemClickListener?.let{ click ->
                click(vocaLearningData[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return vocaLearningData.size
    }


    class VocaLearningViewHolder(
        val binding : ItemVocaLearningBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(vocaLearningData : String, vocaLearningCheck : Boolean){
            binding.apply {
                vocaLearning = vocaLearningData
                vocaLearningChecked = vocaLearningCheck
                executePendingBindings()
            }
        }
    }


    fun setVocaLearning(vocaLearningData: MutableList<String> ){
        this.vocaLearningData = vocaLearningData
        notifyDataSetChanged()
    }

    fun setVocaLearningCheck(position : Int){
        vocaLearningCheck[position] = !vocaLearningCheck[position]
        when(position){
            0 -> if(vocaLearningCheck[0]){
                vocaLearningCheck = mutableListOf(true,false,false,false)
            }
            1 -> if(vocaLearningCheck[1]){
                vocaLearningCheck = mutableListOf(false,true,false,false)
            }
            2 -> if(vocaLearningCheck[2]){
                vocaLearningCheck = mutableListOf(false,false,true,false)
            }
            3 -> if(vocaLearningCheck[3]){
                vocaLearningCheck = mutableListOf(false,false,false,true)
            }
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener : (String) -> Unit){
        onItemClickListener = listener
    }
}