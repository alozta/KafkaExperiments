/**
 * Created by alozta on 8/12/16.
 *
 * Simple partitioner for Kafka.
 * Describes partition information.
 * Used in Producer's partitioner.class section.
 */
package com.imarine.kafka.Producer;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner implements Partitioner {
    public SimplePartitioner (VerifiableProperties props) {

    }

    public int partition(Object key, int a_numPartitions) {
        int partition = 0;
        String stringKey = (String) key;
        int offset = stringKey.lastIndexOf('.');
        if (offset > 0) {
            partition = Integer.parseInt( stringKey.substring(offset+1)) % a_numPartitions;
        }
        return partition;
    }

}
