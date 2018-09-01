package com.taotao.content.service.ipml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbcontentcategoryMapper;
import com.taotao.pojo.Tbcontentcategory;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbcontentcategoryMapper tbContentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		List<Tbcontentcategory> tbcontentcategorys = tbContentCategoryMapper.getTbContentCatgoryByParentId(parentId);
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		for (Tbcontentcategory tbcontentcategory : tbcontentcategorys) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbcontentcategory.getId());
			node.setText(tbcontentcategory.getName());
			node.setState(tbcontentcategory.getIsParent()?"closed":"open");
			result.add(node);
		}
		
		return result;
	}
	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		Tbcontentcategory tbcontentcategory = new Tbcontentcategory();
		//我们添加的节点一定是一个子节点 但是他的父节点 是否是子节点 不好说 所以要判断
		tbcontentcategory.setIsParent(false);
		tbcontentcategory.setName(name);
		tbcontentcategory.setParentId(parentId);
		tbcontentcategory.setSortOrder(1);
		tbcontentcategory.setStatus(1);
		Date date = new Date();
		tbcontentcategory.setCreated(date);
		tbcontentcategory.setUpdated(date);
		//插入数据到数据库
		tbContentCategoryMapper.addTbContentCatgory(tbcontentcategory);
		/**
		 * 修改父级目录
		 * 页面传递过来的数据 有两个parentId和name 其中的parentId 为当前内容分类的id name就是当前内容分类的名称 
		 * select * from tbcontentcategory where parentId = id
		 */
		Tbcontentcategory category = tbContentCategoryMapper.getTbContentCategoryById(parentId);
		//则表示当前节点为父节点
		if(!category.getIsParent()) {
			category.setId(parentId);
			category.setIsParent(true);
			tbContentCategoryMapper.updateTbContentCategory(tbcontentcategory);
		}
		return TaotaoResult.ok(tbcontentcategory);
	}

}
