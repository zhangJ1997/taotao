package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.Tbitem;

public interface ItemService {
	/**
	 * 根据商品id查询指定商品信息
	 * 商品id
	 * @return 商品信息
	 */
	public Tbitem getItemById(long itemId);
	/**
	 * 根据页面传递过来的参数分页显示商品信息
	 * @param page 当前页
	 * @param rows 每一页显示的商品
	 * @return 
	 */
	EasyUIDataGridResult getItems(int page, int rows);
	/**
	 * 添加商品基本信息和商品描述信息，商品的规格参数以后添加
	 * @param item 商品基本信息 
	 * @param itemDesc 商品描述信息
	 * @return 包含了状态码和是否成功的message 以及json数据
	 */
	TaotaoResult addItem(Tbitem item, String desc);
	
}
