package com.taiyuancity.bean;

/**
 * ������ĵ���ͼ��ʵ����. ȫ��int����Ϊ�洢������Դ��id
 * 
 * @author neng
 * @date 
 */
public class MainIcon
{
	private int title;
	private int imageId;

	public MainIcon(int title, int imageId)
	{
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public int getTitle()
	{
		return title;
	}

	public void setTitle(int title)
	{
		this.title = title;
	}

	public int getImageId()
	{
		return imageId;
	}

	public void setImageId(int imageId)
	{
		this.imageId = imageId;
	}
}
