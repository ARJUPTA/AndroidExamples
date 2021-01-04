package com.arjupta.androidtrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.arjupta.androidtrivia.databinding.FragmentTitleBinding

class TileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentTitleBinding = DataBindingUtil.inflate(
                inflater,R.layout.fragment_title,container,false)
        binding.playButton.setOnClickListener{view->
//           method-1: Navigation.findNavController(it).navigate(R.id.action_tileFragment_to_gameFragment)
//           method-2: it.findNavController().navigate(R.id.action_tileFragment_to_gameFragment)
//           method-3: Navigation.createNavigateOnClickListener(R.id.action_tileFragment_to_gameFragment)
            view.findNavController().navigate(R.id.action_tileFragment_to_gameFragment)
        }
        return binding.root
    }
}