/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lsjwzh.widget.materialloadingprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Private class created to work around issues with AnimationListeners being
 * called before the animation is actually complete and support shadows on older
 * platforms.
 *
 */
 public class CircleProgressBar extends ImageView {

    private static final int KEY_SHADOW_COLOR = 0x1E000000;
    private static final int FILL_SHADOW_COLOR = 0x3D000000;
    // PX
    private static final float X_OFFSET = 0f;
    private static final float Y_OFFSET = 1.75f;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final int SHADOW_ELEVATION = 4;


    private static final int DEFAULT_CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    private static final int DEFAULT_CIRCLE_DIAMETER = 56;
    private static final int STROKE_WIDTH_LARGE = 3;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final int ARROW_HEIGHT_LARGE = 6;

    private Animation.AnimationListener mListener;
    private int mShadowRadius;
    private int mBackGroundColor;
    private int mProgressColor;
    private int mProgressStokeWidth;
    private int mArrowWidth;
    private int mArrowHeight;
    private int mProgress;
    private int mMax;
    private int mDiameter;

    public CircleProgressBar(Context context) {
        super(context);
        init(context, null, 0);

    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);

    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CircleProgressBar, defStyleAttr,0);
//        <attr name="mlpb_inner_radius" format="dimension"/>
//        <attr name="mlpb_background_color" format="color"/>
//        <attr name="mlpb_progress_color" format="color"/>
//        <attr name="mlpb_progress_stoke_width" format="dimension"/>
//        <attr name="mlpb_arrow_width" format="dimension"/>
//        <attr name="mlpb_arrow_height" format="dimension"/>
//
//        <attr name="mlpb_progress" format="integer"/>
//        <attr name="mlpb_max" format="integer"/>
//
//
//        <attr name="mlpb_progress_text_size" format="dimension"/>
//        <attr name="mlpb_progress_text_color" format="color"/>
//
//        <attr name="mlpb_progress_text_offset" format="dimension"/>
//
//        <attr name="mlpb_progress_text_visibility" format="enum">
//        <enum name="visible" value="0"/>
//        <enum name="invisible" value="1"/>
//        </attr>
        final float density = getContext().getResources().getDisplayMetrics().density;

        mBackGroundColor = a.getColor(
                R.styleable.CircleProgressBar_mlpb_background_color, DEFAULT_CIRCLE_BG_LIGHT);

        mProgressColor = a.getColor(
                R.styleable.CircleProgressBar_mlpb_progress_color, DEFAULT_CIRCLE_BG_LIGHT);//ToDO 默认颜色


        mProgressStokeWidth = a.getDimensionPixelOffset(
                R.styleable.CircleProgressBar_mlpb_progress_stoke_width, (int) (STROKE_WIDTH_LARGE*density));
        mArrowWidth = a.getDimensionPixelOffset(
                R.styleable.CircleProgressBar_mlpb_arrow_width,  (int) (ARROW_WIDTH_LARGE*density));
        mArrowHeight = a.getDimensionPixelOffset(
                R.styleable.CircleProgressBar_mlpb_arrow_height,  (int) (ARROW_HEIGHT_LARGE*density));
        mProgress = a.getInt(R.styleable.CircleProgressBar_mlpb_progress, 0);
        mMax = a.getInt(R.styleable.CircleProgressBar_mlpb_max, 100);



        a.recycle();
    }



    private boolean elevationSupported() {
        return android.os.Build.VERSION.SDK_INT >= 21;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!elevationSupported()) {
            setMeasuredDimension(getMeasuredWidth() + mShadowRadius * 2, getMeasuredHeight()
                    + mShadowRadius * 2);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final float density = getContext().getResources().getDisplayMetrics().density;
        mDiameter = Math.min(getMeasuredWidth(),getMeasuredHeight());
        if(mDiameter <=0){
            mDiameter = (int)density*DEFAULT_CIRCLE_DIAMETER;
        }
        if(getBackground()==null){
            final int shadowYOffset = (int) (density * Y_OFFSET);
            final int shadowXOffset = (int) (density * X_OFFSET);
            mShadowRadius = (int) (density * SHADOW_RADIUS);


            ShapeDrawable circle;
            if (elevationSupported()) {
                circle = new ShapeDrawable(new OvalShape());
                ViewCompat.setElevation(this, SHADOW_ELEVATION * density);
            } else {
                OvalShape oval = new OvalShadow(mShadowRadius, mDiameter);
                circle = new ShapeDrawable(oval);
                ViewCompat.setLayerType(this, ViewCompat.LAYER_TYPE_SOFTWARE, circle.getPaint());
                circle.getPaint().setShadowLayer(mShadowRadius, shadowXOffset, shadowYOffset,
                        KEY_SHADOW_COLOR);
                final int padding = (int) mShadowRadius;
                // set padding so the inner image sits correctly within the shadow.
                setPadding(padding, padding, padding, padding);
            }
            circle.getPaint().setColor(mBackGroundColor);
            setBackgroundDrawable(circle);
        }
        if(getDrawable()==null) {
            MaterialProgressDrawable progressDrawable = new MaterialProgressDrawable(getContext(), this);
            progressDrawable.setBackgroundColor(mBackGroundColor);
            progressDrawable.setArrowScale(1f);
            progressDrawable.showArrow(true);
//            progressDrawable.setStartEndTrim(0, 180);
            progressDrawable.setSizeParameters(mDiameter, mDiameter,
                    mDiameter * 0.4,
                    mProgressStokeWidth,
                    mArrowWidth,
                    mArrowHeight);
            super.setImageDrawable(progressDrawable);
            progressDrawable.setAlpha(150);
            progressDrawable.start();
        }
    }

    @Override
    final public void setImageResource(int resId) {

    }

    @Override
    final public void setImageURI(Uri uri) {
        super.setImageURI(uri);
    }

    @Override
    final  public void setImageDrawable(Drawable drawable) {
    }

    public void setAnimationListener(Animation.AnimationListener listener) {
        mListener = listener;
    }

    @Override
    public void onAnimationStart() {
        super.onAnimationStart();
        if (mListener != null) {
            mListener.onAnimationStart(getAnimation());
        }
    }

    @Override
    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (mListener != null) {
            mListener.onAnimationEnd(getAnimation());
        }
    }

    /**
     * Update the background color of the circle image view.
     */
    public void setBackgroundColor(int colorRes) {
        if (getBackground() instanceof ShapeDrawable) {
            final Resources res = getResources();
            ((ShapeDrawable) getBackground()).getPaint().setColor(res.getColor(colorRes));
        }
    }

    private class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private int mShadowRadius;
        private Paint mShadowPaint;
        private int mCircleDiameter;

        public OvalShadow(int shadowRadius, int circleDiameter) {
            super();
            mShadowPaint = new Paint();
            mShadowRadius = shadowRadius;
            mCircleDiameter = circleDiameter;
            mRadialGradient = new RadialGradient(mCircleDiameter / 2, mCircleDiameter / 2,
                    mShadowRadius, new int[] {
                            FILL_SHADOW_COLOR, Color.TRANSPARENT
                    }, null, Shader.TileMode.CLAMP);
            mShadowPaint.setShader(mRadialGradient);
        }

        @Override
        public void draw(Canvas canvas, Paint paint) {
            final int viewWidth = CircleProgressBar.this.getWidth();
            final int viewHeight = CircleProgressBar.this.getHeight();
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, (mCircleDiameter / 2 + mShadowRadius),
                    mShadowPaint);
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, (mCircleDiameter / 2), paint);
        }
    }
}
