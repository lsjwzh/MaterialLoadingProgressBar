# MaterialLoadingProgressBar
MaterialLoadingProgressBar   provide a styled ProgressBar which looks  like SwipeRefreshLayout's loading indicator(support-v4  v21+)

![NumberProgressBar](https://github.com/lsjwzh/MaterialLoadingProgressBar/blob/master/screen.gif)
## Usage

### how to import?    
add this into gradle

    compile('com.lsjwzh:materialloadingprogressbar:0.2.0-RELEASE')


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

        app:mlpb_show_arrow="true"
        app:mlpb_arrow_height="5dp"
        app:mlpb_arrow_width="10dp"

        app:mlpb_progress_stoke_width="5dp"
        app:mlpb_progress_text_visibility="visible"
        android:layout_width="60dp"
        android:layout_height="60dp"/>
```

### java api:



License
-------

    Copyright 2014 lsjwzh

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

