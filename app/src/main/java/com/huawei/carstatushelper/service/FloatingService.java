package com.huawei.carstatushelper.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huawei.carstatushelper.MainActivity;
import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.floating.CarControlPage;
import com.huawei.carstatushelper.floating.CarCurrentTripPage;
import com.huawei.carstatushelper.floating.CarStatusPage;
import com.huawei.carstatushelper.util.NotificationHelper;
import com.huawei.carstatushelper.util.Utils;
import com.viewpagerindicator.UnderlinePageIndicator;
import com.ziwenl.floatingwindowdemo.utils.FloatingWindowHelper;

import java.util.ArrayList;
import java.util.List;

public class FloatingService extends Service implements View.OnClickListener {

    private FloatingWindowHelper floatingWindowHelper;
    private View rootView;
    private CarStatusPage carStatusPage;
    private CarControlPage carControlPage;
    private CarCurrentTripPage carCurrentTripPage;
    //    private TextView item4Text;

    public FloatingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        floatingWindowHelper = new FloatingWindowHelper(this);
        rootView = LayoutInflater.from(this).inflate(R.layout.layout_floating, null, false);
        floatingWindowHelper.addView(rootView, 0, 0, true, false);

        rootView.findViewById(R.id.back_btn).setOnClickListener(this);
        rootView.findViewById(R.id.close_btn).setOnClickListener(this);

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        UnderlinePageIndicator indicator = (UnderlinePageIndicator) rootView.findViewById(R.id.indicator_upi);

        carStatusPage = new CarStatusPage(this);
        carStatusPage.init();
        carStatusPage.onCreate();
        carControlPage = new CarControlPage(this);
        carControlPage.init();
        carControlPage.onCreate();
        carCurrentTripPage = new CarCurrentTripPage(this);
        carCurrentTripPage.init();
        carCurrentTripPage.onCreate();

        viewPager.setAdapter(initAdapter());

        indicator.setViewPager(viewPager);
        indicator.setFades(false);

        if (Utils.isBackground(this)) {
            NotificationHelper.showNotification(this);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        carStatusPage.onDestroy();
        carControlPage.onDestroy();
        carCurrentTripPage.onDestroy();
        floatingWindowHelper.removeView(rootView);
    }

    private PagerAdapter initAdapter() {
        List<View> viewList = new ArrayList<>();
        viewList.add(carStatusPage.getRootView());
        viewList.add(carControlPage.getRootView());
        viewList.add(carCurrentTripPage.getRootView());

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = viewList.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                View view = viewList.get(position);
                container.removeView(view);
            }
        };
        return pagerAdapter;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.back_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            floatingWindowHelper.clear();
            floatingWindowHelper.destroy();
            stopSelf();
        } else if (viewId == R.id.close_btn) {
            floatingWindowHelper.clear();
            floatingWindowHelper.destroy();
            stopSelf();
        }
    }
}