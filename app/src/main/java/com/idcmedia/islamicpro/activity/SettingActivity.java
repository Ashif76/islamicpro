package com.idcmedia.islamicpro.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private  SwitchCompat swKuran;
    private SwitchCompat swDua;
    public static final String KEY_SWITCH_STATE_KURAN = "kuran";
    public static final String KEY_SWITCH_STATE_DUA = "dua";
    private boolean isFirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        RelativeLayout rlKuranSettings  =findViewById(R.id.rl_kuran_translation_setting);
        RelativeLayout rlDuaSettings = findViewById(R.id.rl_dua_translation_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Settings");
        swKuran = findViewById(R.id.sw_kuran);
        swDua =findViewById(R.id.sw_dua);
//        rlDuaSettings.setOnClickListener(this);
//        rlKuranSettings.setOnClickListener(this);
        boolean isKuranSwitchEnabled = Utils.getBooleanSharedPref(this, KEY_SWITCH_STATE_KURAN);
        swKuran.setOnCheckedChangeListener(this);
        swDua.setOnCheckedChangeListener(this);
        if (isKuranSwitchEnabled){
            enableKuranSwitch();
        }else{
            disableKuranSwitch();
        }
        boolean isDuaSwitchEnabled = Utils.getBooleanSharedPref(this, KEY_SWITCH_STATE_DUA);
        if (isDuaSwitchEnabled){
            enableDuaSwitch();
        }else{
            disableDuaSwitch();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id){
            case R.id.sw_kuran :{
                if (!isChecked){
                    disableKuranSwitch();
                }else{
                    enableKuranSwitch();
                }

                break;
            } case R.id.sw_dua :{
                if (!isChecked){
                    disableDuaSwitch();
                }else{
                    enableDuaSwitch();
                }
                break;
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
        }
    }

    private void disableKuranSwitch(){
        swKuran.setChecked(false);
        swKuran.refreshDrawableState();
        swKuran.setText("Disabled:");
        Utils.setBooleanSharedPref(this,KEY_SWITCH_STATE_KURAN,false);
    }

    private void enableKuranSwitch(){
        swKuran.setChecked(true);
        swKuran.refreshDrawableState();
        swKuran.setText("Enabled:");
        Utils.setBooleanSharedPref(this,KEY_SWITCH_STATE_KURAN,true);
    }

     private void disableDuaSwitch(){
            swDua.setChecked(false);
         swDua.refreshDrawableState();
         swDua.setText("Disabled:");
         Utils.setBooleanSharedPref(this,KEY_SWITCH_STATE_DUA,false);
    }

    private void enableDuaSwitch(){
        swDua.setChecked(true);
        swDua.refreshDrawableState();
        swDua.setText("Enabled:");
        Utils.setBooleanSharedPref(this,KEY_SWITCH_STATE_DUA,true);
    }


}
