package com.agile.sample.factory;

import android.view.ViewGroup;

import com.agile.sample.view.HomeView;
import com.agile.sample.view.MessageView;
import com.agile.sample.view.MoreView;
import com.agile.sample.view.QueryView;
import com.decade.framework.DCBaseView;
import com.decade.framework.DCBaseViewActivity;
import com.decade.framework.DCiViewFactory;


public class ViewFactory implements DCiViewFactory{
	@Override
	public DCBaseView createView(DCBaseViewActivity parent, int viewId, ViewGroup root) {
		DCBaseView view = null;
		switch (viewId) {
		case ViewDefine.VIEWDEFINE_HOME:
			view = new HomeView(root, parent);
			break;
		case ViewDefine.VIEWDEFINE_QUERY:
			view = new QueryView(root, parent);
			break;
		case ViewDefine.VIEWDEFINE_MESSAGE:
			view = new MessageView(root, parent);
			break;
		case ViewDefine.VIEWDEFINE_SETTING:
			view = new MoreView(root, parent);
			break;
		}
		return view;
	}

}
