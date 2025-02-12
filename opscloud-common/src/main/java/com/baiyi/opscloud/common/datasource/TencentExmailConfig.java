package com.baiyi.opscloud.common.datasource;

import com.baiyi.opscloud.common.datasource.base.BaseConfig;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author baiyi
 * @Date 2021/10/12 3:07 下午
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TencentExmailConfig extends BaseConfig {

    private Tencent tencent;

    @Data
    @NoArgsConstructor
    @ApiModel
    public static class Tencent {
        private Exmail exmail;
    }

    @Data
    @NoArgsConstructor
    @ApiModel
    public static class Exmail {
        private String apiUrl;
        private String corpId;
        private String name;
        private String corpSecret;
    }

}

