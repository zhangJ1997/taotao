package com.taotao.search.listener;

import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ItemChangeListener implements MessageListener{

    @Autowired
    private SearchItemService searchItemService;

    @Override
    public void onMessage(Message message) {
        /**
         * 根据传过来的id查询数据库 得到商品信息
         * 调用sorlj的代码存入索引库中
         */
        if (message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            try {
               String  itemId= textMessage.getText();
               searchItemService.addDocument(Long.valueOf(itemId));
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }

    }
}
