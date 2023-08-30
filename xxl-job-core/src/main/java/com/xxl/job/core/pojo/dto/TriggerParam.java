package com.xxl.job.core.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 触发参数
 *
 * @author Rong.Jia
 * @date 2023/05/12
 */
@Data
public class TriggerParam implements Serializable{
    private static final long serialVersionUID = 42L;

    private Long jobId;
    private String jobName;
    private String executorHandler;
    private String executorParams;
    private String executorBlockStrategy;
    private int executorTimeout;

    private long logId;
    private long logDateTime;

    private String glueType;
    private String glueSource;
    private long glueUpdatedTime;

    private int broadcastIndex;
    private int broadcastTotal;

    /**
     * kettle kjb 文件
     */
    private byte[] kjb;

    /**
     * kettle ktr 文件
     */
    private List<byte[]> ktrs;

    /**
     * kettle 日志级别(NOTHING:没有日志,ERROR:错误日志,MINIMAL:最小日志,BASIC:基本日志,
     * DETAILED:详细日志,DEBUG:调试,ROWLEVEL:行级日志(非常详细))
     */
    private String kettleLogLevel;

}
