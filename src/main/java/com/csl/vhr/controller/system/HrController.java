package com.csl.vhr.controller.system;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csl.vhr.entity.Hr;
import com.csl.vhr.entity.RespBean;
import com.csl.vhr.service.HrService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/system/hr")
public class HrController {

    private final HrService hrService;

    @GetMapping("")
    public List<Hr> getAll() {
        return hrService.getAll();
    }

    @PutMapping("")
    public RespBean updateHr(@RequestBody Hr hr) {

        LambdaUpdateWrapper<Hr> updateWrapper = Wrappers.<Hr>lambdaUpdate()
                .eq(Hr::getId, hr.getId())
                .set(hr.getEnabled() != null, Hr::getEnabled, hr.getEnabled());

        if (hrService.update(updateWrapper)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

}
