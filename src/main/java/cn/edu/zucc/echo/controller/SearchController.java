package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.entity.ESAnswerSheetEntity;
import cn.edu.zucc.echo.entity.ESQuestionnaireEntity;
import cn.edu.zucc.echo.repository.ESAnswerSheetRepository;
import cn.edu.zucc.echo.repository.EchoAnswerSheetEntityRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.edu.zucc.echo.repository.ESQuestionnaireRepository;


@Controller
public class SearchController {
    @Autowired
    private ESAnswerSheetRepository esAnswerSheetRepository;
    @Autowired
    private ESQuestionnaireRepository esQuestionnaireRepository;
    @Autowired
    private EchoAnswerSheetEntityRepository echoAnswerSheetEntityRepository;
    @GetMapping("search")
    public ModelAndView searchByPageAndSort(Integer start, String key) {

        // 分页：
        if (start == null) {
            start = 0;
        }

        int size = 2;//每页文档数

        // 构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilderQueryBuilder = new NativeSearchQueryBuilder();

        // nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.matchQuery("name", key));
        nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(key, "name", "body"));

        //nativeSearchQueryBuilderQueryBuilder.withHighlightFields(new HighlightBuilder.Field("name").preTags("<span style='background-color: #FFFF00'>").postTags("</span>"));
        // 搜索，获取结果
        // nativeSearchQueryBuilderQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        nativeSearchQueryBuilderQueryBuilder.withPageable(PageRequest.of(start, size));
        Page<ESAnswerSheetEntity> products = esAnswerSheetRepository.search(nativeSearchQueryBuilderQueryBuilder.build());
        // 总条数
        for (ESAnswerSheetEntity product : products) {
            System.out.println(product);
        }
        ModelAndView mav = new ModelAndView("search");
        mav.addObject("page", products);
        mav.addObject("keys", key);
        return mav;
    }

}
