package org.develnext.jphp.fx.jterminal.operations;

import php.runtime.Memory;
import php.runtime.env.Environment;
import php.runtime.env.TraceInfo;
import php.runtime.lang.ForeachIterator;
import php.runtime.memory.support.MemoryOperation;

import java.awt.*;
import java.util.HashMap;

public class DimensionMemoryOperation extends MemoryOperation<Dimension> {
    @Override
    public Class<?>[] getOperationClasses() {
        return new Class[] { Dimension.class };
    }

    @Override
    public Dimension convert(Environment environment, TraceInfo traceInfo, Memory memory) throws Throwable {
        if (memory.isNumber())
            return new Dimension(memory.toInteger(), memory.toInteger());

        if (memory.isArray()) {
            Dimension dimension = new Dimension();

            ForeachIterator iterator = memory.toImmutable().getNewIterator(environment, true, true);

            while (iterator.next()) {
                if ("width".equals(iterator.getStringKey()) || (iterator.isLongKey() && iterator.getMemoryKey().toInteger() == 0))
                    dimension.width = iterator.getValue().toInteger();
                if ("height".equals(iterator.getStringKey()) || (iterator.isLongKey() && iterator.getMemoryKey().toInteger() == 1))
                    dimension.height = iterator.getValue().toInteger();
            }

            return dimension;
        }

        return null;
    }

    @Override
    public Memory unconvert(Environment environment, TraceInfo traceInfo, Dimension dimension) {
        return Memory.wrap(environment, new HashMap<String, Integer>() {{
            put("width", (int) dimension.getWidth());
            put("height", (int) dimension.getHeight());
        }});
    }
}
