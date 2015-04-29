package com.agile.sample.view;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.agile.sample.R;
import com.decade.agile.DZAgileActivity;
import com.decade.agile.components.DZCommonTopView;
import com.decade.framework.DZiTopView;

/**
 * @description: 开心付Activity基类
 * @author: Decade
 * @date: 2014-6-13
 */
public class SampleBaseActivity extends DZAgileActivity {

	protected void initTitle(int title) {
		setTopViewVisibility(View.VISIBLE);
		setTopViewBackground(R.drawable.nav_bar);
		setTopLeftViewBackground(R.drawable.selector_title_back);
		addTopTitle(title, Color.WHITE, 18);
		setTopLeftAction(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected DZiTopView getTopViewLoader() {
		return new DZCommonTopView(this,
				((ViewGroup) findViewById(R.id.main_top_view)));
	}

}
