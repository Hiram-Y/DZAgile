package com.decade.agile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.decade.agile.R;
import com.decade.agile.components.DCPickDialogView.DCPickItem;

public class DCPickDialogAdapter extends BaseAdapter {

	private Context _context;
	private List<DCPickItem> _list;
	private int _indicatorImageResId;

	public DCPickDialogAdapter(Context context, List<DCPickItem> list) {
		_context = context;
		_list = list;
	}

	@Override
	public int getCount() {
		return _list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public DCPickItem getDateWhitPosition(int position) {
		return _list.get(position);
	}

	/**
	 * 设置指示点图标，此ResId是一个selector，参照 @drawable/agile_pick_dialog_checkbox_bg
	 * 
	 * @param indicatorImageResId
	 */
	public void setIndicatorImageResId(int indicatorImageResId) {
		_indicatorImageResId = indicatorImageResId;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(_context).inflate(
					R.layout.agile_pick_dialog_item_view, null);
			holder.img_imv = (ImageView) view
					.findViewById(R.id.pick_item_img_imv);
			holder.title_txv = (TextView) view
					.findViewById(R.id.pick_item_title_txv);
			holder.flag_ckb = (CheckBox) view
					.findViewById(R.id.pick_item_flag_ckb);
			// holder.title.getPaint().setFakeBoldText(true);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		DCPickItem ss = _list.get(position);
		holder.title_txv.setText(ss.getTitle());
		if (ss.isSelected()) {
			holder.flag_ckb.setChecked(true);
		} else {
			holder.flag_ckb.setChecked(false);
		}
		if (ss.getIconId() != 0) {
			holder.img_imv.setImageResource(ss.getIconId());
			holder.img_imv.setVisibility(View.VISIBLE);
		} else {
			holder.img_imv.setVisibility(View.GONE);
		}

		if (_indicatorImageResId != 0) {
			holder.flag_ckb.setButtonDrawable(_indicatorImageResId);
		} else {
			holder.flag_ckb
					.setButtonDrawable(R.drawable.agile_pick_dialog_checkbox_bg);
		}

		return view;
	}

	static class ViewHolder {
		ImageView img_imv;
		TextView title_txv;
		CheckBox flag_ckb;

	}

}
