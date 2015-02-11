# MaterialLoadingProgressBar
MaterialLoadingProgressBar   provide a styled ProgressBar which looks  like SwipeRefreshLayout's loading indicator(support-v4  v21+)

![NumberProgressBar](https://github.com/lsjwzh/MaterialLoadingProgressBar/blob/master/screen.gif)
## Usage

### how to import?    
add this into gradle

    compile('com.lsjwzh:materialloadingprogressbar:0.2.0')


### xml:    

```
<com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar
        android:id="@+id/progressBar"
        android:layout_width="60dp"
        android:layout_height="60dp"/>
```
### options:

```
<com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar
        android:id="@+id/progressBar"
        app:mlpb_progress_stoke_width="5dp"
        app:mlpb_progress_text_visibility="visible"
        android:layout_width="60dp"
        android:layout_height="60dp"/>
```

### java api:


