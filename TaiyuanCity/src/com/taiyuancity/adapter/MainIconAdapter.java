package com.taiyuancity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taiyuancity.R;
import com.taiyuancity.bean.MainIcon;
import com.taiyuancity.bean.MainIconHolder;

/**
 * ������ͼ�������������
 * 
 * @author neng
 * @date 2013-2-16
 */
public class MainIconAdapter extends BaseAdapter
{
	private LayoutInflater inflater; // ��������������ֵġ�
	private List<MainIcon> icons;   // ���е�ͼ��ʵ�屻���
	private Context context;

	public MainIconAdapter(int[] titles, int[] iconsImage, Context context)
	{
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		icons = new ArrayList<MainIcon>();

		for (int i = 0; i < iconsImage.length; i++)
		{
			icons.add(new MainIcon(titles[i], iconsImage[i]));
		}
	}

	@Override
	public int getCount()
	{
		// ����List�Ĵ�С
		if (null != icons)
		{
			return icons.size();
		} else
		{
			return 0;
		}
	}

	@Override
	public Object getItem(int position)
	{
		return icons.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		MainIconHolder mainIconHolder;
		// ��ȡlayout�����ļ�
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.bean_main_icon, null);
			mainIconHolder = new MainIconHolder();
			mainIconHolder.title = (TextView) convertView
					.findViewById(R.id.mainIcon_title);
			mainIconHolder.image = (ImageView) convertView
					.findViewById(R.id.mainIcon_iconImage);
			convertView.setTag(mainIconHolder);
		} else
		{
			mainIconHolder = (MainIconHolder) convertView.getTag();
		}
		// ���ؼ���ֵ
		mainIconHolder.title.setText(context.getString(icons.get(position).getTitle()));
		mainIconHolder.image.setImageResource(icons.get(position).getImageId());

		//Log.i("ImageActivity", "getView:" + position);

		return convertView;

	}

}
