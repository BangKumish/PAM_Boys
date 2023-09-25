package com.omkumis.projectpam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omkumis.projectpam.databinding.SkillItemBinding

class Adaptor(val list:ArrayList<Info>):RecyclerView.Adapter<Adaptor.MyView>() {

    inner class MyView(val itemBinding: SkillItemBinding):RecyclerView.ViewHolder(itemBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        return MyView(SkillItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.itemBinding.imageView.setImageResource(list[position].image)
        holder.itemBinding.textView.text = list[position].name
    }
}