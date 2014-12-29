package com.decade.agile.kit;

import java.util.List;

import android.text.TextUtils;

import com.decade.agile.components.DCPickDialogView.DCPickItem;

public class DCPickDialogHelper {
	
	public static void setSelected(String selected, List<DCPickItem> items) {
		if (TextUtils.isEmpty(selected))
			return;
		for (int i = 0; i < items.size(); i++) {
			if (selected.equals(items.get(i).getTitle())) {
				items.get(i).setSelected(true);
			}
		}
	}
}
