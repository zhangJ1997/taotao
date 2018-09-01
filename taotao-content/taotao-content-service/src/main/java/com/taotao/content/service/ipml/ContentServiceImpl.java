package com.taotao.content.service.ipml;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbcontentMapper;
import com.taotao.pojo.Tbcontent;

@Service
public class ContentServiceImpl implements ContentService {
	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TbcontentMapper tbContentMapper;
	@Override
	public EasyUIDataGridResult findContentAll(long categoryId) {
		List<Tbcontent> tbContentAll = tbContentMapper.findTbContentAll(categoryId);
		
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(tbContentAll.size());
		result.setRows(tbContentAll);
		
		return result;
	}
	@Override
	public TaotaoResult addContent(Tbcontent tbContent) {
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		tbContentMapper.addTbContent(tbContent);
		jedisClient.hdel(CONTENT_KEY, tbContent.getCategoryId()+"");
		
		return TaotaoResult.ok();
	}
	@Override
	public List<Tbcontent> getTbContent(long categoryId) {
		//这里取缓存
		try {
			String json = jedisClient.hget(CONTENT_KEY, categoryId+"");
			if(StringUtils.isNotBlank(json)) {
				List<Tbcontent> result = JsonUtils.jsonToList(json, Tbcontent.class);
			
				return result;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/**
		 * 第一次请求的时候由于没有缓存 所以直接查询数据库，查询完成数据库以后
		 * 在return之前 把数据库里面的数据加入到redis缓存中
		 * 第二次访问的时候 由于先走上面的代码，取到直接return 没有取到代码直接加缓存
		 */
		List<Tbcontent> result = tbContentMapper.findTbContentAll(categoryId);
		
		//这里加缓存 把list集合变成json字符串
		try {
			jedisClient.hset(CONTENT_KEY, categoryId+"", JsonUtils.objectToJson(result));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
