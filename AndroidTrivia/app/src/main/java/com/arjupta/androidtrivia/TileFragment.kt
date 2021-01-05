package com.arjupta.androidtrivia

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.arjupta.androidtrivia.databinding.FragmentTitleBinding

class TileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentTitleBinding = DataBindingUtil.inflate(
                inflater,R.layout.fragment_title,container,false)
        setHasOptionsMenu(true)
        binding.playButton.setOnClickListener{view->
//           method-1: Navigation.findNavController(it).navigate(R.id.action_tileFragment_to_gameFragment)
//           method-2: it.findNavController().navigate(R.id.action_tileFragment_to_gameFragment)
//           method-3: Navigation.createNavigateOnClickListener(R.id.action_tileFragment_to_gameFragment)
            view.findNavController().navigate(R.id.action_tileFragment_to_gameFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu,menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}