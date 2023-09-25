package com.omkumis.projectpam.ui.skill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omkumis.projectpam.R
import com.omkumis.projectpam.Adaptor
import com.omkumis.projectpam.Info
import com.omkumis.projectpam.databinding.ActivityMainBinding
import com.omkumis.projectpam.databinding.FragmentSkillBinding

class SkillFragment : Fragment() {

    private lateinit var adaptor: Adaptor
    private lateinit var list: ArrayList<Info>
    private lateinit var binding: FragmentSkillBinding

    private lateinit var recyclerView: RecyclerView
    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSkillBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_skill)
        recyclerView.setHasFixedSize(true)
        list = ArrayList()
        list.add(Info(R.drawable.cpp, "CPP"))
        list.add(Info(R.drawable.csharp, "C#"))
        list.add(Info(R.drawable.js, "JavaScript"))
        list.add(Info(R.drawable.kotlin, "Kotlin"))
        list.add(Info(R.drawable.python, "Python"))

        adaptor = Adaptor(list)
        recyclerView.adapter = adaptor
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())


    }

}