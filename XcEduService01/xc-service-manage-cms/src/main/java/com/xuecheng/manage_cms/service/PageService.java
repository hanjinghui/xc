package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PageService {
        @Autowired
        CmsPageRepository cmsPageRepository;

    /**
     * 条件查询
      * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    public QueryResponseResult findList( int page, int size, QueryPageRequest queryPageRequest){

        if (queryPageRequest==null){
            queryPageRequest = new QueryPageRequest();
        }

        //判断当前页数是否小于零，默认是1
        page = page-1;
        if (page<0){
            page = 0;
        }

        //判断每页显示的条数是否小于零，设置未20
        if (size<=0){
            size=20;
        }

        //构建pageable对象
        Pageable pageable = PageRequest.of(page,size);

        //添加条件查询
        CmsPage cmsPage = new CmsPage();
        /*cmsPage.setPageAliase("轮播");*/

        //创建条件匹配对象
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();

        //添加条件匹配
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("siteId",ExampleMatcher.GenericPropertyMatchers.exact()).withMatcher("templateId",ExampleMatcher.GenericPropertyMatchers.exact());

        //判断对象中的数据是否是空值，不是则赋值给对象CmsPage
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }

        //创建E下
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        //获得分页查询的对象
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);


        //获取queryResult对象，，作为返回对象的参数使用
        QueryResult<CmsPage> queryResult = new QueryResult<>();


        //从分页对象中获取数据封装到  QueryResult 要作为参数的对象中
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());



        //创建返回对象，  传入参数返回；
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    /**
     * 添加页面
     * @param cmsPage
     * @return
     */
    public CmsPageResult add(CmsPage cmsPage){
        //判断页面是否存在，
        if (cmsPage!=null){
            CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());

            //判断查出的结果对象是否有值，
             if (cmsPage1==null){
                 cmsPage.setPageId(null);
                 cmsPageRepository.save(cmsPage);
                 CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS, cmsPage);
                 return cmsPageResult;
             }
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id查询页面CmsPage
     * @param pageId
     * @return
     */
    public CmsPage findById(String pageId){

        //判断前端传过来的id是不是空字符
        if (StringUtils.isNotEmpty(pageId)){
            Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
            if (optional.isPresent()){
                CmsPage cmsPage = optional.get();
                return cmsPage;
            }
        }
        return null;
    }


    /**
     * 修改数据
     * @param cmsPage
     * @return
     */
    public CmsPageResult update(String id,CmsPage cmsPage){

        //根据id查询页面数据信息
        CmsPage one = this.findById(id);

        if (one!=null){
            //更新模板id
            one.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            one.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            one.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            one.setPageName(cmsPage.getPageName());
            //更新页面访问路径
            one.setPageWebPath(cmsPage.getPageWebPath());
            //更细腻页面网络路径
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //更新dataUrl数据
            one.setDataUrl(cmsPage.getDataUrl());

            //执行更新
            CmsPage save = cmsPageRepository.save(one);
            if (save!=null){
                return new CmsPageResult(CommonCode.SUCCESS,save);
            }
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }


    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    public ResponseResult delect(String id){
        try {
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(CommonCode.FAIL);
        }

    }
}
