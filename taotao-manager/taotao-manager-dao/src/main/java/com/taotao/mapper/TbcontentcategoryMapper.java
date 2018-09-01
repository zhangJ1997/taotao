package com.taotao.mapper;

import com.taotao.pojo.Tbcontentcategory;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbcontentcategoryMapper {
	
	/**
	 * 根据id查询内容分类信息
	 * @param parentId 父级类目id
	 * @return 当前父级类目下的子内容分类
	 */
	List<Tbcontentcategory> getTbContentCatgoryByParentId(long parentId);
	/**
	 * 添加一个分类信息到数据库
	 * @param tbcontentcategory 需要添加的分类信息对象
	 */
	void addTbContentCatgory(Tbcontentcategory tbcontentcategory);
	/**
	 * 根据当前id查询 制定内容那个分类
	 * @param parentId 当前父级目录id
	 * @return 当前父级目录分类信息
	 */
	Tbcontentcategory getTbContentCategoryById(long parentId);
	
	void updateTbContentCategory(Tbcontentcategory tbconentcategory);
}