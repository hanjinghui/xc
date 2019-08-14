package com.xuecheng.manage_cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Test
    public void findList(){

        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        CmsPage cmsPage = new CmsPage();
        cmsPage.setTemplateId("5a925be7b00ffc4b3c1578b5");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        System.out.println(all);
    }

    @Test
    public void save(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("韩靖辉");
        cmsPage.setPageId("p01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageCreateTime(new Date());

        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("name1");
        cmsPageParam.setPageParamValue("value1");

        ArrayList<CmsPageParam> cmsPageParams = new ArrayList<>();
        cmsPageParams.add(cmsPageParam);

        cmsPage.setPageParams(cmsPageParams);

        cmsPageRepository.save(cmsPage);
    }

    @Test
    public void update(){
        Optional<CmsPage> optional = cmsPageRepository.findById("p01");

        if (optional.isPresent()){
            CmsPage cmsPage = optional.get();
            cmsPage.setPageName("张涛");
            cmsPageRepository.save(cmsPage);
        }
    }
}
