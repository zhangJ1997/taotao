package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

public interface SearchService {
	
	/**
	 * 根据当前页面和需要查询的条件 返回商品列表集合总记录条数和总页数
	 * @param queryString 查询条数
	 * @param page 当前页
	 * @param rows 每页显示条数
	 * @return 包含一堆
	 * @throws Exception 
	 */
	public SearchResult search(String queryString, int page, int rows) throws Exception;
}
