package com.csl.vhr.controller.system.basic;

import com.csl.vhr.entity.Menu;
import com.csl.vhr.entity.RespBean;
import com.csl.vhr.entity.Role;
import com.csl.vhr.service.MenuRoleService;
import com.csl.vhr.service.MenuService;
import com.csl.vhr.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MaoLongLong
 * @date 2020/8/20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/system/basic/permiss")
public class PermissionController {

    private final RoleService roleService;
    private final MenuService menuService;
    private final MenuRoleService menuRoleService;

    @GetMapping("")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getMenusWithChildren();
    }

    @GetMapping("/menu_ids/{id}")
    public List<Integer> getMenuIdByRoleId(@PathVariable Integer id) {
        return menuService.getMenuIdsByRoleId(id);
    }

    @PutMapping("/menu_role/{id}")
    public RespBean updateMenuRole(@PathVariable Integer id,
                                   @RequestBody Integer[] ids) {

        if (menuRoleService.updateMenuRole(id, ids)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/role/{id}")
    public RespBean deleteRole(@PathVariable Integer id) {
        if (roleService.removeById(id)) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
