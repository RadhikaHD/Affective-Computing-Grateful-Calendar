
package com.natasha.grateful.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.natasha.grateful.R;
import com.natasha.grateful.ui.activity.AbstractColoringActivity;
import com.natasha.grateful.ui.widget.ColorButton;

import java.util.ArrayList;
import java.util.List;

public class PickColorActivity extends AbstractColoringActivity implements
        View.OnClickListener
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Apparently this cannot be set from the style.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        setContentView(R.layout.pick_color);

        List<ColorButton> colorButtons = new ArrayList<ColorButton>();
        findAllColorButtons(colorButtons);
        for (int i = 0; i < colorButtons.size(); i++)
        {
            colorButtons.get(i).setOnClickListener(this);
        }

    }

    public void onClick(View view)
    {
        if (view instanceof ColorButton)
        {
            ColorButton button = (ColorButton) view;
            setResult(button.getColor());
            finish();
        }
    }
}
