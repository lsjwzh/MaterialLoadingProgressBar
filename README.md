# MaterialLoadingProgressBar
MaterialLoadingProgressBar   provide a styled ProgressBar which looks  like SwipeRefreshLayout's loading indicator(support-v4  v21+)

![ProgressBar](https://github.com/lsjwzh/MaterialLoadingProgressBar/blob/master/screen.gif)
## Usage

### how to import?    
add this into gradle

    compile('com.lsjwzh:materialloadingprogressbar:0.5.4-RELEASE')


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
        app:mlpb_enable_circle_background="true"

        app:mlpb_progress_stoke_width="5dp"
        app:mlpb_progress_text_visibility="visible"
        android:layout_width="60dp"
        android:layout_height="60dp"/>
```

### java api:
#### show arrow
'CircleProgressBar' will not show arrow by default.
You can enable arrow drawing like this:
```
    circleProgressBar.setShowArrow(true);
```

#### Disable circle background
There is a white circle background on drawing 'CircleProgressBar' by default.
If you need hide the circle background,you can add a xml item

```
    app:mlpb_enable_circle_background="false"
```

or set it by java code
```
   circleProgressBar.setCircleBackgroundEnabled(false);
```

### release notes:
    0.5.4:  fix bug: attr progress_color invalid

    0.5.3: 	add default ring color;fix bug:NPE happens when ring color has never been setted.

    0.5.2: 	support setColorSchemeColors.

	0.5.1:  fix bug: arrow be putting into incorrect position.    

	0.5.0: 	support enable/disable circle back ground.    

	0.4.0:  fix bug:#1 :progressbar can not hide.    
        	fix bug:#2 :progressbar show full ring when running  too long.
        	
	0.3.0:  add showArrow option.



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

