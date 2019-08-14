package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page/")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    PageService pageService;


    /**
     * 条件查询接口
     * @param page 当前页
     * @param size 每页显示的条数
     * @param queryPageRequest  查询条件封装类
     * @return QueryResponseResult
     */
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {

        /*//使用的是测试数据
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(2);
        List list = new ArrayList();
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试页面");
        list.add(cmsPage);
        queryResult.setList(list);

        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);*/
        return pageService.findList(page,size,queryPageRequest);
    }

    /**
     * 添加页面
     * @param cmsPage 添加数据的类
     * @return CmsPageResult  返回操作是否成功的展示
     */
    @PostMapping("/add")
    public CmsPageResult add (@RequestBody CmsPage cmsPage){
        return pageService.add(cmsPage);
    }


    /**
     * 根据id查找Cmspage
     * @param id
     * @return
     */
    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable String id) {
        return pageService.findById(id);
    }

    /**
     * 修改数据
     * @param id
     * @param cmsPage
     * @return
     */
    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable String id,@RequestBody CmsPage cmsPage) {
        return pageService.update(id,cmsPage);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delect(@PathVariable String id) {
        return pageService.delect(id);
    }

}
