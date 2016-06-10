
package com.natasha.grateful.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.natasha.grateful.R;
import com.natasha.grateful.ui.activity.PaintActivity;

import org.androidsoft.utils.ui.WhatsNewActivity;

public class SplashActivity extends WhatsNewActivity implements OnClickListener
{

    private Button mButtonPlay;

    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);

        setContentView(R.layout.splash);

        mButtonPlay = (Button) findViewById(R.id.button_go);
        mButtonPlay.setOnClickListener(this);

        ImageView image = (ImageView) findViewById(R.id.image_splash);
        image.setImageResource(R.drawable.splash);

    }

    /**
     * {@inheritDoc }
     */
    public void onClick(View v)
    {
        if (v == mButtonPlay)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public int getFirstRunDialogTitleRes()
    {
        return R.string.first_run_dialog_title;
    }

    @Override
    public int getFirstRunDialogMsgRes()
    {
        return R.string.first_run_dialog_message;
    }

    @Override
    public int getWhatsNewDialogTitleRes()
    {
        return R.string.whats_new_dialog_title;
    }

    @Override
    public int getWhatsNewDialogMsgRes()
    {
        return R.string.whats_new_dialog_message;
    }
}
