/** *
  * @author mrchi
  * @Project FlinkProj
  * @File FilterOperator.java
  * @date 2019年04月12日 00:03
  * @Website http://www.mrchi.cn
  * @version 1.0
  ***/
import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011

object DataCleanFilterScala {
  def main(args: Array[String]): Unit = {
    //获取Flink函数入口点  检测点啥的就不设置了毕竟是测试用例
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    //定义Kafka信息
    var bootstrap = "master:9092,slave1:9092,slave2:9092";
    var zookeeper = "master:2181,slave1:2181,slave2:2181"
    var kafkaTopic = "allData";
    var kafkaGroup = "con1";

    val prop = new Properties()
    prop.put("zookeeper.connect", zookeeper)
    prop.put("bootstrap.servers", bootstrap)
    prop.put("group.id", kafkaGroup)
    val myConsumer = new FlinkKafkaConsumer011[String](kafkaTopic, new SimpleStringSchema(), prop);
//    添加消费到Flink
    val data = env.addSource(myConsumer)
//    Scala过滤出包含US的信息进行打印
//    Filter用例Scala
    data.filter(x=>x.contains("US")).print()
//  执行
    env.execute("FilterOperatorScala")
  }
}
