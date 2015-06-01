package org.valarin.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.NodeFields;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.valarin.runtime.ValRegistry;

@NodeFields(
        {@NodeField(name = "registry", type = ValRegistry.class),
        @NodeField(name = "name", type = String.class)}
)
public abstract class ValGlobalReadNode extends ValCrapNode {

    protected abstract ValRegistry getRegistry();
    protected abstract String getName();

    @Specialization
    public long readLong(VirtualFrame frame, long value) {
        return (long)getRegistry().lookup(getName());
    }
    
    @Specialization
    public boolean readBoolean(VirtualFrame frame, boolean value) {
        return (boolean)getRegistry().lookup(getName());
    }
    
    @Specialization
    public Double readDouble(VirtualFrame frame, double value) {
        return (Double)getRegistry().lookup(getName());
    }

    @Specialization
    public Object readObject(VirtualFrame frame) {
        return (Object)getRegistry().lookup(getName());
    }

    /*@Specialization(contains = {"readLong", "readBoolean", "readObject"})
    public Object read(VirtualFrame frame) {
        return getRegistry().lookup(getName());
    }*/

}
