package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.Tbitemparam;

public interface ItemParamService {
    /**
     * 根据分类id查询指定分类下面是否有规格参数模板存在
     *@param  itemCatId 分类id
     * @return  200表示该分类存在 否则返回ok
     */
    TaotaoResult getItemParamByCid(long itemCatId);

    /**
     * 保存模板json数据到数据库中的模板表中
     * @param tbitemParam 需要保存的json模板数据
     * @return  200表示成功 否则返回ok
     */
    TaotaoResult addItemParam(Tbitemparam tbitemParam);

}
