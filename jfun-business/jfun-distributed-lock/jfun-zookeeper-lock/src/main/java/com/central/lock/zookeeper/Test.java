package com.central.lock.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.queue.DistributedQueue;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import sun.awt.SunHints;

import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.*;

/**
 * @author: miv
 * @Date: 2019-06-26 00:46
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws Exception {
        RetryPolicy r = new RetryOneTime(1000);
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(1000)
                .retryPolicy(r)
                .build();
        cf.start();
//        atomic(cf);
        cf.close();
    }

    static void barrier(CuratorFramework cf)throws Exception{
        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(cf,"/barrier",5);
        barrier.enter();
        barrier.leave();
    }
    static void atomic(CuratorFramework cf)throws Exception{
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(cf,"/at",new RetryNTimes(3,1000));
        AtomicValue<Integer> value = atomicInteger.get();

        atomicInteger.increment();


        System.out.println(value.succeeded());
        System.out.println(value.preValue());
        System.out.println(value.postValue());
    }
    static void lock(CuratorFramework cf) throws Exception {
        final InterProcessMutex lock = new InterProcessMutex(cf, "/lock");
        lock.acquire();
        lock.release();
    }

    static void watch(CuratorFramework cf) throws Exception {
        //nodecachelisener监听节点
        String nodepath = "/supser";
        //false 不接受数据变化
        PathChildrenCache cache = new PathChildrenCache(cf, nodepath, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("add:" + event.getData().getPath());
                        System.out.println("add:" + event.getData().getData());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("update:" + event.getData().getPath());
                        System.out.println("update:" + event.getData().getData());

                        break;
                    case CHILD_REMOVED:
                        System.out.println("remove:" + event.getData().getPath());
                        System.out.println("remove:" + event.getData().getData());
                        break;
                    default:
                        System.out.println(event.getType());
                }

            }
        });
        cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(nodepath + "/c", "haha".getBytes());
        Thread.sleep(1000);
        cf.setData().forPath(nodepath + "/c", "ok".getBytes());
        Thread.sleep(1000);
        cf.delete().deletingChildrenIfNeeded().forPath(nodepath + "/c");
        Thread.sleep(1000);
    }

    static void curd(CuratorFramework cf) throws Exception {

        String str = new String(cf.getData().forPath("/jfun/aa"));
        System.out.println(str);

        cf.setData().forPath("/jfun/aa", "ok".getBytes());
        String str1 = new String(cf.getData().forPath("/jfun/aa"));
        System.out.println(str1);

        cf.delete().forPath("/jfun/aa");
        cf.close();
    }
}
