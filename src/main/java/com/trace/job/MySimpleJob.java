package com.trace.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author szq
 * @create 2023-03-12 7:08
 * @description
 */
public class MySimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("我是分片项：" + shardingContext.getShardingItem() +
                ",总分片项：" + shardingContext.getShardingTotalCount());
    }
}
