package com.example.tiagoc.festafimdeano.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.tiagoc.festafimdeano.R;
import com.example.tiagoc.festafimdeano.constant.FimdeAnoConstants;
import com.example.tiagoc.festafimdeano.util.SecurityPreferences;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkBoxParticipate = (CheckBox) findViewById(R.id.checkbox_participate);

        this.mViewHolder.checkBoxParticipate.setOnClickListener(this);

        this.loadDataFromActivity();

    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(FimdeAnoConstants.PRESENCE);
            if (presence.equals(FimdeAnoConstants.CONFIRM_WILL_GO)) {
                this.mViewHolder.checkBoxParticipate.setChecked(true);
            } else if (presence.equals(FimdeAnoConstants.CONFIRM_WONT_GO)) {
                this.mViewHolder.checkBoxParticipate.setChecked(false);
            }
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.checkbox_participate) {
            if (this.mViewHolder.checkBoxParticipate.isChecked()) {
                this.mSecurityPreferences.storeString(FimdeAnoConstants.PRESENCE, FimdeAnoConstants.CONFIRM_WILL_GO);
            } else {
                this.mSecurityPreferences.storeString(FimdeAnoConstants.PRESENCE, FimdeAnoConstants.CONFIRM_WONT_GO);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkBoxParticipate;
    }
}
