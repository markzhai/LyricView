LyricView
=========
Android LyricView that accepts lrc stream as input, it is used in my project [LyricHere](https://github.com/markzhai/LyricHere).

This library offers a simple view that accepts lrc stream as input, shows lyric and automatically move according the timestamp of each sentence in lyric. It also supports scroll gesture.

Usage
-----
```gradle
dependencies {
    compile 'cn.zhaiyifan:lyricview:1.0.1'
}
```

```java
mLyricView = (LyricView) findViewById(R.id.lyricView);
// You can call setLyric anytime to change the lyric to another
mLyricView.setLyric(LyricUtils.parseLyric(getResources().openRawResource(R.raw.testfile), "UTF-8"));
mLyricView.setLyricIndex(0);
mLyricView.play();

// When you want to stop playing lyric, just call
mLyricView.stop();
```

```xml
<cn.zhaiyifan.lyricview.widget.LyricView
    android:id="@+id/lyricView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

TODO
----
- Improve drawing performance
