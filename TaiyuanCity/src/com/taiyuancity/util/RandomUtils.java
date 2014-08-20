package com.taiyuancity.util;

import java.util.Random;

import com.taiyuancity.app.AppConstant;

/**
 * �������, ͨ��layout��id������
 * 
 * @author neng
 * @date 2013-2-10
 */
public class RandomUtils
{
	/**
	 * ����layout������intֵ, ����ʱ����-1
	 * 
	 * @return
	 */
	public static int randomLayout()
	{
		Random randomArray = new Random();
		int whichArray = randomArray.nextInt(3);	//0-2
		//System.out.println("3�������еĵ�" + whichArray + "��!");
		switch (whichArray)
		{
		case 0:
			return AppConstant.layout_lcty[randomArray
					.nextInt(AppConstant.layout_lcty.length)];
		case 1:
			return AppConstant.layout_wzty[randomArray
					.nextInt(AppConstant.layout_wzty.length)];
		case 2:
			return AppConstant.layout_xzty[randomArray
					.nextInt(AppConstant.layout_xzty.length)];
		default:
			break;
		}
		return -1;
	}
}
