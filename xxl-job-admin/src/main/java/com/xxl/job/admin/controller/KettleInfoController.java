package com.xxl.job.admin.controller;

import cn.hutool.extra.validation.ValidationUtil;
import com.xxl.job.admin.common.pojo.dto.KettleInfoDTO;
import com.xxl.job.admin.common.pojo.dto.KettleInfoFilterDTO;
import com.xxl.job.admin.common.pojo.vo.KettleInfoVO;
import com.xxl.job.admin.common.pojo.vo.PageVO;
import com.xxl.job.admin.service.KettleInfoService;
import com.xxl.job.core.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * kettle信息 前端控制器
 * </p>
 *
 * @author Rong.Jia
 * @since 2023-09-10
 */
@Slf4j
@Validated
@RestController
@Api(tags = "kettle信息管理")
@RequestMapping("/kettle-info")
public class KettleInfoController extends AbstractController {

    @Autowired
    private KettleInfoService kettleInfoService;

    @ApiOperation("添加-版本升级kettle信息")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> saveKettleInfo(@Validated KettleInfoDTO kettleInfoDTO) {
        log.info("saveKettleInfo {}", kettleInfoDTO.toString());
        kettleInfoDTO.setCreatedUser(getAccount());
        kettleInfoService.saveKettleInfo(kettleInfoDTO);
        return ResponseVO.success();
    }

    @ApiOperation("查询kettle信息")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<KettleInfoVO>> queryKettleInfo(@Validated KettleInfoFilterDTO filterDTO) {
        log.info("queryKettleInfo {}", filterDTO.toString());
        ValidationUtil.validate(filterDTO);
        return ResponseVO.success(kettleInfoService.page(filterDTO));
    }

    @ApiOperation("删除kettle信息")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataTypeClass = Long.class, value = "kettle信息id", required = true),
    })
    public ResponseVO<Void> deleteKettleInfo(@PathVariable("id") @NotNull(message = "kettle信息ID不能为空") Long id) {
        log.info("deleteKettleInfo {}", id);
        kettleInfoService.delete(id);
        return ResponseVO.success();
    }

    @ApiOperation("批量删除kettle信息")
    @DeleteMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> deleteKettleInfos(@RequestBody @Validated List<Long> ids) {
        log.info("deleteKettleInfos {}", ids);
        kettleInfoService.delete(ids);
        return ResponseVO.success();
    }

    @ApiOperation("根据ID查询kettle信息")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataTypeClass = Long.class, value = "kettle信息id", required = true),
    })
    public ResponseVO<KettleInfoVO> findKettleInfoById(@PathVariable("id") @NotNull(message = "kettle信息ID不能为空") Long id) {
        log.info("findKettleInfoById {}", id);
        return ResponseVO.success(kettleInfoService.queryById(id));
    }

    @ApiOperation("下载文件")
    @GetMapping(value = "/download/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataTypeClass = Long.class, value = "主键ID", required = true),
    })
    public void download(@PathVariable("id") @NotNull(message = "主键ID 不能为空") Long id, HttpServletRequest request, HttpServletResponse response) {
        log.info("download {}", id);
        kettleInfoService.download(id, request, response);
    }

    @ApiOperation("根据ID查询更高版本的模型")
    @GetMapping(value = "/advanced-version/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataTypeClass = Long.class, value = "主键ID", required = true),
    })
    public ResponseVO<List<KettleInfoVO>> findKettleAdvancedVersionById(@PathVariable("id") @NotNull(message = "主键ID 不能为空") Long id) {
        log.info("findKettleAdvancedVersionById {}", id);
        return ResponseVO.success(kettleInfoService.findKettleAdvancedVersionById(id));
    }










}
