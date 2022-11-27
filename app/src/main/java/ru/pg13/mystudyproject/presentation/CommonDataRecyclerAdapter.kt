package ru.pg13.mystudyproject.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.data.CommonDataModel

class CommonDataRecyclerAdapter() :
    RecyclerView.Adapter<CommonDataRecyclerAdapter.CommonDataViewHolder>() {

    private val list = ArrayList<CommonUiModel>()

    fun show(data:List<CommonUiModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_data_item, parent, false)
        return CommonDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommonDataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class CommonDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)
        fun bind(model: CommonUiModel) = model.show(textView)
    }
}