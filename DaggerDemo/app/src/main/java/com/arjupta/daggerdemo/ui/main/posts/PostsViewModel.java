package com.arjupta.daggerdemo.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.arjupta.daggerdemo.SessionManager;
import com.arjupta.daggerdemo.model.Post;
import com.arjupta.daggerdemo.network.main.MainApi;
import com.arjupta.daggerdemo.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.arjupta.daggerdemo.BaseApplication.TAG;

public class PostsViewModel extends ViewModel {

    private final SessionManager sessionManager;
    private final MainApi mainApi;
    private MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        Log.d(TAG, "PostsViewModel: was created");
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
    }

    public LiveData<Resource<List<Post>>> fetchPostsByUser(){
        if(posts == null) {
            posts = new MediatorLiveData<>();
            LiveData<Resource<List<Post>>> liveData = LiveDataReactiveStreams
                    .fromPublisher(
                            mainApi.getPostFromUsers(sessionManager.getUser().getValue().data.getId())
                            .onErrorReturn(new Function<Throwable, List<Post>>() {
                                @NonNull
                                @Override
                                public List<Post> apply(@NonNull Throwable throwable) throws Exception {
                                    Log.d(TAG, "apply: "+throwable.toString());
                                    Post post = new Post();
                                    post.setId(-1);
                                    ArrayList<Post> arrayList = new ArrayList<>();
                                    arrayList.add(post);
                                    return arrayList;
                                }
                            }).map(new Function<List<Post>, Resource<List<Post>>>() {
                                @NonNull
                                @Override
                                public Resource<List<Post>> apply(@NonNull List<Post> posts) throws Exception {
                                    if(posts.size()>0){
                                        if(posts.get(0).getId()==-1)
                                            return Resource.error("Some Error Happened",null);
                                    }
                                    return Resource.success(posts);
                                }
                            }).subscribeOn(Schedulers.io())
                    );
            posts.addSource(liveData, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(liveData);
                }
            });
        }
        return posts;
    }
}
