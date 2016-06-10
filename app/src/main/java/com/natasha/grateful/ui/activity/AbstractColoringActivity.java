

package com.natasha.grateful.ui.activity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.natasha.grateful.ui.widget.ColorButton;

import org.androidsoft.utils.ui.NoTitleActivity;

import java.util.List;

public abstract class AbstractColoringActivity extends NoTitleActivity
{

    public static final String INTENT_START_NEW = "com.natasha.grateful.paint.START_NEW";
    public static final String INTENT_PICK_COLOR = "com.natasha.grateful.paint.PICK_COLOR";
    public static final String INTENT_ABOUT = "com.natasha.grateful.paint.ABOUT";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        _displayWidth = d.getWidth();
        _displayHeight = d.getHeight();
        
    }

    public static int getDisplayWitdh()
    {
        return _displayWidth;
    }

    public static int getDisplayHeight()
    {
        return _displayHeight;
    }

    protected void findAllColorButtons(List<ColorButton> result)
    {
        findAllColorButtons((ViewGroup) getWindow().getDecorView(), result);
    }

    protected void findAllColorButtons(ViewGroup g, List<ColorButton> result)
    {
        for (int i = 0; i < g.getChildCount(); i++)
        {
            View v = g.getChildAt(i);
            if (v instanceof ViewGroup)
            {
                findAllColorButtons((ViewGroup) v, result);
            }
            if (v instanceof ColorButton)
            {
                result.add((ColorButton) v);
            }
        }
    }
    protected static int _displayWidth;
    protected static int _displayHeight;
}