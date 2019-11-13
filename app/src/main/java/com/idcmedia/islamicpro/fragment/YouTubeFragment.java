package com.idcmedia.islamicpro.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.activity.YouVideoPlayActivity;
import com.idcmedia.islamicpro.adapter.YouTubeFragmentAdapter;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.model.YouTubeDataItems;
import com.idcmedia.islamicpro.model.YouTubeDataStubs;
import com.idcmedia.islamicpro.network.api.APIClient;
import com.idcmedia.islamicpro.network.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class YouTubeFragment extends Fragment implements ItemClickListener<YouTubeDataItems> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String CATEGORY = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final int WHATS_APP_SHARED = 101;

    private OnFragmentInteractionListener mListener;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private APIInterface apiInterface;
    private ProgressBar progressbar;
    private YouTubeFragmentAdapter recyclerViewAdapter;
    private int pageNo = 0;
    private boolean isLoading = false;
    private int pageSize = 10;
    private SwipeRefreshLayout swipeContainer;
    private boolean isLoadMoreRequired = true;

    public YouTubeFragment() {
    }


    public static YouTubeFragment newInstance(String param1, String param2) {
        YouTubeFragment fragment = new YouTubeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(CATEGORY, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(CATEGORY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_youtube, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        recyclerView.setHasFixedSize(true);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        progressbar = (ProgressBar) view.findViewById(R.id.progress_bar);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        progressbar.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");
        initAdapter();
        initScrollListener();
        initRefreshContainer();
        return view;
    }

    private void initRefreshContainer() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                isSwipingRefresh = true;
                pageNo = 0;
                hitAPi("", pageSize);

            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
    private boolean isSwipingRefresh = false;
    private void clearList(){
        itemList.clear();
    }

    List<YouTubeDataItems> itemList = new ArrayList<>();

    private void initAdapter() {

        itemList.add(null);
        recyclerViewAdapter = new YouTubeFragmentAdapter(itemList, getActivity(),this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == itemList.size() - 1) {
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });


    }

    private void loadMore() {
        if (itemList.size() != 1) {
            itemList.add(null);
            recyclerViewAdapter.notifyItemInserted(itemList.size() - 1);
        }
        if(isLoadMoreRequired)
        hitAPi(nextPageToken, pageSize);
    }

    private String nextPageToken = "";

    private void doLoading(YouTubeDataStubs youTubeDataStubs) {
        if (itemList.size() > 0) {
            itemList.remove(itemList.size() - 1);
        }
        int scrollPosition = itemList.size();
        recyclerViewAdapter.notifyItemRemoved(scrollPosition);
        if(itemList==null){
            itemList = new ArrayList<>();
        }
        itemList.addAll(youTubeDataStubs.getItems());
        recyclerViewAdapter.notifyDataSetChanged();
        if(isSwipingRefresh){
            swipeContainer.setRefreshing(false);
            isSwipingRefresh = false;
        }
        isLoading = false;
    }

    private void hitAPi(String pageToken, int pageSize) {
        Call<YouTubeDataStubs> call = apiInterface.getChannelData("snippet", pageToken, "UC-ibpI7MJyqVSj-1LEJI6-Q",
                pageSize, Utils.YOUTUBE_API_KEY);
        try {

            call.enqueue(new Callback<YouTubeDataStubs>() {
                @Override
                public void onResponse(Call<YouTubeDataStubs> call, Response<YouTubeDataStubs> response) {
                    progressbar.setVisibility(View.GONE);
                    YouTubeDataStubs news = response.body();
                    if (news == null) {
                        return;
                    }
                    if (isSwipingRefresh) {
                        clearList();
                    }
                    nextPageToken = news.getNextPageToken();
                    if(nextPageToken==null ||nextPageToken.isEmpty()){
                        isLoadMoreRequired = false;
                    }
                    doLoading(news);
                }

                @Override
                public void onFailure(Call<YouTubeDataStubs> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);
                    if (t != null)
                        Toast.makeText(YouTubeFragment.this.getContext(), "error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Override
//    public void onImageClick(YouTubeDataStubs news) {
//
////        Intent intent = new Intent(getActivity(),NewsDetailsActivity.class);
////        intent.putExtra(NewsDetailsActivity.NEWS_ITEM,news);
////        startActivity(intent);
//    }

//    @Override
//    public void onWhatsAppShare(News news) {
//        shareOnWhatsApp(news);
//    }

    private boolean isSharedOnWhatsApp = false;

//    @Override
//    public void onResume() {
//        super.onResume();
//        if(isSharedOnWhatsApp){
//
//            isSharedOnWhatsApp = false;
//        }
//    }

//    private void shareOnWhatsApp(News news) {
//        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
//        whatsappIntent.setType("text/plain");
//        whatsappIntent.setPackage("com.whatsapp");
//        String msg = news.getTitle()+"  "+news.getHtmlUrl()+" click " +" https://google.com "+" to  download the app for verity of news ";
//        whatsappIntent.putExtra(Intent.EXTRA_TEXT, msg);
//        try {
//
//            startActivityForResult(whatsappIntent, WHATS_APP_SHARED);
//            progressDialog.setMessage("Please wait while we are updating your credit rupees...");
//            progressDialog.show();
//            updateUserCredit();
//        } catch (android.content.ActivityNotFoundException ex) {
////            ToastHelper.MakeShortText("Whatsapp have not been installed.");
//        }
//    }

    private ProgressDialog progressDialog ;

    @Override
    public void onItemClick(YouTubeDataItems youTubeDataItem) {
        Intent intent=new Intent(view.getContext(), YouVideoPlayActivity.class);
        intent.putExtra("videoId",youTubeDataItem.getVideoIdInfo().getVideoId());
        view.getContext().startActivity(intent);
    }
//    private void updateUserCredit() {
//        Call<User> userCall = apiInterface.updateUserCredit(SharedPrefrenceStub.getUser(getContext()).getContent().getUserId());
//        userCall.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
//                User user = response.body();
//                if(user!=null) {
//                    SharedPrefrenceStub.updateUser(ViewPagerItemFragment.this.getContext(), user);
//
//                }
//                Toast.makeText(ViewPagerItemFragment.this.getContext(), "your credit updated", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                progressDialog.dismiss();
//                if(t!=null)
//                    Toast.makeText(ViewPagerItemFragment.this.getContext(), "error " + t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
