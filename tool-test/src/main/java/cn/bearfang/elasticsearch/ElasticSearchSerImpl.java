package cn.bearfang.elasticsearch;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2021-02-27 17:43
 **/
public class ElasticSearchSerImpl {

    private static final int DEFAULT_NUMBER_OF_SHARDS = 2; // 分片数
    private static final int DEFAULT_NUMBER_OF_REPLICAS = 1; // 副本数

    public static Settings getSpecialSetting(){
        Settings.Builder builder = Settings.builder();
        builder.put("number_of_shards", DEFAULT_NUMBER_OF_SHARDS);
        builder.put("number_of_replicas",DEFAULT_NUMBER_OF_REPLICAS);

        return builder.build();
    }

    public static XContentBuilder getSpecialMapping(){
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties")
                    .startObject("companyCode")
                    .field("type","keyword")
                    .endObject()
                    .startObject("sessionId")
                    .field("type","keyword")
                    .endObject()
                    .startObject("sessionType")
                    .field("type","integer")
                    .endObject()
                    .startObject("sendMsgCount")
                    .field("type","integer")
                    .endObject()
                    .startObject("autoQcScore")
                    .field("type","integer")
                    .endObject()
                    .startObject("satisfiedFlag")
                    .field("type","integer")
                    .endObject()
                    .startObject("receiveMsgCount")
                    .field("type","integer")
                    .endObject()
                    .startObject("startTime")
                    .field("type","long")
                    .endObject()
                    .startObject("agentEname")
                    .field("type","keyword")
                    .endObject()
                    .startObject("intention")
                    .field("type","keyword")
                    .endObject()
                    .startObject("messageMap")
                    .startObject("properties")
                    .startObject("0")
                    .field("type", "text")
                    //.field("analyzer", "jcseg_complex")
                    .field("index", "true")
                    .endObject()
                    .startObject("1")
                    .field("type", "text")
                    //.field("analyzer", "jcseg_complex")
                    .field("index", "true")
                    .endObject()
                    .startObject("2")
                    .field("type", "text")
                    //.field("analyzer", "jcseg_complex")
                    .field("index", "true")
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            //System.out.println(mapping.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapping;
    }
}
