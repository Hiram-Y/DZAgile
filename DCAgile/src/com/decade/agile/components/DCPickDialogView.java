package com.decade.agile.components;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.decade.agile.R;
import com.decade.framework.DCApplication;

public class DCPickDialogView extends DCCommonDialogView{
	private ListView single_selection_lsv;

	public DCPickDialogView(Context context,
			DCPromptDialogParams params) {
		super(context, params);
	}

	@Override
	protected void addToCenterView(LinearLayout layout) {
		View center = View.inflate(_context, R.layout.agile_single_selection_dialog_view,
				layout);
		single_selection_lsv = (ListView)center.findViewById(R.id.single_selection_lsv);
	}
	
	public void setOnItemClickListener(OnItemClickListener listener){
		if(listener != null){
			single_selection_lsv.setOnItemClickListener(listener);
		}
	}
	
	public void setAdapter(BaseAdapter adapter){
		single_selection_lsv.setAdapter(adapter);
	}
	
	public void setSelector(int resID){
		single_selection_lsv.setSelector(resID);
	}
	
	public ListView getListView(){
		return single_selection_lsv;
	}
	
	@Override
	protected int getDialogHeight() {
		return DCApplication.getApp().getWorkSpaceHeight() / 10 * 6;
	}

	public static class DCPickItem{
		private int _iconId;
		private String _title;
		private boolean _selected; 
		
		public DCPickItem(String title){
			_title = title;
		}
		
		public int getIconId() {
			return _iconId;
		}
		public void setIconId(int iconId) {
			_iconId = iconId;
		}
		public String getTitle() {
			return _title;
		}
		public void setTitle(String title) {
			_title = title;
		}

		public boolean isSelected() {
			return _selected;
		}

		public void setSelected(boolean selected) {
			_selected = selected;
		}
		
		 
	}

}
