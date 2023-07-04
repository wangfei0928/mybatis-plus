package com.wf.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

//@TableName("tb_user")
public class User {

//    @TableId(type = IdType.AUTO)    //自增ID
//    @TableId(type = IdType.INPUT)   //手动输入ID
//    @TableId(type = IdType.ASSIGN_UUID)   //UUID
    @TableId(type = IdType.ASSIGN_ID)  //雪花算法生成ID
    private Long id;
    private String name;
    @TableField(value = "pwd",select = false)
    private String password;
    private Integer age;
    private String tel;
    @TableField(exist = false)
    private Integer online;

    //逻辑删除字段，标记当前记录是否被删除
//    @TableLogic(value = "0",delval = "1")
    private Integer deleted;

    @Version
    private Integer version;
}
