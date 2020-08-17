package com.csl.vhr.controller.system.basic;


import com.csl.vhr.entity.Position;
import com.csl.vhr.entity.RespBean;
import com.csl.vhr.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        position.setEnabled(true);
        position.setCreateDate(LocalDateTime.now());
        boolean ok = positionService.save(position);
        if (ok) {
            return RespBean.ok("添加成功", position);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("")
    public RespBean updatePosition(@RequestBody Position position) {
        if (position.getEnabled() == null) {
            position.setEnabled(true);
        }
        boolean ok = positionService.updateById(position);
        if (ok) {
            return RespBean.ok("修改成功", position);
        }
        return RespBean.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable("id") Integer id) {
        boolean ok = positionService.removeById(id);
        if (ok) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("")
    public RespBean deletePositionsByIds(@RequestBody Integer[] ids) {
        boolean ok = positionService.removeByIds(Arrays.asList(ids));
        if (ok) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
