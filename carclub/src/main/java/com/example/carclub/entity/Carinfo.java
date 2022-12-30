package com.example.carclub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName carinfo
 */
@TableName(value ="carinfo")
@Data
public class Carinfo implements Serializable {
    /**
     * 
     */
    @TableId
    private String carnum;

    /**
     * 
     */
    private String polivynum;

    /**
     * 
     */
    private String settlestatus;

    /**
     * 
     */
    private String checkstatus;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")
    private Date checktime;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")
    private Date settletime;

    /**
     * 
     */
    private String vipnum;

    /**
     * 
     */
    private String policystatus;

    /**
     * 
     */
    private String cartype;

    /**
     * 
     */
    private String urlup;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Carinfo other = (Carinfo) that;
        return (this.getCarnum() == null ? other.getCarnum() == null : this.getCarnum().equals(other.getCarnum()))
            && (this.getPolivynum() == null ? other.getPolivynum() == null : this.getPolivynum().equals(other.getPolivynum()))
            && (this.getSettlestatus() == null ? other.getSettlestatus() == null : this.getSettlestatus().equals(other.getSettlestatus()))
            && (this.getCheckstatus() == null ? other.getCheckstatus() == null : this.getCheckstatus().equals(other.getCheckstatus()))
            && (this.getChecktime() == null ? other.getChecktime() == null : this.getChecktime().equals(other.getChecktime()))
            && (this.getSettletime() == null ? other.getSettletime() == null : this.getSettletime().equals(other.getSettletime()))
            && (this.getVipnum() == null ? other.getVipnum() == null : this.getVipnum().equals(other.getVipnum()))
            && (this.getPolicystatus() == null ? other.getPolicystatus() == null : this.getPolicystatus().equals(other.getPolicystatus()))
            && (this.getCartype() == null ? other.getCartype() == null : this.getCartype().equals(other.getCartype()))
            && (this.getUrlup() == null ? other.getUrlup() == null : this.getUrlup().equals(other.getUrlup()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCarnum() == null) ? 0 : getCarnum().hashCode());
        result = prime * result + ((getPolivynum() == null) ? 0 : getPolivynum().hashCode());
        result = prime * result + ((getSettlestatus() == null) ? 0 : getSettlestatus().hashCode());
        result = prime * result + ((getCheckstatus() == null) ? 0 : getCheckstatus().hashCode());
        result = prime * result + ((getChecktime() == null) ? 0 : getChecktime().hashCode());
        result = prime * result + ((getSettletime() == null) ? 0 : getSettletime().hashCode());
        result = prime * result + ((getVipnum() == null) ? 0 : getVipnum().hashCode());
        result = prime * result + ((getPolicystatus() == null) ? 0 : getPolicystatus().hashCode());
        result = prime * result + ((getCartype() == null) ? 0 : getCartype().hashCode());
        result = prime * result + ((getUrlup() == null) ? 0 : getUrlup().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", carnum=").append(carnum);
        sb.append(", polivynum=").append(polivynum);
        sb.append(", settlestatus=").append(settlestatus);
        sb.append(", checkstatus=").append(checkstatus);
        sb.append(", checktime=").append(checktime);
        sb.append(", settletime=").append(settletime);
        sb.append(", vipnum=").append(vipnum);
        sb.append(", policystatus=").append(policystatus);
        sb.append(", cartype=").append(cartype);
        sb.append(", urlup=").append(urlup);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}