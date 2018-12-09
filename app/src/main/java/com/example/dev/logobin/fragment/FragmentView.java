package com.example.dev.logobin.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public abstract class FragmentView extends Fragment {
    public FragmentActivity Activity;
    public View ViewMain;
    public String Tag;

    public abstract void OnCreate();

    public void OnResume() { }

    public void OnOpen() { }

    public void OnPause() { }

    public void OnDestroy() { }

    public void OnActivityResult(int RequestCode, int ResultCode, Intent intent) { }
}
