package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdBanner;
import com.drelephant.elephantadmin.business.basedata.mapper.BdBannerMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdBannerService;
import com.drelephant.elephantadmin.business.basedata.util.BdUtil;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * banner 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdBannerServiceImpl extends ServiceImpl<BdBannerMapper, BdBanner> implements BdBannerService {


    @Override
    public R insertRecAndCheckCount10(@Nonnull BdBanner entity) {
        //check 数量
        final int selectCount = selectCount(Condition.create().in("status", Constans.ACTIVE + "," + Constans.INVALID).last("limit 10"));
        if (selectCount > 9) {
            return R.error("首页图片数量超出 10, 保存失败");
        }
        // check必要字段
        String fileId = entity.getFileId();
        String fileName = entity.getFileName();
        String isOpenLink = entity.getIsOpenLink();
        String linkAddress = entity.getLinkAddress();
        String picAddress = entity.getPicAddress();
        if (BdUtil.isAnyBlank(fileId, fileName, isOpenLink, linkAddress, picAddress)) {
            return R.error("fileId, fileName, isOpenLink, linkAddress, picAddress 全部为必填");
        }
        String remark = entity.getRemark();
        final int strMax = 20;
        if (StringUtils.length(remark) > strMax) {
            return R.error("备注信息不得超过20字");
        }
        if (!StringUtils.equals(isOpenLink, "Y") && !StringUtils.equals(isOpenLink, "N")) {
            return R.error("isOpenLink 必须是 Y/N");
        }
        // save
        entity.setStatus("ACT");
        entity.setId(null);
        entity.setOrderNumber(selectCount + 1);
        entity.setUpdateTime(new Date());

        final boolean insert = insert(entity);
        return insert ? R.ok() : R.error("数据保存失败");
    }

    @Override
    public R updateBdBander(BdBanner bdBanner) {
        BdBanner bd = new BdBanner();
        bd.setPicAddress(bdBanner.getPicAddress());
        bd.setIsOpenLink(bdBanner.getIsOpenLink());
        bd.setRemark(bdBanner.getRemark());
        bd.setLinkAddress(bdBanner.getLinkAddress());
        bd.setUpdateTime(new Date());
        boolean flag = update(bdBanner, Condition.create().eq("id", bdBanner.getId()));
        return flag ? R.ok() : R.error("更新失败");
    }

    @Override
    public BdBanner selectOneBd(String id) {
        return selectOne(Condition.create().eq("id", id));
    }

    @Override
    public List<BdBanner> listAllACT() {
        Condition con = Condition.create();
        con.eq("status", "ACT");
        con.orderBy("orderNumber", true);
        return selectList(con);
    }

    @Override
    public R updateStatus(String id) {
        BdBanner bd = new BdBanner();
        bd.setStatus(Constans.DELETED);
        boolean flag = update(bd, Condition.create().eq("id", id));
        return flag ? R.ok() : R.error("删除失败");
    }

    @Override
    public R moveUp(String id) {
        List<BdBanner> list = listAllACT();
        int orderNumber = 0;
        BdBanner banner = null;
        for (int i = 0; i < list.size(); i++) {
            banner = list.get(i);
            if (banner.getId().equals(id)) {
                orderNumber = banner.getOrderNumber();
                if (i == 0) { // 当前记录是排在最前面的记录
                    break;
                } else {
                    baseMapper.updateOrderNumber(id, orderNumber - 1); // 前移1个号码

                    // 前一个BdBanner后移1个号码
                    BdBanner prBanner = list.get(i - 1);
                    baseMapper.updateOrderNumber(prBanner.getId(), prBanner.getOrderNumber() + 1);
                }
            }
        }
        // s上移的逻辑是 ,
        return R.ok();
    }

    @Override
    public R moveDown(String id) {
        List<BdBanner> list = listAllACT();
        int orderNumber = 0;
        BdBanner banner = null;
        for (int i = 0; i < list.size(); i++) {
            banner = list.get(i);
            if (banner.getId().equals(id)) {
                orderNumber = banner.getOrderNumber();
                if (i == (list.size() - 1)) { // 当前记录是排在最后面的记录
                    break;
                } else {
                    baseMapper.updateOrderNumber(id, orderNumber + 1); // 后移1个号码

                    // 下一个BdBanner前移1个号码
                    BdBanner nextBanner = list.get(i + 1);
                    baseMapper.updateOrderNumber(nextBanner.getId(), nextBanner.getOrderNumber() - 1);
                }
            }
        }
        //
        return R.ok();
    }
}
