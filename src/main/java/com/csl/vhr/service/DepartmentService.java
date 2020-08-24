package com.csl.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.vhr.entity.Department;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
public interface DepartmentService extends IService<Department> {

    List<Department> getAllDepartmentByParentId(Integer id);

    boolean addDepartment(Department department);

    boolean deleteDepartment(Integer id);
}
