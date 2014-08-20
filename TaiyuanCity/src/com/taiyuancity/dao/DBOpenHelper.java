package com.taiyuancity.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ����ļ�����SQLiteOpenHelper����SQLite���ݿ⣬�Լ�������汾.
 */
public class DBOpenHelper extends SQLiteOpenHelper
{
	private static final int VERSION = 1;// �汾�Ǵ� 1 ��ʼ��.�޸İ汾��ִ��upgrade������
	private static final String DBNAME = "favourite.db";// ���ݿ���
	public static final String MyFavouriteTable = "t_favourite"; //����, ���ɸ���

	public DBOpenHelper(Context context)
	{
		super(context, DBNAME, null, VERSION);
	}

	// ��װ��ʱ�򡣵��б��ʱ���ǲ�ִ�еġ�ִֻ��һ�Ρ�
	public void onCreate(SQLiteDatabase db)
	{
		// ������
		db.execSQL("create table " + MyFavouriteTable
				+ "(layoutId integer primary key)");
	}

	// ���ݿ����а汾�ģ����ִ�а汾�ĸ��£����ݵ�
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}

}
