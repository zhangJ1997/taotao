package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	/**
	 * 根据分类id查询分类
	 * @param parentId 分类id
	 * @return 查询分类类目返回json数据 包含了 id text state
	 */
	List<EasyUITreeNode> getCatList(long parentId);
}
