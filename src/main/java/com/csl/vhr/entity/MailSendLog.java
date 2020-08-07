package com.csl.vhr.entity;

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
public class MailSendLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("msgId")
    private String msgId;

    @TableField("empId")
    private Integer empId;

    /**
     * 0发送中，1发送成功，2发送失败
     */
    private Integer status;

    @TableField("routeKey")
    private String routeKey;

    private String exchange;

    /**
     * 重试次数
     */
    private Integer count;

    /**
     * 第一次重试时间
     */
    @TableField("tryTime")
    private LocalDate tryTime;

    @TableField("createTime")
    private LocalDate createTime;

    @TableField("updateTime")
    private LocalDate updateTime;


}
