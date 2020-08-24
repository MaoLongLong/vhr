package com.csl.vhr.controller;


import com.csl.vhr.entity.Department;
import com.csl.vhr.entity.RespBean;
import com.csl.vhr.service.DepartmentService;
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
@RequestMapping("/system/basic/department")
public class DepartmentController {

    DepartmentService departmentService;

    @GetMapping("")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartmentByParentId(-1);
    }

    @PostMapping("")
    public RespBean addDepartment(@RequestBody Department department) {
        if (departmentService.addDepartment(department)) {
            return RespBean.ok("添加成功", department);
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id) {
        if (departmentService.deleteDepartment(id)) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败，该部门存在子部门或者仍有员工");
    }

}
