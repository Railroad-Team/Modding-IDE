module stuff {
    requires javafx.controls;
    opens com.turtywurty.railroad to javafx.graphics;
    exports com.turtywurty.railroad;
}
