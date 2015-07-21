package cn.zhaiyifan.lyricview.demo;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;

import cn.zhaiyifan.lyricview.LyricUtils;
import cn.zhaiyifan.lyricview.widget.LyricView;

public class DemoActivity extends Activity {

    private LyricView mLyricView;
    private boolean mIsForeground;
    private Handler mHandler = new Handler();
    private UIUpdateRunnable mUiUpdateRunnable = null;

    private Runnable mUpdateResultsRunnable = new Runnable() {
        public void run() {
            mLyricView.invalidate();
        }
    };

    private Runnable mClearScreenOnFlagRunnable = new Runnable() {
        public void run() {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mIsForeground = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        mIsForeground = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLyricView = (LyricView) findViewById(R.id.lyricView);
        mLyricView.setLyric(LyricUtils.parseLyric(
                getResources().openRawResource(R.raw.testfile), "UTF-8"));
        mLyricView.setLyricIndex(0);
    }

    private class UIUpdateRunnable implements Runnable {
        private long mStartTime = -1;
        private long mNextSentenceTime = -1;
        private boolean mStopping = false;
        private boolean mStopped = false;

        public void reset() {
            mStartTime = -1;
            mNextSentenceTime = -1;
            mStopping = false;
        }

        public void stop() {
            mStopping = true;
        }

        public boolean isStopped() {
            return mStopped;
        }

        // TODO: Improve user touch response
        public void run() {
            if (mStartTime == -1) {
                mStartTime = System.currentTimeMillis();
            }

            while (mLyricView.getLyricIndex() != -2) {
                if (mStopping) {
                    mStopped = true;
                    return;
                }
                long ts = System.currentTimeMillis() - mStartTime;
                if (ts >= mNextSentenceTime || mLyricView.checkUpdate()) {
                    mNextSentenceTime = mLyricView.updateIndex(ts);

                    // Redraw only when window is visible
                    if (mIsForeground) {
                        mHandler.post(mUpdateResultsRunnable);
                    }
                }
                if (mNextSentenceTime == -1) {
                    mStopped = true;
                    // Clear KEEP_SCREEN_ON flag when finish playing
                    mHandler.post(mClearScreenOnFlagRunnable);
                    return;
                }
            }
        }
    }
}