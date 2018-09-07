package com.taotao.service.ipml;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbitemparamMapper;
import com.taotao.pojo.Tbitemparam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbitemparamMapper tbitemparamMapper;

    @Override
    public TaotaoResult getItemParamByCid(long itemCatId) {
        Tbitemparam itemParam = tbitemparamMapper.getItemParamByCid(itemCatId);
        if(itemParam != null){
            return TaotaoResult.ok(itemParam);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult addItemParam(Tbitemparam tbitemParam) {
        try {
            Date date = new Date();
            tbitemParam.setCreated(date);
            tbitemParam.setUpdated(date);
            tbitemparamMapper.addItemParam(tbitemParam);
            return TaotaoResult.ok(tbitemParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }
}
