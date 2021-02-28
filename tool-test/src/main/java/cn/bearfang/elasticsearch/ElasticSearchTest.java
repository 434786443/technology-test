package cn.bearfang.elasticsearch;

import cn.bearfang.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
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
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2021-02-27 19:42
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest bangbang = new CreateIndexRequest("bangbang");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(bangbang, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }

    /**
     * 自定义索引 mapping结构
     * @throws IOException
     */
    @Test
    public void testCreateSpecialIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("we_session");
        createIndexRequest.settings(ElasticSearchSerImpl.getSpecialSetting());
        createIndexRequest.mapping(ElasticSearchSerImpl.getSpecialMapping());
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }

    @Test
    public void testExistIndex() throws IOException {
        GetIndexRequest req = new GetIndexRequest("bangbang");
        boolean exists = restHighLevelClient.indices().exists(req, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest req = new DeleteIndexRequest("bangbang");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(req, RequestOptions.DEFAULT);

        System.out.println(delete);
    }

    @Test
    public void testPutIndex() throws IOException {
        User user = new User();
        user.setName("crt");
        user.setAge(23);
        user.setSex("女");
        ObjectMapper objectMapper = new ObjectMapper();
        String source = objectMapper.writeValueAsString(user);

        IndexRequest indexRequest = new IndexRequest("bangbang");
        //PUT /bangbang/_doc/1
        indexRequest.id("2");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        indexRequest.source(source, XContentType.JSON);

        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(index.toString());
        System.out.println(index.status());
    }

    @Test
    public void testDocExits() throws IOException {
        GetRequest getRequest = new GetRequest("bangbang", "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void testGetDoc() throws IOException {
        GetRequest getRequest = new GetRequest("bangbang");
        getRequest.id("2");

        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> source = getResponse.getSource();
        System.out.println(getResponse.toString());
    }

    @Test
    public void testUpdateDoc() throws IOException {
        UpdateRequest request = new UpdateRequest("bangbang","1");
        GetRequest getRequest = new GetRequest("bangbang", "1");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

        String sourceAsString = getResponse.getSourceAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(sourceAsString, User.class);
        user.setName("fx");

        request.doc(objectMapper.writeValueAsString(user), XContentType.JSON);
        UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);

        System.out.println(update);
    }

    @Test
    public void testDeleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("bangbang");
        deleteRequest.id("1");

        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(delete.status());
    }

    @Test
    public void testBulk() throws Exception {
        BulkRequest bulkRequest = new BulkRequest("bangbang");

        List<User> users = Arrays.asList(new User("fx11", 23, "男"),
                new User("fx12", 23, "男"),
                new User("fx13", 23, "男"));

        bulkRequest.timeout("60s");
        for (int i = 0; i < users.size(); i++) {
            bulkRequest.add(new IndexRequest("bangbang")
                    .id(String.valueOf(i+3))
                    .source(mapper.writeValueAsString(users.get(i)),XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
    }

    @Test
    public void testSearch() throws Exception {
        SearchRequest searchRequest = new SearchRequest("bangbang");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //精确匹配
        //GET bangbang/_search
        //{
        //  "query": {
        //    "term": {
        //      "name": {
        //        "value": "crt"
        //      }
        //    }
        //  }
        //  , "timeout": "60s"
        //}
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "crt");
//        searchSourceBuilder.query(termQueryBuilder);

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "方");
        searchSourceBuilder.query(matchQueryBuilder);

//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
//        searchSourceBuilder.query(matchAllQueryBuilder);

        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(60));
        //分页
//        searchSourceBuilder.from();
//        searchSourceBuilder.size();

        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(search.status());
        System.out.println(mapper.writeValueAsString(search.getHits()));
        search.getHits().forEach(h->{
            try {
                System.out.println(mapper.writeValueAsString(h.getSourceAsString()));
            } catch (JsonProcessingException e) {
            }
        });
    }
}
