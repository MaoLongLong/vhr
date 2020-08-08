package com.csl.vhr.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author MaoLongLong
 * @date 2020/8/6
 */
@Data
public class Meta implements Serializable {

    private static final long serialVersionUID = 6282760949189433571L;

    @TableField("keepAlive")
    private Boolean keepAlive;

    @TableField("requireAuth")
    private Boolean requireAuth;

}
