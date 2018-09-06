package com.taotao.search.dao;

import com.taotao.common.pojo.SearchItem;

import java.util.List;

public interface SearchItemMapper {
	/**
	 * 查询数据库中的tbitem，tbitemcat，tbitemdesc
	 * @return SearchItem
	 */
	public List<SearchItem> getItemList();

	public SearchItem getItemById(long itemId);
}
