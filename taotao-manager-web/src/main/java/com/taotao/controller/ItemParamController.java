package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.Tbitemparam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable long itemCatId){
        TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
        return result;
    }

    @RequestMapping("/save/{itemCatId}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable long itemCatId, String paramData){
        Tbitemparam tbitemParam = new Tbitemparam();
        tbitemParam.setItemCatId(itemCatId);
        tbitemParam.setParamData(paramData);
        TaotaoResult result = itemParamService.addItemParam(tbitemParam);
        return result;
    }

}
