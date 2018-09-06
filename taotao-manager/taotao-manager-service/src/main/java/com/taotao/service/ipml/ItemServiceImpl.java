package com.taotao.service.ipml;

import java.util.Date;
import java.util.List;

import com.taotao.common.utils.JsonUtils;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbitemdescMapper;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbitemMapper;
import com.taotao.pojo.Tbitem;
import com.taotao.pojo.Tbitemdesc;
import com.taotao.service.ItemService;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 替代<bean>标签
 * id itemServiceImpl
 * @author ZJZJZJ
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Value("${BASE}")
	private String BASE;
	@Value("${DESC}")
	private String DESC;
	@Value("${PARAM}")
	private String PARAM;
	@Value("${Expiry_TIME}")
	private Integer Expiry_TIME;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private ActiveMQTopic topic;
	@Autowired
	private TbitemMapper tbItemMapper;
	@Autowired
	private TbitemdescMapper tbitemdescMapper;
	@Override
	public Tbitem getItemById(long itemId) {
		System.out.println("aaaaaaaaa");
		try {
			//从缓冲中取数据
			String json = jedisClient.get(ITEM_INFO + ":" + itemId + BASE);
			//判断不为null 并且不为""
			if(StringUtils.isNotBlank(json)){
				Tbitem tbitem = JsonUtils.jsonToPojo(json, Tbitem.class);
				return tbitem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Tbitem tbitem = tbItemMapper.getItemById(itemId);
		try {
			//把数据库中的数据加入缓存
			jedisClient.set(ITEM_INFO+":"+itemId+BASE, JsonUtils.objectToJson(tbitem));
			jedisClient.expire(ITEM_INFO+":"+itemId+BASE,Expiry_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tbitem;
	}
	@Override
	public EasyUIDataGridResult getItems(int page, int rows) {
		//设置分页信息 
		PageHelper.startPage(page, rows);
		 //得到所有商品信息
		 List<Tbitem> list = tbItemMapper.getTbItem();
		 //取分页信息
		 PageInfo<Tbitem> pageInfo = new PageInfo<>(list);
		 EasyUIDataGridResult result = new EasyUIDataGridResult();
		 result.setTotal(pageInfo.getTotal());
		 result.setRows(list);
		 
		return result;
	}
	@Override
	public TaotaoResult addItem(final Tbitem item, String desc) {
		//补全商品信息
		final long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//补全状态
		item.setStatus((byte) 1);
		Date date = new Date();
		//补全创建时间
		item.setCreated(date);
		//补全更新时间
		item.setUpdated(date);
		//添加商品基本信息
		tbItemMapper.addTbItem(item);
		
		Tbitemdesc itemdesc = new  Tbitemdesc();
		//补全商品描述表中的商品id
		itemdesc.setItemId(itemId);
		//补全商品描述信息
		itemdesc.setItemDesc(desc);
		//补全商品描述表中的创建时间
		itemdesc.setCreated(date);
		//补全商品描述表中的更新时间
		itemdesc.setUpdated(date);
		tbItemMapper.addTbItemDesc(itemdesc);
		System.out.printf(String.valueOf(itemdesc));

		jmsTemplate.send(topic, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				//发送id
				TextMessage textMessage= session.createTextMessage(itemId+"");
				return textMessage;
			}
		});
		return TaotaoResult.ok();
	}

	@Override
	public Tbitemdesc getItemDescById(long itemId) {
		//从缓冲中取数据
		try {
			String json = jedisClient.get(ITEM_INFO + ":" + itemId + DESC);
			//判断不为null 并且不为""
			if(StringUtils.isNotBlank(json)){
				Tbitemdesc tbItemDesc = JsonUtils.jsonToPojo(json, Tbitemdesc.class);
				return tbItemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Tbitemdesc itemDesc = tbitemdescMapper.getItemDescById(itemId);
		try {
			//把数据库中的数据加入缓存
			jedisClient.set(ITEM_INFO+":"+itemId+DESC, JsonUtils.objectToJson(itemDesc));
			jedisClient.expire(ITEM_INFO+":"+itemId+DESC,Expiry_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemDesc;
	}

}
