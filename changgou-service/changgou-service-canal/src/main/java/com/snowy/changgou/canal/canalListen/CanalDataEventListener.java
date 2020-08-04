package com.snowy.changgou.canal.canalListen;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.DeleteListenPoint;
import com.xpand.starter.canal.annotation.InsertListenPoint;
import com.xpand.starter.canal.annotation.UpdateListenPoint;


/**
 * @author snowy
 * @date 2020/8/2 21:20
 */
@CanalEventListener
public class CanalDataEventListener {

    /*
     * @Description 添加监控
     * @Author: snowy
     * @Date: 2020/8/2 21:26
     * @Param:[entryType, rowData]
     * @Return: void
     **/
   /* @InsertListenPoint
    public void onEvenInsert(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        rowData.getAfterColumnsList().stream().forEach(c->{
            System.out.println("By--Annotation: " + c.getName() + " ::   " + c.getValue());
        });
    }*/
    /*
     * @Description 修改监控
     * @Author: snowy
     * @Date: 2020/8/2 21:32
     * @Param:[rowData]
     * @Return: void
     **/
   /* @UpdateListenPoint
    public void onEvenUpdate(CanalEntry.RowData rowData){
        System.out.println("修改监控");
        rowData.getAfterColumnsList().stream().map(c->c.getName()).forEach(System.out::print);
        System.out.println();
        rowData.getAfterColumnsList().stream().map(c->c.getValue()).forEach(System.out::print);
    }
*/
    /*
     * @Description 删除监控
     * @Author: snowy
     * @Date: 2020/8/2 21:33
     * @Param:[eventType, rowData]
     * @Return: void
     **/
    /*@DeleteListenPoint
    public void onEventDelete(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println("删除监控");
        rowData.getBeforeColumnsList().stream().map(c->c.getName()).forEach(System.out::print);
        System.out.println();
        rowData.getBeforeColumnsList().stream().map(c->c.getValue()).forEach(System.out::print);
    }*/
}
