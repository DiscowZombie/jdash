module jdash.common {

    exports jdash.common;
    exports jdash.common.entity;
    exports jdash.common.internal to jdash.client, jdash.events, jdash.graphics;

    requires org.apache.commons.lang3;
    requires static org.immutables.value;
}