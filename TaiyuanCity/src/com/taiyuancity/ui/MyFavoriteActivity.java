package com.taiyuancity.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.taiyuancity.R;
import com.taiyuancity.adapter.MyFavouriteAdapter;
import com.taiyuancity.bean.MyFavourite;
import com.taiyuancity.dao.MyFavouriteDAO;
import com.taiyuancity.util.ConstantUtils;

/**
 * "�ҵ��ղ�"������
 * 
 * @author neng
 * @date 2013-2-22
 */
public class MyFavoriteActivity extends Activity
{
	private ImageButton back;
	private ListView listView;
	private MyFavouriteAdapter myFavouriteAdapter;
	private MyFavouriteDAO myFavouriteDAO; //��ѯ���ݿ�
	private int[] layoutIds;			//��ѯ��������layoutId

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_favourite);

		myFavouriteDAO = new MyFavouriteDAO(this);

		initListView();

		//���˰�ť����
		back = (ImageButton) findViewById(R.id.my_favourite_head_back);
		back.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				MyFavoriteActivity.this.finish();
			}
		});
	}

	/**
	 * listview����
	 */
	private void initListView()
	{
		listView = (ListView) findViewById(R.id.listview);
		myFavouriteAdapter = new MyFavouriteAdapter(this, getMyFavouriteLists());
		listView.setAdapter(myFavouriteAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Intent intent = new Intent(MyFavoriteActivity.this,
						ChildActivity.class);
				//����Intent����, layout��id
				intent.putExtra("id", layoutIds[position]);
				startActivity(intent);
			}
		});

		//Ϊlistviewע��ContextMenu
		registerForContextMenu(listView);
	}

	/**
	 * ��ȡMyFavourite���б�, ��������adapter
	 * 
	 * @return
	 */
	public List<MyFavourite> getMyFavouriteLists()
	{
		List<MyFavourite> myFavouritesLists = new ArrayList<MyFavourite>();
		//�õ����ݿ�������layoutId
		layoutIds = myFavouriteDAO.findAll();
		//�ҳ�layoutId��Ӧ����Դ, ��װ��MyFvourite��myFavouritesLists
		for (int i = 0; i < layoutIds.length; i++)
		{
			Log.i("MyFavoriteActivity", "layoutId-->" + i + " :" + layoutIds[i]);
			//ȥ��
			if (0 != layoutIds[i])
			{
				myFavouritesLists.add(ConstantUtils
						.getMyFavourite(layoutIds[i]));
				//Log.i("MyFavoriteActivity","MyFavourite-->"+ i+ " :"+ getString((ConstantUtils.getMyFavourite(layoutIds[i])).getContent()));
			} else
				break;
		}
		return myFavouritesLists;
	}

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo mi)
	{
		menu.setHeaderTitle(R.string.menu_my_favourite_context_title);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_my_favourite_context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		//�õ�ѡ�е���
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		//����λ�û�ȡ���, ���������MyFavourite
		MyFavourite myFavourite = (MyFavourite) listView
				.getItemAtPosition((int) menuInfo.id);
		switch (item.getItemId())
		{
		//		case Menu.FIRST:
		case R.id.menu_my_favourite_context_no:
			int layoutIdDel = ConstantUtils.getLayoutId(myFavourite);//�õ�layoutID
			myFavouriteDAO.delete(layoutIdDel);
			Toast.makeText(MyFavoriteActivity.this,
					getString(R.string.db_my_favourite_delete),
					Toast.LENGTH_SHORT).show();
			//���������������Ը��½���
			myFavouriteAdapter = new MyFavouriteAdapter(this,
					getMyFavouriteLists());
			listView.setAdapter(myFavouriteAdapter);
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
}