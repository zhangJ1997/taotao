package com.taotao.mapper;

import com.taotao.pojo.Tbitemcat;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbitemcatMapper {
	/**
	 * 根据id查询分类信息
	 * @param parentId 当前分类id
	 * @return 当前分类下的子类目
	 */
    List<Tbitemcat> getItemCatByParentId(long parentId);
}