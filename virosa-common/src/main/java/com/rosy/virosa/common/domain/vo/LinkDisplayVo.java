package com.rosy.virosa.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkDisplayVo {
    private Long id;
    private String name;
    private String logo;
    private String description;
    // 网站地址
    private String address;
}
