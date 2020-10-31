package com.example.simple_instagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simple_instagram.Post;
import com.example.simple_instagram.PostsAdapter;
import com.example.simple_instagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    public static final String TAG = "PostsFragment";
    private RecyclerView rvPosts;
    private PostsAdapter adapter;
    private List<Post> allPosts;

    public PostsFragment() {
        // Required empty public constructor
    }


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);

        // Steps to use Recycler View:
        // 0. create layout for one row in the list
        // 1. create the adapter
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);
        // 2. create the data source
        // 3. set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        // 4. set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        // get user's posts
        queryPosts();
    }

    /**
     * Get all posts from query
     */
    private void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        query.include(Post.KEY_USER);

        // Specify the object id
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    // query posts failed
                    Log.e(TAG, "Issue with getting posts!", e);
                    return;
                }
                // query posts successfully
                Log.i(TAG, "Get posts successfully!");
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription()
                            + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}