package com.csl.vhr.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author MaoLongLong
 * @date 2020/8/6
 */
@Data
public class Meta {

    @TableField("keepAlive")
    private Boolean keepAlive;

    @TableField("requireAuth")
    private Boolean requireAuth;

}
