package cn.zhaiyifan.lyricview.demo;

import android.app.Activity;
import android.os.Bundle;

import cn.zhaiyifan.lyricview.LyricUtils;
import cn.zhaiyifan.lyricview.widget.LyricView;

public class DemoActivity extends Activity {

    private LyricView mLyricView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLyricView = (LyricView) findViewById(R.id.lyricView);
        mLyricView.setLyric(LyricUtils.parseLyric(
                getResources().openRawResource(R.raw.testfile), "UTF-8"));
        mLyricView.setLyricIndex(0);
        mLyricView.play();
    }
}