package com.aisile.pojo.entity.group;

import java.io.Serializable;
import java.util.List;

import com.aisile.pojo.TbGoods;
import com.aisile.pojo.TbGoodsDesc;
import com.aisile.pojo.TbItem;

public class Goods implements Serializable{
       //spu
	private TbGoods goods;
	//spu desc
	private TbGoodsDesc goodsDesc;
	//sku
	private  List<TbItem> itemList;
	public TbGoods getGoods() {
		return goods;
	}
	public void setGoods(TbGoods goods) {
		this.goods = goods;
	}
	public TbGoodsDesc getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(TbGoodsDesc goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public List<TbItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<TbItem> itemList) {
		this.itemList = itemList;
	}
	
	
	
	
}
