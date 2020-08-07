package com.csl.vhr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
@Data
public class Adjustsalary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer eid;

    /**
     * 调薪日期
     */
    @TableField("asDate")
    private LocalDate asDate;

    /**
     * 调前薪资
     */
    @TableField("beforeSalary")
    private Integer beforeSalary;

    /**
     * 调后薪资
     */
    @TableField("afterSalary")
    private Integer afterSalary;

    /**
     * 调薪原因
     */
    private String reason;

    /**
     * 备注
     */
    private String remark;


}
