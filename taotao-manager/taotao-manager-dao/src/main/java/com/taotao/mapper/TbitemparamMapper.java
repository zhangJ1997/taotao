package com.taotao.mapper;

import com.taotao.pojo.Tbitemparam;

public interface TbitemparamMapper {
    /**
     * 根据分类id查询数据库中规则参数模板表
     * @param cId 分类id
     * @return 当前分类id所对应的模板对象
     */
    Tbitemparam getItemParamByCid(long itemCatId);
}