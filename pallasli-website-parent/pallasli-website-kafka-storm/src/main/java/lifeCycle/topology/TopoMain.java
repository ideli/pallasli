package lifeCycle.topology;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lifeCycle.bolt.TransferBolt;
import lifeCycle.bolt.WriterBolt;
import lifeCycle.spout.RandomWordSpout;

public class TopoMain {

	private static final Logger log = LoggerFactory.getLogger(TopoMain.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("random", new RandomWordSpout(), 2);
		builder.setBolt("transfer", new TransferBolt(), 4).shuffleGrouping("random");
		builder.setBolt("writer", new WriterBolt(), 4).fieldsGrouping("transfer", new Fields("word"));
		Config conf = new Config();
		conf.setNumWorkers(2);
		conf.setDebug(true);
		log.warn("$$$$$$$$$$$ submitting topology...");
		StormSubmitter.submitTopology("life-cycle", conf, builder.createTopology());
		log.warn("$$$$$$$4$$$ topology submitted !");
	}

}
