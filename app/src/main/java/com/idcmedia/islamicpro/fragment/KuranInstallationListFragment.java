package com.idcmedia.islamicpro.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.activity.KuranReadMainActivity;
import com.idcmedia.islamicpro.adapter.KuranInstallationListFragmentAdapter;
import com.idcmedia.islamicpro.adapter.KuranListFragmentAdapter;
import com.idcmedia.islamicpro.jazzyviewpager.Util;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.model.KuranParahItem;
import com.idcmedia.islamicpro.model.KuranSurahData;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;
import com.idcmedia.islamicpro.utils.JsonConvertUtil;

import java.io.File;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class KuranInstallationListFragment extends Fragment implements ItemClickListener<KuranParahItem> {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KuranInstallationListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static KuranInstallationListFragment newInstance(int columnCount) {
        KuranInstallationListFragment fragment = new KuranInstallationListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    String parahNameResume;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kuran_list_fragment, container, false);
        progressDialog =  new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        String prahResume =Utils.getStringValueSharedPrefCommon(getContext(),Utils.RESUME_READ_PARAH);

        if(prahResume!=null && !prahResume.isEmpty()) {
            String[] nameData = prahResume.split("_");
            String name = nameData[0];
            parahNameResume = name;
        }

        // Set the adapter
       intializeDownloader();
            Context context = view.getContext();
            recyclerView = view.findViewById(R.id.rv_dua_details);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        prepareData();
//        int surahPosition = Utils.getIntSharedPref(context, Utils.SURAH_POSITION_KEY);
//        if (surahPosition!=0){
//            recyclerView.scrollToPosition(surahPosition);
//        }
        return view;
    }
    private boolean isTempFileExist(KuranParahItem mItem) {
        String dirPath = Utils.getRootDirPath(getContext())+"/"+mItem.getEnglish_name()+".zip.temp";
        File file = new File(dirPath);
        return file.exists();
    }



    private boolean isFileExist(KuranParahItem mItem) {
        String dirPath = Utils.getRootDirPath(getContext())+"/.unzip/"+mItem.getEnglish_name();
        File file = new File(dirPath);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files.length == mItem.getFileCount()) {
                mItem.setLocalFilePath(dirPath);
                return true;
            }
        }
        return false;
    }
    int finalPos = -1;
    List<KuranParahItem> kuranParahList;
    KuranParahItem oldItem;
    KuranInstallationListFragmentAdapter adapter;
    public void prepareData(){
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i =-1;

                kuranParahList   = JsonConvertUtil.getKuranParahList(getContext());
                for(KuranParahItem item:kuranParahList){
                    i++;
                    if(isFileExist(item)){
                        if(parahNameResume!=null && !parahNameResume.isEmpty() && parahNameResume.equalsIgnoreCase(item.getEnglish_name()) ){
                            finalPos = i;
                            oldItem = item;
                            item.setReading(true);
                        }
                        item.setCurrentFileStatus(Utils.DOWNLOADED);
                    }else  if(isTempFileExist(item)){
                        item.setCurrentFileStatus(Utils.RESUME_DOWNLOADING);
                    }else{
                        item.setCurrentFileStatus(Utils.START_DOWNLOADING);
                    }
                }


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new KuranInstallationListFragmentAdapter(getContext(),kuranParahList, KuranInstallationListFragment.this);
                            recyclerView.setAdapter(adapter);
                            if(finalPos!=-1){
                                recyclerView.scrollToPosition(finalPos);
                            }
                           progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    private void intializeDownloader() {
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getActivity().getApplicationContext(), config);

// Setting timeout globally for the download network requests:
//        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
//                .setReadTimeout(30_000)
//                .setConnectTimeout(30_000)
//                .build();
//        PRDownloader.initialize(getApplicationContext(), config);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }





    private void startDownload(KuranSurahData kuranSurahData) {

    }


    @Override
    public void onItemClick(KuranParahItem kuranParahItem) {
        int position = Utils.getIntSharedPref(getContext(), Utils.CURENT_CLICK_PARAH_POS);
        KuranParahItem kuranParahItem1 = kuranParahList.get(position);
        String clickedName = kuranParahItem1.getEnglish_name();
        String prahResume =Utils.getStringValueSharedPrefCommon(getContext(),Utils.RESUME_READ_PARAH);
        if(prahResume!=null && !prahResume.isEmpty() ) {

            String[] nameData = prahResume.split("_");
            String name = nameData[0];
            if(clickedName.equalsIgnoreCase(name)){
                kuranParahItem1.setReading(true);
                if(oldItem!=null && !oldItem.getEnglish_name().equalsIgnoreCase(kuranParahItem1.getEnglish_name())){
                    oldItem.setReading(false);
                }
                parahNameResume = name;
                adapter.notifyDataSetChanged();
                oldItem = kuranParahItem1;
            }

        }


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
