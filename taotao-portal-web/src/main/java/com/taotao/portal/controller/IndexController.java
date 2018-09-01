package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.Tbcontent;
import com.taotao.portal.pojo.Ad1Node;

@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;
	
	@Value("${AD1_CID}")
	private Long AD1_CID;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		List<Tbcontent> tbContent = contentService.getTbContent(AD1_CID);
		List<Ad1Node> ad1 = new ArrayList<Ad1Node>();
		for (Tbcontent tbcontent2 : tbContent) {
			Ad1Node node = new Ad1Node();
			node.setAlt(tbcontent2.getTitle());
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHT_B);
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_WIDTH_B);
			node.setHref(tbcontent2.getUrl());
			node.setSrc(tbcontent2.getPic());
			node.setSrcB(tbcontent2.getPic2());
			//添加到列表
			ad1.add(node);
		}
		model.addAttribute("ad1", JsonUtils.objectToJson(ad1));
		
		
		return "index";
	}
	
}
