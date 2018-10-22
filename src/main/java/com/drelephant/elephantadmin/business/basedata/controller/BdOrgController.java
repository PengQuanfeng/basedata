package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.dto.reqeuest.DbOrgRequest;
import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.drelephant.elephantadmin.business.basedata.service.BdOrgService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "组织机构")
@RestController
@RequestMapping("bdOrg")
public class BdOrgController extends BaseController {
    @Autowired
    private BdOrgService bdOrgService;

	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "公司名称", required = true)		
	})
    @ApiOperation("增加公司")
    @PostMapping("/addCompay")
    public R addCompay(@RequestBody @ApiParam("数据对象") BdOrg entity ){   	
    	if(entity == null){
    		return R.error("增加公司失败，参数无效!");
		}
    	//
    	return bdOrgService.addCompany(entity);
    }
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "公司名称", required = true),
		@ApiImplicitParam(name = "code", value = "公司编号", required = true)
	})
    @ApiOperation("编辑公司")
    @PostMapping("/editCompany")
    public R editCompany(@RequestBody @ApiParam("数据对象")BdOrg data){
        return bdOrgService.updateCompany(data);
    }
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "公司编号", required = true)
	})
    @ApiOperation("删除公司")
    @PostMapping("/deleteCompany")
    public R deleteCompany(@RequestBody @ApiParam("数据对象")BdOrg data){
        return bdOrgService.deleteCompany(data.getCode());
    }

    @ApiOperation("查询机构")
    @GetMapping("/gerOrgList")
    public R getOrgList(@ApiParam("机构查询对象") DbOrgRequest request){
        return bdOrgService.getOrgList(request);
    }
	
    @ApiOperation("获取公司信息的list")
    @GetMapping("/getCompanyList")
    public R getCompanyList(){
        return bdOrgService.selectCompay();
    }
    
    @ApiOperation("新增医院信息")
    @PostMapping("/saveHospital")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "医院编码", required = true),
		@ApiImplicitParam(name = "provinceCode", value = "省编码", required = true),
		@ApiImplicitParam(name = "cityCode", value = "城市编码", required = true),
		@ApiImplicitParam(name = "name", value = "医院名称", required = true),
		@ApiImplicitParam(name = "hospitalLevel", value = "医院等级编码", required = true),
		@ApiImplicitParam(name = "status", value = "状态", required = true)
	})
    public R saveHospital(@RequestBody @ApiParam("数据对象")BdOrg data){
    	if(data==null){
    		return R.error().put("msg", "参数为空");
    	}
		return bdOrgService.addHospital(data);   	
    }
    @ApiOperation("获取医院信息的list")
    @GetMapping("/getListHospital")
    public R getListHospital(@ApiParam("当前页")String current,@ApiParam("分页大小")String pageSize,@ApiParam("医院编码")String code,
    		@ApiParam("省编码")String provinceCode,@ApiParam("市编码")String cityCode,
    		@ApiParam("医院名称")String name,@ApiParam("医院等级")String hospitalLevel,@ApiParam("状态")String status){
    	int offset = 1;
		int limit = 1000; 
		if (StringUtils.isNotBlank(current)) {
			// 当前记录数
			offset = Integer.parseInt(current);
		}
		if (StringUtils.isNotBlank(pageSize)) {
			// 每页限制数
			limit = Integer.parseInt(pageSize);
		}
    	Page<BdOrg> page=new Page<>(offset,limit);
    	bdOrgService.getListBdOrg(page,code,provinceCode,cityCode,name,hospitalLevel,status);
        return R.ok().put("list", page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("更新医院状态")
    @PostMapping("/updateHosStatus")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "医院编码", required = true),
		@ApiImplicitParam(name = "provinceCode", value = "省编码", required = true),
		@ApiImplicitParam(name = "cityCode", value = "城市编码", required = true),
		@ApiImplicitParam(name = "name", value = "医院名称", required = true),
		@ApiImplicitParam(name = "hospitalLevel", value = "医院等级编码", required = true),
		@ApiImplicitParam(name = "status", value = "状态", required = true)
	})
    public R updateHosStatus(@RequestBody @ApiParam("数据对象") BdOrg data){ 
    	if(data==null){
    		return R.error().put("msg", "参数为空");
    	}
		return bdOrgService.updateOneHosStatus(data);    	
    }
    @ApiOperation("单条删除医院信息/区分聚合调用")
    @PostMapping("/deleteOneHosStatus")
    public R deleteOneHosStatus(@RequestBody Map<String, String> map){
    	String code = map.get("code");
    	if(StringUtils.isBlank(code)){
			return R.error().put("msg", "医院code不能为空");
		}
        return bdOrgService.deleteOneHosStatus(code);
    }
    @ApiOperation("单条删除医院信息/前端调用")
    @PostMapping("/deleteOne")
    public R deleteOne(@ApiParam(name="code",value="医院编码",required=true)String code){   	
    	if(StringUtils.isBlank(code)){
			return R.error().put("msg", "医院code不能为空");
		}
        return bdOrgService.deleteOneHosStatus(code);
    }
    @ApiOperation("批量更新医院状态")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "codes", value = "医院编码使用,号拼接", required = true),
		@ApiImplicitParam(name = "status", value = "状态", required = true)
	})
    @PostMapping("/editBatchHosStatus")
    public R editBatchHosStatus(@RequestBody Map<String,String> codes ){
    	String code=codes.get("codes");
    	String status=codes.get("status");
    	if(StringUtils.isNotBlank(status)){
    		if(status.equals(Constans.ACTIVE)){
    			status=Constans.ACTIVE;
    		}else{
    			status=Constans.INVALID;
    		}
    	}
        return bdOrgService.editBatchHosStatus(status,code);
    }
//    @ApiOperation("获取省的列表")
//    @GetMapping("/getListProvince")
//    public R getListProvince(){
//    	
//        return bdOrgService.getProvinceList();
//    }
//    @ApiOperation("获取城市列表")
//    @ApiImplicitParam(name = "provinceCode", value = "省编码", required = true)
//    @GetMapping("/getListCity")
//    public R getListCity(@ApiParam("省编码") String provinceCode){
//        return bdOrgService.getCityList(provinceCode);
//    }
    
    private void addItem(List<Map<String, String>> list, String code, String name) {
    	Map<String, String> item = new HashMap<String, String>();
    	item.put("code", code);
    	item.put("name", name);
    	list.add(item);
    }
    
    @ApiOperation("获取等级列表")
    @GetMapping("/getListLevel")
    public R getListLevel(){
    	
		List<Map<String, String>> list=new ArrayList<Map<String, String>>();
		addItem(list, Constans.HOSPITALLEVEL_A, Constans.HOSPITALLEVEL_A_NMAE);
		addItem(list, Constans.HOSPITALLEVEL_B, Constans.HOSPITALLEVEL_B_NMAE);
		addItem(list, Constans.HOSPITALLEVEL_C, Constans.HOSPITALLEVEL_C_NMAE);
		addItem(list, Constans.HOSPITALLEVEL_D, Constans.HOSPITALLEVEL_D_NMAE);
        return R.ok().put("list", list);
    }
    
    @ApiOperation("获取状态列表")
    @GetMapping("/getListStatus")
    public R getListStatus(){
    	List<Map<String, String>> list=new ArrayList<Map<String, String>>();
		addItem(list, Constans.ACTIVE,"启用");
		addItem(list, Constans.INVALID, "禁用");
    	return R.ok().put("list", list);
    }

    @ApiOperation("获取机构")
	@GetMapping("/getAllOrgOption")
	public R getAllOrgOption(){
		return bdOrgService.getAllOrgOption();
	}
    
}
