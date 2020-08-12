package com.csl.vhr.controller.system.basic;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csl.vhr.entity.Position;
import com.csl.vhr.entity.RespBean;
import com.csl.vhr.service.PositionService;
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
@RequestMapping("/system/basic/pos")
@AllArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping("")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @PostMapping("")
    public RespBean addPosition(@RequestBody Position position) {
        boolean ok = positionService.save(position);
        if (ok) {
            return RespBean.ok("添加成功",
                    positionService.getById(position.getId()));
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/{id}")
    public RespBean updatePosition(@PathVariable Integer id,
                                   @RequestBody Position position) {

        LambdaUpdateWrapper<Position> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(Position::getId, id)
                .set(Position::getName, position.getName())
                .set(Position::getEnabled, position.getEnabled());

        boolean ok = positionService.update(wrapper);
        if (ok) {
            return RespBean.ok("修改成功",
                    positionService.getById(id));
        }
        return RespBean.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        boolean ok = positionService.removeById(id);
        if (ok) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
