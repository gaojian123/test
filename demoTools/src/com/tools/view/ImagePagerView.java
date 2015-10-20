package com.tools.view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.tools.R;
import com.tools.utils.Tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 轮播图组件
 * 
 * @author G
 *
 */
public class ImagePagerView extends RelativeLayout {

	private Context mContext;

	private LinearLayout mPointLayout;
	private WrapContentHeightViewPager mVPager;
	private List<ImageView> mImageViews;
	private View[] views;

	private String[] mImgUrls;
	private int offset = 0;// 动画图片偏移量
	private int bmpW;// 动画图片宽度
	private int PageIndex = 0;
	private ImgPagerAdapter myPagerAdapter;
	private OnLoopImgItemClickListener onLoopImgItemClickListener;
	private DisplayImageOptions mImgOptions;
	private Drawable mDefaultDrawable;
	private ScheduledExecutorService mExecutorService;
	private boolean mFlipable=false;
    private int mFlipSeconds=5;
	public ImagePagerView(Context context) {
		super(context);
		mContext = context;
		initView();
	}

	public ImagePagerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImagePagerView);
		mDefaultDrawable = a.getDrawable(R.styleable.ImagePagerView_defaultDrawable);
		mFlipable=a.getBoolean(R.styleable.ImagePagerView_auto_Flipable, false);
		
		int second=a.getInt(R.styleable.ImagePagerView_auto_Flipseconds, 0);
		if (second>0) {
			mFlipSeconds=second;
		}
		a.recycle();
		if (null == mDefaultDrawable) {
			mDefaultDrawable = getResources().getDrawable(R.drawable.default_img);
		}
		mContext = context;
		initView();
	}

	private void initView() {
		inflate(mContext, R.layout.component_loop_img, this);
		mVPager = (WrapContentHeightViewPager) findViewById(R.id.vPager);
		mPointLayout = (LinearLayout) findViewById(R.id.llayout);
		mImgOptions = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				.showImageOnLoading(mDefaultDrawable).showImageForEmptyUri(mDefaultDrawable)
				.showImageOnFail(mDefaultDrawable).cacheInMemory(true).cacheOnDisk(true).considerExifParams(false)
				.resetViewBeforeLoading(true) // default
				.displayer(new FadeInBitmapDisplayer(500, true, true, false))
				// .displayer(new RoundedBitmapDisplayer(1000))
				// .displayer(new RoundedVignetteBitmapDisplayer(1000, 100))
				.delayBeforeLoading(500).build();
	}

	public void setOnLoopImgItemClickListener(OnLoopImgItemClickListener onLoopImgItemClickListener) {
		this.onLoopImgItemClickListener = onLoopImgItemClickListener;
	}

	public void setImgUrls(String[] picUrls) {
		mImgUrls = picUrls;
		mImageViews = new ArrayList<ImageView>();
		mPointLayout.removeAllViews();
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(Tools.getPxInt(5, mContext),
				Tools.getPxInt(5, mContext));
		pointParams.setMargins(Tools.getPxInt(5, mContext), 0, Tools.getPxInt(5, mContext), 0);
		Drawable focuse = getResources().getDrawable(R.drawable.dot_selected);
		Drawable unFocuse = getResources().getDrawable(R.drawable.dot_none);
		int len = picUrls.length;
		if (len < 2) {
			mPointLayout.setVisibility(View.GONE);
		}
		views = new View[len];
		for (int i = 0; i < len; i++) {
			ImageView imageView = new ImageView(mContext);
			imageView.setAdjustViewBounds(true);
			imageView.setLayoutParams(layoutParams);
			ImageLoader.getInstance().displayImage(picUrls[i], imageView, mImgOptions);
			// ImageLoader.getInstance().displayImage(picUrls[i], imageView);
			mImageViews.add(imageView);
			imageView.setOnClickListener(new LoopImgClickListener(i));
			View view = new View(mContext);
			view.setLayoutParams(pointParams);
			if (i == 0) {
				view.setBackgroundDrawable(focuse);
			} else {
				view.setBackgroundDrawable(unFocuse);
			}
			mPointLayout.addView(view);
			views[i] = view;
		}
		myPagerAdapter = new ImgPagerAdapter(mImageViews);
		mVPager.setAdapter(myPagerAdapter);
		mVPager.setCurrentItem(0);

		mVPager.setOnPageChangeListener(new MyOnPageChangeListener(unFocuse, focuse));
		if (mFlipable) {
			startTask();
		}
		
	}

	/**
	 * ViewPager适配器
	 */
	public class ImgPagerAdapter extends PagerAdapter {
		public List<ImageView> mListViews;

		public ImgPagerAdapter(List<ImageView> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	/**
	 * 头标点击监听
	 */
	public class TabOnClickListener implements OnClickListener {
		private int index = 0;

		public TabOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mVPager.setCurrentItem(index);
		}
	};

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量
		private Drawable mUnfocused;
		private Drawable mfocuse;

		private MyOnPageChangeListener(Drawable unfocused, Drawable foucese) {
			if (unfocused == null)
				mUnfocused = getResources().getDrawable(R.drawable.dot_none);
			else
				this.mUnfocused = unfocused;

			if (foucese == null)
				mfocuse = getResources().getDrawable(R.drawable.dot_selected);
			else
				this.mfocuse = foucese;
		}

		@Override
		public void onPageSelected(int arg0) {
			PageIndex = arg0;
			Tools.changeBtnBgImg(views, PageIndex, mfocuse, mUnfocused);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	private class LoopImgClickListener implements OnClickListener {

		private int _position;

		public LoopImgClickListener(int position) {
			_position = position;
		}

		@Override
		public void onClick(View v) {
			if (null != onLoopImgItemClickListener) {
				onLoopImgItemClickListener.OnLoopImgItemClick(v, _position);
			}

		}

	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// 设置当前页面
			mVPager.setCurrentItem(PageIndex);
		}

	};

	// 切换图片
	private class ViewPagerTask implements Runnable {

		@Override
		public void run() {
			System.out.println("HomePageFragment.ViewPagerTask.run()");
			// // TODO Auto-generated method stub
			PageIndex = (PageIndex + 1) % mImgUrls.length;
			// 更新界面
			// handler.sendEmptyMessage(0);
			handler.obtainMessage().sendToTarget();
		}

	}

	private void startTask() {
		mExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 每隔5秒钟切换一张图片
		mExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),mFlipSeconds, mFlipSeconds, TimeUnit.SECONDS);
	}

	public interface OnLoopImgItemClickListener {
		void OnLoopImgItemClick(View v, int position);
	}

	// public interface OnLoopImgItemClickListener extends OnClickListener{
	// @Override
	// public void onClick(View v);
	//
	// }

}
