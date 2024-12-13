package com.rosy.virosa.common.domain;

import com.rosy.virosa.common.enums.AppHttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemException extends RuntimeException {
    private int code;
    private String message;

    public SystemException(AppHttpStatusEnum status) {
        code = status.getCode();
        message = status.getMsg();
    }
}
