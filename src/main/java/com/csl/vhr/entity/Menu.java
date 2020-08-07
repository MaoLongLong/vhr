package com.csl.vhr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    @TableField("iconCls")
    private String iconCls;

    @TableField(exist = false)
    private Meta meta;

    @TableField(exist = false)
    private List<Menu> children;

    @TableField("parentId")
    private Integer parentId;

    private Boolean enabled;


}
