package co.prandroid.kotlin_admobintegration

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private var TAG = "MainActivity";
    }

    internal lateinit var mInterstitialAd: InterstitialAd
    internal lateinit var adRequest : AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, resources.getString(R.string.app_id));

        // bannar add
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        //Interstitial ad
        mInterstitialAd =  InterstitialAd(this)
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        requestNewInterstitial()
    }

    //
    fun adClick(view: View){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            //go to second activity or do something
        }

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                requestNewInterstitial()
                //go to second activity or do something
            }

            override fun onAdLoaded() {
            }

            override fun onAdFailedToLoad(i: Int) {
                Log.w("AgeActivity", "onAdFailedToLoad:" + i)
            }
        }
    }


    private fun requestNewInterstitial() {
        adRequest = AdRequest.Builder().build()
        mInterstitialAd.loadAd(adRequest)
    }
}
