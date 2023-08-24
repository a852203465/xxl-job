package com.xxl.job.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxl.job.admin.common.pojo.entity.LogReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 任务日志报表 Mapper 接口
 * </p>
 *
 * @author Rong.Jia
 * @since 2023-05-13
 */
public interface LogReportMapper extends BaseMapper<LogReport> {

    /**
     * 根据出发时间查询日志报告
     *
     * @param triggerDayFrom 触发开始时间, 天
     * @param triggerDayTo   触发结束时间, 天
     * @return {@link List}<{@link LogReport}>
     */
    List<LogReport> queryLogReportByTriggerDay(@Param("triggerDayFrom") Long triggerDayFrom,
                                          @Param("triggerDayTo") Long triggerDayTo);

    /**
     * 查询所有
     *
     * @return {@link LogReport}
     */
    LogReport queryAll();

    /**
     * 查询日志报告根据一天
     *
     * @param day 一天
     * @return {@link LogReport}
     */
    LogReport queryLogReportByDay(@Param("day") Long day);












}
