package com.taotao.service.ipml;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbitemMapper;
import com.taotao.pojo.Tbitem;
import com.taotao.pojo.Tbitemdesc;
import com.taotao.service.ItemService;
/**
 * 替代<bean>标签
 * id itemServiceImpl
 * @author ZJZJZJ
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbitemMapper tbItemMapper;
	@Override
	public Tbitem getItemById(long itemId) {
		Tbitem tbitem = tbItemMapper.getItemById(itemId);
		return tbitem;
	}
	@Override
	public EasyUIDataGridResult getItems(int page, int rows) {
		//设置分页信息 
		PageHelper.startPage(page, rows);
		 //得到所有商品信息
		 List<Tbitem> list = tbItemMapper.getTbItem();
		 //取分页信息
		 PageInfo<Tbitem> pageInfo = new PageInfo<>(list);
		 EasyUIDataGridResult result = new EasyUIDataGridResult();
		 result.setTotal(pageInfo.getTotal());
		 result.setRows(list);
		 
		return result;
	}
	@Override
	public TaotaoResult addItem(Tbitem item, String desc) {
		//补全商品信息
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//补全状态
		item.setStatus((byte) 1);
		Date date = new Date();
		//补全创建时间
		item.setCreated(date);
		//补全更新时间
		item.setUpdated(date);
		//添加商品基本信息
		tbItemMapper.addTbItem(item);
		
		Tbitemdesc itemdesc = new  Tbitemdesc();
		//补全商品描述表中的商品id
		itemdesc.setItemId(itemId);
		//补全商品描述表中的创建时间
		itemdesc.setCreated(date);
		//补全商品描述表中的更新时间
		itemdesc.setUpdated(date);
		tbItemMapper.addTbItemDesc(itemdesc);
		
		return TaotaoResult.ok();
	}

}
