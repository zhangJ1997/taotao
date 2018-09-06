package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemParamService {
    /**
     * 根据分类id查询指定分类下面是否有规格参数模板存在
     *@param  cId 分类id
     * @return  200表示该分类存在 否则返回ok
     */
    TaotaoResult getItemParamByCid(long itemCatId);

}
