package com.rosy.virosa.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.rosy.virosa.common.security.CustomUserDetails;
import com.rosy.virosa.utilis.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityUtils.getUserDetails();
        Long userId = customUserDetails.getUser().getId();

        //TODO: 在用户注册时自动填充userId时的策略

        this.strictInsertFill(metaObject, "created_by", Long.class, userId);
        this.strictInsertFill(metaObject, "updated_by", Long.class, userId);
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityUtils.getUserDetails();
        Long userId = customUserDetails.getUser().getId();
        this.strictUpdateFill(metaObject, "updated_by", Long.class, userId);
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
