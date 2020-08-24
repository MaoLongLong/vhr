package com.csl.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.vhr.entity.Department;
import com.csl.vhr.entity.Employee;
import com.csl.vhr.mapper.DepartmentMapper;
import com.csl.vhr.mapper.EmployeeMapper;
import com.csl.vhr.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl
        extends ServiceImpl<DepartmentMapper, Department>
        implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<Department> getAllDepartmentByParentId(Integer id) {
        return departmentMapper.getAllDepartmentByParentId(id);
    }

    @Override
    @Transactional
    public boolean addDepartment(Department department) {

        department.setEnabled(true);
        department.setIsParent(false);
        department.setChildren(new ArrayList<>());

        departmentMapper.insert(department);

        Department parent = departmentMapper.selectOne(Wrappers.<Department>lambdaQuery()
                .eq(Department::getId, department.getParentId())
                .select(Department::getId, Department::getDepPath));

        String path = parent.getDepPath() + "." + department.getId();
        department.setDepPath(path);

        LambdaUpdateWrapper<Department> updateWrapper = Wrappers.<Department>lambdaUpdate()
                .eq(Department::getId, department.getId())
                .set(Department::getDepPath, path);

        departmentMapper.update(null, updateWrapper);

        updateWrapper = Wrappers.<Department>lambdaUpdate()
                .eq(Department::getId, parent.getId())
                .set(Department::getIsParent, true);

        departmentMapper.update(null, updateWrapper);

        return true;
    }

    @Override
    @Transactional
    public boolean deleteDepartment(Integer id) {

        Integer count = departmentMapper.selectCount(Wrappers
                .<Department>lambdaQuery()
                .eq(Department::getId, id)
                .eq(Department::getIsParent, false));

        if (count == 0) {
            return false;
        }

        count = employeeMapper.selectCount(Wrappers
                .<Employee>lambdaQuery()
                .eq(Employee::getDepartmentId, id));

        if (count > 0) {
            return false;
        }

        Department department = departmentMapper.selectOne(Wrappers
                .<Department>lambdaQuery()
                .eq(Department::getId, id)
                .eq(Department::getIsParent, false)
                .select(Department::getParentId));

        departmentMapper.deleteById(id);

        count = departmentMapper.selectCount(Wrappers
                .<Department>lambdaQuery()
                .eq(Department::getParentId, department.getParentId()));

        if (count == 0) {
            departmentMapper.update(null, Wrappers
                    .<Department>lambdaUpdate()
                    .eq(Department::getId, department.getParentId())
                    .set(Department::getIsParent, false));
        }

        return true;
    }

}
