package com.snowy.changgou.canal.canalListen;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.snowy.changgou.content.entity.Content;
import com.snowy.changgou.content.feign.ContentFeign;
import com.snowy.changgou.content.tool.Result;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author snowy
 * @date 2020/8/3 21:40
 */
@CanalEventListener
public class ContentDataListen {
    @Autowired
    private ContentFeign contentFeign;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*
     * @Description 监听广告数据库
     * @Author: snowy
     * @Date: 2020/8/3 21:46
     * @Param:[entryType, rowData]
     * @Return: void
     **/
    @ListenPoint(
            destination = "example",
            schema = "changgou_content",
            table = {"tb_content_category","tb_content"},
            eventType = {
            CanalEntry.EventType.UPDATE,
            CanalEntry.EventType.DELETE,
            CanalEntry.EventType.INSERT
            }
    )
    public void onEventCustomUpdate(CanalEntry.EventType  entryType,CanalEntry.RowData rowData){
        String columnID = getColumnValue(entryType, rowData);
        System.out.println(columnID);
        List<Content> content = contentFeign.findByCategory(Long.parseLong(columnID));
        stringRedisTemplate.boundValueOps("content::"+columnID).set(JSONObject.toJSONString(content));
    }

    private String getColumnValue(CanalEntry.EventType eventType, CanalEntry.RowData rowData){
        String categoryId = "";
        //判断 如果是删除  则获取beforlist
        if (eventType == CanalEntry.EventType.DELETE){
            List<CanalEntry.Column> category = rowData.getBeforeColumnsList().stream()
                    .filter(f -> "category_id".equalsIgnoreCase(f.getName()))
                    .collect(Collectors.toList());
            for (CanalEntry.Column column : category){
                categoryId = column.getValue();
                return categoryId;
            }
        }else {
            List<CanalEntry.Column> category = rowData.getAfterColumnsList().stream()
                    .filter(f -> f.getName().equalsIgnoreCase("category_id"))
                    .collect(Collectors.toList());
            for (CanalEntry.Column column : category){
                categoryId = column.getValue();
                return categoryId;
            }
        }
        return categoryId;
    }
}
