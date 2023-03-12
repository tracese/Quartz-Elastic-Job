package com.trace;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.trace.job.MySimpleJob;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    /**
     * job configuration
     *
     * @return
     */
    public static LiteJobConfiguration configuration() {
        // job核心配置
        JobCoreConfiguration jcc = JobCoreConfiguration
                .newBuilder("mySimpleJob", "0/10 * * * * ?", 2)
                .build();

        // job类型配置
        JobTypeConfiguration jtc = new SimpleJobConfiguration(jcc, MySimpleJob.class.getCanonicalName());

        // job根的配置
        LiteJobConfiguration ljc = LiteJobConfiguration.newBuilder(jtc).build();
        return ljc;
    }

    private static String connectionString = "host102:2181,host103:2181,host104:2181";

    /**
     * zookeeper 注册中心
     * @return
     */
    public static CoordinatorRegistryCenter zkCenter() {
        ZookeeperConfiguration zc = new ZookeeperConfiguration(connectionString, "simple-job");
        CoordinatorRegistryCenter crc = new ZookeeperRegistryCenter(zc);
        crc.init();// 注册中心初始化
        return crc;
    }
}
