package com.idcmedia.islamicpro.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.activity.KuranReadMainActivity;
import com.idcmedia.islamicpro.model.DuaStubs;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.model.KuranParahItem;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;
import com.idcmedia.islamicpro.utils.UnzipUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DuaStubs} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class KuranInstallationListFragmentAdapter extends RecyclerView.Adapter<KuranInstallationListFragmentAdapter.ViewHolder> {

    private Context context;
    private final List<KuranParahItem> mValues;
    private final ItemClickListener mListener;

    public KuranInstallationListFragmentAdapter(Context context, List<KuranParahItem> items, ItemClickListener listener) {
        this.context = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kuran_installation_list_fragment_adapter, parent, false);
        return new ViewHolder(view);
    }


    private void setDownloadingView(ViewHolder holder, int position) {
        holder.mTvTranslation.setText("Install Siparah "+(position+1));
        holder.mTvArabic.setText("Download");
//        holder.mTvExplanation.setText(mValues.get(position).getEnglishNameTranslation());
        holder.mTvExplanation.setText(mValues.get(position).getEnglish_name());
        holder.mTvSurahNo.setText((position+1)+".");
        holder.tvReadingStatus.setVisibility(View.GONE);

//        int surahPosition = Utils.getIntSharedPref(context, Utils.SURAH_POSITION_KEY);
//        if (surahPosition ==position ) {
//            holder.tvReadingStatus.setVisibility(View.VISIBLE);
//        }else{
//            holder.tvReadingStatus.setVisibility(View.GONE);
//        }
    }

    private void setResumeView(ViewHolder holder, int position) {
        holder.mTvTranslation.setText("Resume Siparah "+(position+1)+" Installation");
        holder.mTvArabic.setText("Resume");
//        holder.mTvExplanation.setText(mValues.get(position).getEnglishNameTranslation());
        holder.mTvExplanation.setText(mValues.get(position).getEnglish_name());
        holder.mTvSurahNo.setText((position+1)+".");
        holder.tvReadingStatus.setVisibility(View.GONE);
    }

    private void setReadingView(ViewHolder holder, int position) {
        holder.mTvTranslation.setText("Siparah"+((position+1)));
        holder.mTvArabic.setText(mValues.get(position).getArabic_name());
//        holder.mTvExplanation.setText(mValues.get(position).getEnglishNameTranslation());
        holder.mTvExplanation.setText(mValues.get(position).getEnglish_name());
        if(mValues.get(position).isReading()){
            holder.tvReadingStatus.setVisibility(View.VISIBLE);
        }else{
            holder.tvReadingStatus.setVisibility(View.GONE);
        }
        holder.mTvSurahNo.setText((position+1)+".");
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        if(holder.mItem.getCurrentFileStatus() ==Utils.DOWNLOADED) {
          setReadingView(holder,position);
        }else if(holder.mItem.getCurrentFileStatus() ==Utils.RESUME_DOWNLOADING){
            setResumeView(holder,position);
        }else{
            setDownloadingView(holder,position);

        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    if (holder.mItem.getCurrentFileStatus() ==Utils.DOWNLOADED) {
                        openImageReadingView(holder.mItem.getLocalFilePath(),position, holder.mItem.getEnglish_name());
//                        Toast.makeText(context,"already completed",Toast.LENGTH_SHORT).show();
                    }else {
                        startDownload(holder);

                    }
                }
            }
        });
    }

//    private void openPdfReadingView(String localFilePath) {
//        Intent intent =  new Intent(context, LatestPdfViewActivity.class);
//        intent.putExtra(Utils.FILE_PATH,localFilePath);
//        context.startActivity(intent);
//    }

    private void openImageReadingView(String localFilePath, int position, String english_name) {
        Intent intent =  new Intent(context, KuranReadMainActivity.class);
        intent.putExtra(Utils.FILE_PATH,localFilePath);
        intent.putExtra(Utils.PARAH_NAME,english_name);
        Utils.setIntSharedPref(context,Utils.CURENT_CLICK_PARAH_POS,position);
//        intent.putExtra(Utils.CURENT_CLICK_PARAH_POS,position);

        ((Activity)context).startActivityForResult(intent,200);
    }



    int downloadId;
    private void startDownload(ViewHolder holder) {
        String dirPath = Utils.getRootDirPath(context);
        KuranParahItem mItem = holder.mItem;
        ProgressDialog progressDialog =  createProgressDialog(mItem.getEnglish_name());
//        startUnZipping(dirPath,mItem, progressDialog);
         downloadId = PRDownloader.download(mItem.getUrl(), dirPath, mItem.getEnglish_name()+".zip")
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {

                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
                        progressDialog.show();
                        long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                        progressDialog.setProgress((int) progressPercent);

//                        holder.progressBar.setVisibility(View.VISIBLE);
//                        holder.progressBar.setProgress((int) progressPercent);
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        progressDialog.setMessage("Setting up display...");
                      startUnZipping(dirPath,mItem,progressDialog);
                    }

                    @Override
                    public void onError(Error error) {
                        notifyDataSetChanged();
                        Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
        Log.d("pr","");
    }

    private void startUnZipping(String dirPath, KuranParahItem mItem, ProgressDialog progressDialog) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String destPath = dirPath+"/.unzip/";
                String ziFilePath = dirPath+"/"+mItem.getEnglish_name()+".zip";
                File archive =  new File(ziFilePath);
                UnzipUtil unzipUtil =  new UnzipUtil(ziFilePath,destPath);
                unzipUtil.unzip();

//                try {
//
//                    ZipFile zipfile = new ZipFile(archive);
//                    for (Enumeration e = zipfile.entries(); e.hasMoreElements();)
//                    {
//                        ZipEntry entry = (ZipEntry) e.nextElement();
//                        unzipEntry(zipfile, entry, destPath);
//                    }
//
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String destLocal = destPath+"/"+mItem.getEnglish_name();
                        File file =  new File(destLocal);
                        if(file.isDirectory()){
                            File[] files = file.listFiles();
                            if(files.length ==mItem.getFileCount()){
                                archive.delete();
                                mItem.setCurrentFileStatus(Utils.DOWNLOADED);
                                mItem.setLocalFilePath(destLocal);
                                notifyDataSetChanged();
                                progressDialog.dismiss();
                                Toast.makeText(context,"completed",Toast.LENGTH_SHORT).show();
                            }else{
                                notifyDataSetChanged();
                                progressDialog.dismiss();
                                Toast.makeText(context,"Error while displaying",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            notifyDataSetChanged();
                            progressDialog.dismiss();
                            Toast.makeText(context,"Error while displaying",Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        }).start();

//                        mItem.setCurrentFileStatus(Utils.DOWNLOADED);
//
//                        mItem.setLocalFilePath(path);
//                        notifyDataSetChanged();
//        Toast.makeText(context,"completed",Toast.LENGTH_SHORT).show();
//        progressDialog.dismiss();

//                        Utils.setIntSharedPref(context,mItem.getEnglish_name(),downloadId);
    }

    private void unzipEntry(ZipFile zipfile, ZipEntry entry,String outputDir) throws IOException
    {

        if (entry.isDirectory())
        {
            createDir(new File(outputDir, entry.getName()));
            return;
        }

        File outputFile = new File(outputDir, entry.getName());
        if (!outputFile.getParentFile().exists())
        {
            createDir(outputFile.getParentFile());
        }

        // Log.v("", "Extracting: " + entry);
        BufferedInputStream inputStream = new BufferedInputStream(zipfile.getInputStream(entry));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

        try
        {

        }
        finally
        {
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }

    private void createDir(File dir)
    {
        if (dir.exists())
        {
            return;
        }
        if (!dir.mkdirs())
        {
            throw new RuntimeException("Can not create dir " + dir);
        }
    }

    public ProgressDialog createProgressDialog(String msg)
    {

        ProgressDialog    progressdialog = new ProgressDialog(context);

        progressdialog.setIndeterminate(false);

        progressdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressdialog.setCancelable(false);
        progressdialog.setMessage("Wait Installing Siprah "+msg+" .....");

        progressdialog.setMax(100);

        progressdialog.show();
        return progressdialog;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTvArabic;
        public final TextView mTvTranslation;
        public final TextView mTvExplanation;
        public final TextView mTvSurahNo;
        public KuranParahItem mItem;
        public TextView tvReadingStatus;
        public ProgressBar progressBar;
        public ImageView downloadIcon;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvArabic = (TextView) view.findViewById(R.id.tv_arabic);
            mTvTranslation = (TextView) view.findViewById(R.id.tv_translation);
            mTvExplanation = (TextView) view.findViewById(R.id.tv_exmplanation);
            mTvSurahNo = (TextView)view.findViewById(R.id.tv_surah_no);
            tvReadingStatus = view.findViewById(R.id.tv_reading_status);
            progressBar = view.findViewById(R.id.progressBar);
            downloadIcon = view.findViewById(R.id.src_download);
        }

    }
}
