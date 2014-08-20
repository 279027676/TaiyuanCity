package com.taiyuancity.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * ���ݿ�ֻ�洢һ��layoutId.
 */
public class MyFavouriteDAO
{
	// 1��ͨ�����췽����ʵ����һ��DBOpenHelper�����DBOpenHelper����Խ������ݿ�Ĳ�����
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public MyFavouriteDAO(Context context)
	{
		helper = new DBOpenHelper(context);
	}

	/**
	 * ���ݿ��������Ϣ
	 * 
	 * @param layoutId
	 */
	public void add(int layoutId)
	{
		db = helper.getWritableDatabase();
		// ��exec�������������ڶ�������������һ���е�ռλ����������
		db.execSQL("insert into " + DBOpenHelper.MyFavouriteTable
				+ "(layoutId) values (?)", new Object[] { layoutId });
	}

	/**
	 * ��ѯ��Ϣ
	 * 
	 * @param layoutId
	 * @return layoutId|-1,
	 */
	public int find(int layoutId)
	{
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select layoutId from "
				+ DBOpenHelper.MyFavouriteTable + " where layoutId = ?",
				new String[] { String.valueOf(layoutId) });
		// �жϵ�һ�������Ƿ�Ϊ�գ������Ϊ�գ��򷵻�
		while (cursor.moveToNext())
		{
			// cursor.getColumnIndex("title")���ڲ�֪�������������ͨ����������ȡ������
			return cursor.getInt(cursor.getColumnIndex("layoutId"));
		}
		return -1;
	}

	/**
	 * ��ѯ����layoutId��Ϣ
	 * 
	 * @return int[]
	 */
	public int[] findAll()
	{
		int[] layoutIds = new int[40];
		int i = 0;
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select layoutId from "
				+ DBOpenHelper.MyFavouriteTable, null);
		// �жϵ�һ�������Ƿ�Ϊ�գ������Ϊ�գ��򷵻�
		while (cursor.moveToNext())
		{
			layoutIds[i] = cursor.getInt(cursor.getColumnIndex("layoutId"));
			i++;
		}
		return layoutIds;
	}

	/**
	 * ɾ����Ϣ
	 * 
	 * @param ...layoutId,��ʾ�����ĸ����ǲ��̶���
	 */
	public void delete(Integer... layoutId)
	{
		if (layoutId.length > 0)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < layoutId.length; i++)
			{
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);// ɾ�����һ�����š�
			// delete from t_favourite where layoutId in (?,?,?,?);ɾ������һ��
			db = helper.getWritableDatabase();
			db.execSQL("delete from " + DBOpenHelper.MyFavouriteTable
					+ " where layoutId in (" + sb + ")", (Object[]) layoutId);
		}
	}

}
