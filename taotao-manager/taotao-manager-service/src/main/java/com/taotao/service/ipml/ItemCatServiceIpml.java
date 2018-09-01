package com.taotao.service.ipml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbitemcatMapper;
import com.taotao.pojo.Tbitemcat;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceIpml implements ItemCatService {
	
	@Autowired
	private TbitemcatMapper tbItemCatMapper;
	@Override
	public List<EasyUITreeNode> getCatList(long parentId) {
		List<Tbitemcat> tbItemCats = tbItemCatMapper.getItemCatByParentId(parentId);
		List<EasyUITreeNode> result = new ArrayList<>();
		for (Tbitemcat tbItemCat : tbItemCats) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsparent()?"closed":"open");
			result.add(node);
		}
		return result;
	}

}
