package com.taotao.item.pojo;

import com.taotao.pojo.Tbitem;

import java.io.Serializable;

public class Item extends Tbitem implements Serializable{
    //外界查询数据库把数据库中的数据装载到TbItem对象里 我们要把对象的属性赋值到Item属性里
    public Item(Tbitem tbItem){

        this.setBarcode(tbItem.getBarcode());
        this.setCid(tbItem.getCid());
        this.setCreated(tbItem.getCreated());
        this.setId(tbItem.getId());
        this.setImage(tbItem.getImage());
        this.setNum(tbItem.getNum());
        this.setPrice(tbItem.getPrice());
        this.setSellPoint(tbItem.getSellPoint());
        this.setStatus(tbItem.getStatus());
        this.setTitle(tbItem.getTitle());
        this.setUpdated(tbItem.getUpdated());
    }

    public String[] getImages() {
        String image2 = this.getImage();
        if (image2 != null && !"".equals(image2)) {
            String[] strings = image2.split(",");
            return strings;
        }
        return null;
    }

}
