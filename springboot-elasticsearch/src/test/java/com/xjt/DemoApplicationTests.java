package com.xjt;

import com.alibaba.fastjson.JSON;
import com.xjt.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;


    //1、创建索引
    @Test
    void testCreateIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("xiong_index");
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(response.isAcknowledged());// 查看是否创建成功
        System.out.println(response);
        client.close();
    }

    //2、获取所有，判断索引是否存在
    @Test
    void testGetIndex() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("xiong_index");
        GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        System.out.println(getIndexResponse);
        System.out.println(exists);

        client.close();
    }

    // 测试索引删除
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("xiong_index");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());// 是否删除成功
        client.close();
    }

    /*文档操作*/
    //1、文档的添加
    @Test
    void testDocumentCreate() throws IOException {
        User user = new User("zhangsan", 18);
        //设置规则
        IndexRequest indexRequest = new IndexRequest("xiong_index");
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueMillis(1000));
//        indexRequest.timeout("1s");
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);

        //IndexResponse[index=xiong_index,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
        System.out.println(response);
        //CREATED
        System.out.println(response.status());
    }

    //2、文档信息的获取
    @Test
    void testDocumentGet() throws IOException {
        GetRequest request = new GetRequest("xiong_index", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());   //{"age":18,"name":"zhangsan"}
        System.out.println(response);
        //{"_index":"test_index","_type":"_doc","_id":"1","_version":1,"_seq_no":0,"_primary_term":1,"found":true,"_source":{"age":18,"name":"zhangsan"}}
    }

    //2、文档是否存在
    @Test
    public void testDocumentIsExists() throws IOException {
        GetRequest request = new GetRequest("xiong_index", "1");
        // 不获取返回的 _source的上下文了
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //3、更新文档
    @Test
    public void testDocumentUpadte() throws IOException {
        UpdateRequest request = new UpdateRequest("xiong_index", "1");
        User new_user = new User("张无忌", 22);
        request.doc(JSON.toJSONString(new_user),XContentType.JSON);
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        System.out.println(updateResponse);
        System.out.println(updateResponse.status());
    }

    //4、搜索文档
    @Test
    public void testSearch(){
        SearchRequest searchRequest = new SearchRequest("xiong_index");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "张无忌");
    }

    // 查询
    // SearchRequest 搜索请求
    // SearchSourceBuilder 条件构造
    // HighlightBuilder 高亮
    // TermQueryBuilder 精确查询
    // MatchAllQueryBuilder
    // xxxQueryBuilder ...
    @Test
    public void testAdvancedSearch() throws IOException {
        // 1.创建查询请求对象
        SearchRequest searchRequest = new SearchRequest();
        // 2.构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // (1)查询条件 使用QueryBuilders工具类创建
        // 精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "liuyou");
        //        // 匹配查询
        //        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        // (2)其他<可有可无>：（可以参考 SearchSourceBuilder 的字段部分）
        // 设置高亮
        searchSourceBuilder.highlighter(new HighlightBuilder());
        //        // 分页
        //        searchSourceBuilder.from();
        //        searchSourceBuilder.size();
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // (3)条件投入
        searchSourceBuilder.query(termQueryBuilder);
        // 3.添加条件到请求
        searchRequest.source(searchSourceBuilder);
        // 4.客户端查询请求
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        // 5.查看返回结果
        SearchHits hits = search.getHits();
        System.out.println(JSON.toJSONString(hits));
        System.out.println("=======================");
        for (SearchHit documentFields : hits.getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }
    }

    // 下面的这些api无法批量增加数据（只会保留最后一个source）
    @Test
    public void test() throws IOException {
        IndexRequest request = new IndexRequest("xiong_index");        // 没有id会自动生成一个随机ID
        request.source(JSON.toJSONString(new User("liu",1)),XContentType.JSON);
        request.source(JSON.toJSONString(new User("min",2)),XContentType.JSON);
        request.source(JSON.toJSONString(new User("kai",3)),XContentType.JSON);
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);
        System.out.println(index.status());// created
    }

    // 特殊的，真的项目一般会 批量插入数据
    @Test
    public void testBulk() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("liuyou-1",1));
        users.add(new User("liuyou-2",2));
        users.add(new User("liuyou-3",3));
        users.add(new User("liuyou-4",4));
        users.add(new User("liuyou-5",5));
        users.add(new User("liuyou-6",6));
        // 批量请求处理
        for (int i = 0; i < users.size(); i++) {
            bulkRequest.add(
                    // 这里是数据信息
                    new IndexRequest("xiong_index")
                            .id(""+(i + 1))         // 没有设置id 会自定生成一个随机id
                            .source(JSON.toJSONString(users.get(i)),XContentType.JSON)
            );
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());          // ok
    }


}
