package com.aisile.sellergoods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbBrandMapper;
import com.aisile.mapper.TbGoodsDescMapper;
import com.aisile.mapper.TbGoodsMapper;
import com.aisile.mapper.TbItemCatMapper;
import com.aisile.mapper.TbItemMapper;
import com.aisile.mapper.TbSellerMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbGoods;
import com.aisile.pojo.TbGoodsDesc;
import com.aisile.pojo.TbGoodsExample;
import com.aisile.pojo.TbGoodsExample.Criteria;
import com.aisile.pojo.TbItem;
import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.TbItemExample;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.pageResult;
import com.aisile.pojo.entity.group.Goods;
import com.aisile.sellergoods.service.BrandService;
import com.aisile.sellergoods.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service 
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper tbGoodsMapper;
	@Autowired
	private TbGoodsDescMapper tbGoodsDescMapper;
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbBrandMapper tbBrandMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Autowired
	private TbSellerMapper tbSellerMapper;

	
	@Override
	public List<TbGoods> findAll() {
		// TODO Auto-generated method stub
		//条件为空 查询全部 
		return tbGoodsMapper.selectByExample(null);
	}
	@Override
	public pageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbGoods>  pageinfo= (Page<TbGoods>)tbGoodsMapper.selectByExample(null);
		TbGoodsExample goodsExample = new TbGoodsExample();
		Criteria criteria = goodsExample.createCriteria();
		criteria.andIsDeleteIsNull();//非删除状态
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	
	@Override
	public void add(Goods goods) {
		goods.getGoods().setAuditStatus("0");		
		tbGoodsMapper.insert(goods.getGoods());	//插入商品表
		goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
		tbGoodsDescMapper.insert(goods.getGoodsDesc());//插入商品扩展数据
		saveItemList(goods);//插入商品SKU列表数据
	}
	
	//封装
	private void saveItemList(Goods goods){	
		if("1".equals(goods.getGoods().getIsEnableSpec())){
			for(TbItem item :goods.getItemList()){
				//标题
				String title= goods.getGoods().getGoodsName();
				Map<String,Object> specMap = JSON.parseObject(item.getSpec());
				for(String key:specMap.keySet()){
					title+=" "+ specMap.get(key);
				}
				item.setTitle(title);
				setItemValus(goods,item);
				tbItemMapper.insert(item);
			}		
		}else{					
			TbItem item=new TbItem();
			item.setTitle(goods.getGoods().getGoodsName());//商品KPU+规格描述串作为SKU名称
			item.setPrice( goods.getGoods().getPrice() );//价格			
			item.setStatus("1");//状态
			item.setIsDefault("1");//是否默认			
			item.setNum(99999);//库存数量
			item.setSpec("{}");			
			setItemValus(goods,item);					
			tbItemMapper.insert(item);
		}	
		
	}
	
	
	private void setItemValus(Goods goods,TbItem item) {
		item.setGoodsId(goods.getGoods().getId());//商品SPU编号
		item.setSellerId(goods.getGoods().getSellerId());//商家编号
		item.setCategoryid(goods.getGoods().getCategory3Id());//商品分类编号（3级）
		item.setCreateTime(new Date());//创建日期
		item.setUpdateTime(new Date());//修改日期 
		
		//品牌名称
		TbBrand brand = tbBrandMapper.selectByPrimaryKey(goods.getGoods().getBrandId());
		item.setBrand(brand.getName());
		//分类名称
		TbItemCat itemCat = tbItemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id());
		item.setCategory(itemCat.getName());
		
		//商家名称
		TbSeller seller = tbSellerMapper.selectByPrimaryKey(goods.getGoods().getSellerId());
		item.setSeller(seller.getNickName());
		
		//图片地址（取spu的第一个图片）
		List<Map> imageList = JSON.parseArray(goods.getGoodsDesc().getItemImages(), Map.class) ;
		if(imageList.size()>0){
			item.setImage ( (String)imageList.get(0).get("url"));
		}		
	}
	
	
	
	@Override
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		goods.getGoods().setAuditStatus("0");//设置未申请状态:如果是经过修改的商品，需要重新设置状态
		tbGoodsMapper.updateByPrimaryKey(goods.getGoods());//保存商品表
		tbGoodsDescMapper.updateByPrimaryKey(goods.getGoodsDesc());//保存商品扩展表
		//删除原有的sku列表数据		
		TbItemExample example=new TbItemExample();
		com.aisile.pojo.TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(goods.getGoods().getId());	
		tbItemMapper.deleteByExample(example);
		//添加新的sku列表数据
		saveItemList(goods);//插入商品SKU列表数据
	}
	@Override
	public Goods findOne(Long id) {
		// TODO Auto-generated method stub
		Goods goods=new Goods();
		TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(id);
		goods.setGoods(tbGoods);
		TbGoodsDesc goodsDesc = tbGoodsDescMapper.selectByPrimaryKey(id);
		goods.setGoodsDesc(goodsDesc);
		//查询SKU商品列表
		TbItemExample example=new TbItemExample();
		com.aisile.pojo.TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(id);//查询条件：商品ID
		List<TbItem> itemList = tbItemMapper.selectByExample(example);		
		goods.setItemList(itemList);
		return goods;
	}
	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		for(Long id:ids){
			TbGoods goods = tbGoodsMapper.selectByPrimaryKey(id);
			goods.setIsDelete("1");
			tbGoodsMapper.updateByPrimaryKey(goods);
		}		
	}
	@Override
	public pageResult findSearch(TbGoods goods, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		TbGoodsExample brandExample = new TbGoodsExample();
		Criteria criteria = brandExample.createCriteria();
		if(goods.getSellerId()!=null && goods.getSellerId().length()>0){
			
			criteria.andSellerIdEqualTo(goods.getSellerId());
		}
		if(goods.getAuditStatus()!=null && goods.getAuditStatus().length()>0){
			
			criteria.andAuditStatusEqualTo(goods.getAuditStatus());
		}
		if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
			
			criteria.andGoodsNameEqualTo(goods.getGoodsName());
		}
		Page<TbGoods>  pageinfo= (Page<TbGoods>)tbGoodsMapper.selectByExample(brandExample);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	//审核与驳回
	public void updateStatus(Long[] ids, String status) {
		for(Long id:ids){
			TbGoods goods = tbGoodsMapper.selectByPrimaryKey(id);
			goods.setAuditStatus(status);
			tbGoodsMapper.updateByPrimaryKey(goods);
		}
	}
	

	//上下架
	@Override
	public void updateMake(Long[] ids, String isMarketable) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(id);
			tbGoods.setIsMarketable(isMarketable);
			tbGoodsMapper.updateByPrimaryKeySelective(tbGoods);
		}
	}
	@Override
	public void ChangeStatus(Long[] ids, String auditStatus) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(id);
			tbGoods.setAuditStatus(auditStatus);
			tbGoodsMapper.updateByPrimaryKeySelective(tbGoods);
		}
	}
	
	

}
