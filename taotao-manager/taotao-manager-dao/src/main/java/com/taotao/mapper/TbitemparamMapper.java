package com.taotao.mapper;

import com.taotao.pojo.Tbitemparam;

public interface TbitemparamMapper {
    /**
     * 根据分类id查询数据库中规则参数模板表
     * @param cId 分类id
     * @return 当前分类id所对应的模板对象
     */
    Tbitemparam getItemParamByCid(long itemCatId);

    /**
     * 把商品规格参数模板对象存入数据库中
     * @param tbitemParam 商品规格参数模板对象
     */
    void addItemParam(Tbitemparam tbitemParam);
}