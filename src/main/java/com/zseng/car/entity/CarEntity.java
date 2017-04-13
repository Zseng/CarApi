package com.zseng.car.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.zseng.car.common.OutputEntityJsonView;
import com.zseng.car.common.Util;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

/**
 * Created by cc on 2017/4/10.
 */
@Entity
@Table(name = "car", schema = "car_grad", catalog = "")
public class CarEntity {
    private long id;
    private String name = "";
    private String info = "";
    private String brand = "";
    private int city = 0;
    private int county = 0;
    private int district = 0;
    private int type = 0;
    private int priceType = 0;
    private double price = 0;
    private double discount = 0;
    private String phone = "";
    private String img = "";
    private long hot = 0;
    private int status = 0;
    private long createTime = 0;
    private long updateTime = 0;
    private long ownerId;

    private UserEntity user;
    private List<String> imgList;

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
    @Column(name = "name", nullable = false, length = 299)
    @JsonProperty("name")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "info", nullable = false, length = 299)
    @JsonProperty("info")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "brand", nullable = false, length = 99)
    @JsonProperty("brand")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "city", nullable = false)
    @JsonProperty("city")
    @JsonView({OutputEntityJsonView.Detail.class})
    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    @Basic
    @Column(name = "county", nullable = false)
    @JsonProperty("county")
    @JsonView({OutputEntityJsonView.Detail.class})
    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    @Basic
    @Column(name = "district", nullable = false)
    @JsonProperty("district")
    @JsonView({OutputEntityJsonView.Detail.class})
    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    @Basic
    @Column(name = "type", nullable = false)
    @JsonProperty("type")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "price_type", nullable = false)
    @JsonProperty("price_type")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    @JsonProperty("price")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "discount", nullable = false, precision = 0)
    @JsonProperty("discount")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 99)
    @JsonProperty("phone")
    @JsonView({OutputEntityJsonView.Detail.class})
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "img", nullable = false, length = 299)
    @JsonProperty("img")
    @JsonView({OutputEntityJsonView.Basic.class})
    public String getImg() {

        imgList = Util.explodeUrlString(img);
        return imgList.size() > 0 ? imgList.get(0) : "";
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "hot", nullable = false)
    @JsonProperty("hot")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getHot() {
        return hot;
    }

    public void setHot(long hot) {
        this.hot = hot;
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
    @Column(name = "owner_id", nullable = false)
    @JsonProperty("owner_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    @JsonProperty("owner")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    @JsonProperty("create_time")
    @JsonView({OutputEntityJsonView.Detail.class})
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    @JsonProperty("update_time")
    @JsonView({OutputEntityJsonView.Detail.class})
    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Transient
    @JsonProperty("img_list")
    @JsonView({OutputEntityJsonView.Detail.class})
    public List<String> getImgList() {
        imgList = Util.explodeUrlString(img);
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (id != carEntity.id) return false;
        if (city != carEntity.city) return false;
        if (county != carEntity.county) return false;
        if (district != carEntity.district) return false;
        if (type != carEntity.type) return false;
        if (priceType != carEntity.priceType) return false;
        if (Double.compare(carEntity.price, price) != 0) return false;
        if (Double.compare(carEntity.discount, discount) != 0) return false;
        if (hot != carEntity.hot) return false;
        if (createTime != carEntity.createTime) return false;
        if (updateTime != carEntity.updateTime) return false;
        if (name != null ? !name.equals(carEntity.name) : carEntity.name != null) return false;
        if (info != null ? !info.equals(carEntity.info) : carEntity.info != null) return false;
        if (brand != null ? !brand.equals(carEntity.brand) : carEntity.brand != null) return false;
        if (phone != null ? !phone.equals(carEntity.phone) : carEntity.phone != null) return false;
        if (img != null ? !img.equals(carEntity.img) : carEntity.img != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + city;
        result = 31 * result + county;
        result = 31 * result + district;
        result = 31 * result + type;
        result = 31 * result + priceType;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (int) (hot ^ (hot >>> 32));
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        return result;
    }
}
