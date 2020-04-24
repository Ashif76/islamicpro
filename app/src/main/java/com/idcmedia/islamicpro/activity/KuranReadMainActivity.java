package com.idcmedia.islamicpro.activity;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.jazzyviewpager.JazzyViewPager;
import com.idcmedia.islamicpro.jazzyviewpager.OutlineContainer;
import com.idcmedia.islamicpro.model.KuranParahItem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class KuranReadMainActivity extends Activity {

	private JazzyViewPager mJazzy;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kuran_read_activity_main);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		String dirPath = Utils.getRootDirPath(this)+"/"+"img_kur9"+".jpg";
		 mp = MediaPlayer.create(KuranReadMainActivity.this, R.raw.page_flip_sound);

//		List<String> fileList =  new ArrayList<>();
//		for(int i=0; i<=20; i++){
//			fileList.add(dirPath);
//		}
//		setupJazziness(JazzyViewPager.TransitionEffect.Tablet,fileList);
        setData();
	}

    private boolean setData() {
        String stringPath = getIntent().getStringExtra(Utils.FILE_PATH);
//        String dirPath = Utils.getRootDirPath(this)+"/unzip/"+mItem.getEnglish_name();
        File file = new File(stringPath);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            List<File> fileList =  new ArrayList<>();

            for(int i=files.length-1; i>=0; i--){
                fileList.add(files[i]);
            }

			Collections.sort(fileList, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					System.out.println("hii");
//					String name =  o1.getName();
//					System.out.println("myname "+o1.getName());
					String[] o1Split = o1.getName().split("\\.");
					String[] o2Split = o2.getName().split("\\.");
					Integer no1 = Integer.valueOf(o1Split[0]);
					Integer no2 = Integer.valueOf(o2Split[0]);
					return no2.compareTo(no1);
//					return  o1.getName().compareTo(o2.getName());
				}
			});
//            if(files.length == mItem.getFileCount()) {

                setupJazziness(JazzyViewPager.TransitionEffect.Tablet,fileList);
//                mItem.setLocalFilePath(dirPath);
//                return true;
//            }
        }
        return false;
    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Toggle Fade");
		String[] effects = this.getResources().getStringArray(R.array.jazzy_effects);
		for (String effect : effects)
			menu.add(effect);
		return true;
	}


	int currentPagePos=0;
	private void setupJazziness(JazzyViewPager.TransitionEffect effect, List<File> fileList) {
		mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		mJazzy.setTransitionEffect(effect);
		mJazzy.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				currentPagePos =position;
				int soundStatus = Utils.getIntSharedPref(KuranReadMainActivity.this, Utils.SOUND_STATUS);
				if(soundStatus ==0){
					generateSound();
				}

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		mJazzy.setAdapter(new MainAdapter(fileList, this));
		mJazzy.setPageMargin(30);
		String currentParahName = getIntent().getStringExtra(Utils.PARAH_NAME);
		String parahName =Utils.getStringValueSharedPrefCommon(KuranReadMainActivity.this,Utils.RESUME_READ_PARAH);
		if(parahName!=null){

				String[] nameData = parahName.split("_");
				String name = nameData[0];
			if(currentParahName.equalsIgnoreCase(name)){
				int pos = Integer.parseInt(nameData[1]);
				mJazzy.setCurrentItem(pos);
				return;
			}

		}

			mJazzy.setCurrentItem(fileList.size() - 1);
	}

	private void generateSound() {
		mp.start();
	}

	private class MainAdapter extends PagerAdapter {


		private final List<File> fileList;
		private Context context;


		public MainAdapter(List<File> fileList, Context context) {
			this.fileList = fileList;
			this.context = context;
		}




		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

			View view = inflater.inflate(R.layout.latest_pdf_activity, null);
			ImageView imageView  =view.findViewById(R.id.img_page);
			RelativeLayout imgSound = view.findViewById(R.id.rl_sound);
			RelativeLayout imgUnSound = view.findViewById(R.id.rl_unsound);
			ImageView imgOverlay = view.findViewById(R.id.img_spund);
			ImageView imgMark = view.findViewById(R.id.img_mark);
			int soundStatus = Utils.getIntSharedPref(KuranReadMainActivity.this, Utils.SOUND_STATUS);
			if(soundStatus==0){
				imgSound.setVisibility(View.VISIBLE);
				imgUnSound.setVisibility(View.GONE);
			}else{
				imgUnSound.setVisibility(View.VISIBLE);
				imgSound.setVisibility(View.GONE);
			}
			imgMark.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String parahName = getIntent().getStringExtra(Utils.PARAH_NAME);
					Utils.setStringSharedPrefCommon(KuranReadMainActivity.this,Utils.RESUME_READ_PARAH,parahName+"_"+currentPagePos);
					Snackbar snackbar = Snackbar
							.make(view, "Page has been saved. You can resume your reading from here later.", Snackbar.LENGTH_SHORT);
//					snackbar = Snackbar.make(view, "Page has been marked you can resume latter...", Snackbar.LENGTH_SHORT);
//					View snackBarView = snackbar.getView();
//					snackBarView.setBackgroundColor(getResources().getColor(R.color.holo_blue));
//
					snackbar.show();
//					Toast.makeText(KuranReadMainActivity.this, "Page has been marked you can resume latter...", Toast.LENGTH_SHORT).show();
				}
			});

			imgSound.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Utils.setIntSharedPref(KuranReadMainActivity.this,Utils.SOUND_STATUS,1);
					imgSound.setVisibility(View.GONE);
					imgUnSound.setVisibility(View.VISIBLE);
					notifyDataSetChanged();
				}
			});

			imgUnSound.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					imgUnSound.setVisibility(View.GONE);
					imgSound.setVisibility(View.VISIBLE);
					Utils.setIntSharedPref(KuranReadMainActivity.this,Utils.SOUND_STATUS,0);
					notifyDataSetChanged();
				}
			});
//			ImageView imageView =  new ImageView(KuranReadMainActivity.this);
			File f = fileList.get(position);
			if(f.exists()) {
				Glide.with(KuranReadMainActivity.this).load(f)
						.thumbnail(0.5f)
						.crossFade()
						.diskCacheStrategy(DiskCacheStrategy.ALL)
						.into(imageView);
			}


			container.addView(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			mJazzy.setObjectForPosition(view, position);
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object obj) {
			container.removeView(mJazzy.findViewFromObject(position));
		}
		@Override
		public int getCount() {
			return fileList.size();
		}
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			if (view instanceof OutlineContainer) {
				return ((OutlineContainer) view).getChildAt(0) == obj;
			} else {
				return view == obj;
			}
		}		
	}
}
