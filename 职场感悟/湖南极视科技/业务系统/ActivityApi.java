package com.ieyecloud.mgmt.web.api;

import com.ieyecloud.mgmt.domain.activity.PromotionActivity;
import com.ieyecloud.mgmt.domain.site.SiteActivity;
import com.ieyecloud.mgmt.domain.site.SiteInfo;
import com.ieyecloud.mgmt.rpc.adapter.PromotionActivityMapper;
import com.ieyecloud.mgmt.rpc.adapter.PromotionActivityVoMapper;
import com.ieyecloud.mgmt.service.activity.PromotionActivityService;
import com.ieyecloud.mgmt.service.site.SiteActivityService;
import com.ieyecloud.mgmt.service.site.SiteInfoService;
import com.ieyecloud.rpc.base.dto.QueryResultDto;
import com.ieyecloud.rpc.base.exception.OperateFailedException;
import com.ieyecloud.rpc.eyebiz.dto.PromotionActivityDto;
import com.ieyecloud.rpc.eyebiz.dto.PromotionActivityVoDto;
import com.ieyecloud.utils.DateTimeUtil;
import com.ieyecloud.utils.QueryResult;
import com.ieyecloud.utils.mybatis.MyBatisUtil;
import com.ieyecloud.utils.mybatis.typehandlers.JsonbTypeHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Column;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dev-lixy on 2018/4/15.
 */
@RestController
@RequestMapping("/activtiy")
public class ActivityApi extends BaseApi{
    @Autowired
    private PromotionActivityService promotionActivityService;

    @Autowired
    private PromotionActivityMapper promotionActivityMapper;

    @Autowired
    private SiteInfoService siteInfoService;

    @Autowired
    private PromotionActivityVoMapper promotionActivityVoMapper;

    @Autowired
    private SiteActivityService siteActivityService;

    @PostMapping("/create")
    public PromotionActivityDto create(@ApiParam(required = true,value = "新增推广活动配置参数：json") @RequestBody
                                                   PromotionActivity promotionActivity) {
        promotionActivity.setCreatedAt(new Date());
        return promotionActivityMapper.toDto(promotionActivityService.createOrigin(promotionActivity));
    }

    @PostMapping("/update/{id}")
    public PromotionActivityDto update(@ApiParam(required = false,value = "修改推广活动配置参数：json") @RequestBody PromotionActivity promotionActivity,@ApiParam(required = true,value = "推广活动id")@PathVariable(value = "id")  Long id) {
        promotionActivity.setUpdatedAt(new Date());
        return promotionActivityMapper.toDto(promotionActivityService.updateSelectiveOrigin(promotionActivity));
    }

    @GetMapping("/get/{id}")
    public  PromotionActivityDto getById(@ApiParam(required = true,value = "推广活动id")@PathVariable(value = "id") Long id) throws OperateFailedException {
        return promotionActivityMapper.toDto(promotionActivityService.selectByPkOrigin(id)) ;
    }
    @GetMapping("/paginateBy")
    public QueryResultDto<PromotionActivityVoDto> paginateBy(	@ApiParam(required = true,value = "站点id") @RequestParam("siteId")Integer siteId,
                                                                 @ApiParam(required = false,value = "活动名字") @RequestParam("name")String name,
                                                                 @ApiParam(required = false,value = "活动状态") @RequestParam("status") String status,
                                                                 @ApiParam(required = false,value = "第几页，可为空，默认第1页") @RequestParam("page") Integer page,
                                                                 @ApiParam(required = false,value = "每页展示的行数，可为空，默认10行") @RequestParam("rows") Integer rows) {
        Example example = new Example(PromotionActivity.class);
        Example.Criteria criteria = example.createCriteria();
        if(siteId != null){
            List<Integer> siteIds = siteInfoService.findSubsIdByParentId(siteId);
            criteria.andIn("activitySiteId", siteIds);
        }
        if (name != null && !"".equals(name)) {
            String nameStr = MyBatisUtil.matchAnywhere(name);
            criteria.andLike("name", nameStr);
        }
        if (status != null && !"".equals(status)) {
            criteria.andEqualTo("status", status);
        }
        int p = page==null?1:page;
        int r = rows==null?10:rows;
        QueryResult<PromotionActivity> promotionActivityQueryResult = promotionActivityService.paginateByExampleOrigin(example, p, r);
        return new QueryResultDto<>(promotionActivityQueryResult.totalRecords, promotionActivityVoMapper.toDtos(promotionActivityQueryResult.data));
    }

    @GetMapping("/siteId/{siteId}")
    @ApiOperation(value = "无分页,有效的活动")
    public QueryResultDto<PromotionActivityDto> queryBySiteId(@ApiParam(required = true,value = "站点id") @PathVariable("siteId")Integer siteId){
        List<Integer> siteIds = siteInfoService.findParentsBySiteId(siteId).stream().map(SiteInfo::getId).collect(Collectors.toList());
        List<PromotionActivity> list = new ArrayList<>();
        for (int i = siteIds.size() - 1; i >= 0; i--) {
            if (list != null && list.size() != 0) {
                break;
            }
            Example example = new Example(PromotionActivity.class);
            example.createCriteria().andEqualTo("activitySiteId", siteIds.get(i)).andEqualTo("status","enable");
            list = promotionActivityService.selectByExampleOrigin(example);
        }
        List<PromotionActivityDto> promotionActivityDtos = promotionActivityMapper.toDtos(list);
        return  new QueryResultDto<>(promotionActivityDtos.size(), promotionActivityDtos);
    }


    @GetMapping("/siteId-v2/{siteId}")
    @ApiOperation(value = "无分页,站点下所有活动，根据参数来")
    public QueryResultDto<SiteActivityVo> queryBySiteId(@ApiParam(required = true,value = "站点id") @PathVariable("siteId")Integer siteId,
                                                        @ApiParam(required = false,value = "活动状态") @RequestParam("status")String status,
                                                        @ApiParam(required = false,value = "可用状态") @RequestParam("available")String available,
                                                        @ApiParam(required = false,value = "第几页，可为空，默认第1页") @RequestParam("page") Integer page,
                                                        @ApiParam(required = false,value = "每页展示的行数，可为空，默认10行") @RequestParam("rows") Integer rows
    ){
        List<Integer> siteIds = siteInfoService.findParentsBySiteId(siteId).stream().map(SiteInfo::getId).collect(Collectors.toList());//只查了上一级
        List<PromotionActivity> list = new ArrayList<>();
        int p = page==null?1:page;
        int r = rows==null?10:rows;
        QueryResult<PromotionActivity> promotionActivityQueryResult =null;
        for (int i = siteIds.size() - 1; i >= 0; i--) {
            if (list != null && list.size() != 0) {
                break;
            }
            Example example = new Example(PromotionActivity.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("activitySiteId", siteIds.get(i));
            if (status != null && !"".equals(status)) {//转换成时间的处理方式
                Date date = DateTimeUtil.today();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
                String strDate = sdf.format(date);
                if(status.equalsIgnoreCase("enable")){//有效的
                    criteria.andLessThanOrEqualTo("startTime",strDate);
                    criteria.andGreaterThanOrEqualTo("endTime",strDate);
                    Example example2 = new Example(PromotionActivity.class);
                    Example.Criteria criteria2 = example2.createCriteria();
                    criteria2.andEqualTo("activitySiteId", siteIds.get(i));
                    criteria2.andIsNull("startTime");
                    criteria2.andIsNull("endTime");
                    example.or(criteria2);
                }else{
                    Example example2 = new Example(PromotionActivity.class);
                    Example.Criteria criteria2 = example2.createCriteria();

                    Example.Criteria startTime = criteria.andGreaterThan("startTime", strDate);
                    criteria2.andEqualTo("activitySiteId", siteIds.get(i));
                    Example.Criteria endTime = criteria2.andLessThan("endTime", strDate);
                    example.or(criteria2);

                }
                //criteria.andEqualTo("status", status);
            }
            if(StringUtils.isNotEmpty(available)&&("n".equalsIgnoreCase(available)||"y".equalsIgnoreCase(available))){//1，有效1一部分来源于主表，一部分来源于从表；无效n全部来源于从表
                p= 1;
                r = 100000;//重新附一个比较大的值,利于从表分页
            }
            promotionActivityQueryResult = promotionActivityService.paginateByExampleOrigin(example, p, r);
            list = promotionActivityQueryResult.data;
        }
        //1,没传status和available,查询所有活动
        List<SiteActivityVo> collect=null;
        if(StringUtils.isEmpty(status)&&StringUtils.isEmpty(available)){
            collect = list.stream().map(PromotionActivity -> dealSiteActivty(siteId,status,available,page,rows,PromotionActivity)).collect(Collectors.toList());
        }
        //2,有传status,没有available
        if(StringUtils.isNotEmpty(status)&&StringUtils.isEmpty(available)){
            collect = list.stream().map(PromotionActivity -> dealSiteActivty(siteId,status,available,page,rows,PromotionActivity)).collect(Collectors.toList());
        }

        //3,没传status，有传available=y，分页主表可以做，因为默认关系
        if(StringUtils.isEmpty(status)&&StringUtils.isNotEmpty(available)&&"y".equalsIgnoreCase(available)){//有传available,查询站点下的活动,利于站点表分页
            collect = list.stream().map(PromotionActivity -> dealSiteActivtyExclude(siteId,status,available,page,rows,PromotionActivity)).filter(Objects::nonNull).filter(item -> item.getSiteActivity().getAvailable()!="n").collect(Collectors.toList());
            //内存做分页
            List<SiteActivityVo> fenye = fenye(collect, rows, page);
            return new QueryResultDto<>(collect.size(), fenye);
        }
        //4,没传status，有传available=n
        if(StringUtils.isEmpty(status)&&StringUtils.isNotEmpty(available)&&"n".equalsIgnoreCase(available)){//从表站点表分页
            Example example = new Example(SiteActivity.class);
            Example.Criteria criteria =example.createCriteria();
            criteria.andEqualTo("activitySiteId", siteId);
            criteria.andEqualTo("available", available);
            QueryResult<SiteActivity> siteActivityQueryResult = siteActivityService.paginateByExample(example, page, rows);
            if(siteActivityQueryResult.data!=null&&siteActivityQueryResult.data.size()>0){
                collect = siteActivityQueryResult.data.stream().map(SiteActivity -> dealSiteActivtyPageBy(siteId, status, available, page, rows, SiteActivity)).collect(Collectors.toList());
                return new QueryResultDto<>(siteActivityQueryResult.totalRecords, collect);
            }
        }
       // 5,同时传status和available=y,//
        if(StringUtils.isNotEmpty(status)&&StringUtils.isNotEmpty(available)&&"y".equalsIgnoreCase(available)){
            collect = list.stream().map(PromotionActivity -> dealSiteActivtyExclude(siteId,status,available,page,rows,PromotionActivity)).filter(Objects::nonNull).filter(item -> item.getSiteActivity().getAvailable()!="n").collect(Collectors.toList());
            //内存做分页
            List<SiteActivityVo> fenye = fenye(collect, rows, page);
            return new QueryResultDto<>(collect.size(), fenye);
        }

        //6,同时传status和available=n,
        if(StringUtils.isNotEmpty(status)&&StringUtils.isNotEmpty(available)&&"n".equalsIgnoreCase(available)){
            collect = list.stream().map(PromotionActivity -> dealSiteActivtyExclude(siteId,status,available,page,rows,PromotionActivity)).filter(Objects::nonNull).filter(item -> item.getSiteActivity().getAvailable()!="y").collect(Collectors.toList());
            //内存做分页
            List<SiteActivityVo> fenye = fenye(collect, rows, page);
            return new QueryResultDto<>(collect.size(), fenye);
        }
        //collect = list.stream().map(PromotionActivity -> dealSiteActivty(siteId,available,PromotionActivity)).filter(Objects::nonNull).collect(Collectors.toList());
        //构造出一个siteActivity对象
       // List<PromotionActivityDto> promotionActivityDtos = promotionActivityMapper.toDtos(list);
        //return  new QueryResultDto<>(promotionActivityDtos.size(), promotionActivityDtos);
        return  new QueryResultDto<>(promotionActivityQueryResult.totalRecords, collect);
    }

    private <R> SiteActivityVo dealSiteActivtyPageBy(Integer siteId, String status, String available, Integer page, Integer rows, SiteActivity siteActivity) {
        //4,有传available=n,分页在从表做
        Example example = new Example(PromotionActivity.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("id",siteActivity.getPromotionActivityId() );//promotionActivityId
        List<PromotionActivity> promotionActivities = promotionActivityService.selectByExampleOrigin(example);//实际上只会存在一条，因为要处理一下status
        SiteActivityVo siteActivityVo=new SiteActivityVo(siteActivity);
        promotionActivities.forEach(promotionActivity -> {
                    siteActivityVo.setId(promotionActivity.getId());
                    siteActivityVo.setName(promotionActivity.getName());
                    siteActivityVo.setStatus(promotionActivity.getStatus());
                    siteActivityVo.setActivitySiteId(promotionActivity.getActivitySiteId());
                    siteActivityVo.setCreateSiteId(promotionActivity.getCreateSiteId());
                    siteActivityVo.setCreatedAt(promotionActivity.getCreatedAt());
                    siteActivityVo.setUpdatedAt(promotionActivity.getUpdatedAt());
                    siteActivityVo.setExtra(promotionActivity.getExtra());
                    siteActivityVo.setStartTime(promotionActivity.getStartTime());
                    siteActivityVo.setEndTime(promotionActivity.getEndTime());
                }
        );
        return siteActivityVo;
    }

    private <T> SiteActivityVo dealSiteActivtyExclude(Integer siteId,String status,String available,Integer page,Integer rows,PromotionActivity promotionActivity) {
        Example example = new Example(SiteActivity.class);
        Example.Criteria criteria =example.createCriteria();
        //3,有传available=y,分页在主从表都不好做
        criteria.andEqualTo("promotionActivityId", promotionActivity.getId());
//        if(StringUtils.isNotEmpty(available)){
//            criteria.andEqualTo("available", available);
//        }
        criteria.andEqualTo("activitySiteId",siteId);//
        SiteActivity siteActivity=siteActivityService.selectOneByExample(example);;
        SiteActivityVo siteActivityVo=null;
        //3. 只传available,查询站点下的活动,available=y，
        if(StringUtils.isEmpty(status)&&StringUtils.isNotEmpty(available)){
            if(null==siteActivity&&"y".equalsIgnoreCase(available)){//默认可用,初始查询创建中心下的活动
                SiteActivity siteActivity2=new SiteActivity();
                siteActivity2.setPromotionActivityId(promotionActivity.getId());
                siteActivity2.setAvailable("y");
                siteActivity2.setActivitySiteId(siteId);
                siteActivityVo=new SiteActivityVo(siteActivity2);
            }else if(null==siteActivity&&"n".equalsIgnoreCase(available)){//为n，肯定存在于t_site_activty
                return null;
            }else{
                siteActivityVo=new SiteActivityVo(siteActivity);
            }
        }

        //6. 包括available,查询站点下的活动,available=y，
        if(StringUtils.isNotEmpty(status)&&StringUtils.isNotEmpty(available)){
            if(null==siteActivity&&"y".equalsIgnoreCase(available)){//默认可用,初始查询创建中心下的活动
                SiteActivity siteActivity2=new SiteActivity();
                siteActivity2.setPromotionActivityId(promotionActivity.getId());
                siteActivity2.setAvailable("y");
                siteActivity2.setActivitySiteId(siteId);
                siteActivityVo=new SiteActivityVo(siteActivity2);
            }else if(null==siteActivity&&"n".equalsIgnoreCase(available)){//为n，肯定存在于t_site_activty
                return null;
            }else{
                siteActivityVo=new SiteActivityVo(siteActivity);
            }
        }
        siteActivityVo.setId(promotionActivity.getId());
        siteActivityVo.setName(promotionActivity.getName());
        siteActivityVo.setStatus(promotionActivity.getStatus());
        siteActivityVo.setActivitySiteId(promotionActivity.getActivitySiteId());
        siteActivityVo.setCreateSiteId(promotionActivity.getCreateSiteId());
        siteActivityVo.setCreatedAt(promotionActivity.getCreatedAt());
        siteActivityVo.setUpdatedAt(promotionActivity.getUpdatedAt());
        siteActivityVo.setExtra(promotionActivity.getExtra());
        siteActivityVo.setStartTime(promotionActivity.getStartTime());
        siteActivityVo.setEndTime(promotionActivity.getEndTime());
        return siteActivityVo;
    }

    private <T> SiteActivityVo dealSiteActivty(Integer siteId,String status,String available,Integer page,Integer rows,PromotionActivity promotionActivity) {
        Example example = new Example(SiteActivity.class);
        Example.Criteria criteria =example.createCriteria();
        //4,有传available
        criteria.andEqualTo("promotionActivityId", promotionActivity.getId());
        criteria.andEqualTo("activitySiteId",siteId);//
        if(StringUtils.isNotEmpty(available)){
            criteria.andEqualTo("available", available);
        }
        SiteActivity siteActivity=siteActivityService.selectOneByExample(example);;
        SiteActivityVo siteActivityVo=null;
        //1. 没传status和available,查询所有活动
        if(StringUtils.isEmpty(status)&&StringUtils.isEmpty(available)){
            if(null==siteActivity){//初始查询创建中心下的活动
                SiteActivity siteActivity2=new SiteActivity();
                siteActivity2.setPromotionActivityId(promotionActivity.getId());
                siteActivity2.setAvailable("y");
                siteActivity2.setActivitySiteId(siteId);
                siteActivityVo=new SiteActivityVo(siteActivity2);
            }else{
                siteActivityVo=new SiteActivityVo(siteActivity);
            }
        }
        //2. 只传status,查询创建中心下的活动
        if(StringUtils.isNotEmpty(status)&&StringUtils.isEmpty(available)){
            if(null==siteActivity){//初始查询创建中心下的活动
                SiteActivity siteActivity2=new SiteActivity();
                siteActivity2.setPromotionActivityId(promotionActivity.getId());
                siteActivity2.setAvailable("y");
                siteActivity2.setActivitySiteId(siteId);
                siteActivityVo=new SiteActivityVo(siteActivity2);
            }else{
                siteActivityVo=new SiteActivityVo(siteActivity);
            }
        }

        siteActivityVo.setId(promotionActivity.getId());
        siteActivityVo.setName(promotionActivity.getName());
        siteActivityVo.setStatus(promotionActivity.getStatus());
        siteActivityVo.setActivitySiteId(promotionActivity.getActivitySiteId());
        siteActivityVo.setCreateSiteId(promotionActivity.getCreateSiteId());
        siteActivityVo.setCreatedAt(promotionActivity.getCreatedAt());
        siteActivityVo.setUpdatedAt(promotionActivity.getUpdatedAt());
        siteActivityVo.setExtra(promotionActivity.getExtra());
        siteActivityVo.setStartTime(promotionActivity.getStartTime());
        siteActivityVo.setEndTime(promotionActivity.getEndTime());
        return siteActivityVo;
    }

    public static  List  fenye(List list,int pagesize,int page) {
        int totalcount = list.size();
        int pagecount = 0;
        int m = totalcount % pagesize;
        if (m > 0) {
            pagecount = totalcount / pagesize + 1;
        } else {
            pagecount = totalcount / pagesize;
        }
        List subList =null;
        for (int i = 1; i <= pagecount; i++) {
            if (m == 0) {
                 subList = list.subList((i - 1) * pagesize, pagesize * (i));
            } else {
                if (i == pagecount) {
                     subList = list.subList((i - 1) * pagesize, totalcount);
                } else {
                    if(page==i){
                         subList = list.subList((i - 1) * pagesize, pagesize * (i));
                        return subList;
                    }
                }
            }
        }
        return subList;
    }

        class SiteActivityVo extends PromotionActivity{

        private SiteActivity siteActivity;
        public SiteActivityVo(SiteActivity siteActivity){
                super();
                this.siteActivity=siteActivity;
        }

        public SiteActivity getSiteActivity() {
            return siteActivity;
        }

        public void setSiteActivity(SiteActivity siteActivity) {
            this.siteActivity = siteActivity;
        }
    }
}


