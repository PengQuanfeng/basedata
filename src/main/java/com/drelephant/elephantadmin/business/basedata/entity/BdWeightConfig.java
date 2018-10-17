package com.drelephant.elephantadmin.business.basedata.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * @author zhou.fan
 * @date 2018/10/17 17:30
 * @description
 */
@TableName("bd_weight_config")
public class BdWeightConfig implements Serializable {

    private Integer id;
    /** 供应商id */
    private Integer supplierId;
    /** 药品字典覆盖率 */
    private int medicineDictionary;
    /** 在售药品库存量 */
    private int stock;
    /** 订单平均毛利率 */
    private int orderAvg;
    /** 药品订单总金额 */
    private int medicineOrderTotalPrice;
    /** 发货仓储覆盖量 */
    private int warehouseCover;
    /** 实际发到货时长 */
    private int logisticsArriveDuration;
    /** 匹配订单发货率 */
    private int orderSend;
    /** 用户端服务评价 */
    private int userEvaluate;
    /** 供应商发货时长 */
    private int logisticsSendDuration;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public int getMedicineDictionary() {
        return medicineDictionary;
    }

    public void setMedicineDictionary(int medicineDictionary) {
        this.medicineDictionary = medicineDictionary;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getOrderAvg() {
        return orderAvg;
    }

    public void setOrderAvg(int orderAvg) {
        this.orderAvg = orderAvg;
    }

    public int getMedicineOrderTotalPrice() {
        return medicineOrderTotalPrice;
    }

    public void setMedicineOrderTotalPrice(int medicineOrderTotalPrice) {
        this.medicineOrderTotalPrice = medicineOrderTotalPrice;
    }

    public int getWarehouseCover() {
        return warehouseCover;
    }

    public void setWarehouseCover(int warehouseCover) {
        this.warehouseCover = warehouseCover;
    }

    public int getLogisticsArriveDuration() {
        return logisticsArriveDuration;
    }

    public void setLogisticsArriveDuration(int logisticsArriveDuration) {
        this.logisticsArriveDuration = logisticsArriveDuration;
    }

    public int getOrderSend() {
        return orderSend;
    }

    public void setOrderSend(int orderSend) {
        this.orderSend = orderSend;
    }

    public int getUserEvaluate() {
        return userEvaluate;
    }

    public void setUserEvaluate(int userEvaluate) {
        this.userEvaluate = userEvaluate;
    }

    public int getLogisticsSendDuration() {
        return logisticsSendDuration;
    }

    public void setLogisticsSendDuration(int logisticsSendDuration) {
        this.logisticsSendDuration = logisticsSendDuration;
    }
}
