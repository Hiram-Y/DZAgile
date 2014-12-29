package com.agile.sample.activity;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.agile.sample.R;
import com.decade.agile.DCAgileActivity;
import com.decade.agile.components.DCCommonTopView;
import com.decade.agile.kit.DCWorkspace;
import com.decade.framework.DCiTopView;

/**
 * @description: 开心付Activity基类
 * @author: Decade
 * @date: 2014-6-13
 */
public class JollyPayBaseActivity extends DCAgileActivity{
	
	protected void init(int title) {
		DCWorkspace.saveWorkspaceSize(this);
		setTopViewVisibility(View.VISIBLE);
		addTopTitle(title, Color.WHITE,
				getDimension(R.dimen.title_size));
		setTopViewBackground(R.drawable.nav_bar);
	//	setTopLeftViewBackground(R.drawable.shoudan_top_back_normal_selector);
		setTopLeftViewVisibility(View.GONE);
		setTopLeftAction(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	@Override
	protected DCiTopView getTopViewLoader() {
		return new DCCommonTopView(this,
				((ViewGroup) findViewById(R.id.main_top_view)));
	}
	
}
