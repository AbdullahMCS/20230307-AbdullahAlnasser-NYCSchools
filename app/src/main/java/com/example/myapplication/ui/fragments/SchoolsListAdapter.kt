package com.example.myapplication.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.HighSchoolResponse

class SchoolsListAdapter(private  val dataset: List<HighSchoolResponse>, val itemClicked: (HighSchoolResponse) -> Unit):
RecyclerView.Adapter<SchoolsListAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvSchoolsName: TextView
        val tvSchoolsInfo: TextView
        val btnShowDetails: Button

        init {
            tvSchoolsName = view.findViewById(R.id.tv_schools_name)
            tvSchoolsInfo = view.findViewById(R.id.tv_schools_info)
            btnShowDetails = view.findViewById(R.id.btn_show_details)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schools_list, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvSchoolsName.text = dataset[position].schoolName
        holder.tvSchoolsInfo.text = dataset[position].overviewParagraph

        holder.btnShowDetails.setOnClickListener {
            itemClicked.invoke(dataset[position])
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}