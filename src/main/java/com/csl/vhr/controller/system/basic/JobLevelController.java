package com.csl.vhr.controller.system.basic;


import com.csl.vhr.entity.JobLevel;
import com.csl.vhr.entity.RespBean;
import com.csl.vhr.service.JobLevelService;
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
@AllArgsConstructor
@RequestMapping("/system/basic/jl")
public class JobLevelController {

    private final JobLevelService jobLevelService;

    @GetMapping("")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.list();
    }

    @PostMapping("")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel) {
        jobLevel.setCreateDate(LocalDateTime.now());
        jobLevel.setEnabled(true);
        boolean ok = jobLevelService.save(jobLevel);
        if (ok) {
            return RespBean.ok("添加成功", jobLevel);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel) {
        boolean ok = jobLevelService.updateById(jobLevel);
        if (ok) {
            return RespBean.ok("修改成功", jobLevel);
        }
        return RespBean.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable("id") Integer id) {
        boolean ok = jobLevelService.removeById(id);
        if (ok) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("")
    public RespBean deleteJobLevelsByIds(@RequestBody Integer[] ids) {
        boolean ok = jobLevelService.removeByIds(Arrays.asList(ids));
        if (ok) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
