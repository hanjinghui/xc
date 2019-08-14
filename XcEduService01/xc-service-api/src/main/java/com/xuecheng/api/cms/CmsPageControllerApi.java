package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value ="cms页面管理接口",description = "cms页面管理接口,提供增 删 改 查 的方法。")
public interface CmsPageControllerApi {
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "每页显示的条数",required = true,paramType = "path",dataType = "int")
 })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) ;

    /**
     * 添加页面的接口
     * @param cmsPage
     * @return
     */
    @ApiOperation("添加页面")
    public CmsPageResult add(CmsPage cmsPage);

    /**
     * 通过查询数据
     * @param id
     * @return
     */
    @ApiOperation("通过id查询数据")
    public CmsPage findById(String id);

    /**
     * 修改页面
     * @param cmsPage
     * @return
     */
    @ApiOperation("修改页面")
    public CmsPageResult edit(String id,CmsPage cmsPage);


    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @ApiOperation("根据id删除数据")
    public ResponseResult delect(String id);
}
