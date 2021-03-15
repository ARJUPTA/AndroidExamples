package com.arjupta.daggerdemo.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arjupta.daggerdemo.R;
import com.arjupta.daggerdemo.model.Post;
import com.arjupta.daggerdemo.ui.main.Resource;
import com.arjupta.daggerdemo.util.VerticalSpacingItemDecoration;
import com.arjupta.daggerdemo.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.arjupta.daggerdemo.BaseApplication.TAG;

public class PostsFragment extends DaggerFragment {

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    PostsRecyclerAdapter recyclerAdapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: for Posts Fragment");
        viewModel = ViewModelProviders.of(this,providerFactory).get(PostsViewModel.class);
        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.fetchPostsByUser().removeObservers(getViewLifecycleOwner());
        viewModel.fetchPostsByUser().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource!=null)
                switch (listResource.status){
                    case LOADING:{
                        Log.d(TAG, "onChanged: loading");
                        break;
                    }
                    case SUCCESS:{
                        recyclerAdapter.setPosts(listResource.data);
                        Log.d(TAG, "onChanged: got data");
                        break;
                    }
                    case ERROR:{
                        Log.d(TAG, "onChanged: got error"+listResource.message);
                        break;
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
