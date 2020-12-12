package com.example.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment

class RecipeListFragment : Fragment() {
    fun RecipeListFragment(){

    }

    override fun <I : Any?, O : Any?> prepareCall(contract: ActivityResultContract<I, O>, callback: ActivityResultCallback<O>): ActivityResultLauncher<I> {
        TODO("Not yet implemented")
    }

    override fun <I : Any?, O : Any?> prepareCall(contract: ActivityResultContract<I, O>, registry: ActivityResultRegistry, callback: ActivityResultCallback<O>): ActivityResultLauncher<I> {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipe_list,container)
        return view
    }
}