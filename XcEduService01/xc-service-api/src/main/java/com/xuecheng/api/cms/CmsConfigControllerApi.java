package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "cms配置管理接口",description = "cms配置管理接口,提供模型数据管理接口，查询接口")
public interface CmsConfigControllerApi {

    @ApiOperation("根据id查询数据管理信息")
    public CmsConfig getMoel(String id);
}
