package com.decade.agile.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-5-9
 */
public class DCFragmentPagerAdapter extends FragmentPagerAdapter{
	
	private List<String> _titles;
	private List<Fragment> _framents;

	/**
	 * @param fm
	 */
	public DCFragmentPagerAdapter(FragmentManager fm ,List<String> title,List<Fragment> frament) {
		super(fm);
		_titles = title;
		_framents = frament;
	}

	@Override
	public Fragment getItem(int arg0) {
		return _framents.get(arg0);
	}

	@Override
	public int getCount() {
		return _framents.size();
	}
	@Override
	public CharSequence getPageTitle(int position) {
		if(_titles!= null){
			return (_titles.size() > position) ? _titles.get(position) : "";
		}
		return super.getPageTitle(position);
	}
}
