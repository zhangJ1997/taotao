package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchDao searchDao;
	@Override
	public SearchResult search(String queryString, int page, int rows) throws Exception{
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(queryString);
		//设置默认搜索域
		query.set("df","item_title");
		//开始
		query.setStart((page - 1) * rows);
		query.setRows(rows);

		//开启高亮
		query.setHighlight(true);
		//设置哪个域高亮
		query.addHighlightField("item_title");
		//前缀
		query.setHighlightSimplePre("<em style=\"color:red\">");
		//后缀
		query.setHighlightSimplePost("</em>");
		SearchResult result = searchDao.search(query);
		//计算总页数
		long pageCount = result.getRecordCount()%rows==0?result.getRecordCount()/rows:result.getRecordCount()/rows+1;
		result.setPageCount(pageCount);
		
		return result;
	}

}
