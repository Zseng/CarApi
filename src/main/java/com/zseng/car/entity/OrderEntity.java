package com.zseng.car.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.zseng.car.common.OutputEntityJsonView;

import javax.persistence.*;

/**
 * Created by cc on 2017/4/12.
 */
@Entity
@Table(name = "[order]", schema = "car_grad", catalog = "")
public class OrderEntity {
    public static final int ORDER_STATUS_CANCEL = -1;
    public static final int ORDER_STATUS_REJECT = -2;
    public static final int ORDER_STATUS_INIT = 0;
    public static final int ORDER_STATUS_SUCCESS = 1;

    private long id;
    private long userId = 0;
    private long carId = 0;
    private long beginTime = 0;
    private long endTime = 0;
    private String info = "";
    private String reply;
    private int status = ORDER_STATUS_INIT;
    private long carOwnerId = 0;
    private long createTime = 0;
    private long updateTime = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    @JsonProperty("user_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "car_id", nullable = false)
    @JsonProperty("car_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Basic
    @Column(name = "begin_time", nullable = false)
    @JsonProperty("begin_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "end_time", nullable = false)
    @JsonProperty("end_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "info", nullable = false, length = 999)
    @JsonProperty("info")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "car_owner_id", nullable = false)
    public long getCarOwnerId() {
        return carOwnerId;
    }

    public void setCarOwnerId(long carOwnerId) {
        this.carOwnerId = carOwnerId;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    @JsonProperty("create_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    @JsonProperty("update_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (carId != that.carId) return false;
        if (beginTime != that.beginTime) return false;
        if (endTime != that.endTime) return false;
        if (status != that.status) return false;
        if (carOwnerId != that.carOwnerId) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (carId ^ (carId >>> 32));
        result = 31 * result + (int) (beginTime ^ (beginTime >>> 32));
        result = 31 * result + (int) (endTime ^ (endTime >>> 32));
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (int) (status ^ (status >>> 32));
        result = 31 * result + (int) (carOwnerId ^ (carOwnerId >>> 32));
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        return result;
    }

    @Basic
    @Column(name = "reply", nullable = false, length = 999)
    @JsonProperty("reply")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
