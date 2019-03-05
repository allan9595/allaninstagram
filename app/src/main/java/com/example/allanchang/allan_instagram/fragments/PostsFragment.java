package com.example.allanchang.allan_instagram.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.allanchang.allan_instagram.Post;
import com.example.allanchang.allan_instagram.PostsAdapter;
import com.example.allanchang.allan_instagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class PostsFragment extends Fragment {

    public static final String TAG = "the fragment posts";
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> mposts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvPosts = view.findViewById(R.id.rvPosts);

        //create the adapter
        mposts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), mposts);
        //create the data source
        //set the adapter on the recycle view

        rvPosts.setAdapter(adapter);
        //set the layout manager on the recycle view

        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

    }


    protected void queryPosts() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.setLimit(20);
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
                mposts.addAll(posts);
                adapter.notifyDataSetChanged();

                for(int i = 0; i< posts.size(); i++){
                    Log.d(TAG,"Post: " + posts.get(i).getDescription() + "username: " + posts.get(i).getUser().getUsername());
                }
            }
        });
    }
}
