package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {

    //根据页面名称，页面id，页面路径 查找页面
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageNane,String sideId,String pageWebPath);

    //根据id查询数据
    Optional<CmsPage> findById(String pageId);


}
