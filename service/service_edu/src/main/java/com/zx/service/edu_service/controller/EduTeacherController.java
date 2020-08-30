package com.zx.service.edu_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zx.service.edu_service.entity.EduTeacher;
import com.zx.service.edu_service.entity.vo.TeacherQuery;
import com.zx.service.edu_service.service.EduTeacherService;
import com.zx.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zx
 * @since 2020-08-02
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "查询所有教师数据")
    @GetMapping("findAll")
    public Result findAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{page}/{limit}")
    public Result pageListTeacher(
        @ApiParam(name = "page", value = "当前页码", required = true)
        @PathVariable Long page,
        @ApiParam(name = "limit", value = "每页记录数", required = true)
        @PathVariable Long limit){
            Page<EduTeacher> pageParam = new Page<>(page, limit);
            eduTeacherService.page(pageParam, null);
            List<EduTeacher> records = pageParam.getRecords();
            long total = pageParam.getTotal();
            return  Result.ok().data("total", total).data("rows", records);
        }

    @ApiOperation(value = "分页条件查询讲师")
    @PostMapping("pageTeacherCondition/{page}/{limit}")
    public Result pageTeacherCondition(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        //查询条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<EduTeacher>()
                .like(StringUtils.isNotEmpty(teacherQuery.getName()),"name",teacherQuery.getName())
                .eq(null!=teacherQuery.getLevel(),"level",teacherQuery.getLevel())
                .ge(StringUtils.isNotEmpty(teacherQuery.getBegin()),"gmt_create", teacherQuery.getBegin())
                .le(StringUtils.isNotEmpty(teacherQuery.getEnd()),"gmt_modified",teacherQuery.getEnd());
        eduTeacherService.page(pageTeacher,queryWrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        return  Result.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public Result save(
        @ApiParam(name = "teacher", value = "讲师对象", required = true)
        @RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.save(teacher);
        if (flag){
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation("根据id进行查询讲师信息")
    @GetMapping("getTeacherId/{id}")
    public Result getTeacherId(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher",eduTeacher);
    }
    @ApiOperation("修改讲师信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        //查询讲师ID
        //EduTeacher eduTeacherInfo = eduTeacherService.getById(eduTeacher.getId());
        //根据讲师ID修改讲师信息
        boolean flag = eduTeacherService.updateById(eduTeacher);
        System.out.println(flag);
        if (flag){
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

