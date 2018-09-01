package com.taotao.content.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.Tbcontent;

import java.util.List;

public interface ContentService {
	/**
	 * 根据内容分类id查询得到指定内容信息
	 * @param categoryId 分类id
	 * @return json数据 包含总记录条数和每条记录的json数据
	 */
	EasyUIDataGridResult findContentAll(long categoryId);
	/**
	 * 添加一个cms
	 * @param tbContent
	 * @return
	 */
	TaotaoResult addContent(Tbcontent tbContent);
	/**
	 * 根据分类id查询指定内容
	 * @param categoryId 分类id
	 * @return 指定分类下面的所有内容
	 */
	List<Tbcontent> getTbContent(long categoryId);
}
