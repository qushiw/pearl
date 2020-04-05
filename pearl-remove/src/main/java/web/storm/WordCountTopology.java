package web.storm;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

public class WordCountTopology {

    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("RandomSentenceSpout", new RandomSentenceSpout());
        topologyBuilder.setBolt("SplitSentenceBolt", new SplitSentenceBolt()).shuffleGrouping("RandomSentenceSpout");
        topologyBuilder.setBolt("WordCountBolt", new WordCountBolt()).shuffleGrouping("SplitSentenceBolt");
        topologyBuilder.setBolt("PrintBolt", new PrintBolt()).shuffleGrouping("WordCountBolt");

        StormTopology topology = topologyBuilder.createTopology();
        Config config = new Config();

        /*LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("WordCountTopology", config, topology);*/

        try {
            StormSubmitter.submitTopology("WordCountTopology", config, topology);
        } catch (AlreadyAliveException e) {
            e.printStackTrace();
        } catch (InvalidTopologyException e) {
            e.printStackTrace();
        } catch (AuthorizationException e) {
            e.printStackTrace();
        }
    }


}
