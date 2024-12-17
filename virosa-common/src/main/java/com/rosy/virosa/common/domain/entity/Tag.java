package com.rosy.virosa.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 标签(Tag)表实体类
 *
 * @author rosy
 * @since 2024-12-16 22:42:41
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("vr_tag")
public class Tag {
    @TableId
    private Long id;
    // 标签名
    private String name;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    // 删除标志（0代表未删除，1代表已删除）
    @TableLogic
    private Integer delFlag;
    // 乐观锁版本
    @Version
    private Integer version;
}

