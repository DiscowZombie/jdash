module jdash.graphics {

    exports jdash.graphics;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.configuration2;
    requires org.apache.commons.lang3;
    requires java.desktop;
    requires transitive jdash.common;

    requires static com.fasterxml.jackson.annotation;
}