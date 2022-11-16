package com.djin.myflink;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

/**
 * @auther dj
 * @date 2022/11/17
 * @note:
 */
public class KafkaSinkTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "node001:9092,node002:9092,node003:9092");
        DataStreamSource<String> stream = env.readTextFile("data/clicks.csv");

        stream.addSink(new FlinkKafkaProducer<String>(
                "clicks", new SimpleStringSchema(), properties
        ));
        env.execute();
    }
}
