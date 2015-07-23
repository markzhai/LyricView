LyricView
=========
Android LyricView that accepts lrc stream as input.

This is actually a sub-part of my another project [LyricHere](https://github.com/markzhai/LyricHere).

As that one is too large, I pick core part of lyric play feature and name it LyricView.

This library offers a simple view that accepts lrc stream as input, and it will automatically move according the timestamp of each sentence in lyric. It also supports scroll gesture.

Usage
-----
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

Pre-requisites
--------------

- Android API v8

TODO
----

- Improve drawing performance
