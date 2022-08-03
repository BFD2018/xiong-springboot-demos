package com.xjt;

import com.alibaba.fastjson.JSON;
import com.xjt.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
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
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

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
        System.out.println(response);
    }

    //2、获取所有，判断索引是否存在
    @Test
    void testGetIndex() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("jd_goods");
        GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(getIndexResponse);
        System.out.println(exists);

        client.close();
    }

    //3、获取所有，判断索引是否存在
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("xiong_index");
        AcknowledgedResponse response = client.indices().delete(deleteIndexRequest,RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());      //true

        client.close();
    }

    /*文档操作*/
    //1、文档的添加
    @Test
    void testDocumentCreate() throws IOException {
        User user = new User("zhangsan", 18);
        //设置规则
        IndexRequest indexRequest = new IndexRequest("test_index");
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueMillis(1000));
//        indexRequest.timeout("1s");
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println(response.status());
        //IndexResponse[index=test_index,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
        //CREATED
    }

    //2、文档信息的获取
    @Test
    void testDocumentGet() throws IOException {
        GetRequest request = new GetRequest("test_index", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());   //{"age":18,"name":"zhangsan"}
        System.out.println(response);
        //{"_index":"test_index","_type":"_doc","_id":"1","_version":1,"_seq_no":0,"_primary_term":1,"found":true,"_source":{"age":18,"name":"zhangsan"}}
    }

    //2、文档是否存在
    @Test
    public void testDocumentIsExists() throws IOException {
        GetRequest request = new GetRequest("test_index", "1");
        // 不获取返回的 _source的上下文了
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //3、更新文档
    @Test
    public void testDocumentUpadte() throws IOException {
        UpdateRequest request = new UpdateRequest("test_index", "1");
        User new_user = new User("张无忌", 22);
        request.doc(JSON.toJSONString(new_user),XContentType.JSON);
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        System.out.println(updateResponse);
        System.out.println(updateResponse.status());
    }

    //4、搜索文档
    @Test
    public void testSearch(){
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "python全栈");
    }


}
