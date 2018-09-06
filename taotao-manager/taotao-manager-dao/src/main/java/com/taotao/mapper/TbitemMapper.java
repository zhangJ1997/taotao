package com.taotao.mapper;

import com.taotao.pojo.Tbitem;
import com.taotao.pojo.Tbitemdesc;

import java.util.List;

public interface TbitemMapper {
	/**
	 * 根据商品id查询商品信息
	 * @param itemId 商品id
	 * @return 指定商品id的商品信息
	 */
    public Tbitem getItemById(long itemId);
    /**
     * 分页查询商品信息
     * @return 每页显示的商品数量
     */
    List<Tbitem> getTbItem();
    /**
     * 添加商品基本信息
     * @param tbitem
     */
    void addTbItem(Tbitem tbitem);
    /**
     * 添加商品描述信息
     * @param tbitemdesc
     */
    void addTbItemDesc(Tbitemdesc tbitemdesc);
}