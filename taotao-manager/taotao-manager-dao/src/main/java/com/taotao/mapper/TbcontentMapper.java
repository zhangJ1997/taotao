package com.taotao.mapper;

import com.taotao.pojo.Tbcontent;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbcontentMapper {
	/**
	 * 查询指定内容分类下的所有内容
	 * @param categoryId 内容分类ID
	 * @return 当前指定分类下的所有内容
	 */
    List<Tbcontent> findTbContentAll(long categoryId);
    /**
     * 添加一个cms内容到数据库
     * @param tbcontent 需要添加的内容对象
     */
    void addTbContent(Tbcontent tbContent);

}