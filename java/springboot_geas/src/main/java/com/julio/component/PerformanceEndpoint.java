package com.julio.component;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.lang.management.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Julio
 * @date 2020/9/15-10:22
 * @EndPoint中的id不能使用驼峰法，需要以-分割。
 * @Spring Boot会去扫描@EndPoint注解下的@ReadOperation, @WriteOperation, @DeleteOperation注解，
 * 分别对应生成Get/Post/Delete的Mapping。注解中有个produces参数，可以指定media type, 如：application/json等。
 **/
@Component
@Endpoint(id = "performancePoint")
public class PerformanceEndpoint{

    @ReadOperation
    public Map<String,Object> performanceInfo(){
        HashMap<String, Object> map = new HashMap<>();
        MemoryMXBean memoryMBean = ManagementFactory.getMemoryMXBean(); //获取jvm内存对象
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean(); //获取线程对象
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans(); //GC器

        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) { //顺序是先PS Scavenge 后 PS MarkSweep
            map.put(garbageCollectorMXBean.getName()+"Count",garbageCollectorMXBean.getCollectionCount());
            map.put(garbageCollectorMXBean.getName()+"Time",garbageCollectorMXBean.getCollectionTime());
        }



        MemoryUsage heapMemoryUsage = memoryMBean.getHeapMemoryUsage();
        long initheap = heapMemoryUsage.getInit()/1024/1024;
        long commitheap = heapMemoryUsage.getCommitted()/1024/1024;
        long maxheap = heapMemoryUsage.getMax()/1024/1024;
        long usedheap = heapMemoryUsage.getUsed()/1024/1024;

        int threadCount = threadMXBean.getThreadCount(); //线程总数
        int daemonThreadCount = threadMXBean.getDaemonThreadCount();//守护线程数
        int peakThreadCount = threadMXBean.getPeakThreadCount();//峰值线程数
        long currentThreadCpuTime = threadMXBean.getCurrentThreadCpuTime();
        long currentThreadUserTime = threadMXBean.getCurrentThreadUserTime();


        map.put("initHeapMem",initheap);
        map.put("commitHeapMem",commitheap);
        map.put("maxHeapMem",maxheap);
        map.put("usedHeapMem",usedheap);

        map.put("threadCount",threadCount);
        map.put("daemonThread",daemonThreadCount);
        map.put("peakThread",peakThreadCount);
        map.put("currentThreadCpuTime",currentThreadCpuTime);
        map.put("currentThreadUserTime",currentThreadUserTime);
        return map;
    }

}
