package pro.lgebicki.restclient.api;

import java.util.List;

public interface ObjectNodeList {
    List<String> asStringList();

    List<Long> asLongList();

    List<Integer> asIntList();

    List<Double> asDoubleList();

    List<ObjectNode> asObjectList();
}
