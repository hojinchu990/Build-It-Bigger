package com.udacity.gradle.builditbigger;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {

    InterstitialAd mInterstitialAd;
    AdView mAdView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_main, container, false);
        View root = super.onCreateView(inflater, container, savedInstanceState);

        mAdView = (AdView) root.findViewById(R.id.adView);
        mAdView.loadAd(buildTestAdRequest());

        mInterstitialAd=new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-8851761175252738/9317429242");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                tellJoke();
            }
        });

        //미리 추가함을 로드한다.
        requestNewInterstitial();

        //여기 추가해야 함///////
        //광고를 보여줄 새로운 리스너 재등록
        mTellJokeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mSpinner.setVisibility(View.VISIBLE);
                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                }else {
                    tellJoke();
                }
            }
        });

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
/*        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);*/
        return root;
    }

    private AdRequest buildTestAdRequest(){
        return new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
    }

    private void requestNewInterstitial(){
        mInterstitialAd.loadAd(buildTestAdRequest());
    }
}




























