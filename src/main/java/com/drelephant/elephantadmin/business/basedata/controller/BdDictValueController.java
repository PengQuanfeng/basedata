package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdDictValue;
import com.drelephant.elephantadmin.business.basedata.service.BdDictValueService;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典表 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "数据字典表")
@RestController
@RequestMapping("bdDictValue")
public class BdDictValueController extends BaseController {

    @Autowired
    private BdDictValueService bdDictValueService;

    @ApiOperation("根据typeCode查询数据字典列表")
    @GetMapping("/selectValue")
    public R selectValue(@ApiParam("数据对象typeCode") String typeCode) {
        if (StringUtils.isBlank(typeCode)) {
            return R.error("参数为空");
        }
        List<BdDictValue> list = bdDictValueService.listByTypeCode(typeCode);
        return R.ok().put("list", list);
    }

    /**
     * api- selectByTypeCode
     *
     * @param typeCode typeCode
     * @return list
     */
    @GetMapping("/selectByTypeCodeApi")
    public List<BdDictValue> listByTypeCodeApi(@ApiParam("typeCode") String typeCode) {
        if (StringUtils.isBlank(typeCode)) {
            return Collections.emptyList();
        }
        return bdDictValueService.listByTypeCode(typeCode);
    }

    /**
     * api- selectByCode
     *
     * @param code code
     * @return BdDictValue or null
     */
    @GetMapping("/selectByCodeApi")
    public BdDictValue selectByCodeApi(@ApiParam("code") String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        //noinspection unchecked
        return bdDictValueService.selectOne(Condition.create().eq("code", code).eq("status", "ACT"));
    }

    /**
     * api- listByParentCode
     *
     * @param parentCode parentCode
     * @return List
     */
    @GetMapping("/listByParentCodeApi")
    public List<BdDictValue> listByParentCodeApi(@ApiParam("parentCode") String parentCode) {
        if (StringUtils.isBlank(parentCode)) {
            return Collections.emptyList();
        }
        final Wrapper wrapper = Condition.create().eq("parentCode", parentCode).eq("status", "ACT");
        return bdDictValueService.selectList(wrapper);
    }

    /**
     * api- codeStr
     * get请求, 注意数据长度.
     *
     * @param codeStr like 1,2,3
     * @return map ,like {a:哎,b:比}
     */
    @GetMapping("/mapCodeNameByCodesApi")
    public Map<String, String> mapCodeNameByCodesApi(String codeStr) {
        if (StringUtils.isBlank(codeStr)) {
            return Collections.emptyMap();
        }
        //noinspection unchecked
        List<BdDictValue> list = bdDictValueService.selectList(
                Condition.create().eq("status", "ACT").in("code", codeStr).setSqlSelect("code,name"));
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        final Map<String, String> codeNameMap = new HashMap<>(list.size());
        list.forEach(v -> codeNameMap.put(v.getCode(), v.getName()));
        return codeNameMap;
    }

}
